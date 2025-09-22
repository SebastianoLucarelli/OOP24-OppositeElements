package it.unibo.sampleapp.model.api;

import it.unibo.sampleapp.utils.api.Position;

/**
 * Interface for the platforms that can move.
 */
public interface MovableIPlatform extends GameObject {

    /**
     * set the new position of the platform.
     *
     * @param position contains the new position
     */
    void setPosition(Position position);

    /**
     * move the platform.
     */
    void move();

    /**
     * @return the speed of the platform
     */
    int getSpeed();
}
