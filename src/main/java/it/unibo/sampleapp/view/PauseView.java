package it.unibo.sampleapp.view;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * Class for the Pause Screen.
 * It extends JDialog to create a window that overlays the main game level window when paused.
 */
public class PauseView extends JDialog {

    private static final long serialVersionUID = 1L;

    private static final int DIALOG_WIDTH = 550;
    private static final int DIALOG_HEIGHT = 350;
    private static final int TITLE_WIDTH = 200;
    private static final int TITLE_HEIGHT = 75;
    private static final int TITLE_Y = 35;
    private static final int BUTTON_Y = 100;
    private static final int BUTTON_GAP = 70;

    private static final int BUTTON_WIDTH = 130;
    private static final int BUTTON_HEIGHT = 50;

    private final transient BufferedImage background;
    private final transient BufferedImage pauseTitleImage;
    private final transient BufferedImage continueImage;
    private final transient BufferedImage restartImage;
    private final transient BufferedImage homeImage;

    private transient Runnable backHome;
    private transient Runnable resumeLevel;
    private transient Runnable restartLevel;

    /**
     * Builder for the Pause Screen.
     *
     * @param parentFrame the parent JFrame to attach the dialog to
     */
    public PauseView(final JFrame parentFrame) {
        super(parentFrame, "Pause", true);

        this.background = loadImage("/img/Menu.png");
        this.pauseTitleImage = loadImage("/img/Pause.png");
        this.continueImage = loadImage("/img/ContinueButton.png");
        this.restartImage = loadImage("/img/RestartButton.png");
        this.homeImage = loadImage("/img/HomeButton.png");
    }

    /**
     * Initializes the Puase Screen.
     */
    public void initializePauseView() {
        setUndecorated(true);
        setResizable(false);
        setLayout(new BorderLayout());
        setBackground(new Color(0, 0, 0, 0));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        final JPanel imagPanel = new JPanel() {
            @Override
            protected void paintComponent(final Graphics g) {
                super.paintComponent(g);
                if (background != null) {
                    g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        imagPanel.setLayout(null);
        imagPanel.setOpaque(false);
        imagPanel.setPreferredSize(new Dimension(DIALOG_WIDTH, DIALOG_HEIGHT));

        if (pauseTitleImage != null) {
            final JLabel titleLabel = new JLabel(new ImageIcon(
            pauseTitleImage.getScaledInstance(TITLE_WIDTH, TITLE_HEIGHT, Image.SCALE_SMOOTH)));
            final int titleX = (DIALOG_WIDTH - TITLE_WIDTH) / 2;
            titleLabel.setBounds(titleX, TITLE_Y, TITLE_WIDTH, TITLE_HEIGHT);
            imagPanel.add(titleLabel);
        }

        final JButton continueButton = createButton(continueImage, "Continue", BUTTON_Y, e -> {
            dispose();
            runIfNotNull(resumeLevel);
        });

        final JButton restartButton = createButton(restartImage, "Restart", BUTTON_Y + BUTTON_GAP, e -> {
            dispose();
            runIfNotNull(restartLevel);
        });

        final JButton homeButton = createButton(homeImage, "Home", BUTTON_Y + BUTTON_GAP * 2, e -> {
            dispose();
            runIfNotNull(backHome);
        });

        imagPanel.add(continueButton);
        imagPanel.add(restartButton);
        imagPanel.add(homeButton);

        add(imagPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getParent());
    }

    /**
     * Creates a JButton with an image or fallback text and assings an action listener.
     *
     * @param img the image to use fot the button
     * @param fallbackString the string to show if image is null
     * @param y the vertical position of the button
     * @param actionListener the action to perfomr when the button is clicked
     * @return the JButton
     */
    private JButton createButton(final BufferedImage img, final String fallbackString, 
    final int y, final ActionListener actionListener) {
        final JButton btn = new JButton();
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);

        if (img != null) {
            btn.setIcon(new ImageIcon(img.getScaledInstance(BUTTON_WIDTH, BUTTON_HEIGHT, Image.SCALE_SMOOTH)));
        } else {
            btn.setText(fallbackString);
        }

        final int x = (DIALOG_WIDTH - BUTTON_WIDTH) / 2;
        btn.setBounds(x, y, BUTTON_WIDTH, BUTTON_HEIGHT);
        btn.addActionListener(actionListener);
        return btn;
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
     * Shows the pause window and sets the actions for each button of the screen.
     *
     * @param resumeLevelRun the action to continue the level
     * @param restartLevelRun the action to restart the level
     * @param backHomeRun the action to return back home
     */
    public void showPauseView(final Runnable resumeLevelRun, final Runnable restartLevelRun, final Runnable backHomeRun) {
        this.resumeLevel = resumeLevelRun;
        this.restartLevel = restartLevelRun;
        this.backHome = backHomeRun;
        setVisible(true);
    }
}
