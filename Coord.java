public class Coord {
    public int x, y;

    public Coord(int a, int b) {
        x = a;
        y = b;
    }

    public String toString() {
        return "Coordinate: " + Integer.toString(x) + ", " + Integer.toString(y);
    }
}
