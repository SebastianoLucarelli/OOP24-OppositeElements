package it.unibo.sampleapp.model.object.api;

/**
 * Interface for the buttons.
 */
public interface Button extends GameObject {

    /**
     * @return the state of the button
     */
    boolean isPressed();

    /**
     * change the state of the button.
     *
     * @param pressed is the new state of the button
     */
    void setPressed(boolean pressed);

    /**
     * @return the linked platform
     */
    MovableIPlatform getLinkedPlatform();

    /**
     * Called when a player presses a button.
     */
    void press();

    /**
     * Called when a player releases a button.
     */
    void release();
}
