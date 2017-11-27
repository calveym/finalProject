import java.util.Vector;

public class Snake {

        private Vector<Coord> positions;
        private int direction;

        // constructor

        public Snake(Coord startCoordinate) {
            direction = 0;
            positions = new Vector<Coord>();
            positions.add(startCoordinate);
        }


        // Accessors

        public Coord head() {
            return positions.get(0);  // returns first item in positions vector
        }

        public Coord tail() {
            return positions.get(positions.capacity() - 1);  // returns last item in positions vector
        }


        // Movement

        public void move() {
            Coord newPos = head();

            // calculate which coordinate changes
            if(direction == 0) {
                newPos.y = y--;
            } else if(direction == 1) {
                newPos.x = x++;
            } else if(direction == 2) {
                newPos.y = y++;
            } else if(direction == 3) {
                newPos.x = x--;
            }

            positions.remove(positions.capacity() - 1);  // remove tail
            positions.add(newPos);  // add new head position
        }


        // Directions

        public void turn(int dir) {
            direction += dir;
            normalize();
        }

        public void left() {
            direction = 3;
        }

        public void right() {
            direction = 1;
        }

        public void up() {
            direction = 0;
        }

        public void down() {
            direction = 2;
        }


        // helpers

        public void normalize() {
            if(direction > 3)
                direction = 0;
            if(direction < 0)
                direction = 3;
        }
}
