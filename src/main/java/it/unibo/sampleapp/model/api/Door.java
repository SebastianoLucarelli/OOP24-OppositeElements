package it.unibo.sampleapp.model.api;

/**
 * interface for the different types of doors in the game. In the FIRE door can pass only the FIRE character
 * and in the WATER door can pass only the WATER character.
 */
public interface Door extends GameObject {

    /**
     * An enum for the type of the door corresponding to the the type of the character that can pass throught it.
     */
    enum DoorType {
        FIRE,
        WATER
    }

    /**
     * @return the type of the door
     */
    DoorType getType();

}
