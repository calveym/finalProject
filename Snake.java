import java.util.Vector;

public class Snake {

        private Vector<Coord> positions;


        // constructor

        public Snake() {
            positions = new Vector<Coord>();
            positions.add(new Coord(0, 15));
            System.out.println(Integer.toString(positions.get(0).y));
        }

        private class Coord {
            public int x, y;

            public Coord(int a, int b) {
                x = a;
                y = b;
            }
        }
}
