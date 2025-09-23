package it.unibo.sampleapp.model.impl;

import it.unibo.sampleapp.model.api.Hazard;
import it.unibo.sampleapp.utils.api.Position;
import it.unibo.sampleapp.utils.impl.PositionImpl;

/**
 * Implementation of the Hazard interface.
 */
public class HazardImpl implements Hazard {

    private final HazardType type;
    private final Position position;
    private final int width;
    private final int height;

    /**
     * Constructor of the HazardImpl.
     *
     * @param position contains the position of the hazard
     * @param width contains the width of the hazard
     * @param height contains the height of the hazard
     * @param type contains the type of the hazard
     */
    public HazardImpl(final Position position, final int width, final int height, final HazardType type) {
        this.position = new PositionImpl(position.getX(), position.getY());
        this.height = height;
        this.width = width;
        this.type = type;
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
    public HazardType getType() {
        return this.type;
    }
}
