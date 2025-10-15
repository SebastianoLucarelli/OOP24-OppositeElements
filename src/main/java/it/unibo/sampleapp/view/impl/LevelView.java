package it.unibo.sampleapp.view.impl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import it.unibo.sampleapp.model.object.api.Door;
import it.unibo.sampleapp.model.object.api.GameObject;
import it.unibo.sampleapp.model.object.api.Gem;
import it.unibo.sampleapp.model.object.api.Hazard;
import it.unibo.sampleapp.model.object.api.Player;

/**
 * View for the level.
 */
public class LevelView extends JPanel {

    //private static final Color BACKGROUND_COLOR = Color.BLACK;
    private static final Color PLAYER_COLOR = Color.RED;
    private static final Color OBJECT_COLOR = Color.GRAY;
    private static final int PAUSE_DIMENSION = 50;

    private static final long serialVersionUID = 1L;
    private transient Image background;
    private transient Image fireBoyImg;
    private transient Image waterGirlImg;
    private transient Image platformImg;
    private transient Image movablePlatformImg;
    private transient Image buttonImg;
    private transient Image fireDoorImg;
    private transient Image waterDoorImg;
    private transient Image acidHazardImg;
    private transient Image fireHazardImg;
    private transient Image waterHazardImg;
    private transient Image gemFireImg;
    private transient Image gemWaterImg;
    private transient Image pauseImg;
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
        SwingUtilities.invokeLater(this::addPauseButton);
    }

    /**
     * Initializes the view.
     */
    private void initView() {
        background = new ImageIcon(getClass().getClassLoader().getResource("img/BackgroundLevel.png")).getImage();
        fireBoyImg = new ImageIcon(getClass().getClassLoader() .getResource("img/FireBoy.png")).getImage();
        waterGirlImg = new ImageIcon(getClass().getClassLoader().getResource("img/WaterGirl.png")).getImage();
        platformImg = new ImageIcon(getClass().getClassLoader().getResource("img/Platform.png")).getImage();
        movablePlatformImg = new ImageIcon(getClass().getClassLoader().getResource("img/MovablePlatform.png")).getImage();
        buttonImg = new ImageIcon(getClass().getClassLoader().getResource("img/Button.png")).getImage();
        fireDoorImg = new ImageIcon(getClass().getClassLoader().getResource("img/FireDoor.png")).getImage();
        waterDoorImg = new ImageIcon(getClass().getClassLoader().getResource("img/WaterDoor.png")).getImage();
        acidHazardImg = new ImageIcon(getClass().getClassLoader().getResource("img/AcidHazard.png")).getImage();
        fireHazardImg = new ImageIcon(getClass().getClassLoader().getResource("img/FireHazard.png")).getImage();
        waterHazardImg = new ImageIcon(getClass().getClassLoader().getResource("img/WaterHazard.png")).getImage();
        gemFireImg = new ImageIcon(getClass().getClassLoader().getResource("img/FireGem.png")).getImage();
        gemWaterImg = new ImageIcon(getClass().getClassLoader().getResource("img/WaterGem.png")).getImage();
        pauseImg = new ImageIcon(getClass().getClassLoader().getResource("img/Home.png")).getImage();
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
        final String className = obj.getClass().getSimpleName();
            switch (className) {
                case "PlatformImpl" -> g.drawImage(
                    platformImg,
                    (int) Math.round(obj.getPosition().getX()),
                    (int) Math.round(obj.getPosition().getY()),
                    obj.getWidth(),
                    obj.getHeight(),
                    this
                );
                case "MovablePlatformImpl" -> g.drawImage(
                    movablePlatformImg,
                    (int) Math.round(obj.getPosition().getX()),
                    (int) Math.round(obj.getPosition().getY()),
                    obj.getWidth(),
                    obj.getHeight(),
                    this
                );
                case "ButtonImpl" -> g.drawImage(
                    buttonImg,
                    (int) Math.round(obj.getPosition().getX()),
                    (int) Math.round(obj.getPosition().getY()),
                    obj.getWidth(),
                    obj.getHeight(),
                    this
                );
                case "DoorImpl" -> {
                    final Door door = (Door) obj;
                    final Image img = switch (door.getType()) {
                        case FIRE -> fireDoorImg;
                        case WATER -> waterDoorImg;
                    };
                    g.drawImage(
                        img,
                        (int) Math.round(door.getPosition().getX()),
                        (int) Math.round(door.getPosition().getY()),
                        door.getWidth(),
                        door.getHeight(),
                        this
                    );
                }
                case "HazardImpl" -> {
                    final Hazard hazard = (Hazard) obj;
                    final Image img = switch (hazard.getType()) {
                        case ACID -> acidHazardImg;
                        case FIRE -> fireHazardImg;
                        case WATER -> waterHazardImg;
                    };
                    g.drawImage(
                        img,
                        (int) Math.round(hazard.getPosition().getX()),
                        (int) Math.round(hazard.getPosition().getY()),
                        hazard.getWidth(),
                        hazard.getHeight(),
                        this
                    );
                }
                case "GemImpl" -> {
                    final Gem gem = (Gem) obj;
                    final Image img = switch (gem.getType()) {
                        case FIRE -> gemFireImg;
                        case WATER -> gemWaterImg;
                    };
                    g.drawImage(
                        img,
                        (int) Math.round(gem.getPosition().getX()),
                        (int) Math.round(gem.getPosition().getY()),
                        gem.getWidth(),
                        gem.getHeight(),
                        this
                    );
                }
                default -> g.fillRect(
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

    private void addPauseButton() {
        setLayout(null);
        final Image scaled = pauseImg.getScaledInstance(PAUSE_DIMENSION, PAUSE_DIMENSION, Image.SCALE_SMOOTH);
        final JButton pauseButton = new JButton(new ImageIcon(scaled));
        pauseButton.setBorderPainted(false);
        pauseButton.setContentAreaFilled(false);
        pauseButton.setFocusPainted(false);
        pauseButton.setBounds(0, 0, PAUSE_DIMENSION, PAUSE_DIMENSION); 
        pauseButton.addActionListener(e -> {
            //we'll go to implement that tomorrow
        });
        add(pauseButton);
    }

}
