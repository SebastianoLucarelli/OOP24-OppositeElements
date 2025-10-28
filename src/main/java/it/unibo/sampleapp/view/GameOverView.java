package it.unibo.sampleapp.view;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

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
 * class for the Game Over screen.
 */
public class GameOverView extends JDialog {

    private static final long serialVersionUID = 1L;

    private static final int DIALOG_WIDTH = 550;
    private static final int DIALOG_HEIGHT = 350;
    private static final int TITLE_WIDTH = 260;
    private static final int TITLE_HEIGHT = 80;
    private static final int TITLE_Y = 35;
    private static final int BUTTON_Y = 100;
    private static final int BUTTON_GAP = 70;
    private static final int BUTTON_WIDTH = 130;
    private static final int BUTTON_HEIGHT = 50;

    private final transient BufferedImage background;
    private final transient BufferedImage gameOverTitleImage;
    private final transient BufferedImage restartImage;
    private final transient BufferedImage homeImage;

    private transient Runnable backHome;
    private transient Runnable restartLevel;

    /**
     * constructor for the Game Over screen.
     *
     * @param parentFrame the parent JFrame to attach the dialog to
     */
    public GameOverView(final JFrame parentFrame) {
        super(parentFrame, "Game Over", true);
        this.background = loadImage("/img/Menu.png");
        this.gameOverTitleImage = loadImage("/img/gameover.png");
        this.restartImage = loadImage("/img/RestartButton.png");
        this.homeImage = loadImage("/img/backButton.png");
    }

    /**
     * Loads the image from the given path.
     *
     * @param img the path of the image
     * @return the loaded BufferedImage or null if loading fails
     */
    private BufferedImage loadImage(final String img) {
        final InputStream is = PauseView.class.getResourceAsStream(img);
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
     * Initializes the Game Over view components. 
     */
    public void initializeGameOverView() {
        setUndecorated(true);
        setResizable(false);
        setLayout(new BorderLayout());
        setBackground(new Color(0, 0, 0, 0));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        final JPanel imagePanel = new JPanel() {
            @Override
            protected void paintComponent(final Graphics g) {
                super.paintComponent(g);
                if (background != null) {
                    g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        imagePanel.setLayout(null);
        imagePanel.setOpaque(false);
        imagePanel.setPreferredSize(new Dimension(DIALOG_WIDTH, DIALOG_HEIGHT));

        if (gameOverTitleImage != null) {
            final JLabel titleLabel = new JLabel(new ImageIcon(
                gameOverTitleImage.getScaledInstance(TITLE_WIDTH, TITLE_HEIGHT, Image.SCALE_SMOOTH)));
            final int titleX = (DIALOG_WIDTH - TITLE_WIDTH) / 2;
            titleLabel.setBounds(titleX, TITLE_Y, TITLE_WIDTH, TITLE_HEIGHT);
            imagePanel.add(titleLabel);
        }

        final JButton restartButton = createButton(restartImage, "Restart", BUTTON_Y + BUTTON_GAP, e -> {
            dispose();
            runIfNotNull(restartLevel);
        });

        final JButton homeButton = createButton(homeImage, "Home", BUTTON_Y + BUTTON_GAP * 2, e -> {
            dispose();
            runIfNotNull(backHome);
        });

        imagePanel.add(restartButton);
        imagePanel.add(homeButton);
        add(imagePanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getParent());
    }

    /**
     * Shows the Game Over view. 
     *
     * @param backHomeRun the action to perform to go back home
     * @param restartLevelRun the action to perform to restart the level
     */
    public void showGameOverView(final Runnable backHomeRun, final Runnable restartLevelRun) {
        this.backHome = backHomeRun;
        this.restartLevel = restartLevelRun;
        setVisible(true);
    }

    /**
     * Creates a JButton with the given image.
     *
     * @param img the image for the button
     * @param fallbackString the fallback text loaded if the image is null
     * @param y the vertical position of the button
     * @param actionListener the action to perform when the button is clicked
     * @return the created JButton
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

}
