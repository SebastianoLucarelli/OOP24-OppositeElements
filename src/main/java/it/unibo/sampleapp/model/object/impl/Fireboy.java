package it.unibo.sampleapp.model.object.impl;

import it.unibo.sampleapp.model.object.api.Hazard.HazardType;
import it.unibo.sampleapp.model.object.api.PlayerType;

/**
 * Class representing the character Fireboy.
 */
public class Fireboy extends AbstractPlayer {

    /**
     * Builder of the Fireboy class.
     *
     * @param startX initial X coordinate
     * @param startY initial Y coordinate
     * @param width player width
     * @param height player height
     */
    public Fireboy(final int startX, final int startY, final int width, final int height) {
        super(startX, startY, width, height);
    }

    /**
     * Determines whether Fireboy dies when in contact with a certain type of hazard.
     *
     * @param type of hazard
     * @return true if Fireboy dies, false otherwise
     */
    public boolean diesIn(final HazardType type) {
        return switch (type) {
            case WATER, ACID -> true;
            case FIRE -> false;
        };
    }

    @Override
    public final PlayerType getType() {
       return PlayerType.FIRE;
    }
}
