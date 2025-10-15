package it.unibo.sampleapp.controller.core.impl;

import it.unibo.sampleapp.controller.core.api.GameEngine;

import javax.swing.JFrame;
import javax.swing.JPanel;

import it.unibo.sampleapp.controller.api.GameController;
import it.unibo.sampleapp.controller.api.HomeController;
import it.unibo.sampleapp.controller.api.LevelProcessController;
import it.unibo.sampleapp.controller.impl.GameControllerImpl;
import it.unibo.sampleapp.controller.impl.HomeControllerImpl;
import it.unibo.sampleapp.controller.impl.LevelProcessControllerImpl;
import it.unibo.sampleapp.model.api.LevelProcess;
import it.unibo.sampleapp.model.game.GameState;
import it.unibo.sampleapp.model.game.api.Game;
import it.unibo.sampleapp.model.game.impl.GameImpl;
import it.unibo.sampleapp.model.impl.LevelProcessImpl;
import it.unibo.sampleapp.model.level.api.LevelLoader;
import it.unibo.sampleapp.model.level.impl.LevelLoaderImpl;
import it.unibo.sampleapp.view.impl.HomePanel;
import it.unibo.sampleapp.view.impl.LevelProcessView;
import it.unibo.sampleapp.view.impl.LevelScreen;
import it.unibo.sampleapp.view.impl.LevelView;

/**
 * The gameEngine implementation.
 */
public class GameEngineImpl implements GameEngine {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 612;

    private GameState currentState;
    private final LevelProcess levelProcess;
    private final JFrame mainFrame;

    private int currentLevelNumber;

    /**
     * Builder for the GameEngine.
     */
    public GameEngineImpl() {
        this.currentState = GameState.HOME;
        this.levelProcess = new LevelProcessImpl(3);

        this.mainFrame = new JFrame("Opposite Elements");
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setSize(WIDTH, HEIGHT);
        this.mainFrame.setLocationRelativeTo(null);
        this.mainFrame.setVisible(true);
    }

    /**
     * Implementation of the loop of the game.
     */
    @Override
    public void gameLoop() {
        switch (currentState) {
            case HOME -> showHomePanel();
            case LEVEL_SELECTION -> showLevelSelection();
            case PLAYING -> startCurrentLevel();
            default -> throw new IllegalArgumentException("Unexpected value: " + currentState);
        }
    }

    /**
     * It changes the current state of the level.
     */
    @Override
    public void changeState(final GameState state) {
        this.currentState = state;
        gameLoop();
    }

    /**
     * Used to start the correct level.
     */
    @Override
    public void startLevel(final int levelNumber) {
        this.currentState = GameState.PLAYING;
        this.currentLevelNumber = levelNumber;
        gameLoop();
    }

    /**
     * Initialized the current level screen and its logic.
     */
    private void startCurrentLevel() {
        final LevelLoader levelLoader = new LevelLoaderImpl();
        final LevelScreen levelScreen = new LevelScreen(currentLevelNumber, levelLoader);

        final Game game = new GameImpl(levelScreen.getLevel());
        final LevelView levelView = levelScreen.getLevelView();

        final GameController gameController = new GameControllerImpl(game, levelView);

        showPanel(levelView);
        gameController.start();
    }

    /**
     * Initializes and displays the level selection screen.
     */
    private void showHomePanel() {
        final HomePanel homePanel = new HomePanel();
        homePanel.initHomePanel();
        final HomeController homeController = new HomeControllerImpl(this);

        homePanel.setStartButton(homeController::startGame);
        homePanel.setInstructionsButton(homeController::showInstructions);
        homePanel.setExitButton(homeController::exitGame);

        showPanel(homePanel);
    }

    /**
     * Initializes and displays the level selection screen.
     * Sets up callbacks for level selection and returning to the menu.
     */
    private void showLevelSelection() {
        final LevelProcessView levelSelView = new LevelProcessView(levelProcess);
        final LevelProcessController levelSelController = new LevelProcessControllerImpl(this, levelProcess);

        levelSelView.setSelectionLevel(levelSelController::levelSelected);
        levelSelView.setBackToMenu(levelSelController::backToMenu);

        showPanel(levelSelView);
    }

    /**
     * Displays the specified panel by clearing and updating the main frame's content.
     *
     * @param panel the JPanel to be shown in the main application window
     */
    private void showPanel(final JPanel panel) {
        mainFrame.getContentPane().removeAll();
        mainFrame.getContentPane().add(panel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }
}
