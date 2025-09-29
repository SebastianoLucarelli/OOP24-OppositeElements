package it.unibo.sampleapp.view.impl;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Class for the game's home screen.
 */
@SuppressFBWarnings("SE_BAD_FIELD")
public class HomePanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private static final int GRID_ROWS = 6;
    private static final int GRID_COLS = 1;

    private static final int START_WIDTH = 200;
    private static final int START_HEIGHT = 80;
    private static final int INSTRUCTION_WIDTH = 185;
    private static final int INSTRUCTION_HEIGHT = 80;
    private static final int EXIT_WIDTH = 200;
    private static final int EXIT_HEIGHT = 80;

    private static final int TITLE_WIDTH = 600;
    private static final int TITLE_HEIGHT = 150;
    private static final int CHARACTER_WIDTH = 150;
    private static final int CHARACTER_HEIGHT = 150;

    private static final int TITLE_Y = 20;
    private static final int FIRE_X = 60;
    private static final int WATER_X = 60;
    private static final int BOTTOM = 40;

    private final ImageIcon backgroundImage;
    private final ImageIcon titleImage;
    private final ImageIcon waterGirl;
    private final ImageIcon fireBoy;

    private final JButton startButton;
    private final JButton instructionsButton;
    private final JButton exitButton;

    private Runnable onPlay;
    private Runnable onInstructions;
    private Runnable onExit;

    /**
     * Home Screen builder.
     */
    public HomePanel() {
        backgroundImage = loadImage("/img/PlayBackground.png");
        titleImage = loadImage("/img/title.png");
        waterGirl = loadImage("/img/WaterGirl.png");
        fireBoy = loadImage("/img/FireBoy.png");

        startButton = createImageButton("/img/StartButton.png", START_WIDTH, START_HEIGHT);
        instructionsButton = createImageButton("/img/InstructionsButton.png", INSTRUCTION_WIDTH, INSTRUCTION_HEIGHT);
        exitButton = createImageButton("/img/ExitButton.png", EXIT_WIDTH, EXIT_HEIGHT);

        startButton.addActionListener(e -> {
            if (onPlay != null) {
                onPlay.run();
            }
        });

        instructionsButton.addActionListener(e -> {
            if (onInstructions != null) {
                onInstructions.run();
            }
        });

        exitButton.addActionListener(e -> {
            if (onExit != null) {
                onExit.run();
            }
        });

       SwingUtilities.invokeLater(this::initPanel);
    }

    /**
     * Initializes the UI layout.
     */
    private void initPanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLayout(new GridLayout(GRID_ROWS, GRID_COLS));

        add(new JPanel());
        add(new JPanel());
        add(startButton);
        add(instructionsButton);
        add(exitButton);
        add(new JPanel());
    }

    /**
     *  Loads an image from the path.
     *
     * @param path image path
     * @return loaded image
     */
    private ImageIcon loadImage(final String path) {
        final var resource = HomePanel.class.getResource(path);
        if (resource == null) {
            throw new IllegalArgumentException("Resource not found");
        }
        return new ImageIcon(resource);
    }

    /**
     * Create a JButton that uses a resized image.
     *
     * @param path image path
     * @param width button width
     * @param height button height
     * @return JButton with image
     */
    private JButton createImageButton(final String path, final int width, final int height) {
        final ImageIcon image = new ImageIcon(HomePanel.class.getResource(path));
        final Image resize = image.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        final JButton button = new JButton(new ImageIcon(resize));
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        return button;
    }

    /**
     * Draw the graphic elements on the panel.
     *
     * @param g Graphics context
     */
    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);

        g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);

        final int titleX = (getWidth() - TITLE_WIDTH) / 2;
        g.drawImage(titleImage.getImage(), titleX, TITLE_Y, TITLE_WIDTH, TITLE_HEIGHT, this);

        final int playerY = getHeight() - CHARACTER_HEIGHT - BOTTOM;
        g.drawImage(fireBoy.getImage(), FIRE_X, playerY, CHARACTER_WIDTH, CHARACTER_HEIGHT, this);

        final int waterX = getWidth() - CHARACTER_WIDTH - WATER_X;
        g.drawImage(waterGirl.getImage(), waterX, playerY, CHARACTER_WIDTH, CHARACTER_HEIGHT, this);
    }

    /**
     * Set the callback to be executed when "Start" is pressed.
     *
     * @param r the Runnable to be executed when the Start button is clicked
     */
    public void setPlayButton(final Runnable r) {
        this.onPlay = r;
    }

    /**
     * Set the callback to be executed when "Instructions" is pressed.
     *
     * @param r the Runnable to be executed when the Instruction button is clicked
     */
    public void setInstructionsButton(final Runnable r) {
        this.onInstructions = r;
    }

    /**
     * Set the callback to be executed when "Exit" is pressed.
     *
     * @param r the Runnable to be executed when the Exit button is clicked
     */
    public void setExitButton(final Runnable r) {
        this.onExit = r;
    }
}
