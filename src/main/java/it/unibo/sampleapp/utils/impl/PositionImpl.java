package it.unibo.sampleapp.utils.impl;

import java.util.Objects;

import it.unibo.sampleapp.utils.api.Position;

/**
 * Class implementing Position to handle spatial coordinates on a map.
 */
public final class PositionImpl implements Position {

    private Pair<Integer, Integer> position;

    /**
     * Create a position.
     *
     * @param x initial X coordinate
     * @param y initial Y coordinate
     */
    public PositionImpl(final int x, final int y) {
        this.position = new Pair<>(x, y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getX() {
        return this.position.getX();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getY() {
        return this.position.getY();
    }

    /**
     * {@inheritDoc}
     *
     * @param x new X coordinate
     */
    @Override
    public void setX(final int x) {
        final int newX = x;
        this.position = new Pair<>(newX, this.position.getY());
    }

    /**
     * {@inheritDoc}
     *
     * @param y new Y coordinate
     */
    @Override
    public void setY(final int y) {
        final int newY = y;
        this.position = new Pair<>(this.position.getX(), newY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Position)) {
            return false;
        }
        final Position other = (Position) obj;
        return other.getX() == this.position.getX()
            && other.getY() == this.position.getY();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.position.getX(), this.position.getY());
    }
}
