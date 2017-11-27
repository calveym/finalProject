import java.util.Vector;

public class Snake {

        private Vector<Coord> positions;
        private int direction;

        // constructor

        public Snake(Coord startCoordinate) {
            positions = new Vector<Coord>();
            positions.add(startCoordinate);
        }

        public Coord head() {
            return positions.get(0);
        }

        public Coord tail() {
            return positions.get(positions.capacity() - 1);
        }


        // use one of the following two approaches to turning:

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
        }
}
