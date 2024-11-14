package com.persianrug.engine;

import com.persianrug.entity.Player;
import com.persianrug.entity.Platform;
import com.persianrug.utils.Constants;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import java.util.ArrayList;
import java.util.List;

public class GameEngine {
    private Canvas canvas;
    private GraphicsContext gc;
    private Player player;
    private InputManager inputManager;
    private AnimationTimer gameLoop;
    private Camera camera;
    private List<Platform> platforms;
    private Image backgroundImage;
    private GameState gameState;
    private Menu menu;

    public GameEngine(Stage stage) {
        initializeGame(stage);
    }

    private void initializeGame(Stage stage) {
        // Create canvas
        canvas = new Canvas(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Create player
        player = new Player((double) Constants.WINDOW_WIDTH / 2, Constants.WINDOW_HEIGHT - Constants.PLAYER_HEIGHT);

        // Initialize components
        inputManager = new InputManager();
        camera = new Camera();
        menu = new Menu();
        gameState = GameState.MENU;

        // Initialize platforms
        platforms = new ArrayList<>();
        initializePlatforms();

        // Load background image
        try {
            backgroundImage = new Image(getClass().getResource("/images/background.png").toString());
            System.out.println("Background image loaded successfully");
        } catch (Exception e) {
            System.err.println("Background image loading failed: " + e.getMessage());
            e.printStackTrace();
        }

        Pane root = new Pane(canvas);
        Scene scene = new Scene(root);

        // 키 입력 처리 수정
        scene.setOnKeyPressed(e -> {
            inputManager.handleKeyPress(e.getCode());
            handleKeyPress(e.getCode());
            if (e.getCode() == KeyCode.SPACE && gameState == GameState.PLAYING) {
                System.out.println("Space pressed!");
            }
        });
        scene.setOnKeyReleased(e -> inputManager.handleKeyRelease(e.getCode()));

        // Set up stage
        stage.setScene(scene);
        stage.setTitle("Persian Rug");
        stage.setResizable(false);

        // Create game loop
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                render();
            }
        };
    }

    private void handleKeyPress(KeyCode code) {
        switch (gameState) {
            case MENU:
                handleMenuInput(code);
                break;
            case PLAYING:
                if (code == KeyCode.ESCAPE) {
                    gameState = GameState.PAUSED;
                }
                break;
            case PAUSED:
                if (code == KeyCode.ESCAPE) {
                    gameState = GameState.PLAYING;
                } else if (code == KeyCode.M) {
                    gameState = GameState.MENU;
                }
                break;
        }
    }

    private void handleMenuInput(KeyCode code) {
        switch (code) {
            case UP:
                menu.moveUp();
                break;
            case DOWN:
                menu.moveDown();
                break;
            case ENTER:
                if (menu.getSelectedOption() == 0) {
                    gameState = GameState.PLAYING;
                } else if (menu.getSelectedOption() == 2) {
                    javafx.application.Platform.exit();
                }
                break;
        }
    }

    private void initializePlatforms() {
        platforms = new ArrayList<>();
        platforms.add(new Platform(0, Constants.LEVEL_HEIGHT - 20, Constants.LEVEL_WIDTH, 20));
        platforms.add(new Platform(600, 400, 150, 20));
        platforms.add(new Platform(900, 350, 200, 20));
        platforms.add(new Platform(1200, 350, 150, 20));
        platforms.add(new Platform(1500, 450, 200, 20));
        platforms.add(new Platform(1800, 400, 150, 20));
        platforms.add(new Platform(2100, 300, 200, 20));
        platforms.add(new Platform(2400, 450, 150, 20));
        platforms.add(new Platform(2700, 350, 200, 20));
    }

    private void update() {
        switch (gameState) {
            case PLAYING:
                updateGame();
                break;
            case MENU:
            case PAUSED:
                break;
        }
    }

    private void updateGame() {
        // Handle input
        if (inputManager.isKeyPressed(KeyCode.LEFT)) {
            player.moveLeft();
        }
        if (inputManager.isKeyPressed(KeyCode.RIGHT)) {
            player.moveRight();
        }
        if (inputManager.isKeyPressed(KeyCode.UP)) {
            System.out.println("Attempting to jump");
            player.jump();
        }

        player.update();
        camera.update(player);
        checkCollisions();
    }

    private void checkCollisions() {
        player.setOnGround(false);

        if (player.getY() + player.getHeight() >= Constants.LEVEL_HEIGHT - 20) {
            player.setOnGround(true);
        }

        for (Platform platform : platforms) {
            if (player.intersects(platform)) {
                player.handlePlatformCollision(platform);
            }
        }
    }

    private void render() {
        gc.clearRect(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

        switch (gameState) {
            case MENU:
                menu.render(gc);
                break;
            case PLAYING:
                renderGame();
                break;
            case PAUSED:
                renderGame();
                renderPauseScreen();
                break;
        }
    }

    private void renderGame() {
        // Render background
        if (backgroundImage != null) {
            double bgX = -camera.getX() * 0.5;
            double currentX = bgX % backgroundImage.getWidth();
            if (currentX > 0) currentX -= backgroundImage.getWidth();

            while (currentX < Constants.WINDOW_WIDTH) {
                gc.drawImage(backgroundImage, currentX, 0,
                        backgroundImage.getWidth(), Constants.WINDOW_HEIGHT);
                currentX += backgroundImage.getWidth();
            }
        }

        gc.save();
        gc.translate(-camera.getX(), -camera.getY());

        for (Platform platform : platforms) {
            platform.render(gc);
        }

        player.render(gc);

        gc.restore();
    }

    private void renderPauseScreen() {
        gc.setFill(new Color(0, 0, 0, 0.5));
        gc.fillRect(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

        gc.setFill(Color.WHITE);
        gc.setFont(new Font("Arial", 36));
        gc.setTextAlign(TextAlignment.CENTER);
        gc.fillText("PAUSED", Constants.WINDOW_WIDTH / 2, Constants.WINDOW_HEIGHT / 2);
        gc.setFont(new Font("Arial", 18));
        gc.fillText("Press ESC to resume", Constants.WINDOW_WIDTH / 2, Constants.WINDOW_HEIGHT / 2 + 40);
        gc.fillText("Press M for menu", Constants.WINDOW_WIDTH / 2, Constants.WINDOW_HEIGHT / 2 + 70);
    }

    public void start() {
        gameLoop.start();
    }

    public void stop() {
        gameLoop.stop();
    }
}