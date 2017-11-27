public class TestSnake extends TestUtils {

    static Snake testSnake;


    public static void main(String[] args) {

        // call testing functions here
        // ie

        testHead();
    }


    // reset game env

    public static void setup() {
        testSnake = new Snake(new Coord(10, 10));
    }


    // tests

    public static void testHead() {
        setup();

        // tests that snake initialises with a positions vector by returning head
        expect(equal((new Coord(10, 10)), testSnake.head()), "Snake head not at 10, 10");
    }

    public static void testMove() {
        setup();

        testSnake.move();
        testSnake.move();
        testSnake.move();
    }

}
