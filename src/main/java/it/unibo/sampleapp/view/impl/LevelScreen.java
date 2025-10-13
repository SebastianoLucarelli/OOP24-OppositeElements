package it.unibo.sampleapp.view.impl;

import java.awt.BorderLayout;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JPanel;
import it.unibo.sampleapp.model.level.api.Level;
import it.unibo.sampleapp.model.level.api.LevelLoader;

/**
 * Panel that represents a specific level of the game.
 * It creates the LevelView using static data (positions read from file).
 */
public final class LevelScreen extends JPanel {

    private static final long serialVersionUID = 1L;
    private transient Level level;
    private transient LevelView levelView;

    /**
     * Builds the LevelScreen for a specific level number.
     *
     * @param levelNumber the index of the level to load (e.g., 1)
     * @param loader the LevelLoader used to read the file data
     */
    public LevelScreen(final int levelNumber, final LevelLoader loader) {
        super(new BorderLayout());
        final String filename = "level" + levelNumber + ".txt";
        this.level = loader.loadLevel(filename);
        this.levelView = new LevelView(level.getPlayers(), level.getGameObjects());
        initPanel();
    }

    /**
     * Initializes the layout of the level.
     * Marked final to avoid PMD "overridable method in constructor" warning.
     */
    private void initPanel() {
        super.add(this.levelView, BorderLayout.CENTER);
    }

    /**
     * Restores transient fields after deserialization.
     *
     * @param in the input stram
     * @throws IOException if an I/O error occurs
     * @throws ClassNotFoundException if the class is not found
     */
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.level = null;
        this.levelView = null;
    }

    /**
     * @return the Level instance associated with this screen.
     */
    public Level getLevel() {
        return this.level;
    }

    /**
     * @return the LevelView instance.
     */
    public LevelView getLevelView() {
        return new LevelView(this.level.getPlayers(), this.level.getGameObjects());
    }
}
