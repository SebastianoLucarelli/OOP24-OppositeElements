package it.unibo.sampleapp.view;

import it.unibo.sampleapp.model.game.api.Game;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Dialog shown when a level is completed.
 */
public class LevelCompleteDialog extends JDialog {

    private static final long serialVersionUID = 1L;

    private static final int DIALOG_WIDTH = 550;
    private static final int DIALOG_HEIGHT = 350;

    private static final int TARGET_START_X = 100;
    private static final int TARGET_START_Y = 60;
    private static final int PADDING = 60;
    private static final int SIZE_IMAGE = 55;
    private static final int TARGET_RESULT_X = 160;
    private static final int IMPLY_X = 220;
    private static final int GEMS_X = 300;
    private static final int IMPLY_Y = 100;

    private static final int BUTTON_WIDTH = 150;
    private static final int BUTTON_HEIGHT = 50;
    private static final int BUTTON_Y = 260;

    private final boolean allGemsCollected;
    private final boolean timerObjectiveReached;

    private final transient BufferedImage backgroundImage;
    private final transient BufferedImage continueButton;
    private final transient BufferedImage diamondsImage;
    private final transient BufferedImage failedGoalImage;
    private final transient BufferedImage fireXwaterImage;
    private final transient BufferedImage implyImage;
    private final transient BufferedImage successGoalImage;
    private final transient BufferedImage timerImage;
    private final transient BufferedImage gemImage;

    /**
     * Creates a new dialog that appears when the level is completed.
     *
     * @param parentFrame the parent frame above which the JDialo appears
     * @param game the current game instance
     */
    public LevelCompleteDialog(final JFrame parentFrame, final Game game) {
        super(parentFrame, "Level Completed", true);
        this.allGemsCollected = game.areAllGemsCollected();
        this.timerObjectiveReached = game.isTimerObjectiveReached();

        this.continueButton = loadImage("/img/ContinueButton.png");
        this.backgroundImage = loadImage("/img/Menu.png");
        this.diamondsImage = loadImage("/img/Diamonds.png");
        this.failedGoalImage = loadImage("/img/FailedGoal.png");
        this.fireXwaterImage = loadImage("/img/FirexWater.png");
        this.implyImage = loadImage("/img/Imply.png");
        this.successGoalImage = loadImage("/img/SuccessGoal.png"); 
        this.timerImage = loadImage("/img/Timer.png");
        this.gemImage = loadImage("/img/LevelCompleted.png");
    }

    /**
     * Create and initialize the dialog.
     *
     * @param parentFrame the parent frame
     * @param game the current game
     * @return the complete level dialog created
     */
    public static LevelCompleteDialog create(final JFrame parentFrame, final Game game) {
        final LevelCompleteDialog dialog = new LevelCompleteDialog(parentFrame, game);
        dialog.initDialog();
        return dialog;
    }

    /**
     * Initialisez the dialog.
     */
    private void initDialog() {
        setUndecorated(true);
        setResizable(false);
        setLayout(new BorderLayout());
        setBackground(new Color(0, 0, 0, 0));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        final JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(final Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }

                if (fireXwaterImage != null) {
                    g.drawImage(fireXwaterImage, TARGET_START_X, TARGET_START_Y, SIZE_IMAGE, SIZE_IMAGE, this);
                }

                if (successGoalImage != null) {
                    g.drawImage(successGoalImage, TARGET_RESULT_X, TARGET_START_Y, SIZE_IMAGE, SIZE_IMAGE, this);
                }

                if (diamondsImage != null) {
                    g.drawImage(diamondsImage, TARGET_START_X, TARGET_START_Y + PADDING, SIZE_IMAGE, SIZE_IMAGE, this);
                }

                if (allGemsCollected) {
                    g.drawImage(successGoalImage, TARGET_RESULT_X, TARGET_START_Y + PADDING, SIZE_IMAGE, SIZE_IMAGE, this);
                } else {
                    g.drawImage(failedGoalImage, TARGET_RESULT_X, TARGET_START_Y + PADDING, SIZE_IMAGE, SIZE_IMAGE, this);
                }

                if (timerImage != null) {
                    g.drawImage(timerImage, TARGET_START_X, TARGET_START_Y + PADDING * 2, SIZE_IMAGE, SIZE_IMAGE, this);
                }

                if (timerObjectiveReached) {
                     g.drawImage(successGoalImage, TARGET_RESULT_X, TARGET_START_Y + PADDING * 2, SIZE_IMAGE, SIZE_IMAGE, this);
                } else {
                     g.drawImage(failedGoalImage, TARGET_RESULT_X, TARGET_START_Y + PADDING * 2, SIZE_IMAGE, SIZE_IMAGE, this);
                }

                if (implyImage != null) {
                    g.drawImage(implyImage, IMPLY_X, IMPLY_Y, SIZE_IMAGE * 2, SIZE_IMAGE * 2, this);
                }

                if (gemImage != null) {
                    g.drawImage(gemImage, GEMS_X, IMPLY_Y, SIZE_IMAGE * 3, SIZE_IMAGE * 2, this);
                }
            }
        };
        panel.setLayout(null);
        panel.setOpaque(false);
        panel.setPreferredSize(new Dimension(DIALOG_WIDTH, DIALOG_HEIGHT));
 
        final JButton continueBtn = new JButton();
        continueBtn.setBorderPainted(false);
        continueBtn.setContentAreaFilled(false);
        continueBtn.setFocusPainted(false);

        if (continueButton != null) {
            continueBtn.setIcon(new ImageIcon(continueButton.getScaledInstance(BUTTON_WIDTH, BUTTON_HEIGHT, 
                                    java.awt.Image.SCALE_SMOOTH)));
        } else {
            continueBtn.setText("Continue");
        }
        final int btnX = (DIALOG_WIDTH - BUTTON_WIDTH) / 2;
        continueBtn.setBounds(btnX, BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);

        panel.add(continueBtn);
        add(panel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(getParent());
    }

    /**
     * Loads an image from the path.
     *
     * @param path image path
     * @return loaded image
     */
    private BufferedImage loadImage(final String path) {
        final InputStream is = LevelCompleteDialog.class.getResourceAsStream(path);
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
     * Show the popup.
     *
     * @param onContinue Action to execute when the dialog closes.
     */
    public void showDialog(final Runnable onContinue) {
        final JButton button = (JButton) ((JPanel) getContentPane().getComponent(0)).getComponent(0);
        button.addActionListener(e -> {
            dispose();
            if (onContinue != null) {
                onContinue.run();
            }
        });
        setVisible(true);
    }
}
