package it.unibo.sampleapp.view.impl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import it.unibo.sampleapp.model.object.api.GameObject;
import it.unibo.sampleapp.model.object.api.Player;

/**
 * View for the level.
 */
public class LevelView extends JPanel {

    //private static final Color BACKGROUND_COLOR = Color.BLACK;
    private static final Color PLAYER_COLOR = Color.RED;
    private static final Color OBJECT_COLOR = Color.GRAY;

    private static final long serialVersionUID = 1L;
    private transient Image background;
    private transient Image fireBoyImg;
    private transient Image waterGirlImg;
    private transient Image platformImg;
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
        background = new ImageIcon(getClass().getClassLoader().getResource("img/BackgroundLevel.png")).getImage();
        fireBoyImg = new ImageIcon(getClass().getClassLoader() .getResource("img/FireBoy.png")).getImage();
        waterGirlImg = new ImageIcon(getClass().getClassLoader().getResource("img/WaterGirl.png")).getImage();
        platformImg = new ImageIcon(getClass().getClassLoader().getResource("img/Platform.png")).getImage();
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
        if (background != null) {
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }
        g.setColor(OBJECT_COLOR);
        for (final GameObject obj : objects) {
            if ("PlatformImpl".equals(obj.getClass().getSimpleName())) {
                g.drawImage(
                    platformImg,
                    (int) Math.round(obj.getPosition().getX()),
                    (int) Math.round(obj.getPosition().getY()),
                    obj.getWidth(),
                    obj.getHeight(),
                    this
                );
            } else {
                g.setColor(OBJECT_COLOR);
                g.fillRect(
                    (int) Math.round(obj.getPosition().getX()),
                    (int) Math.round(obj.getPosition().getY()),
                    obj.getWidth(),
                    obj.getHeight()
                );
            }
        }
        g.setColor(PLAYER_COLOR);
        for (final Player p : players) {
            final Image img = "FIRE".equals(p.getType().toString()) ? fireBoyImg : waterGirlImg;
            g.drawImage(
                img,
                (int) Math.round(p.getPosition().getX()),
                (int) Math.round(p.getPosition().getY()),
                p.getWidth(),
                p.getHeight(),
                this
            );
        }
    }
}
