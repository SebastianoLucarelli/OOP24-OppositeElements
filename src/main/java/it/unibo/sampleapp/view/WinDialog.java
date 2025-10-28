package it.unibo.sampleapp.view;

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
 * Win screen. 
 */
public class WinDialog extends JDialog {

    private static final long serialVersionUID = 1L;

    private static final int DIALOG_WIDTH = 550;
    private static final int DIALOG_HEIGHT = 350;

    private static final int TITLE_X = 75;
    private static final int TITLE_Y = 70;
    private static final int TITLE_WIDTH = 400;
    private static final int TITLE_HEIGHT = 120;

    private static final int BUTTON_WIDTH = 150;
    private static final int BUTTON_HEIGHT = 50;
    private static final int BUTTON_Y = 250;
    private static final int BACK_X = 120;
    private static final int EXIT_X = 280;
    private final transient BufferedImage backgroundImage;
    private final transient BufferedImage backButtonImg;
    private final transient BufferedImage exitButtonImg;
    private final transient BufferedImage winTitle;

    /**
     * Constructor of the win dialog.
     *
     * @param parentFrame the parant frame above which the JDialog appears
     */
    public WinDialog(final JFrame parentFrame) {
        super(parentFrame, "Win", true);

        this.backgroundImage = loadImage("/img/Menu.png");
        this.backButtonImg = loadImage("/img/backButton.png");
        this.exitButtonImg = loadImage("/img/ExitButton.png");
        this.winTitle = loadImage("/img/YouWinTitle.png");

    }

    /**
     * Creates and initializes a win dialog tied to the frame.
     *
     * @param parentFrame the parent frame
     * @return the win dialog created
     */
    public static WinDialog create(final JFrame parentFrame) {
        final WinDialog dialog = new WinDialog(parentFrame);
        dialog.initDialog();
        return dialog;
    }

    /**
     * Initializes the dialog layout and graphics.
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

                 if (winTitle != null) {
                    g.drawImage(winTitle, TITLE_X, TITLE_Y, TITLE_WIDTH, TITLE_HEIGHT, this);
                }
            }
        };

        panel.setLayout(null);
        panel.setOpaque(false);
        panel.setPreferredSize(new Dimension(DIALOG_WIDTH, DIALOG_HEIGHT));

        final JButton backBtn = new JButton();
        backBtn.setBorderPainted(false);
        backBtn.setContentAreaFilled(false);
        backBtn.setFocusPainted(false);

        if (backButtonImg != null) {
            backBtn.setIcon(new ImageIcon(backButtonImg.getScaledInstance(BUTTON_WIDTH, BUTTON_HEIGHT, 
                                        java.awt.Image.SCALE_SMOOTH)));
        }

        backBtn.setBounds(BACK_X, BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        panel.add(backBtn);

        final JButton exitBtn = new JButton();
        exitBtn.setBorderPainted(false);
        exitBtn.setContentAreaFilled(false);
        exitBtn.setFocusPainted(false);
        if (exitButtonImg != null) {
            exitBtn.setIcon(new ImageIcon(exitButtonImg.getScaledInstance(BUTTON_WIDTH, BUTTON_HEIGHT, 
                                        java.awt.Image.SCALE_SMOOTH)));
        }

        exitBtn.setBounds(EXIT_X, BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        panel.add(exitBtn);

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
     * Shows the dialog.
     *
     * @param onBack action when "Back" is pressed
     * @param exit action when "Exit" is pressed
     */
    public void showDialog(final Runnable onBack, final Runnable exit) {
        final JPanel panel = (JPanel) getContentPane().getComponent(0);
        final JButton backBtn = (JButton) panel.getComponent(0);
        final JButton exitBtn = (JButton) panel.getComponent(1);

        backBtn.addActionListener(e -> {
            dispose();
            if (onBack != null) {
                onBack.run();
            }
        });

        exitBtn.addActionListener(e -> {
            dispose();
            if (exit != null) {
                exit.run();
            }
        });
        setVisible(true);
    }
}
