package it.unibo.sampleapp.model.impl;

import it.unibo.sampleapp.model.api.LevelProcess;

/**
 * Class that implements the LevelProcess.
 */
public class LevelProcessImpl implements LevelProcess {

    private final LevelState[] levels;

    /**
     * Initialized the level, the first one is unlocked, meanwhile the other are locked.
     *
     * @param totalLev the number of levels of the game
     */
    public LevelProcessImpl(final int totalLev) {
        levels = new LevelState[totalLev];
        levels[0] = LevelState.UNLOCKED;
        for (int i = 1; i < totalLev; i++) {
            levels[i] = LevelState.LOCKED;
        }
    }

    /**
     * Returns the state of the given level. 
     */
    @Override
    public LevelState getLevelState(final int ind) {
        return levels[ind];
    }

    /**
     * Marks a level as Completed and unlock the next one.
     */
    @Override
    public void finishedLevel(final int ind) {
        levels[ind] = LevelState.COMPLETED;
        if (ind + 1 < levels.length) {
            levels[ind + 1] = LevelState.UNLOCKED;
        }
    }
}
