package it.unibo.sampleapp.model.object.impl;

import it.unibo.sampleapp.model.object.api.Hazard;
import it.unibo.sampleapp.model.object.api.Player;
import it.unibo.sampleapp.model.object.api.PlayerType;
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

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean safeForPlayer(final Player player) {
        switch (this.type) {
            case ACID: return false; 
            case FIRE: return player.getType() == PlayerType.FIRE;
            case WATER: return player.getType() == PlayerType.WATER;
        }
        return false;
    }
}
