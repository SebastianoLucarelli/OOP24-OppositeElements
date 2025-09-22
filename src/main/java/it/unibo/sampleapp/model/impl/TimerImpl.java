package it.unibo.sampleapp.model.impl;

import it.unibo.sampleapp.model.api.Timer;

/**
 * Timer implementation.
 */
public final class TimerImpl implements Timer {

    private long timeStart;
    private long timeElapsed;
    private boolean running;

    /**
     * Creates a new Timer with no elapsed timer, and in a stopped state.
     */
    public TimerImpl() {
        this.timeElapsed = 0;
        this.running = false;
    }

    /**
     * Start and re-start of the timer.
     */
    @Override
    public void start() {
        if (!running) {
            this.timeStart = System.currentTimeMillis();
            this.running = true;
        }
    }

    /**
     * Stop and save the time that has already passed.
     */
    @Override
    public void stop() {
        if (running) {
            timeElapsed += System.currentTimeMillis() - timeStart;
            running = false;
        }

    }

    /**
     * Reset the timer and the state of running.
     */
    @Override
    public void reset() {
        this.timeElapsed = 0;
        this.running = false;
    }

    /**
     * Returns the total duration measured in milliseconds.
     */
    @Override
    public long getTotalDurationMillis() {
        if (running) {
            return timeElapsed + System.currentTimeMillis() - timeStart;
        } else {
            return timeElapsed;
        }
    }

    /**
     * Returns the total duration in seconds.
     */
    @Override
    public long getTotalDurationSeconds() {
        return getTotalDurationMillis() / 1000;
    }
}
