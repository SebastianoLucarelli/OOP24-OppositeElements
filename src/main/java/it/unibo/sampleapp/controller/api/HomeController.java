package it.unibo.sampleapp.controller.api;

/**
 * Interface defining the controller for the game's home screen.
 */

public interface HomeController {

    /**
     * Callback triggered when the "Start" button is pressed.
     */
    void startGame();

    /**
     * Callback triggered when the "Instructions" button is pressed.
     */
    void showInstructions();

    /**
     * Callback triggered when the "Exit" button is pressed.
     */
    void exitGame();
}
