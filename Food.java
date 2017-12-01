import java.util.Vector;
import java.applet.*;
import java.awt.*;
import java.util.*; // for Random

public class Food {

    Coord pos;
    boolean exists;
    int numEaten;
    int nTiles;
    Random rand;

    public Food(int tiles) {
        nTiles = tiles;
        rand = new Random();
        pos = new Coord(0, 0);
        generateFood();
    }

    public Coord position() {
        return pos;
    }

    public void checkExists() {
        if(!exists)
            generateFood();
    }

    void generateFood() {
        int x = rand.nextInt(nTiles);
        int y = rand.nextInt(nTiles);

        pos.x = x;
        pos.y = y;
        exists = true;
    }

    public void drawFood(Graphics g, Main m) {
        int tile = m.SCREEN_WIDTH / m.TILES;

        int x = pos.x * tile;
        int y = pos.y * tile;

        g.setColor(Color.red);
        g.fillRect(x, y, tile, tile);
    }

    public void eat() {
        exists = false;
        numEaten++;
    }
}
