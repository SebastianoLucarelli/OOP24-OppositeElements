package it.unibo.sampleapp.model.impl;

import it.unibo.sampleapp.model.api.Player;
import it.unibo.sampleapp.utils.api.Position;
import it.unibo.sampleapp.utils.impl.PositionImpl;

/**
 * Abstract class that implements Player.
 * Contains common player logic:
 * - position
 * - speed and gravity
 * - jump
 * - movement
 */
public abstract class AbstractPlayer implements Player {

    // --- Constants
    private static final double SPEED_MOVE = 200.0;
    private static final double SPEED_JUMP = -400.0;
    private static final double GRAVITY = 800.0;

    // --- Fields
    private final Position position;
    private double speedX;
    private double speedY;
    private final int width;
    private final int height;
    private boolean onFloor;

    /**
     * Constructor for AbstractPlayer.
     *
     * @param startX initial X coordinate
     * @param startY initial Y coordinate
     * @param width  player width
     * @param height player height
     */
    public AbstractPlayer(final int startX, final int startY, final int width, final int height) {
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
        } else if (right && !left) {
            speedX = SPEED_MOVE;
        } else {
            speedX = 0.0;
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
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHeight() {
        return height;
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
    public int getWidth() {
        return width;
    }

    /**
     * @return the horizontal speed
     */
    public double getSpeedX() {
        return speedX;
    }

    /**
     * @return true if the player is on the floor
     */
    public boolean isOnFloor() {
        return onFloor;
    }

    /**
     * Apply gravity to vertical speed.
     *
     * @param deltaTime time since last update
     */
    protected void gravityApply(final double deltaTime) {
        speedY += GRAVITY * deltaTime;
    }

    /**
     * Move the player horizontally.
     *
     * @param deltaTime time since last update
     */
    protected void horizontalMove(final double deltaTime) {
        final int newX = (int) (position.getX() + speedX * deltaTime);
        position.setX(newX);
    }

    /**
     * Move the player vertically.
     *
     * @param deltaTime time since last update
     */
    protected void verticalMove(final double deltaTime) {
        final int newY = (int) (position.getY() + speedY * deltaTime);
        position.setY(newY);
    }
}
