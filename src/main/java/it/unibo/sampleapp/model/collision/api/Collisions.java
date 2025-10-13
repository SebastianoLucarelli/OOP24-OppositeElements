package it.unibo.sampleapp.model.collision.api;

import it.unibo.sampleapp.model.game.api.Game;

/**
 * Represents a Collision event that can be handled in the game.
 */
@FunctionalInterface
public interface Collisions {

    /**
     * Applies the effect of the collision to the game.
     *
     * @param game the current game instance where the collision occurred
     */
    void manageCollision(Game game);
}
