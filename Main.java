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

        //window.drawRect(280, 280, 40, 40);
        snake = new Snake(new Coord(10, 10));

        repaint();
    }

    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(20, 20, 40, 40);
    }

    public void gameLoop() {
        snake.move();

        //
        // add logic here
        //

    }

    public void actionPerformed(ActionEvent a) {

    }


    // UI Helpers

    // creates bottom button panel
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
