package it.unibo.sampleapp.model.impl;

import it.unibo.sampleapp.model.api.Fan;
import it.unibo.sampleapp.utils.api.Position;

/**
 * the implementation of the Fan interface.
 */
public class FanImpl extends AbstractGameObject implements Fan {

    private boolean isActive;

    /**
     * costructor of FanImpl.
     *
     * @param position is the position of the fan
     * @param width is the width of the fan
     * @param height is the height of the fan
     */
    public FanImpl(final Position position, final int width, final int height) {
        super(position, width, height);
        this.isActive = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isActive() {
        return isActive;
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

}
