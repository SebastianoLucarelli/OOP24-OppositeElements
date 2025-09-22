package it.unibo.sampleapp.model.impl;

import it.unibo.sampleapp.model.api.Platform;
import it.unibo.sampleapp.utils.api.Position;
import it.unibo.sampleapp.utils.impl.PositionImpl;

/**
 * Implementation of the Platform interface.
 */
public class PlatformImpl implements Platform {

    private final Position position;
    private final int width;
    private final int height;

    /**
     * Constructor of the PlatformImpl.
     *
     * @param position is the position of the platform
     * @param width is the width of the platform
     * @param height is the height of the platform
     */
    public PlatformImpl(final Position position, final int width, final int height) {
        this.position = new PositionImpl(position.getX(), position.getY());
        this.width = width;
        this.height = height;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Position getPosition() {
        return new PositionImpl(this.position.getX(), this.position.getY());
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

}
