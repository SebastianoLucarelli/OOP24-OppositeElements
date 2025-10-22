package it.unibo.sampleapp.model.game;

/**
 * States of the game.
 */
public enum GameState {
    /**
     * State that represents the start-up screen.
     */
    HOME,

    /**
     * State for displaying game instruction.
     */
    INSTRUCTION,

    /**
     * State that represents the screen for the selection of levels.
     */
    LEVEL_SELECTION,

    /**
     * State that represents the game that is running.
     */
    PLAYING,

    /**
     * State that represents the game being in pause.
     */
    PAUSE,

    /**
     * State that represents the game to load.
     */
    LEVEL_COMPLETED,

    /**
     * State that represents the game over of the game.
     */
    GAME_OVER,

    /**
     * State the represents victory in the game.
     */
    WIN
}
