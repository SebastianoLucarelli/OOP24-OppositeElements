package it.unibo.sampleapp.view.impl;

import java.awt.BorderLayout;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JPanel;

import it.unibo.sampleapp.model.level.api.Level;
import it.unibo.sampleapp.model.level.api.LevelLoader;
import it.unibo.sampleapp.model.level.impl.LevelLoaderImpl;

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
        final String baseFile = "level" + levelNumber + ".txt";
        final String objectFile = "level" + levelNumber + "Objects.txt";

        if (loader instanceof LevelLoaderImpl impl) {
            this.level = impl.loadLevelWithObjects(baseFile, objectFile);
        } else {
            this.level = loader.loadLevel(baseFile); // fallback
        }

        this.levelView = new LevelView(level.getPlayers(), level.getGameObjects());
        initPanel();
    }

    /**
     * Initializes the layout of the level.
     */
    private void initPanel() {
        super.add(this.levelView, BorderLayout.CENTER);
    }

    /**
     * Custom deserialization to handle transient fields after restoring the object.
     *
     * @param in the ObjectInputStream used for deserialization
     * @throws IOException if an I/O error occurs while reading the stream
     * @throws ClassNotFoundException if a class required for deserialization cannot be found
     */
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.level = null;
        this.levelView = null;
    }

    /**
     * @return the level instance
     */
    public Level getLevel() {
        return this.level;
    }

    /**
     * @return a new LevelView instance based on the current level
     */
    public LevelView getLevelView() {
        return new LevelView(this.level.getPlayers(), this.level.getGameObjects());
    }
}
