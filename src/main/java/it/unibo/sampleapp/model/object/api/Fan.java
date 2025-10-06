package it.unibo.sampleapp.model.object.api;

/**
 * interface for the Fan. The Fans push the character up
 */
public interface Fan extends GameObject {

    /**
     * @return true if the fan is active
     */
    boolean isActive();

    /**
     * active the fan.
     */
    void active();

    /**
     * deactive the fan.
     */
    void deactive();

}
