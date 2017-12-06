import java.util.Vector;
import java.applet.*;
import java.awt.*;
import java.util.*; // for Random

public class Food {

    // Instance vars

    Coord pos;
    boolean exists;
    int numEaten;
    int nTiles;
    Random rand;

    // Constructor

    public Food(int tiles) {
        nTiles = tiles;
        rand = new Random();
        pos = new Coord(0, 0);
        generateFood();
    }

    
    // public void reset() {
    //     numEaten = 0;
    //     generateFood();
    // }

    // position accessor
    public Coord position() {
        return pos;
    }


    // Logic

    // generate new food if eaten
    public void checkExists() {
        if(!exists)
            generateFood();
    }

    // generate new food
    void generateFood() {
        int x = rand.nextInt(nTiles);
        int y = rand.nextInt(nTiles);

        pos.x = x;
        pos.y = y;
        exists = true;
    
    }

    // Consume food
    public void eat() {
        exists = false;
        numEaten++;
    }


    // Drawing
    public void drawFood(Graphics g, Main m) {
        int tile = m.window.size.width / m.TILES;

        int x = pos.x * tile;
        int y = pos.y * tile;

        g.setColor(Color.red);
        g.fillRect(x, y, tile, tile);
    }
}
