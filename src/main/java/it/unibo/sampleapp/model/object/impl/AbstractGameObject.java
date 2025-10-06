package it.unibo.sampleapp.model.object.impl;

import it.unibo.sampleapp.model.object.api.GameObject;
import it.unibo.sampleapp.utils.api.Position;
import it.unibo.sampleapp.utils.impl.PositionImpl;

/**
 * Implementation of GameObject interface. This class contiains the common methods for all the game objects.
 */
public abstract class AbstractGameObject implements GameObject {

    /**
     * the position of the game object.
     */
    private Position position;
    private final int width;
    private final int height;

    /**
     * Constructor of GameObjectImpl.
     *
     * @param position contains the position of the game object
     * @param width contains the width of the game object
     * @param height contains the height of the game object
     */
    public AbstractGameObject(final Position position, final int width, final int height) {
        this.position = new PositionImpl(position.getX(), position.getY());
        this.width = width;
        this.height = height;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Position getPosition() {
        return new PositionImpl(this.position.getX(), this.position.getY());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getWidth() {
        return this.width;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHeight() {
        return this.height;
    }

    /** 
     * Set the new position of the game object. 
     *
     * @param position is the new position of the game object
     */
    protected void setPosition(final Position position) {
        this.position = new PositionImpl(position.getX(), position.getY());
    }

}
