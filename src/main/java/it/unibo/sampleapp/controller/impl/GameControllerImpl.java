package it.unibo.sampleapp.controller.impl;

import it.unibo.sampleapp.controller.api.GameController;
import it.unibo.sampleapp.model.game.GameState;
import it.unibo.sampleapp.model.game.api.Game;
import it.unibo.sampleapp.view.impl.LevelView;

/**
 * Implements the game controller and manages the level loop.
 */
public class GameControllerImpl implements GameController, Runnable{

    private static final int FPS = 60;
    private static final double FRAME_TIME = 1.0 / FPS;

    private final Game game;
    private final LevelView levelView;

    private Thread levelLoopThread;
    private boolean running;

    /**
     * Builds the controller with game and view.
     *
     * @param game the game
     * @param levelView the view of the level
     */
    public GameControllerImpl(final Game game, final LevelView levelView) {
        this.game = game;
        this.levelView = levelView;
    }

    /**
     * Starts the level loop in a new thread.
     */
    @Override
    public void start() {
        if (running) {
            return;
        }
        running = true;
        levelLoopThread = new Thread(this);
        levelLoopThread.start();
    }

    /**
     * Stops the level loop.
     */
    @Override
    public void stop() {
        running = false;
    }

    /**
     * Pauses the current level if the game State is PLAYING.
     */
    @Override
    public void pauseLevelGame() {
        if (game.getCurrentGameState() == GameState.PLAYING) {
            game.pauseLevel();
        }
    }

    /**
     * Resumes the current level if it is in pause.
     */
    @Override
    public void resumeLevelGame() {
        if (game.getCurrentGameState() == GameState.PAUSE) {
            game.resumeLevel();
        }
    }

    /**
     * Main level loop, it updates game and view.
     */
    @Override
    public void run() {
        long lastTime = System.nanoTime();

        while (running) {
            long now = System.nanoTime();
            double deltaTime = (now - lastTime) / 1_000_000_000.0;
            lastTime = now;

            if (game.getCurrentGameState() == GameState.PLAYING) {
                game.update(deltaTime);
                levelView.repaint();
            }

            if (game.getCurrentGameState() == GameState.LEVEL_COMPLETED || game.getCurrentGameState() == GameState.GAME_OVER) {
                running = false;
            }

            try {
                Thread.sleep((long) (FRAME_TIME * 1000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
