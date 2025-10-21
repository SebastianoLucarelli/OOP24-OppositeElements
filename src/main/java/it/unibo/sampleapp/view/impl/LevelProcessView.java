package it.unibo.sampleapp.view.impl;

import it.unibo.sampleapp.model.api.LevelProcess;
import it.unibo.sampleapp.model.api.LevelProcess.LevelState;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Consumer;

/**
 * Class for the level selection screen.
 */
public class LevelProcessView extends JPanel {

    private static final long serialVersionUID = 1L;

    private static final int WIDTH_CENTERPANEL = 800;
    private static final int HEIGHT_CENTERPANEL = 350;
    private static final int WIDHT_BUTTON = 170;
    private static final int HEIGHT_BUTTON = 130;
    private static final int TOP_TITLE = 20;
    private static final int TOP_CENTERPANEL = 50;
    private static final int MENU_WIDTH = 130;
    private static final int MENU_HEIGHT = 70;
    private static final int BOTTOM = 30;
    private static final String LOCKED_PATH = "/img/LevelBlocked.png";
    private static final String UNLOCKED_PATH = "/img/LevelUnblocked.png";
    private static final String COMPLETE_PATH = "/img/LevelCompleted.png";

    private final transient LevelProcess levelProcess;
    private final int totLev;
    private JButton[] levelButtons;

    private transient Consumer<Integer> selectionOfLevel;
    private transient Runnable backToMenu;

    private final transient BufferedImage background;

    /**
     * Builder that displays the current progression of game levels.
     *
     * @param levelProcess the model interface that provides the current state of each level
     */
    public LevelProcessView(final LevelProcess levelProcess) {
        super(new BorderLayout());
        this.levelProcess = levelProcess;
        this.totLev = levelProcess.getTotalLevels();
        this.background = loadImage("/img/backgroundLevel.png");

        SwingUtilities.invokeLater(this::initLevelProcessView);
    }

    /**
     * Initializes the graphical components of the view.
     */
    private void initLevelProcessView() {
        final JLabel title = new JLabel(new ImageIcon(loadImage("/img/TitleLevel.png")));
        final JPanel top = new JPanel(new FlowLayout(FlowLayout.CENTER));
        top.setBorder(BorderFactory.createEmptyBorder(TOP_TITLE, 0, 0, 0));
        top.setOpaque(false);
        top.add(title);
        add(top, BorderLayout.NORTH);

        final JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 40));
        centerPanel.setOpaque(false);
        centerPanel.setPreferredSize(new Dimension(WIDTH_CENTERPANEL, HEIGHT_CENTERPANEL));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(TOP_CENTERPANEL, 0, 0, 0));
        add(centerPanel, BorderLayout.CENTER);

        final BufferedImage homeImg = loadImage("/img/HomeButton.png");
        final Image scaledHomeImg = homeImg.getScaledInstance(MENU_WIDTH, MENU_HEIGHT, Image.SCALE_SMOOTH);

        final JButton backMenuButton = new JButton(new ImageIcon(scaledHomeImg));
        backMenuButton.setPreferredSize(new Dimension(MENU_WIDTH, MENU_HEIGHT));
        backMenuButton.setBorderPainted(false);
        backMenuButton.setContentAreaFilled(false);
        backMenuButton.setFocusPainted(false);
        backMenuButton.setOpaque(false);
        backMenuButton.addActionListener(e -> {
            if (backToMenu != null) {
                backToMenu.run();
            }
        });

        final JPanel bottom = new JPanel(new BorderLayout());
        bottom.setOpaque(false);
        bottom.setBorder(BorderFactory.createEmptyBorder(0, BOTTOM, BOTTOM, 0));
        bottom.add(backMenuButton, BorderLayout.WEST);
        add(bottom, BorderLayout.SOUTH);

        levelButtons = new JButton[totLev];
        for (int i = 0; i < totLev; i++) {
            final int index = i;
            final JButton button = new JButton();
            button.setPreferredSize(new Dimension(WIDHT_BUTTON, HEIGHT_BUTTON));
            button.setFocusPainted(false);
            button.setContentAreaFilled(false);
            button.setBorderPainted(false);
            button.setOpaque(false);

            button.addActionListener(e -> {
                if (selectionOfLevel != null) {
                    selectionOfLevel.accept(index);
                }
            });

            centerPanel.add(button);
            levelButtons[i] = button;

            updateButtonGraphic(button, levelProcess.getLevelState(i));
        }
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
    }

    /**
     * Loads an image from the given resource path.
     *
     * @param path the path to the image resource
     * @return the loadede BufferedImage
     */
    private BufferedImage loadImage(final String path) {
        final InputStream is = LevelProcessView.class.getResourceAsStream(path);
        if (is == null) {
            return null;
        }
        try {
            return ImageIO.read(is);
        } catch (final IOException e) {
            return null;
        }
    }

    /**
     * Updates the visual appearance of a level button based on the current state.
     * If the image is not found, there is a fallback colored button.
     *
     * @param button the button to update
     * @param state the current level state of the level
     */
    private void updateButtonGraphic(final JButton button, final LevelState state) {
        final int width = (int) button.getPreferredSize().getWidth();
        final int height = (int) button.getPreferredSize().getHeight();

        final String stateLevelPath = (state == LevelState.UNLOCKED)
        ? UNLOCKED_PATH
        : (state == LevelState.COMPLETED)
        ? COMPLETE_PATH
        : LOCKED_PATH;

        final BufferedImage image = loadImage(stateLevelPath);
        if (image != null) {
            final Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(scaledImage));
            button.setText(null);
            button.setOpaque(false);
            button.setContentAreaFilled(false);
            button.setBorderPainted(false);
        } else {
            button.setIcon(null);
            String stringLev = "";
            switch (state) {
                case LOCKED: stringLev = "LOCK";
                    button.setBackground(Color.GRAY);
                    button.setForeground(Color.WHITE);
                    break;
                case UNLOCKED: stringLev = "TRY_LEVEL";
                    button.setBackground(Color.YELLOW);
                    button.setForeground(Color.WHITE);
                    break;
                case COMPLETED: stringLev = "DONE";
                    button.setBackground(Color.GREEN);
                    button.setForeground(Color.WHITE);
                    break;
            }
            button.setText(stringLev);
            button.setHorizontalTextPosition(SwingConstants.CENTER);
            button.setVerticalTextPosition(SwingConstants.CENTER);
            button.setOpaque(true);
            button.setContentAreaFilled(true);
            button.setBorderPainted(false);
        }
        button.revalidate();
        button.repaint();
    }

    /**
     * Forces a refresh of the view by re-reading the model state and updating all level buttons.
     */
    public void refresh() {
        if (!SwingUtilities.isEventDispatchThread()) {
            SwingUtilities.invokeLater(this::refresh);
            return;
        }
        for (int i = 0; i < levelButtons.length; i++) {
            updateButtonGraphic(levelButtons[i], levelProcess.getLevelState(i));
        }
    }

    /**
     * Sets the callback to be triggered when a level button is clicked.
     *
     * @param c a Consumer receiving the index of the selected level
     */
    public void setSelectionLevel(final Consumer<Integer> c) {
        this.selectionOfLevel = c;
    }

    /**
     * Setes the callback to be triggered when the "Menu" button is clicked.
     *
     * @param r a Runnable to execute when returning to the menu
     */
    public void setBackToMenu(final Runnable r) {
        this.backToMenu = r;
    }
}
