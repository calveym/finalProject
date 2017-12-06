## SNAKE!
This project aims to recreate the famous 1976 game Blockade- more commonly known as Snake. The concept of the game is to have a player-controllable snake move about the screen, with the goal of consuming food to grow the snake. The player must plan their moves ahead to avoid colliding with their tail, which ends the game and displays the player’s score.

### Functionality:
The player controls the snake with the up, down, left, and right arrow keys or the w, a, s, and d keys. Each of these coincides with a new direction for the snake. The snake moves by projecting a new square in front of the current head and removing the tail square. The food will generate randomly around the map, and collision detection will run continuously to check for snake-snake and snake-food collisions. We will create our own game loop that continuously executes all of this functionality, and will enable us to fine tune the behaviour and characteristics of the snake.
### Implementation:	
Our implementation of the game will consist of four primary classes. A Snake class that will create a vector of coordinates to represent the snake on the board and ensure proper movement. The Food class which represents the food objects on the board and randomly generates new food when one has been consumed by the snake. A Collision Detector class to check for the snake reaching the edge of the screen, colliding with itself, or reaching the food. Finally, the main class which will create the applet, and drive the movement of the snake. 

### User Interface:
The user interface should be as simple as possible to simplify the experience for user, and increase immersion. Our minimum viable product will have one interactable button at the bottom of the screen to restart the game, as well as displaying text above the button to display the user’s score. Once our MVP is complete, we will consider adding features such as a leaderboard, pause button, dialog boxes, fancier graphics and more.
Domain Model:

### Testing:
In order to speed up the development process and allow us to make more rapid and frequent iterations, we will set up a simple, custom testing framework to handle running tests and error reporting more robustly than using a main function and logging. This will enable us to set up specific environment conditions to test various edge cases and functionalities as we add them.
blockade 
