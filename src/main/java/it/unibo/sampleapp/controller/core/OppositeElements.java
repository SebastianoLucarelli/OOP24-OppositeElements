package it.unibo.sampleapp.controller.core;

import it.unibo.sampleapp.controller.core.api.GameEngine;
import it.unibo.sampleapp.controller.core.impl.GameEngineImpl;

/**
 * Main class.
 */
public final class OppositeElements {

    private OppositeElements() {
    }

    /**
     * Main method.
     *
     * @param args arguments of the main
     */
    public static void main(final String[] args) {
        final GameEngine engine = new GameEngineImpl();
        engine.gameLoop();
    }
}
