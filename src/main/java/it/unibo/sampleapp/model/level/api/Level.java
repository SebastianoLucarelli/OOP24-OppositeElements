package it.unibo.sampleapp.model.level.api;

import java.util.List;

import it.unibo.sampleapp.model.object.api.GameObject;
import it.unibo.sampleapp.model.object.api.Player;

/**
 * Represents a game level with objects, player and size.
 */
public interface Level {

    /**
     * @return all the objects in the level 
     */
    List<GameObject> getGameObjects();

    /**
     * @return the player int he level
     */
    List<Player> getPlayers();

    /**
     * @return the level width in pixels
     */
    int getWidth();

    /**
     * @return the level height in pixels
     */
    int getHeight();

}
