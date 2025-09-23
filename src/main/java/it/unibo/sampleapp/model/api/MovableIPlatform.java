package it.unibo.sampleapp.model.api;

/**
 * Interface for the platforms that can move.
 */
public interface MovableIPlatform extends GameObject {

    /**
     * move the platform in the specified direction.
     *
     * @param direction contains the direction where the platform has to move 
     */
    void move(int direction);

    /**
     * @return the speed of the platform
     */
    int getSpeed();
}
