import java.util.ArrayList;
import java.util.ArrayDeque;

public class Collision {

    // Runs Physics checks
    public void update(Snake snake, Food food) { // base update function for snake-snake and snake-food cols
        checkDie(snake);
        checkEatFood(snake, food);
    }

    // Check for snake - snake overlap
    public void checkDie(Snake snake) {
        int length = snake.length();
        ArrayDeque<Coord> tempDeque = snake.getPositions(); // clone for new instance
        ArrayList<Coord> tempArray = new ArrayList<Coord>();

        for(int i = 0; i < length; i++) {
            Coord item = tempDeque.pop();

            for(int j = 0; j < tempArray.size(); j++) {
                Coord t2 = tempArray.get(j);
                if(t2.x == item.x && t2.y == item.y) {
                    // dup found
                    System.out.println("Item: " + item);
                    snake.die();
                }
            }
            tempArray.add(item);
        }
    }

    // Check for food - head overlap
    void checkEatFood(Snake snake, Food food) {
        Coord foodPosition = food.position(); // get food position
        if(snake.head().x == foodPosition.x && snake.head().y == foodPosition.y) { // if positions overlap
            food.eat(); // note food eaten
            snake.grow(); // grow snake
        }
    }
}
