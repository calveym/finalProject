import java.applet.*;
import java.awt.*;
import java.util.*; // for Random
import java.awt.event.*;


public class Main extends Applet implements ActionListener {

    Canvas window;
    Button restart;
    public Snake snake;

    public void init() {
        setFont(new Font("TimesRoman", Font.BOLD, 14));

        window = new Canvas();
        setLayout(new BorderLayout());
        add("Center", window);
        add("South", makeBottomPanel());

        snake = new Snake(new Coord(10, 10));
    }

    public void gameLoop() {
        snake.move();

        window.repaint();
    }


    // UI Helpers

    public Panel makeBottomPanel() {
        // center button: remove
        restart = new Button("Restart");
        restart.setBackground(Color.cyan);
        restart.addActionListener(this);

        // setup and add to panel
        Panel temp = new Panel();
        temp.setBackground(Color.green);
        temp.setLayout(new GridLayout(1, 1));
        temp.add(restart);

        return temp;
    }

}
