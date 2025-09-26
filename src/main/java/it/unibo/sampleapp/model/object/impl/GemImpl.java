package it.unibo.sampleapp.model.object.impl;

import it.unibo.sampleapp.model.object.api.Gem;
import it.unibo.sampleapp.utils.api.Position;

/**
 * implementation of the interface gem.
 */
public class GemImpl extends AbstractGameObject implements Gem {

    private final GemType type;
    private boolean isCollected;

    /**
     * constructs a new gem.
     *
     * @param position is the position of the gem
     * @param width is the width of the gem
     * @param height is the height of the gem
     * @param type is the type of the gem, fire or water
     */
    public GemImpl(final Position position, final int width, final int height, final GemType type) {
        super(position, width, height);
        this.type = type;
        this.isCollected = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GemType getType() {
        return this.type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCollected() {
        return this.isCollected;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void collect() {
        this.isCollected = true;
    }

}
