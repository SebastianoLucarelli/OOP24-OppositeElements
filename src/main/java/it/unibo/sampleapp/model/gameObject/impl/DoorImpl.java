package it.unibo.sampleapp.model.gameObject.impl;

import it.unibo.sampleapp.model.gameObject.api.Door;
import it.unibo.sampleapp.utils.api.Position;

/**
 * Implementation of the Door interface.
 */
public class DoorImpl extends AbstractGameObject implements Door {

    private final DoorType type;

    /**
     * constructor of DoorImpl.
     *
     * @param position contains the position of the door
     * @param width conrains the width of the door
     * @param height contains the height of the door
     * @param type contains the type, the element, of the door
     */
    public DoorImpl(final Position position, final int width, final int height, final DoorType type) {
        super(position, width, height);
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DoorType getType() {
        return this.type;
    }

}
