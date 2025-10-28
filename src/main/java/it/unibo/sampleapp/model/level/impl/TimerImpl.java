package it.unibo.sampleapp.model.level.impl;

import it.unibo.sampleapp.model.level.api.Timer;

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
     * {@inheritDoc}
     */
    @Override
    public void start() {
        if (!running) {
            this.timeStart = System.currentTimeMillis();
            this.running = true;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop() {
        if (running) {
            timeElapsed += System.currentTimeMillis() - timeStart;
            running = false;
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        this.timeElapsed = 0;
        this.running = false;
    }

    /**
     * {@inheritDoc}
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
     * {@inheritDoc}
     */
    @Override
    public long getTotalDurationSeconds() {
        return getTotalDurationMillis() / 1000;
    }
}
