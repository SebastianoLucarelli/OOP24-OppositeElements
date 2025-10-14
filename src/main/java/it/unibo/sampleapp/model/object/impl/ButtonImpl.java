package it.unibo.sampleapp.model.object.impl;

import it.unibo.sampleapp.model.object.api.Button;
import it.unibo.sampleapp.model.object.api.MovableIPlatform;
import it.unibo.sampleapp.utils.api.Position;

/**
 * Implementation of the Button interface, che class represents the buttons in the game.
 */
public class ButtonImpl extends AbstractGameObject implements Button {

    private boolean pressed;
    private final MovableIPlatform linkedPlatform;

    /**
     * constructor of the ButtonImpl.
     *
     * @param position contains the button's position
     * @param width contains the button's width
     * @param height contains the button's height
     * @param linkPlatform contains the movable platform linked to the button
     */
    public ButtonImpl(final Position position, final int width, final int height, final MovableIPlatform linkPlatform) {
        super(position, width, height);
        this.pressed = false;
        this.linkedPlatform = linkPlatform;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPressed() {
        return this.pressed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPressed(final boolean pressed) {
        this.pressed = pressed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MovableIPlatform getLinkedPlatform() {
        return this.linkedPlatform;
    }

}
