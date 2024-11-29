package com.persianrug.entity;

import com.persianrug.utils.Constants;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.net.URL;

public class Player extends GameObject {
    private double velocityX = 0;
    private double velocityY = 0;
    private boolean onGround = false;
    private boolean canDoubleJump = false;
    private Image characterLeftImage;
    private Image characterRightImage;
    private boolean isFacingRight = true;
    private boolean isDoubleJumping = false;

    private double waveOffset = 0;

    public Player(double x, double y) {
        super(x, y, Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT);
        loadCharacterImages();
    }

    private void loadCharacterImages() {
        try {
            URL leftImageUrl = getClass().getResource("/images/character_left.png");
            URL rightImageUrl = getClass().getResource("/images/character_right.png");

            if (leftImageUrl == null) {
                throw new RuntimeException("Resource not found: /images/character_left.png");
            }
            if (rightImageUrl == null) {
                throw new RuntimeException("Resource not found: /images/character_right.png");
            }

            characterLeftImage = new Image(leftImageUrl.toString());
            characterRightImage = new Image(rightImageUrl.toString());

            System.out.println("Character images loaded successfully");
        } catch (Exception e) {
            System.err.println("Character image loading failed: " + e.getMessage());
        }
    }


    @Override
    public void update() {
        updatePreviousPosition();

        // Update gravity and velocity
        velocityY += Constants.GRAVITY;
        velocityX *= Constants.FRICTION;

        // Update the x and y position for normal gravity and movement
        x += velocityX;
        y += velocityY;

        // Apply wave offset for continuous wave animation
        double waveSpeed = 0.1;
        waveOffset += waveSpeed;

        if (velocityX > 0) {
            isFacingRight = true;
        } else if (velocityX < 0) {
            isFacingRight = false;
        }

        // Boundary check
        if (x < 0) {
            x = 0;
            velocityX = 0;
        }
        if (x > Constants.LEVEL_WIDTH - width) {
            x = Constants.LEVEL_WIDTH - width;
            velocityX = 0;
        }

        // Handle landing on the ground
        if (y > Constants.LEVEL_HEIGHT - height) {
            y = Constants.LEVEL_HEIGHT - height;
            velocityY = 0;
            onGround = true;
            canDoubleJump = false;
            isDoubleJumping = false;
        }

        System.out.println("onGround: " + onGround + ", canDoubleJump: " + canDoubleJump + ", isDoubleJumping: " + isDoubleJumping);
    }

    @Override
    public void render(GraphicsContext gc) {
        Image currentImage = isFacingRight ? characterRightImage : characterLeftImage;

        if (currentImage != null) {
            // Wave parameters for image
            double waveAmplitude = 5;
            double wave = Math.sin(waveOffset) * waveAmplitude;

            gc.drawImage(currentImage, x, y + wave, width, height);
        }
    }

    public void jump() {
        if (onGround) {
            velocityY = Constants.JUMP_FORCE;
            onGround = false;
            canDoubleJump = true;
            isDoubleJumping = false;
            System.out.println("First Jump!");
        } else if (canDoubleJump && !isDoubleJumping) {
            velocityY = Constants.DOUBLE_JUMP_FORCE;
            canDoubleJump = false;
            isDoubleJumping = true;
            System.out.println("Double Jump!");
        }
    }

    public void moveLeft() {
        velocityX = -Constants.PLAYER_SPEED;
    }

    public void moveRight() {
        velocityX = Constants.PLAYER_SPEED;
    }

    public void handlePlatformCollision(Platform platform) {
        double platformTop = platform.getY();
        double platformLeft = platform.getX();
        double platformRight = platform.getX() + platform.getWidth();

        if (velocityY > 0 &&
                previousY + height <= platformTop &&
                y + height >= platformTop &&
                x + width > platformLeft &&
                x < platformRight) {
            y = platformTop - height;
            velocityY = 0;
            onGround = true;
            canDoubleJump = false;
            isDoubleJumping = false;
            System.out.println("Landing on platform!");
        }
    }

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
        if (onGround) {
            canDoubleJump = false;
            isDoubleJumping = false;
        }
    }

    public double getVelocityX() {
        return velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }


    public boolean canDoubleJump() {
        return canDoubleJump;
    }

    public boolean isOnGround() {
        return onGround;
    }

    public void setY(double v) {
        y = v;
    }

    public void setVelocityY(double v) {
        velocityY = v;
    }

    public void setX(double v) {
        x = v;
    }
}