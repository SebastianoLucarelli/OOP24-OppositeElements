package it.unibo.sampleapp.model.gameObject.api;

/**
 * represents the collectible gem in the game. 
 */
public interface Gem extends GameObject {

    /**
     * An enum representig the type of the gem. the fire character can take de fire gem, 
     * the water charatecer collect the water gem.
     */
    enum GemType {
        FIRE,
        WATER
    }

    /**
     * @return the type of the gem
     */
    GemType getType();

    /**
     * @return true if the gem has been collected, false otherwise
     */
    boolean isCollected();

    /**
     * marks the gem as collected.
     */
    void collect();

}
