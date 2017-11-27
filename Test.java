public class Test {

    static Snake testSnake;


    public static void main(String[] args) {
        // setup env

        setup();

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
        expect(equal(new Coord(10, 10), testSnake.head()), "Snake head not at 10, 10");
    }


    // utilities

    public static void log(String s) {
        System.out.println(s);
    }

    public static boolean equal(int a, int b) {
        return a == b;
    }

    public static boolean notEqual(int a, int b) {
        return a != b;
    }

    public static expect(boolean condition, String errorString) {
        if(!condition)
            log(errorString);
        else
            log(".");
    }

}
