package it.unibo.sampleapp.controller.api;

import java.awt.event.KeyEvent;

/**
 * Interface defining the controller for the movement of the player.
 */

public interface PlayerController {

    /**
     * Updates player status based on key presses.
     */
    void inputProcess();

    /**
     * Called when a key is pressed.
     *
     * @param e key press event
     */
    void keyPressed(KeyEvent e);

    /**
     * Called when a key is released.
     *
     * @param e key release event
     */
    void keyReleased(KeyEvent e);

    /**
     * KeyListener method.
     *
     * @param e e
     */
    void keyTyped(KeyEvent e);
}
