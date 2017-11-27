public class TestUtils {

    public static void log(String s) {
        System.out.println(s);
    }

    public static boolean equal(int a, int b) {
        return a == b;
    }

    public static boolean equal(Coord a, Coord b) {
        return (a.x == b.x && a.y == b.y);
    }

    public static boolean equal(boolean a, boolean b) {
        return a == b;
    }

    public static boolean notEqual(int a, int b) {
        return a != b;
    }

    public static void expect(boolean condition, String errorString) {
        if(!condition)
            log("x    " + errorString);
        else
            log(".");
    }

}
