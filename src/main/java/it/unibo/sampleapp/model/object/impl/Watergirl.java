package it.unibo.sampleapp.model.object.impl;

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
    public Watergirl(final double startX, final double startY, final int width, final int height) {
        super(startX, startY, width, height);
    }

    @Override
    public final PlayerType getType() {
       return PlayerType.WATER;
    }
}
