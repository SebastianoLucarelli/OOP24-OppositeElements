package it.unibo.sampleapp.model.object.impl;

import it.unibo.sampleapp.model.object.api.Lever;
import it.unibo.sampleapp.utils.api.Position;

/**
 * implementation of the lever interface.
 */
public class LeverImpl extends AbstractGameObject implements Lever {

    private boolean active;

    /**
     * Constructor of LeverImpl.
     *
     * @param position contains the position of the lever
     * @param width contains the width of the lever
     * @param height contains the height of the lever
     */
    public LeverImpl(final Position position, final int width, final int height) {
        super(position, width, height);
        active = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isActive() {
       return this.active;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void toggle() {
        this.active = !this.active;
    }

}
