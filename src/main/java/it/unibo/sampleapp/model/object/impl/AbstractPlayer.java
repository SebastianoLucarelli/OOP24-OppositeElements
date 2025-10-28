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
    private boolean dead;

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
        this.dead = false;
    }

    /**
     * {@inheritDoc}
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
     * {@inheritDoc}
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
     * {@inheritDoc}
     */
    @Override
    public boolean isAtDoor() {
        return this.atDoor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAtDoor(final boolean atDoor) {
        this.atDoor = atDoor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDirection() {
        return this.direction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getSpeedX() {
        return this.speedX;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getSpeedY() {
        return this.speedY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSpeedY(final double speedY) {
        this.speedY = speedY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSpeedX(final double speedX) {
        this.speedX = speedX;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void landOn(final double newY) {
        this.position.setY(newY);
        this.speedY = 0;
        this.onFloor = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setOnFloor(final boolean onFloor) {
        this.onFloor = onFloor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stopJump(final double newY) {
        this.position = new PositionImpl(this.position.getX(), newY);
        this.speedY = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPosition(final Position newPos) {
        this.position = new PositionImpl(newPos.getX(), newPos.getY());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPositionX(final double x) {
        this.position.setX(x);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPositionY(final double y) {
        this.position.setY(y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stopHorizontalMovement() {
        this.speedX = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDead() {
        return dead;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDead(final boolean dead) {
        this.dead = dead;
    }

    /**
     * {@inheritDoc}
     */
    @Override
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
}
