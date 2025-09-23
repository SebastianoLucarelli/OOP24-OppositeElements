package it.unibo.sampleapp.model.impl;

import it.unibo.sampleapp.model.api.LevelProcess;

public class LevelProcessImpl implements LevelProcess {

    private LevelState[] levels;

    public LevelProcessImpl(int totalLev) {
        levels = new LevelState[totalLev];
        levels[0] = LevelState.UNLOCKED;
        for(int i = 1; i < totalLev; i++) {
            levels[i] = LevelState.LOCKED;
        }
    }

    @Override
    public LevelState getLevelState(int ind) {
        return levels[ind];
    }

    @Override
    public void finishedLevel(int ind) {
        levels[ind] = LevelState.COMPLETED;
        if (ind + 1 < levels.length) {
            levels[ind + 1] = LevelState.UNLOCKED;
        }
    }
}
