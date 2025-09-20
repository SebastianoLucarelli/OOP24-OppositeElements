package it.unibo.sampleapp.model.api;

import it.unibo.sampleapp.utils.api.Position;

/**
 * generic interface for the game objects.
 */
public interface GameObject {

    /**
     * @return the position of the game object
     */
    Position getPosition();

    /**
     * @param posit set the new position of the game object
     */
    void setPosition(Position posit);

    /**
     * @return the object's width
     */
    int getWidth();

    /**
     * @return the object's height
     */
    int getHeight();

}
