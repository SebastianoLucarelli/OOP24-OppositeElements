package it.unibo.sampleapp.model.level.api;

/**
 * A stopwatch-style timer API.
 */
public interface Timer {

    /**
     * Starts or resumes the timer.
     */
    void start();

    /**
     * Stops the timer and accumulates elapsed time.
     */
    void stop();

    /**
     * Resets the timer.
     */
    void reset();

    /**
     * Return the total elapsed time in milliseconds.
     *
     * @return total elapsed time in milliseconds
     */
    long getTotalDurationMillis();

    /**
     * Return the total elapsed tie in seconds.
     *
     * @return total elapsed time in seconds
     */
    long getTotalDurationSeconds();
}
