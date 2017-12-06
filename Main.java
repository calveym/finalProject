import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*; // for Random
import javax.swing.Timer;

public class Main extends Applet implements ActionListener {

    // Instance references
    InputManager input;
    public SnakeCanvas window;
    Button restart;
    public Snake snake;
    Collision collision;
    Food food;
    Label score;

    // Setup constants
    final int TILES = 15;

    // Flow control variables
    boolean running = true;
    Timer timer;

    // Start point
    public void init() {
        snake = new Snake(new Coord(10, 10));
        food = new Food(TILES);
        collision = new Collision();
        input = new InputManager();

        scheduleTimer();

        setupUI();

        window.repaint();
        gameLoop();
       
    }


    // Game Logic

    // Handles game tick
    public void gameLoop() {
        //System.out.println("gameLoop");
        if(running) {
           // System.out.println("running");
            // get recent inputs
            getInput();
            
            // updates game state
            doGameUpdates();

            // repaint new state
            window.repaint(); 
        }
    }

    // updates state
    void doGameUpdates() {
        running = !snake.isDead();

        snake.move(TILES); // moves snake along 1
        food.checkExists(); // check food state

        // run physics
        collision.update(snake, food);
        score.setText("score: " + food.numEaten);
        if(snake.isDead()){
             score.setText("Game Over. Final Score: " + food.numEaten);
             timer.stop();
        }
    }

    // Gameloop scheduler, run by timer
    public void actionPerformed(ActionEvent e) {
        if(running){
            gameLoop();
        }
        
        if (e.getSource() instanceof Button) {
            String label = ((Button)e.getSource()).getLabel();
            if(label.equals("Restart")){
                //running=false;
                restart();
            }
        }  
    }

    public void restart(){
    
         // Create game instances
        snake = new Snake(new Coord(10, 10));
        food.generateFood();
        
        running = true;

        System.out.println(timer.isRunning());
        timer.start();

        System.out.println(timer.isRunning());
        // Prepare UI
        window.newSnake(snake, food);
        // Start game
        //scheduleTimer();
        
        window.repaint();
        //gameLoop();
        //running = true;
        System.out.println("Restarted");
    }

    void scheduleTimer() {
        timer = new Timer(100, this);
        timer.setInitialDelay(1000);
        timer.start();
    }

    // Retrieve input from InputManager
    void getInput() {
        boolean[] tempKeys = input.getInput(); // get pressed keys from

        // update snake direction based on key input
        if(tempKeys[KeyEvent.VK_UP]) {
            snake.up();
        } if(tempKeys[KeyEvent.VK_DOWN]) {
            snake.down();
        } if(tempKeys[KeyEvent.VK_LEFT]) {
            snake.left();
        } if(tempKeys[KeyEvent.VK_RIGHT]) {
            snake.right();
        }
    }


    // UI Helpers

    // creates bottom button panel- not in use
    public Panel makeBottomPanel() {
        // center button: remove
        restart = new Button("Restart");
        restart.setBackground(Color.black);
        restart.addActionListener(this);

        // setup and add to panel
        score = new Label("Score: 0");
        score.setAlignment(Label.CENTER);
        score.setBackground(Color.white);
        Panel temp = new Panel();
        temp.setBackground(Color.orange);
        temp.setLayout(new GridLayout(1, 2));
        temp.add(restart);
        temp.add(score);

        return temp;
    }

    // UI setup
    void setupUI() {
        setFont(new Font("TimesRoman", Font.BOLD, 14));
        setLayout(new BorderLayout());
        window = new SnakeCanvas(this, food, snake);
        window.setBackground(Color.orange);
        window.setFocusable(true);
        window.requestFocus();        // ensures focus is on window
        window.addKeyListener(input); //tells canvas to listen for key presses
        add("Center", window);
        add("South", makeBottomPanel());
    }
}

class SnakeCanvas extends Canvas {

    // References for drawing
    Snake snake;
    Food food;
    Dimension size;
    Main parent;
    Image offscreen;
    Dimension offscreensize;
    Graphics g2;

    // Constructor
    public SnakeCanvas(Main m, Food f, Snake s){
        parent = m;
        size = new Dimension(600, 600);
        food = f;
        snake = s;
    }

    public void newSnake(Snake s, Food f) {
        food = f;
        snake = s;
    }

    // Redraw canvas handling
    public void paint(Graphics g) {
        update(g);
    }

    // Redraw logic
    public void update(Graphics g) {
        // initially (or when size changes) create new image
        if ((offscreen == null)
            || (size.width != offscreensize.width)
            || (size.height != offscreensize.height)) {
            offscreen = createImage(size.width, size.height);
            offscreensize = size;
            g2 = offscreen.getGraphics();
            g2.setFont(getFont());
        }

        // erase old contents:
        g2.setColor(getBackground());
        g2.fillRect(0, 0, size.width, size.height);

        // now, draw as usual, but use g2 instead of g
        //System.out.println("Main: " + parent.window.size.width);
        snake.drawSnake(g2, parent);
        food.drawFood(g2, parent);

        // finally, draw the image on top of the old one
        g.drawImage(offscreen, 0, 0, null);
    }
}

class InputManager  implements KeyListener {

    // Key array
    boolean[] keys = new boolean[65536];

    // returns key array
    public boolean[] getInput() {
        boolean[] temp = keys;
        resetKeys();
        return temp;
    }

    // clears key array
    void resetKeys() {
        keys = new boolean[65536];
    }

    // called on keyPressed event run
    public void keyPressed(KeyEvent e) { // handles keypresses
        int keyCode = e.getKeyCode(); // gets keycode
        keys[keyCode] = true;
    }

    // unused
    public void keyTyped(KeyEvent e) { }
    public void keyReleased(KeyEvent e) { }
}
