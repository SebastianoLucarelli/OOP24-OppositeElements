package it.unibo.sampleapp.model.api;

/**
 * Interface for the platforms that can move.
 */
public interface MovableIPlatform extends GameObject {

    /**
     * @return the speed of the platform
     */
    int getSpeed();

    /**
     * This method activate the platform, so it start moving.
     */
    void active();

    /**
     * This method deactivate the platform, so it start moving back to its initial position.
     */
    void deactive();

    /**
     * this method move the platform toward the target position if it is active.
     */
    void move();

    /**
     * @return true if the platform is active, false otherwise
     */
    boolean isActive();

}
