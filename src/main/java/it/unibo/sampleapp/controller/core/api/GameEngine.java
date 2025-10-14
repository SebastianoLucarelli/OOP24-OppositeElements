package it.unibo.sampleapp.controller.core.api;

import it.unibo.sampleapp.model.game.GameState;

/**
 * The gameEngine interface.
 */
public interface GameEngine {

    /**
     * Loop of the game.
     */
    void gameLoop();

    /**
     *  Method used to change the state of the game.
     *
     * @param state the new state
     */
    void changeState(GameState state);

    /**
     * Method used to start the correct level.
     *
     * @param levelNumber level that must be loaded
     */
    void startLevel(int levelNumber);
}
