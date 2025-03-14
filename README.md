# Persian Rug(COMP2522 Term Project)

## 1. Project Description
**Persian Rug** is an engaging adventure game where the player's character ascends by jumping onto Persian rugs that serve as platforms. As you explore this vibrant world, you'll encounter Persian symbols. Interacting with these symbols presents you with Java OOP-related quizzes that challenge your knowledge and skills. Progress through the game by solving these quizzes, showcasing your expertise while navigating an immersive and dynamic environment.

---

## 2. Names of Contributors
- **Homayoun Khoshi**
- **Juhyun Park**
---

## 3. Technologies and Resources Used
The project is built using the following technologies and resources:

- **Programming Language:** Java
- **Framework:** JavaFX for GUI and game mechanics
- **Game Logic and State Management:** Custom-built game engine
- **Persistence:** Java serialization for saving and loading game state
- **Testing Framework:** JUnit 5 for comprehensive unit testing
- **Graphics:** Custom sprite assets and game visuals
- **IDE:** IntelliJ IDEA for development
- **Version Control:** GitHub for collaboration and versioning
- **External Resources:**
   - Graphics and sprites from [OpenGameArt](https://opengameart.org/)
   - Fonts from [Google Fonts](https://fonts.google.com/)

---

## 4. Usage

### Start the Game

Open the game through your IDE or terminal after running the application.

### Game Controls

- **Move Left/Right**: Use the Left and Right arrow keys to navigate your character.
- **Jump**: Press the Space bar to jump onto the Persian rug platforms.
- **Interact with Persian Symbols**: Stand on a Persian symbol and press Enter to start Java OOP-related quizzes.
- **Pause/Resume Game**: Press the Escape key to open the pause menu.
- **Save/Load Progress**: Use the menu options to save or load your progress in the game.

### Main Menu

- **New Game**: Start a new adventure from the beginning.
- **Load Game**: Load your saved progress and continue where you left off.
- **Exit**: Close the game.

### Gameplay

- **Objective**: Navigate the levels by jumping on Persian rugs as platforms.
- **Interaction**: Solve Java OOP-related quizzes to unlock new levels and earn points.
- **Challenge**: Avoid falling off platforms and improve your coding skills through the quizzes.

### Pause Menu

- **Resume Game**: Continue playing from where you paused.
- **Save Game**: Save your current progress.
- **Main Menu**: Return to the main menu.

### Additional Notes

- **Graphics and Animations**: Enjoy the vibrant visuals and engaging animations during gameplay.
- **User Progress**: Your progress will be saved, allowing you to resume from your last checkpoint.
- **Optimized Experience**: The game is optimized for smooth performance on desktops.

---

## 5. Known Bugs and Limitations

- **Collision Sensitivity:** Occasionally, the character may slightly overlap with platforms, leading to minor visual inconsistencies.
- **Quiz Timings:** Some quizzes may take longer than expected to load, particularly on lower-performance machines.

---

## 6. Features for the Future

- **Additional Levels:** Introducing new levels with diverse challenges to enhance gameplay variety and difficulty.
- **Enhanced Graphics:** Improved animations, environmental details, and character designs for a more immersive experience.
- **Dynamic Quizzes:** Implement adaptive quizzes that adjust difficulty based on player performance to maintain engagement.
- **Mobile Compatibility:** Extend the game to mobile platforms, ensuring seamless gameplay across devices.

---

## 7. Contents of the Project Folder

### Top-Level Files
- `README.md`  
  Detailed documentation about the project, including setup instructions, features, and usage.
- `pom.xml`  
  Maven configuration file for managing project dependencies and build lifecycle.

---

### Project Structure

#### `src/main/java`
Contains the main application code.

- **`com/persianrug`**  
  The main package of the game.
   - `Main.java`  
     Entry point for the game application.

- **`com/persianrug/engine`**  
  Game engine components for managing game state, rendering, input, and logic.
   - `Camera.java`  
     Handles camera movement and player tracking.
   - `GameEngine.java`  
     Core game engine that manages game flow and rendering.
   - `GameSaveManager.java`  
     Manages saving and loading game progress.
   - `InputManager.java`  
     Handles user keyboard input.
   - `Menu.java`  
     Manages and renders game menus (main menu and pause menu).

- **`com/persianrug/entity`**  
  Game objects and entities.
   - `GameObject.java`  
     Abstract base class for all game objects.
   - `Item.java`  
     Represents collectible items in the game.
   - `Platform.java`  
     Represents platforms the player can jump on.
   - `Player.java`  
     Represents the player character and its behavior.
   - `Quiz.java`  
     Handles quizzes associated with collectible items.

- **`com/persianrug/utils`**  
  Utility classes and constants for shared functionality.
   - `Constants.java`  
     Contains global constants such as screen dimensions, gravity, and player attributes.

---

#### `src/main/resources`
Contains the game's resource files.

- **`images/`**  
  Game graphics and sprites.
   - `character_left.png`  
     Image for the player character facing left.
   - `character_right.png`  
     Image for the player character facing right.
   - `platform.png`  
     Image for floating platforms.
   - `symbol.png`  
     Image for interact with the user for quiz.
   - `background.png`
     Image for background.

---

#### `src/test/java`
Contains unit tests for the application.

- **`com/persianrug/engine`**  
  Test files for engine components.
   - `CameraTest.java`  
     Tests for the camera's functionality and boundary conditions.
   - `GameEngineTest.java`  
     Tests for the overall game engine behavior.
   - `GameSaveManagerTest.java`  
     Tests for saving and loading game progress.
   - `InputManagerTest.java`  
     Tests for handling user input.
   - `MenuTest.java`  
     Tests for menu navigation and rendering.

- **`com/persianrug/entity`**  
  Test files for game entities.
   - `GameObjectTest.java`  
     Tests for the base class of all game objects.
   - `ItemTest.java`  
     Tests for collectible items.
   - `PlatformTest.java`  
     Tests for platform behavior.
   - `PlayerTest.java`  
     Tests for player movement, jumping, and collisions.
   - `QuizTest.java`  
     Tests for quiz logic and answer validation.

---

## 8. Credits

We would like to acknowledge the following resources and tools that helped bring this project to life:

- **Graphics and Assets:**  
  Graphics and sprites used in the game were sourced from [OpenGameArt](https://opengameart.org/).

- **Fonts:**  
  Fonts for in-game text and UI were obtained from [Google Fonts](https://fonts.google.com/).

- **Testing Support:**  
  Unit testing and validation were conducted using [JUnit 5](https://junit.org/junit5/).
