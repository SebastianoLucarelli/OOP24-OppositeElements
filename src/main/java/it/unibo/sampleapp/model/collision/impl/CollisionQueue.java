package it.unibo.sampleapp.model.collision.impl;

import java.util.LinkedList;
import java.util.Queue;

import it.unibo.sampleapp.model.collision.api.Collisions;
import it.unibo.sampleapp.model.game.api.Game;

/**
 * Class for queue of Collisions.
 */
public class CollisionQueue {

    private final Queue<Collisions> collisions;

    /**
     * Constructor of CollisionQueue.
     */
    public CollisionQueue() {
        collisions = new LinkedList<>();
    }

    /**
     * Adds a collision in the queue.
     *
     * @param collision the new collision to add
     */
    public void addCollision(final Collisions collision) {
        collisions.add(collision);
    }

    /**
     * Clears the queue of the collisions.
     */
    public void clearQueue() {
        collisions.clear();
    }

    /**
     * Manages and executes all the Collisions in the queue.
     *
     * @param game the game to manage
     */
    public void manageCollisions(final Game game) {
      while (!collisions.isEmpty()) {
          collisions.poll().manageCollision(game);
        }
    }

    /**
     * Checks if the queue isEmpty.
     *
     * @return true if the queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return collisions.isEmpty();
    }
}
