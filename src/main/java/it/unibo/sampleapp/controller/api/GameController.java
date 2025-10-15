package it.unibo.sampleapp.controller.api;

/**
 * Defines basic game control operations..
 */
public interface GameController {

    /**
     * Start the level loop.
     */
    void start();

    /**
     * Stops the level loop.
     */
    void stop();

    /**
     * Pauses the current level.
     */
    void pauseLevelGame();

    /**
     * Resumes the paused level.
     */
    void resumeLevelGame();
}
