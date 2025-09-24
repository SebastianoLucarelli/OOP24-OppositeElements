package it.unibo.sampleapp.model.impl;

import it.unibo.sampleapp.model.api.Platform;
import it.unibo.sampleapp.utils.api.Position;

/**
 * Implementation of the Platform interface.
 * This class rappresents the static platforms in the game.
 */
public class PlatformImpl extends AbstractGameObject implements Platform {

    /**
     * Constructor of the PlatformImpl.
     *
     * @param position is the position of the platform
     * @param width is the width of the platform
     * @param height is the height of the platform
     */
    public PlatformImpl(final Position position, final int width, final int height) {
        super(position, width, height);
    }

}
