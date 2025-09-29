package it.unibo.sampleapp.controller.impl;

import it.unibo.sampleapp.controller.api.HomeController;
import it.unibo.sampleapp.controller.core.api.GameEngine;
import it.unibo.sampleapp.model.game.GameState;

/**
 * Implementation of the HomeController.
 */

public class HomeControllerImpl implements HomeController {

    private final GameEngine engine;

    /**
     * Builder for the HomeController.
     *
     * @param engine the core game engine to change states
     */
    public HomeControllerImpl(final GameEngine engine) {
        this.engine = engine;
    }

    /**
     * Switches the game to level selection state.
     */
    @Override
    public void startGame() {
        engine.changeState(GameState.LEVEL_SELECTION);
    }

    /**
     * Switches the game to instructions state.
     */
    @Override
    public void showInstructions() {
        engine.changeState(GameState.INSTRUCTION);
    }

    /**
     * Exits the application.
     */
    @Override
    public void exitGame() {
        System.exit(0);
    }
}
