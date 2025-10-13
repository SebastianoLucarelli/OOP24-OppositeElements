package it.unibo.sampleapp.model.object.impl;

import it.unibo.sampleapp.model.object.api.Lever;
import it.unibo.sampleapp.model.object.api.MovableIPlatform;
import it.unibo.sampleapp.utils.api.Position;

/**
 * implementation of the lever interface.
 */
public class LeverImpl extends AbstractGameObject implements Lever {

    private boolean active;
    private final MovableIPlatform linkedPlatform;

    /**
     * Constructor of LeverImpl.
     *
     * @param position contains the position of the lever
     * @param width contains the width of the lever
     * @param height contains the height of the lever
     * @param platform contains the movable platform linked to the lever
     */
    public LeverImpl(final Position position, final int width, final int height, final MovableIPlatform platform) {
        super(position, width, height);
        active = false;
        linkedPlatform = platform;
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

    /**
     * {@inheritDoc}
     */
    @Override
    public MovableIPlatform getLinkedPlatform() {
        return this.linkedPlatform;
    }

}
