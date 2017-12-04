public class Collision {

    public void update(Snake snake, Food food) { // base update function for snake-snake and snake-food cols
        checkDie(snake);
        checkEatFood(snake, food);
    }

    public void checkDie(Snake snake) {
        // TODO
    }

    // Food consumption checker
    void checkEatFood(Snake snake, Food food) {
        Coord foodPosition = food.position(); // get food position
        if(snake.head().x == foodPosition.x && snake.head().y == foodPosition.y) { // if positions overlap
            food.eat(); // note food eaten
            snake.grow(); // grow snake
        }
    }

}
