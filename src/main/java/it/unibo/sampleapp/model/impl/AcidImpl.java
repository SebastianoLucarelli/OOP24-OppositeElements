package it.unibo.sampleapp.model.impl;

import it.unibo.sampleapp.model.api.Acid;
import it.unibo.sampleapp.utils.api.Position;
import it.unibo.sampleapp.utils.impl.PositionImpl;

/**
 * Implementation of the Acid interface.
 */

public class AcidImpl implements Acid {

    private final Position position;
    private final int width;
    private final int height;

    /**
     * Constructor of the AcidImpl.
     *
     * @param position is the acid's position
     * @param width is the acid's width
     * @param height is the acid's height
     */
    public AcidImpl(final Position position, final int width, final int height) {
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
