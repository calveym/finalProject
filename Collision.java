public class Collision {

    public void update(Snake snake, Food food) { // base update function for snake-snake and snake-food cols
        checkDie(snake);
        checkEatFood(snake.head(), food);
    }

    public void checkDie(Snake snake) {
        // iterate through positions array and look for dups
    }

    void checkEatFood(Coord head, Food food) {
        Coord foodPosition = food.position();
        if(head.x == foodPosition.x && head.y == foodPosition.y) {
            food.eat();
        }
    }

}
