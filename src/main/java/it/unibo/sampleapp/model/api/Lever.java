package it.unibo.sampleapp.model.api;

/**
 * interface for the lever in the game. The lever can activate and deactivate the movable objects.
 */
public interface Lever extends GameObject {

    /**
     * @return true if the lever is active, false otherwise
     */
    boolean isActive();

    /**
     * toogle the state of the levere, if it's on it turns off anche viceversa.
     */
    void toggle();

}
