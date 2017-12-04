import java.util.ArrayDeque;
import java.applet.*;
import java.awt.*;

public class Snake {

    private ArrayDeque<Coord> positions;  // this vector stores all of the coordinates
                                      // that currently contain a snake part
    private int dir;                  // 0- up, 1- right, 2- down, 3- left
    private boolean grow;

    // Constructor

    public Snake(Coord startCoordinate) {
        dir = 0;
        positions = new ArrayDeque<Coord>();

        positions.addFirst(startCoordinate);
        positions.addLast(below(startCoordinate));
        positions.addLast(below(below(startCoordinate)));
    }


    // Accessors

    public int direction() {
        return dir;
    }

    public Coord head() {
        return new Coord(positions.peekFirst().x, positions.peekFirst().y);
    }


    // Movement

    // controls move logic
    public void move(int tiles) {
        Coord newPos = getPosition(head());
        newPos = checkBound(tiles, newPos); //check if out of bounds and update coords
        positions.addFirst(newPos);  // add new head coordinate

        if(!grow)
            positions.removeLast();  // remove tail coordinate if not growing
        else
            grow = false;
    }

    // stop next tail removal once, called in Collision
    public void grow() {
        grow = true;
    }

    // modify coordinate that needs changing based on direction
    Coord getPosition(Coord head) {
        if(dir == 0) {
            head.y = head().y -1;
        } else if(dir == 1) {
            head.x = head().x +1;
        } else if(dir == 2) {
            head.y = head().y +1;
        } else if(dir == 3) {
            head.x = head().x -1;
        }
        return head;
    }

    // check if overlap
    Coord checkBound(int tiles, Coord pos){
        if(pos.y<0){
            return new Coord(pos.x, tiles-1);
        }else if(pos.x<0){
            return new Coord(tiles-1, pos.y);
        }else if(pos.y>=tiles){
            return new Coord(pos.x, 0);
        }else if(pos.x>=tiles){
            return new Coord(0, pos.y);
        }else{
            return pos;
        }
    }


    // Drawing

    public void drawSnake(Graphics g2, Main m) {
        g2.setColor(Color.black); // reset color
        ArrayDeque<Coord> temp = positions.clone(); // get new deque to protect original

        for(int i = 0; i < positions.size(); i++) {
            drawCoord(temp.pop(), g2, m); // draw coordinate
        }
    }

    // draw helper
    void drawCoord(Coord toDraw, Graphics g2, Main m) {
        int tile   = m.window.size.width / m.TILES;
        int x      = toDraw.x * tile;
        int y      = toDraw.y * tile;
        int width  = tile;
        int height = tile;

        g2.fillRect(x, y, width, height);
    }


    // Directions

    public void left() {
        dir = 3;
    }

    public void right() {
        dir = 1;
    }

    public void up() {
        dir = 0;
    }

    public void down() {
        dir = 2;
    }


    // Helpers

    // ensures rotations wrap around
    public void normalize() {
        if(dir > 3)
            dir = 0;
        if(dir < 0)
            dir = 3;
    }

    // returns coordinate below input
    public Coord below(Coord input) {
        return new Coord(input.x, input.y+1);
    }

    public void printPositions() {
        ArrayDeque<Coord> temp = positions.clone();
        for(int i = 0; i < positions.size(); i++) {
            System.out.println(Integer.toString(i) + ": " + temp.pop());
        }
    }
}
