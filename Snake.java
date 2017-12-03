import java.util.ArrayDeque;
import java.applet.*;
import java.awt.*;

public class Snake {

    private ArrayDeque<Coord> positions;  // this vector stores all of the coordinates
                                      // that currently contain a snake part
    private int dir;                  // 0- up, 1- right, 2- down, 3- left

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
        //System.out.println(positions.size());
        return new Coord(positions.peekFirst().x, positions.peekFirst().y);
    }


    // Movement

    public void move(int tiles) {
        Coord newPos = head();

        // calculate which coordinate changes
        if(dir == 0) {
            newPos.y = head().y -1;
        } else if(dir == 1) {
            newPos.x = positions.peek().x +1;
            newPos.y = head().y;
        } else if(dir == 2) {
            newPos.y = positions.peek().y +1;
            newPos.x = head().x;
        } else if(dir == 3) {
            newPos.x = positions.peek().x -1;
            newPos.y = head().y;
        }

        newPos = checkBound(tiles, newPos); //check if out of bounds and update coords

        positions.addFirst(newPos);  // add new head coordinate

        positions.removeLast();  // remove tail coordinate
    }

    public Coord checkBound(int tiles, Coord pos){
        if(pos.y<0){
            System.out.println("went over top");
            return new Coord(pos.x, tiles-1);
        }else if(pos.x<0){
            return new Coord(tiles-1, pos.y);
        }else if(pos.y>tiles){
            return new Coord(pos.x, 0);
        }else if(pos.x>tiles){
            return new Coord(0, pos.y);
        }else{
            return pos;
        }
    }

    public void drawSnake(Graphics g2, Main m) {
        g2.setColor(Color.black);

        ArrayDeque<Coord> temp = positions.clone();

        for(int i = 0; i < positions.size(); i++) {
            Coord pos = temp.pop();
            int tile = m.window.size.width / m.TILES;

            int x      = pos.x * tile;
            int y      = pos.y * tile;
            int width  = tile;
            int height = tile;

            g2.fillRect(x, y, width, height);
        }
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
