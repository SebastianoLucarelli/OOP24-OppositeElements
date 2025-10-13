package it.unibo.sampleapp.controller.api;

/**
 * Controller Interface for the Pause screen.
 */
public interface PauseController {

    /**
     * Callback triggered when the user wants to return to the Level Selection Screen.
     */
    void backtoLevelSelection();

    /**
     * A function used to restart the current level.
     *
     * @param levelIndex the index of the current level
     */
    void restartTheLevel(int levelIndex);

    /**
     * A function that admit to continue the current level.
     */
    void resumeTheLevel();
}
