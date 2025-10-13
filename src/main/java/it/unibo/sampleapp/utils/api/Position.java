package it.unibo.sampleapp.utils.api;

/**
 * Interface for the positions in the game.
 */
public interface Position {

    /**
     * @return the x of the current position
     */
    double getX();

    /**
     * @return the y of the current position
     */
    double getY();

    /**
     * Change and set the new coordinate x of the current position.
     *
     * @param x is the new coordinate
     */
    void setX(double x);

    /**
     * Change and set the new coordinate y of the current position.
     *
     * @param y is the new coordinate
     */
    void setY(double y);

    /**
     * Compares this position with another object.
     *
     * @param position the object to compare
     * @return true if equal, false otherwise
     */
    @Override
    boolean equals(Object position);

    /**
     * {@inheritDoc}
     */
    @Override
    int hashCode();
}
