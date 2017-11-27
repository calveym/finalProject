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
            log("x    " + getCallerClassName() + ": " + errorString);
        else
            log(".");
    }

    public static String getCallerClassName() {
        StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
        for (int i=1; i<stElements.length; i++) {
            StackTraceElement ste = stElements[i];
            if (!ste.getClassName().equals(TestUtils.class.getName()) && ste.getClassName().indexOf("java.lang.Thread")!=0) {
                return ste.getClassName();
            }
        }
        return null;
     }
}
