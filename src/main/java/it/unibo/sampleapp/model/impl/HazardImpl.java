package it.unibo.sampleapp.model.impl;

import it.unibo.sampleapp.model.api.Hazard;
import it.unibo.sampleapp.utils.api.Position;

/**
 * Implementation of the Hazard interface.
 */
public class HazardImpl extends AbstractGameObject implements Hazard {

    private final HazardType type;

    /**
     * Constructor of the HazardImpl.
     *
     * @param position contains the position of the hazard
     * @param width contains the width of the hazard
     * @param height contains the height of the hazard
     * @param type contains the type of the hazard
     */
    public HazardImpl(final Position position, final int width, final int height, final HazardType type) {
        super(position, width, height);
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HazardType getType() {
        return this.type;
    }
}
