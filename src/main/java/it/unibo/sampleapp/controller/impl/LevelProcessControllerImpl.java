package it.unibo.sampleapp.controller.impl;

import it.unibo.sampleapp.controller.api.LevelProcessController;
import it.unibo.sampleapp.controller.core.api.GameEngine;
import it.unibo.sampleapp.model.game.GameState;
import it.unibo.sampleapp.model.level.api.LevelProcess;
import it.unibo.sampleapp.model.level.api.LevelProcess.LevelState;

/**
 * Controller for the level selection screen.
 */
public class LevelProcessControllerImpl implements LevelProcessController {

    private final GameEngine engine;
    private final LevelProcess levelProcess;

    /**
     * Builder that is referred to the game engine and the level model.
     *
     * @param engine the game core
     * @param levelProcess the model interface that provides the level state information
     */
    public LevelProcessControllerImpl(final GameEngine engine, final LevelProcess levelProcess) {
        this.engine = engine;
        this.levelProcess = levelProcess;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void levelSelected(final int index) {
        final LevelState state = levelProcess.getLevelState(index);
        if (state == LevelState.UNLOCKED || state == LevelState.COMPLETED) {
            engine.startLevel(index + 1);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void backToMenu() {
        engine.changeState(GameState.HOME);
    }
}
