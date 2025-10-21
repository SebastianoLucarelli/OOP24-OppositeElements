
package it.unibo.sampleapp.model.object.api;

/**
 * interface for the lever in the game. The lever can activate and deactivate the movable objects.
 */
public interface Lever extends GameObject {

    /**
     * @return true if the lever is active, false otherwise
     */
    boolean isActive();

    /**
     * activate or deactivate the lever.
     *
     * @param active true to activate, false to deactivate
     */
    void setActive(boolean active);

    /**
     * @return the linked platform
     */
    MovableIPlatform getLinkedPlatform();

    /**
     * @return true if the lever is activated from left side, false otherwise
     */
    boolean isActivedFromLeft();

    /**
     * @param left true if the lever is activated from left side
     */
    void setActivedFromLeft(boolean left);
}
