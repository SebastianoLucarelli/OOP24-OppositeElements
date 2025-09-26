package it.unibo.sampleapp.model.gameObject.impl;

import it.unibo.sampleapp.model.gameObject.api.MovableIPlatform;
import it.unibo.sampleapp.utils.api.Position;
import it.unibo.sampleapp.utils.impl.PositionImpl;

/**
 * Implementation of the MovableIPlatoform interface.
 */
public class MovablePlatformImpl extends AbstractGameObject implements MovableIPlatform {

    private final Position initialPosition;
    private final Position targetPosition;
    private final int stepSize;
    private boolean isActive;

    /**
     * Constructor of the MovablePlatformImpl.
     *
     * @param position is the position of the platform
     * @param width is the width of the platform
     * @param height is the height of the platform
     * @param stepSize is the distance that the platform move each time
     * @param horizontal is true if the platform move horizontally, false if it move vertically
     * @param direction is the direction where the platform has to move, +1 for right or down, -1 for left or up
     */
    public MovablePlatformImpl(final Position position, final int width, final int height, final int stepSize, 
    final boolean horizontal, final int direction) {
        super(position, width, height);
        this.initialPosition = new PositionImpl(position.getX(), position.getY());
        if (horizontal) {
            this.targetPosition = new PositionImpl(position.getX() + (direction * width), position.getY());
        } else {
            this.targetPosition = new PositionImpl(position.getX(), position.getY() + (direction * height));
        }
        this.stepSize = stepSize;
        this.isActive = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSpeed() {
        return this.stepSize;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void active() {
        this.isActive = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deactive() {
        this.isActive = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isActive() {
       return this.isActive;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void move() {
        final Position currentPosition = super.getPosition();
        if (this.isActive) {
            update(currentPosition, targetPosition);
        } else {
            update(currentPosition, initialPosition);
        }
    }

    /**
     * This method calculate the horizontal (dx) or vertical (dy) distance to the destination.
     * It then determines the step size for this frame and ensuring that the platform doesn't overshoot the destination.
     * The use of {@Math.min} and {@Math.max} guarantees that the step taken is never larger than the remaining distance.
     * THe parameters stepX and stepY are contains the actual step that the platform has to do in this frame.
     * They are then added to the current position to get the new position of the platform.
     *
     * @param currentPosition contains the current position of the platform
     * @param destination contains the destination position of the platform, where it has to go
     */
    private void update(final Position currentPosition, final Position destination) {
        final int dx = destination.getX() - currentPosition.getX(); 
        final int dy = destination.getY() - currentPosition.getY();

        int stepX = 0;
        int stepY = 0;

        if (dx != 0) {
            stepX = (dx > 0) ? Math.min(stepSize, dx) : Math.max(-stepSize, dx);
        }
        if (dy != 0) {
            stepY = (dy > 0) ? Math.min(stepSize, dy) : Math.max(-stepSize, dy);
        }
        super.setPosition(new PositionImpl(currentPosition.getX() + stepX, currentPosition.getY() + stepY));
    }

}
