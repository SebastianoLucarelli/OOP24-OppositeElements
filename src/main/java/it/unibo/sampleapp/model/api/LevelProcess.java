package it.unibo.sampleapp.model.api;

/**
 * Interface that defines the state of game levels.
 */
public interface LevelProcess {

    /**
     * States of the level.
     */
    enum LevelState {
        /**
         * State that allows you to play the level.
         */
        UNLOCKED,

        /**
         * State denoting that the level is blocked because the previous one has not been completed.
         */
        LOCKED,

        /**
         * State that denotes the surpassing of the level.
         */
        COMPLETED
    }

    /**
     * Returns the state of the level in order to understand how to proceed.
     *
     * @param ind the index of the level 
     * @return the state level
     */
    LevelState getLevelState(int ind);

    /**
     * Used to set the level success.
     *
     * @param ind the index of the level
     */
    void finishedLevel(int ind);
}
