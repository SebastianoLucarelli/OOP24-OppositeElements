package it.unibo.sampleapp.controller.api;

/**
 * Controller Interface for the level selection screen.
 */
public interface LevelProcessController {

    /**
     * Callback triggered when a level is selected.
     *
     * @param index the index of the level
     */
    void levelSelected(int index);

    /**
     * Callback triggered when the user wants to return to the main Menu.
     */
    void backToMenu();
}
