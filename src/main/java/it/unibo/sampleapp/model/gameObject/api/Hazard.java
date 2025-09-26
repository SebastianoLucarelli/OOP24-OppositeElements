package it.unibo.sampleapp.model.gameObject.api;

/**
 * Interface for the different types of hazard, they going to kill the player when tousched.
 */
public interface Hazard extends GameObject {

    /**
     * This enum contains all the possible types of hazard present in the game.
     */
    enum HazardType {
        LAVA,
        WATER,
        ACID
    }

    /**
     * @return the type of hazard
     */
    HazardType getType();

}
