package it.unibo.sampleapp.view.impl;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Class for the game's home screen.
 */
@SuppressFBWarnings("SE_BAD_FIELD")
public class HomePanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final int BOTTOM = 40;

    private static final int TITLE_WIDTH = 600;
    private static final int TITLE_HEIGHT = 150;
    private static final int TITLE_Y = 20;

    private static final int CHARACTER_WIDTH = 150;
    private static final int CHARACTER_HEIGHT = 150;
    private static final int FIRE_X = 60;
    private static final int WATER_X = 60;

    private static final int START_WIDTH = 200;
    private static final int START_HEIGHT = 80;
    private static final int INSTRUCTION_WIDTH = 185;
    private static final int INSTRUCTION_HEIGHT = 80;
    private static final int EXIT_WIDTH = 200;
    private static final int EXIT_HEIGHT = 80;

    private final ImageIcon backgroundImage;
    private final ImageIcon titleImage;
    private final ImageIcon waterGirl;
    private final ImageIcon fireBoy;

    private JPanel bottomPanel;

    private Runnable onStart;
    private Runnable onInstructions;
    private Runnable onExit;

    /**
     * Home Screen builder.
     */
    public HomePanel() {
        super(new BorderLayout());
        backgroundImage = loadImage("/img/PlayBackground.png");
        titleImage = loadImage("/img/title.png");
        waterGirl = loadImage("/img/WaterGirl.png");
        fireBoy = loadImage("/img/FireBoy.png");
    }

    /**
     * Initializes the panel layout and buttons.
     */
    public void initHomePanel() {
        final JButton startButton = createImageButton("/img/StartButton.png", START_WIDTH, START_HEIGHT);
        final JButton instructionsButton = createImageButton("/img/InstructionsButton.png", 
                                            INSTRUCTION_WIDTH, INSTRUCTION_HEIGHT);
        final JButton exitButton = createImageButton("/img/ExitButton.png", EXIT_WIDTH, EXIT_HEIGHT);

        startButton.addActionListener(e -> {
            if (onStart != null) {
                onStart.run();
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

        bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, BOTTOM, 0));

        startButton.setAlignmentX(CENTER_ALIGNMENT);
        instructionsButton.setAlignmentX(CENTER_ALIGNMENT);
        exitButton.setAlignmentX(CENTER_ALIGNMENT);

        bottomPanel.add(startButton);
        bottomPanel.add(instructionsButton);
        bottomPanel.add(exitButton);

        add(bottomPanel, BorderLayout.SOUTH);

        revalidate();
        repaint();
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
        g.drawImage(waterGirl.getImage(), getWidth() - CHARACTER_WIDTH 
                    - WATER_X, playerY, CHARACTER_WIDTH, CHARACTER_HEIGHT, this);
    }

    /**
     * Loads an image from the path.
     *
     * @param path image path
     * @return loaded image
     */
    private ImageIcon loadImage(final String path) {
        final var resource = HomePanel.class.getResource(path);
        if (resource == null) {
            throw new IllegalArgumentException("Resource not found: " + path);
        }
        return new ImageIcon(resource);
    }

    /**
     * Create a JButton that uses a resized image.
     *
     * @param path image path
     * @param width button width
     * @param height button height
     * @return JBUtton with image
     */
    private JButton createImageButton(final String path, final int width, final int height) {
        final ImageIcon image = new ImageIcon(HomePanel.class.getResource(path));
        final Image resized = image.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        final JButton btn = new JButton(new ImageIcon(resized));
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        return btn;
    }

    /**
     * Set the callback to be executed when "Start" is pressed.
     *
     * @param r the Runnable to be executed when the Start button is clicked
     */
    public void setStartButton(final Runnable r) {
        this.onStart = r;
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
     * @param r the Runnable to be executed when the Exit button is cliked
     */
    public void setExitButton(final Runnable r) {
        this.onExit = r;
    }

    /**
     * Refresh of the panel.
     */
    public void refresh() {
        if (bottomPanel != null) {
            bottomPanel.revalidate();
            bottomPanel.repaint();
        }
        revalidate();
        repaint();
    }
}
