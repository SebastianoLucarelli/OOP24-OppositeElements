package it.unibo.sampleapp.controller.core.api;

/**
 * The gameEngine interface.
 */
@FunctionalInterface
public interface GameEngine {

    /**
     * Loop of the game.
     */
    void gameLoop();
}
