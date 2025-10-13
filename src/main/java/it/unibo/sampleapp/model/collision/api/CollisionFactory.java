package it.unibo.sampleapp.model.collision.api;

import it.unibo.sampleapp.model.object.api.Button;
import it.unibo.sampleapp.model.object.api.Door;
import it.unibo.sampleapp.model.object.api.Gem;
import it.unibo.sampleapp.model.object.api.Hazard;
import it.unibo.sampleapp.model.object.api.Lever;
import it.unibo.sampleapp.model.object.api.MovableIPlatform;
import it.unibo.sampleapp.model.object.api.Player;

/**
 * Interface where each method defines a specific interaction between the players and an object.
 */
public interface CollisionFactory {

    /**
     * Handle the collision between a player and a gem.
     * Useful for determining whether to collect the gem.
     *
     * @param player the player character
     * @param gem the gem object to be collected
     * @return the result of the collision
     */
    Collisions hitGems(Player player, Gem gem);

    /**
     * Handles the collision between a player and a hazard (fire, water or poison).
     * Used to determine if the player should die or not.
     *
     * @param player the player character
     * @param hazard the hazard type
     * @return the result of the collision
     */
    Collisions hitHazard(Player player, Hazard hazard);

    /**
     * Handles the collision between a player and a door. 
     * Used to check if the door is unlocked and if the player can pass through.
     *
     * @param player the player character
     * @param door the door object
     * @return the result of the collision
     */
    Collisions doorUnlockedCollision(Player player, Door door);

    /**
     * Handles the collision beetwen a player and a button.
     * Used to activate the mechanism of the movablePlatform.
     *
     * @param player the player character
     * @param button the button object
     * @return the result of collision
     */
    Collisions buttonClickedCollision(Player player, Button button);

    /**
     * Handles the collision beetwen a player and a lever.
     * Used to change the state of the lever.
     *
     * @param player the player character
     * @param lever the lever object
     * @return the result of collisions
     */
    Collisions leverDisplacementCollision(Player player, Lever lever);

    /**
     * Handles the collision beetwen a player and a movablePlatform.
     *
     * @param player the player character
     * @param movablePlatform the movable platform object 
     * @return the result of collisions
     */
    Collisions movablePlatformCollision(Player player, MovableIPlatform movablePlatform);
}
