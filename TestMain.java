public class TestMain extends TestUtils {

    Main main;

    public static void main(String[] args) {

    }

    public static void setup() {
        main = new Main();
        main.init();
    }


    // Tests

    public static void testSetup() {
        setup();

        expect(equal(main.snake.head().x, 10), "Snake not being initialized in Main class");
    }

}
