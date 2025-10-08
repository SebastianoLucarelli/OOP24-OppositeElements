package it.unibo.sampleapp.view.impl;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import javax.swing.JPanel;
import it.unibo.sampleapp.model.object.api.Player;

/**
 * View for the level.
 */
public class LevelView extends JPanel {

    private static final long serialVersionUID = 1L;
    private transient List<Player> players;

    /**
     * Default constructor.
     */
    public LevelView() {
        this.players = List.of();
        initView();
    }

    /**
     * Constructor.
     *
     * @param players contains the list of players
     */
    public LevelView(final List<Player> players) {
        this.players = List.copyOf(players);
        initView();
    }

    /**
     * Initializes the view.
     */
    private void initView() {
        super.setBackground(Color.BLACK);
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
        this.players = List.of(); // ripristino di sicurezza
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        for (final Player p : players) {
            g.setColor(Color.RED);
            g.fillRect(
                p.getPosition().getX(),
                p.getPosition().getY(),
                p.getWidth(),
                p.getHeight()
            );
        }
    }
}
