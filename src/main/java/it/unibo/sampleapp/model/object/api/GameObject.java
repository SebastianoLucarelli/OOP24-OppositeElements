package it.unibo.sampleapp.model.object.api;

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
     * @return the object's width
     */
    int getWidth();

    /**
     * @return the object's height
     */
    int getHeight();

}
