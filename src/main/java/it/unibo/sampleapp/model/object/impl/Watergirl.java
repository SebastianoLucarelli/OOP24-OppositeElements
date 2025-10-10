package it.unibo.sampleapp.model.object.impl;

import it.unibo.sampleapp.model.object.api.Hazard.HazardType;
import it.unibo.sampleapp.model.object.api.PlayerType;

/**
 * Class representing the character Watergirl.
 */

public class Watergirl extends AbstractPlayer {

    /**
     * Builder of the Watergirl class.
     *
     * @param startX initial X coordinate
     * @param startY initial Y coordinate
     * @param width player width
     * @param height player height
     */
    public Watergirl(final int startX, final int startY, final int width, final int height) {
        super(startX, startY, width, height);
    }

    /**
     * Determines whether Watergirl dies when in contact with a certain type of hazard.
     *
     * @param type of hazard
     * @return true if Watergirl dies, false otherwise
     */
    public boolean diesIn(final HazardType type) {
        return switch (type) {
            case FIRE, ACID -> true;
            case WATER -> false;
        };
    }

    @Override
    public final PlayerType getType() {
       return PlayerType.WATER;
    }
}
