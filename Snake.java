import java.util.Vector;
import java.applet.*;
import java.awt.*;

public class Snake {

        private Vector<Coord> positions;  // this vector stores all of the coordinates
                                          // that currently contain a snake part
        private int dir;                  // 0- up, 1- right, 2- down, 3- left

        // Constructor

        public Snake(Coord startCoordinate) {
            dir = 0;
            positions = new Vector<Coord>();
            //positions.add(below(below(startCoordinate)));
            positions.add(startCoordinate);
            positions.add(below(startCoordinate));

        }


        // Accessors

        public Coord head() {
            return positions.firstElement();  // returns first item in positions vector
        }

        public Coord tail() {
            return positions.lastElement();  // returns last item in positions vector
        }

        public int direction() {
            return dir;
        }


        // Movement

        public void move(int tiles) {
            Coord newPos = head();
            System.out.println("0: " + positions.get(0));
            System.out.println("1: " + positions.get(1));

            // calculate which coordinate changes
            if(dir == 0) {
                newPos.y = head().y -1;
            } else if(dir == 1) {
                newPos.x = head().x +1;
            } else if(dir == 2) {
                newPos.y = head().y +1;
            } else if(dir == 3) {
                newPos.x = head().x -1;
            }

            newPos = checkBound(tiles, newPos); //check if out of bounds and update coords

            System.out.println(newPos);

            positions.add(0, newPos);  // add new head coordinate
            positions.remove(tail());  // remove tail coordinate

            System.out.println("0: " + positions.get(0));
            System.out.println("1: " + positions.get(1));
        }

        public Coord checkBound(int tiles, Coord pos){
            if(pos.y<0){
                System.out.println("went over top");
                return new Coord(pos.x, tiles);
            }else if(pos.x<0){
                return new Coord(tiles, pos.y);
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
            for(int i = 0; i < positions.size(); i++) {
                Coord pos = positions.get(i);
                int tile = m.window.size.width / m.TILES;

                int x      = pos.x * tile;
                int y      = pos.y * tile;
                int width  = tile;
                int height = tile;

                System.out.println("X: " + x);
                System.out.println("y: " + y);

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
}
