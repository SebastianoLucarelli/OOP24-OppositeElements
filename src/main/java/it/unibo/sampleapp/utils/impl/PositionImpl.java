package it.unibo.sampleapp.utils.impl;

import java.util.Objects;

import it.unibo.sampleapp.utils.api.Position;

/**
 * Class implementing Position to handle spatial coordinates on a map.
 */
public final class PositionImpl implements Position {

    private Pair<Double, Double> position;

    /**
     * Create a position.
     *
     * @param x initial X coordinate
     * @param y initial Y coordinate
     */
    public PositionImpl(final double x, final double y) {
        this.position = new Pair<>(x, y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getX() {
        return this.position.getX();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getY() {
        return this.position.getY();
    }

    /**
     * {@inheritDoc}
     *
     * @param x new X coordinate
     */
    @Override
    public void setX(final double x) {
        this.position = new Pair<>(x, this.position.getY());
    }

    /**
     * {@inheritDoc}
     *
     * @param y new Y coordinate
     */
    @Override
    public void setY(final double y) {
        this.position = new Pair<>(this.position.getX(), y);
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
        return Double.compare(other.getX(), this.position.getX()) == 0
            && Double.compare(other.getY(), this.position.getY()) == 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.position.getX(), this.position.getY());
    }
}
