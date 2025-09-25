package it.unibo.sampleapp.model.impl;

import it.unibo.sampleapp.model.api.Button;
import it.unibo.sampleapp.utils.api.Position;

/**
 * Implementation of the Button interface, che class represents the buttons in the game.
 */
public class ButtonImpl extends AbstractGameObject implements Button {

    private boolean pressed;

    /**
     * constructor of the ButtonImpl.
     *
     * @param position contains the button's position
     * @param width contains the button's width
     * @param height contains the button's height
     */
    public ButtonImpl(final Position position, final int width, final int height) {
        super(position, width, height);
        this.pressed = false;
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
