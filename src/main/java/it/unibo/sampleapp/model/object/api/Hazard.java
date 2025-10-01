package it.unibo.sampleapp.model.object.api;

/**
 * Interface for the different types of hazard, they going to kill the player when tousched.
 */
public interface Hazard extends GameObject {

    /**
     * This enum contains all the possible types of hazard present in the game.
     */
    enum HazardType {
        FIRE,
        WATER,
        ACID
    }

    /**
     * @return the type of hazard
     */
    HazardType getType();

}
