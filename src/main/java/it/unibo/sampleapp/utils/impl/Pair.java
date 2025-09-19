package it.unibo.sampleapp.utils.impl;

import java.util.Objects;

/**
 * Standard generic {@code Pair<X, Y>} saving a couple of elements.
 *
 * @param <X> type of the first element
 * @param <Y> type of the second element
 */
public class Pair<X, Y> {

    private final X x;
    private final Y y;

    /**
     * Creates a Pair object with the given elements.
     *
     * @param x first element
     * @param y second element
     */
    public Pair(final X x, final Y y) {
        super();
        this.x = x;
        this.y = y;
    }

    /**
     * @return the first element of the Pair
     */
    public X getX() {
        return x;
    }

    /**
     * @return the second element of the Pair
     */
    public Y getY() {
        return y;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("rawtypes")
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Pair other = (Pair) obj;
        return Objects.equals(x, other.x) && Objects.equals(y, other.y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Pair [x=" + x + ", y=" + y + "]";
    }
}
