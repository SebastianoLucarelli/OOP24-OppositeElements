package it.unibo.sampleapp.model.impl;

import it.unibo.sampleapp.model.api.Button;
import it.unibo.sampleapp.utils.api.Position;
import it.unibo.sampleapp.utils.impl.PositionImpl;

/**
 * Implementation of the Button interface, che class represents the buttons in the game.
 */
public class ButtonImpl implements Button {

    private final Position position;
    private final int width;
    private final int height;
    private boolean pressed;

    /**
     * constructor of the ButtonImpl.
     *
     * @param position contains the button's position
     * @param width contains the button's width
     * @param height contains the button's height
     */
    public ButtonImpl(final Position position, final int width, final int height) {
        this.position = new PositionImpl(position.getX(), position.getY());
        this.width = width;
        this.height = height;
        this.pressed = false;
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

}
