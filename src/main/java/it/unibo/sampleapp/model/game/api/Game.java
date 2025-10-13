package it.unibo.sampleapp.model.game.api;

import java.util.List;

import it.unibo.sampleapp.model.object.api.GameObject;
import it.unibo.sampleapp.model.object.api.Player;

/**
 * This interface update the game, as example the player action,
 * the interactions with the object, etc.
 */
public interface Game {

    /**
     * Updates the game, this method process the game (ex: object Interaction).
     *
     * @param deltaTime the deltaTime
     */
    void update(double deltaTime);

    /**
     * Function called by CollisionFactoryImpl in order to increment the gem collection.
     */
    void collectGems();

    /**
     * Used to check if all the level's gems are collected.
     *
     * @return true if all the gems are collected, false otherwise
     */
    boolean areAllGemsCollected();

    /**
     * Remove an object from the level due to a specific collision. 
     *
     * @param object the object that must be removed
     */
    void removeObject(GameObject object);

    /**
     * Check if the level is completed, i.e. if both players are in front of the correct door.
     */
    void checkLevelWin();

    /**
     * Check the game over.
     */
    void gameOver();

    /**
     * @return the list of the remaining objects
     */
    List<GameObject> getGameObjects();

    /**
     * @return the list of the players
     */
    List<Player> getPlayers();
}
