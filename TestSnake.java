public class TestSnake extends TestUtils {

    static Snake testSnake;


    public static void main(String[] args) {

        // call testing functions here
        // ie

        testHead();
        testMove();
        testTurn();
    }


    // reset snake env

    public static void setup() {
        testSnake = new Snake(new Coord(10, 10));
    }


    // Tests

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

        expect(equal((new Coord(10, 7)), testSnake.head()), "Snake not moving");
    }

    public static void testTurn() {
        setup();

        testSnake.right();
        expect(equal(1, testSnake.direction()), "Snake not turning right");

        testSnake.down();
        expect(equal(2, testSnake.direction()), "Snake not turning down");

        testSnake.left();
        expect(equal(3, testSnake.direction()), "Snake not turning left");
    }
}
