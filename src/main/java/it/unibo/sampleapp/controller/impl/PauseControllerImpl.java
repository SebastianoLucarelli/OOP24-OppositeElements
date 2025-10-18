package it.unibo.sampleapp.controller.impl;

import it.unibo.sampleapp.controller.api.PauseController;
import it.unibo.sampleapp.controller.core.api.GameEngine;
import it.unibo.sampleapp.model.game.GameState;
import it.unibo.sampleapp.model.game.api.Game;

/**
 * Implementation of the PauseController interface.
 * It manages the pause action: resume, restart and return home.
 */
public class PauseControllerImpl implements PauseController {

    private final Game game;
    private final GameEngine gameEngine;
    private final GameControllerImpl gameController;

    /**
     * Builder for the PauseControllerImpl.
     *
     * @param game the game model instance
     * @param gameController the main game controller
     * @param gameEngine the game engine
     */
    public PauseControllerImpl(final Game game, final GameControllerImpl gameController, final GameEngine gameEngine) {
        this.game = game;
        this.gameController = gameController;
        this.gameEngine = gameEngine;
    }

    /**
     * Stops the game level and returns to the home screen.
     */
    @Override
    public void backHome() {
        gameController.stop();
        gameEngine.changeState(GameState.HOME);
    }

    /**
     * Stops the game level and restarts the current level.
     */
    @Override
    public void restartTheLevel() {
        gameController.stop();
        gameEngine.startLevel(gameEngine.getCurrentLevelNumber());
    }

    /**
     * Resumes the paused level.
     */
    @Override
    public void resumeTheLevel() {
        game.resumeLevel();
        gameController.getLevelView().requestFocusInWindow();
    }
}
