import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*; // for Random
import javax.swing.Timer;

public class Main extends Applet implements ActionListener, KeyListener {

    // Instance references
    InputManager input;
    public SnakeCanvas window;
    Button restart;
    public Snake snake;
    Collision collision;
    Food food;

    // Setup constants
    final int TILES = 15;
    final int TARGET_FPS = 5;
    final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;

    // Flow control variables
    boolean gameRunning;
    long fps, lastFpsTime;
    boolean[] keys = new boolean[65536];

    public void init() {
        // Create game instances
        Timer timer = new Timer(100, this);
        timer.setInitialDelay(1900);
        timer.start();

        snake = new Snake(new Coord(10, 10));
        food = new Food(TILES);
        collision = new Collision();
        input = new InputManager();

        // Prepare UI
        setFont(new Font("TimesRoman", Font.BOLD, 14));
        setLayout(new BorderLayout());

        window = new SnakeCanvas(this, food, snake);
        window.setBackground(Color.orange);
        window.setFocusable(true);
        window.requestFocus();
        window.addKeyListener(input); //tells canvas to listen for key presses
        //add("South", makeBottomPanel());
        add("Center", window);

        // prepare instance vars
        fps = 0;
        lastFpsTime = 0;
        gameRunning = true;

        window.repaint();

        //gameLoop();
    }

    // controls game loop
    public void gameLoop() {

        long lastLoopTime = System.nanoTime();
        long now, updateLength;
        double delta;

        //while(gameRunning) {
            // Calculate time values and delta
            now = System.nanoTime();
            updateLength = now - lastLoopTime;
            lastLoopTime = now;
            delta = updateLength / ((double)OPTIMAL_TIME);

            // updates the frame counter
            fps++;
            lastFpsTime += updateLength;

            // Update fps counter if second elapsed
            if(lastFpsTime >= 1000000000) {
                //System.out.println("(FPS: "+fps+")");
                lastFpsTime = 0;
                fps = 0;
            }

            // get recent inputs
            getInput();

            // updates game state
            doGameUpdates(delta);

            // repaint new state
            window.paint(getGraphics());
        //
        //     try {
        //         Thread.sleep(1000);
        //     } catch (InterruptedException e) {
        //         System.out.println("Error: " + e.getMessage());
        //     }
        // }

    }

    void doGameUpdates(double delta) {
        // updatey stuff

        snake.move(TILES);
        food.checkExists();
        collision.update(snake, food);
    }

    public void actionPerformed(ActionEvent e) { // handles keypresses
        gameLoop();
        window.repaint();
        System.out.println("LAJFLK");
    }

    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    public void keyTyped(KeyEvent e) { }
    public void keyReleased(KeyEvent e) { }

    void getInput() {
        boolean[] tempKeys = input.getInput();

        //System.out.println("Getting input");
        if(tempKeys[KeyEvent.VK_UP]) {
            snake.up();
        } else if(tempKeys[KeyEvent.VK_DOWN]) {
            snake.down();
        } else if(tempKeys[KeyEvent.VK_LEFT]) {
            snake.left();
        } else if(tempKeys[KeyEvent.VK_RIGHT]) {
            snake.right();
        }
    }

    // UI Helpers

    // creates bottom button panel
    public Panel makeBottomPanel() {
        // center button: remove
        restart = new Button("Restart");
        restart.setBackground(Color.cyan);

        // setup and add to panel
        Panel temp = new Panel();
        temp.setBackground(Color.orange);
        temp.setLayout(new GridLayout(1, 1));
        temp.add(restart);

        return temp;
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

    public SnakeCanvas(Main m, Food f, Snake s){
        parent = m;
        size = new Dimension(600, 600);
        food = f;
        snake = s;
    }

    public void paint(Graphics g) {
        update(g);
    }

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
    // Keypress handling

    boolean[] keys = new boolean[65536];

    public boolean[] getInput() {
        boolean[] temp = keys;
        resetKeys();
        return temp;
    }

    void resetKeys() {
        keys = new boolean[65536];
    }

    public void keyPressed(KeyEvent e) { // handles keypresses
        int keyCode = e.getKeyCode(); // gets keycode
        keys[keyCode] = true;
    }

    public void keyTyped(KeyEvent e) { }
    public void keyReleased(KeyEvent e) { }
}
