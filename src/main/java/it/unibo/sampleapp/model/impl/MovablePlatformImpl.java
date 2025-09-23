package it.unibo.sampleapp.model.impl;

import it.unibo.sampleapp.model.api.MovableIPlatform;
import it.unibo.sampleapp.utils.api.Position;
import it.unibo.sampleapp.utils.impl.PositionImpl;

/**
 * Implementation of the MovableIPlatoform interface.
 */
public class MovablePlatformImpl implements MovableIPlatform {

    private Position position;
    private final int width;
    private final int height;
    private final int distance;
    private final int maxX;
    private final int minX;

    /**
     * Constructor of the MovablePlatformImpl.
     *
     * @param position is the position of the platform
     * @param width is the width of the platform
     * @param height is the height of the platform
     * @param distance is de distance that the platform move each time
     * @param minX is the minimum X value that the platform can reach
     * @param maxX is the maximum X value that the platform can reach
     */
    public MovablePlatformImpl(final Position position, final int width, final int height, final int distance, 
    final int minX, final int maxX) {
        this.position = new PositionImpl(position.getX(), position.getY());
        this.width = width;
        this.height = height;
        this.distance = distance; 
        this.minX = minX;
        this.maxX = maxX;
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
        return this.width;
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
    public void setPosition(final Position position) {
        this.position = new PositionImpl(position.getX(), position.getY());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void move() {
        final int newX = this.position.getX() + this.distance;
        if (newX > this.minX && newX < this.maxX) {
            position.setX(newX);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSpeed() {
        return this.distance;
    }

}
