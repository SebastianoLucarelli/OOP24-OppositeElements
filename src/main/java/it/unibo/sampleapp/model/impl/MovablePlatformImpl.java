package it.unibo.sampleapp.model.impl;

import it.unibo.sampleapp.model.api.MovableIPlatform;
import it.unibo.sampleapp.utils.api.Position;
import it.unibo.sampleapp.utils.impl.PositionImpl;

/**
 * Implementation of the MovableIPlatoform interface.
 */
public class MovablePlatformImpl extends AbstractGameObject implements MovableIPlatform {

    private final int distance;

    /**
     * Constructor of the MovablePlatformImpl.
     *
     * @param position is the position of the platform
     * @param width is the width of the platform
     * @param height is the height of the platform
     * @param distance is de distance that the platform move each time
     */
    public MovablePlatformImpl(final Position position, final int width, final int height, final int distance) {
        super(position, width, height);
        this.distance = distance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void move(final int direction) {
        final int newX = super.getPosition().getX() + (direction * this.distance);
        super.setPosition(new PositionImpl(newX, super.getPosition().getY()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSpeed() {
        return this.distance;
    }

}
