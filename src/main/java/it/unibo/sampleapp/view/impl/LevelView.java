package it.unibo.sampleapp.view.impl;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import javax.swing.JPanel;

import it.unibo.sampleapp.model.object.api.GameObject;
import it.unibo.sampleapp.model.object.api.Player;

/**
 * View for the level.
 */
public class LevelView extends JPanel {

    private static final Color BACKGROUND_COLOR = Color.BLACK;
    private static final Color PLAYER_COLOR = Color.RED;
    private static final Color OBJECT_COLOR = Color.GRAY;

    private static final long serialVersionUID = 1L;
    private transient List<Player> players;
    private transient List<GameObject> objects;

    /**
     * Default constructor.
     */
    public LevelView() {
        this(List.of(), List.of());
    }

    /**
     * Constructor.
     *
     * @param players contains the list of players
     * @param objects contains the list of game objects
     */
    public LevelView(final List<Player> players, final List<GameObject> objects) {
        this.players = List.copyOf(players);
        this.objects = List.copyOf(objects);
        initView();
    }

    /**
     * Initializes the view.
     */
    private void initView() {
        super.setBackground(BACKGROUND_COLOR);
    }

    /**
     * Restores transient fields after deserialization.
     *
     * @param in the input stream used for deserialization
     * @throws IOException if an I/O error occurs
     * @throws ClassNotFoundException if the class is not found
     */
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.players = List.of();
        this.objects = List.of();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        g.setColor(OBJECT_COLOR);
        for (final GameObject obj : objects) {
            g.fillRect(
                obj.getPosition().getX(),
                obj.getPosition().getY(),
                obj.getWidth(),
                obj.getHeight()
            );
        }
        g.setColor(PLAYER_COLOR);
        for (final Player p : players) {
            g.fillRect(
                p.getPosition().getX(),
                p.getPosition().getY(),
                p.getWidth(),
                p.getHeight()
            );
        }
    }
}
