package it.unibo.sampleapp.controller.impl;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import it.unibo.sampleapp.model.object.impl.Fireboy;
import it.unibo.sampleapp.model.object.impl.Watergirl;
import it.unibo.sampleapp.controller.api.PlayerController;

/**
 * Controller implementation that handles Fireboy and Watergirl inputs.
 */
public class PlayerControllerImpl implements PlayerController, KeyListener {

    private final Fireboy fireboy;
    private final Watergirl watergirl;

    private boolean fireLeft;
    private boolean fireRight;
    private boolean fireJump;
    private boolean waterLeft;
    private boolean waterRight;
    private boolean waterJump;

    /**
     * Creates a controller for both players.
     *
     * @param fireboy the reference to Fireboy
     * @param watergirl the reference to Watergirl
     */
    public PlayerControllerImpl(final Fireboy fireboy, final Watergirl watergirl) {
        this.fireboy = fireboy;
        this.watergirl = watergirl;
    }


    /**
     * Processes input and updates player status.
     */
    @Override
    public void inputProcess() {
        fireboy.input(fireLeft, fireRight, fireJump);
        watergirl.input(waterLeft, waterRight, waterJump);
        fireJump = false;
        waterJump = false;
    }

    /**
     * Handles key presses.
     *
     * @param e KeyEvent event
     */
    @Override
    public void keyPressed(final KeyEvent e) {
       final int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_A:
                this.fireLeft = true;
                break;
            case KeyEvent.VK_D:
                this.fireRight = true;
                break;
            case KeyEvent.VK_W:
                this.fireJump = true;
                break;
            case KeyEvent.VK_LEFT:
                this.waterLeft = true;
                break;
            case KeyEvent.VK_RIGHT:
                this.waterRight = true;
                break;
            case KeyEvent.VK_UP:
                this.waterJump = true;
                break;
            default:
                break;
        }
    }

    /**
     * Handles key release.
     *
     * @param e KeyEvent event
     */
    @Override
    public void keyReleased(final KeyEvent e) {
       final int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_A:
                this.fireLeft = false;
                break;
            case KeyEvent.VK_D:
                this.fireRight = false;
                break;
            case KeyEvent.VK_LEFT:
                this.waterLeft = false;
                break;
            case KeyEvent.VK_RIGHT:
                this.waterRight = false;
                break;
            default:
                break;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void keyTyped(final KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }
}
