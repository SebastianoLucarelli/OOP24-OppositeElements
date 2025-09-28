package it.unibo.sampleapp.controller.core.impl;

import it.unibo.sampleapp.controller.core.api.GameEngine;
import it.unibo.sampleapp.controller.api.LevelProcessController;
import it.unibo.sampleapp.controller.impl.LevelProcessControllerImpl;
import it.unibo.sampleapp.model.api.LevelProcess;
import it.unibo.sampleapp.model.game.GameState;
import it.unibo.sampleapp.model.impl.LevelProcessImpl;
import it.unibo.sampleapp.view.impl.LevelProcessView;

/**
 * The gameEngine implementation.
 */
public class GameEngineImpl implements GameEngine {

    private GameState currentState;
    private final LevelProcess levelProcess;

    public GameEngineImpl() {
        this.currentState = GameState.HOME;
        this.levelProcess = new LevelProcessImpl(3);
    }

    /**
     * Implementation of the loop of the game.
     */
    @Override
    public void gameLoop() {
        switch (currentState) {     
            case LEVEL_SELECTION -> showLevelSelection();
            default -> throw new IllegalArgumentException("Unexpected value: " + currentState);
        }
    }

    /**
     * It changes the current state of the level.
     */
    @Override
    public void changeState(GameState state) {
        this.currentState = state;
        gameLoop();
    }

    /**
     * Initializes and displays the level selection screen.
     * Sets up callbacks for level selection and returning to the menu.
     */
    private void showLevelSelection() {
        final LevelProcessView levelSelView = new LevelProcessView(levelProcess);
        final LevelProcessController levelSelController = new LevelProcessControllerImpl(this, levelProcess);

        levelSelView.setSelectionLevel(levelSelController::levelSelected);
        levelSelView.setBackToMenu(levelSelController::backToMenu);
    }
}
