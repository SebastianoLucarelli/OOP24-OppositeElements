package it.unibo.sampleapp.controller.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.sampleapp.controller.api.GameController;
import it.unibo.sampleapp.controller.core.api.GameEngine;
import it.unibo.sampleapp.model.api.LevelProcess;
import it.unibo.sampleapp.model.game.GameState;
import it.unibo.sampleapp.model.game.api.Game;
import it.unibo.sampleapp.view.impl.LevelView;

/**
 * Implements the game controller and manages the level loop.
 */
public class GameControllerImpl implements GameController, Runnable {

    private static final int FPS = 60;
    private static final double FRAME_TIME = 1.0 / FPS;
    private static final double NANOSECONDS_IN_SECONDS = 1_000_000_000.0;

    private final Game game;
    private final LevelView levelView;
    private final GameEngine gameEngine;
    private final LevelProcess levelProcess;

    private volatile boolean running;

    private final PlayerControllerImpl playerController;

    /**
     * Builds the controller with game and view.
     *
     * @param game the game
     * @param levelView the view of the level
     * @param playerController the controller of players
     * @param gameEngine the game Engine
     */
    @SuppressFBWarnings(value = "EI2", justification = "Controller must hold references to view and model")
    public GameControllerImpl(final Game game, final LevelView levelView, final PlayerControllerImpl playerController,
    final GameEngine gameEngine, final LevelProcess levelProcess) {
        this.game = game;
        this.levelView = levelView;
        this.playerController = playerController;
        this.gameEngine = gameEngine;
        this.levelProcess = levelProcess;
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
        final Thread levelLoopThread = new Thread(this);
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
     * Sets focus on the current level View.
     */
    @Override
    public void refocusLevelView() {
        levelView.requestFocusInWindow();
    }

    /**
     * Main level loop, it updates game and view.
     */
    @Override
    public void run() {
        long lastTime = System.nanoTime();

        while (running) {
            final long now = System.nanoTime();
            final double deltaTime = (now - lastTime) / NANOSECONDS_IN_SECONDS;
            lastTime = now;

            if (game.getCurrentGameState() == GameState.PLAYING) {
                playerController.inputProcess();
                game.update(deltaTime);
                levelView.updateObjects(game.getPlayers(), game.getGameObjects());
                levelView.repaint();
            }

            if (game.getCurrentGameState() == GameState.LEVEL_COMPLETED) {
                running = false;
                levelProcess.finishedLevel(gameEngine.getCurrentLevelNumber());
                gameEngine.changeState(GameState.LEVEL_COMPLETED);
            }

            if (game.getCurrentGameState() == GameState.GAME_OVER) {
                running = false;
                gameEngine.changeState(GameState.GAME_OVER);
            }

            try {
                Thread.sleep((long) (FRAME_TIME * 1000));
            } catch (final InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
