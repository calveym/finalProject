import java.applet.*;
import java.awt.*;
import java.util.*; // for Random
import java.awt.event.*;


public class Main extends Applet implements KeyListener {

    // Instance references
    SnakeCanvas window;
    Button restart;
    public Snake snake;

    // Setup constants
    int SCREEN_HEIGHT, SCREEN_WIDTH, TILES, TARGET_FPS;
    long OPTIMAL_TIME;

    // Control variables
    boolean gameRunning;
    long fps, lastFpsTime;

    public void init() {
        // prepare constant vars
        Dimension d = getSize();
        SCREEN_HEIGHT = d.height;
        SCREEN_WIDTH = d.width;
        TILES = 15;
        TARGET_FPS = 1;
        OPTIMAL_TIME = 1000000000 / TARGET_FPS;

        // prepare instance vars
        fps = 0;
        lastFpsTime = 0;
        gameRunning = true;

        // set up ui
        setFont(new Font("TimesRoman", Font.BOLD, 14));

        window = new SnakeCanvas(this);
        window.setBackground(Color.orange);
        window.addKeyListener(this);

        setLayout(new BorderLayout());
        add("Center", window);
        add("South", makeBottomPanel());

        snake = new Snake(new Coord(10, 10));

        window.repaint();

        gameLoop();
    }

    // controls game loop
    public void gameLoop() {
        long lastLoopTime = System.nanoTime();
        long now, updateLength;
        double delta;

        while(gameRunning) {
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
                System.out.println("(FPS: "+fps+")");
                lastFpsTime = 0;
                fps = 0;
            }

            // updates game state
            doGameUpdates(delta);

            // repaint new state
            window.repaint();

            try {
                Thread.sleep( (lastLoopTime-System.nanoTime() + OPTIMAL_TIME) / 1000000 );
            } catch (InterruptedException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

    }

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) { }

    public void keyTyped(KeyEvent e) { }

    public void doGameUpdates(double delta) {
        // updatey stuff
       snake.move();
    }


    // UI Helpers

    // creates bottom button panel
    public Panel makeBottomPanel() {
        // center button: remove
        restart = new Button("Restart");
        restart.setBackground(Color.cyan);

        // setup and add to panel
        Panel temp = new Panel();
        temp.setBackground(Color.green);
        temp.setLayout(new GridLayout(1, 1));
        temp.add(restart);

        return temp;
    }


    class SnakeCanvas extends Canvas {
        Main parent;

        public SnakeCanvas(Main s){
            parent = s;
        }
        public void paint(Graphics g) {
            g.setColor(Color.black);

            snake.drawSnake(g, parent);
        }

    }
}
