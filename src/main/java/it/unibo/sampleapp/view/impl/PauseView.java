package it.unibo.sampleapp.view.impl;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * Class for the Pause Screen.
 */
public class PauseView extends JPanel {

    private static final long serialVersionUID = 1L;

    private static final int TOP_TITLE = 40;
    private static final int CENTER_WIDTH = 800;
    private static final int CENTER_HEIGHT = 300;
    private static final int BUTTON_WIDTH = 150;
    private static final int BUTTON_HEIGHT = 80;

    private final transient BufferedImage background;

    private transient Runnable backHome;
    private transient Runnable resumeLevel;
    private transient Runnable restartLevel;

    /**
     * Builder for the Pause screen.
     */
    public PauseView() {
        super(new BorderLayout());
        this.background = loadImage("/img/Menu.png");

        SwingUtilities.invokeLater(this::initPauseView);
    }

    /**
     * Initializes the graphical components of the view.
     */
    private void initPauseView() {
        final BufferedImage pauseImg = loadImage("/img/Pause.png");
        if (pauseImg != null) {
            final int scaledWidth = 200;
            final int scaledHeight = (int) ((double) pauseImg.getHeight() / pauseImg.getWidth() * scaledWidth);
            final Image scaledPauseImg = pauseImg.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
            final JLabel title = new JLabel(new ImageIcon(scaledPauseImg));
            final JPanel top = new JPanel(new FlowLayout(FlowLayout.CENTER));
            top.setBorder(BorderFactory.createEmptyBorder(TOP_TITLE, 0, 0, 0));
            top.setOpaque(false);
            top.add(title);
            add(top, BorderLayout.NORTH);
        }

        final JPanel center = new JPanel(new GridLayout(2, 1, 0, 30));
        center.setOpaque(false);
        center.setPreferredSize(new Dimension(CENTER_WIDTH, CENTER_HEIGHT));

        final JPanel firstRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 60, 0));
        firstRow.setOpaque(false);
        firstRow.add(createButton("/img/HomeButton.png", () -> runIfNotNull(backHome)));
        firstRow.add(createButton("/img/RestartButton.png", () -> runIfNotNull(restartLevel)));

        final JPanel secondRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        secondRow.setOpaque(false);
        secondRow.add(createButton("/img/ContinueButton.png", () -> runIfNotNull(resumeLevel)));

        center.add(firstRow);
        center.add(secondRow);
        add(center, BorderLayout.CENTER);
    }

    /**
     * Creates the button fot the pause screen.
     *
     * @param imgPath the path of the image resource
     * @param r a Runnable to execute when the button is pressed
     * @return the right button
     */
    private JButton createButton(final String imgPath, final Runnable r) {
        final BufferedImage img = loadImage(imgPath);
        final JButton button = new JButton();
        button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));

        if (img != null) {
            final Image scaled = img.getScaledInstance(BUTTON_WIDTH, BUTTON_HEIGHT, Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(scaled));
        } else {
            button.setText(imgPath);
            button.setBackground(Color.gray);
            button.setOpaque(false);
        }

        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        button.addActionListener(e -> runIfNotNull(r));
        return button;
    }

    /**
     * Executes the given runnable if it is not null.
     *
     * @param r the runnable to execute
     */
    private void runIfNotNull(final Runnable r) {
        if (r != null) {
            r.run();
        }
    }

    /**
     * Loads an image from the given resource path.
     *
     * @param path the path to the image resource
     * @return the loaded bufferedImage
     */
    private BufferedImage loadImage(final String path) {
        final InputStream is = PauseView.class.getResourceAsStream(path);
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
     * Sets the callback to be triggered when the Home button is clicked.
     *
     * @param back a runnable to execute when returning home
     */
    public void backToHome(final Runnable back) {
        this.backHome = back;
    }

    /**
     * Sets the callback to be triggered when the restart button is clicked.
     *
     * @param restart a runnable to execute when we want to restart a specific level
     */
    public void restartLevel(final Runnable restart) {
        this.restartLevel = restart;
    }

    /**
     * Sets the callback to be triggered when the Continue button is clicked.
     *
     * @param resume a runnable to esecute when we want to continue the level
     */
    public void resumeLevel(final Runnable resume) {
        this.resumeLevel = resume;
    }
}
