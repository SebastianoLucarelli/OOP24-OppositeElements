package it.unibo.sampleapp.model.object.impl;

import it.unibo.sampleapp.model.object.api.Player;
import it.unibo.sampleapp.utils.api.Position;
import it.unibo.sampleapp.utils.impl.PositionImpl;

/**
 * Abstract class that implements Player.
 * Contains common player logic:
 * - position
 * - speed and gravity
 * - jump
 * - movement
 * - animation
 */
public abstract class AbstractPlayer implements Player {

    private static final double SPEED_MOVE = 200.0;
    private static final double SPEED_JUMP = -300.0;
    private static final double GRAVITY = 800.0;
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 540;
    private static final double FRAME_TIME = 0.15;

    private static final String DIRECTION_LEFT = "left";
    private static final String DIRECTION_RIGHT = "right";
    private static final String DIRECTION_FRONT = "front";

    private Position position;
    private double speedX;
    private double speedY;
    private final int width;
    private final int height;
    private boolean onFloor;
    private boolean atDoor;
    private String direction = "front";
    private int frameNum = 1;
    private double animationTimer;

    /**
     * Constructor for AbstractPlayer.
     *
     * @param startX initial X coordinate
     * @param startY initial Y coordinate
     * @param width  player width
     * @param height player height
     */
    public AbstractPlayer(final double startX, final double startY, final int width, final int height) {
        this.position = new PositionImpl(startX, startY);
        this.width = width;
        this.height = height;
        this.speedX = 0.0;
        this.speedY = 0.0;
        this.onFloor = false;
    }

    /**
     * Processes input commands from controller.
     *
     * @param left  true if moving left
     * @param right true if moving right
     * @param jump  true if jumping
     */
    @Override
    public void input(final boolean left, final boolean right, final boolean jump) {
        if (left && !right) {
            speedX = -SPEED_MOVE;
            direction = DIRECTION_LEFT;
        } else if (right && !left) {
            speedX = SPEED_MOVE;
            direction = DIRECTION_RIGHT;
        } else {
            speedX = 0.0;
            direction = DIRECTION_FRONT;
        }

        if (jump && onFloor) {
            speedY = SPEED_JUMP;
            onFloor = false;
        }
    }

    /**
     * Updates player logic every frame.
     *
     * @param deltaTime time since last update
     */
    @Override
    public void updatePlayer(final double deltaTime) {
        gravityApply(deltaTime);
        horizontalMove(deltaTime);
        verticalMove(deltaTime);
        animate(deltaTime);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Position getPosition() {
        return new PositionImpl(position.getX(), position.getY());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHeight() {
        return this.height;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getWidth() {
        return this.width;
    }

    /**
     * @return true if the player is currently at the door
     */
    @Override
    public boolean isAtDoor() {
        return this.atDoor;
    }

    /**
     * Sets whether the player is currently at the door.
     *
     * @param atDoor true if the player is in contact with the door
     */
    @Override
    public void setAtDoor(final boolean atDoor) {
        this.atDoor = atDoor;
    }

    /**
     * @return the current direction
     */
    @Override
    public String getDirection() {
        return this.direction;
    }

    /**
     * @return the current aniamtion frame number
     */
    @Override
    public int getFrameNum() {
        return this.frameNum;
    }

    /**
     * @return the horizontal speed
     */
    @Override
    public double getSpeedX() {
        return this.speedX;
    }

    /**
     * @return the vertical speed
     */
    @Override
    public double getSpeedY() {
        return this.speedY;
    }

    /**
     * Stops vertical movement.
     *
     * @param newY the position where the player should stand
     */
    @Override
    public void landOn(final double newY) {
        this.position.setY(newY);
        this.speedY = 0;
        this.onFloor = true;
    }

    /**
     * Sets whether the player is currently on the floor.
     *
     * @param onFloor true if the player is touching the ground, false otherwise
     */
    @Override
    public void setOnFloor(final boolean onFloor) {
        this.onFloor = onFloor;
    }

    /**
     * Stops the jump when the player hits the platform.
     */
    @Override
    public void stopJump(final double newY) {
        this.position = new PositionImpl(this.position.getX(), newY);
        this.speedY = 0;
    }

    /**
     * @return true if the player is on the floor
     */
    public boolean isOnFloor() {
        return this.onFloor;
    }

    /**
     * Apply gravity to vertical speed.
     *
     * @param deltaTime time since last update
     */
    protected void gravityApply(final double deltaTime) {
        this.speedY += GRAVITY * deltaTime;
    }

    /**
     * Move the player horizontally.
     *
     * @param deltaTime time since last update
     */
    protected void horizontalMove(final double deltaTime) {
        double newX = this.position.getX() + this.speedX * deltaTime;
        if (newX < 0) {
            newX = 0;
        } else if (newX + this.width > SCREEN_WIDTH) {
            newX = SCREEN_WIDTH - this.width;
        }
        this.position.setX(newX);
    }

    /**
     * Move the player vertically.
     *
     * @param deltaTime time since last update
     */
    protected void verticalMove(final double deltaTime) {
        double newY = position.getY() + this.speedY * deltaTime;
        if (newY + this.height >= SCREEN_HEIGHT) {
            newY = SCREEN_HEIGHT - this.height;
            speedY = 0;
            this.onFloor = true;
        }
        this.position.setY(newY);
    }

    /**
     * Handles animation frame changes based on direction and time.
     *
     * @param deltaTime time since last update
     */
    private void animate(final double deltaTime) {
        if (Math.abs(speedX) > 0) {
            animationTimer += deltaTime;
            if (animationTimer >= FRAME_TIME) {
                frameNum = (frameNum == 1) ? 2 : 1;
                animationTimer = 0;
            }
        } else {
            frameNum = 1;
            animationTimer = 0;
        }
    }
}
