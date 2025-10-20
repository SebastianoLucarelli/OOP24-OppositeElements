package it.unibo.sampleapp.view.impl;

import it.unibo.sampleapp.model.game.api.Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Dialog shown when a level is completed.
 */
public class LevelCompleteDialog extends JDialog {

    private static final long serialVersionUID = 1L;

    private static final int DIALOG_WIDTH = 550;
    private static final int DIALOG_HEIGHT = 350;

    private static final int BUTTON_WIDTH = 150;
    private static final int BUTTON_HEIGHT = 50;
    private static final int BUTTON_Y = 260;

    private final BufferedImage backgroundImage;
    private final BufferedImage continueButton;


    private final Game game;

    /**
     * Creates a new dialog that appears when the level is completed.
     *
     * @param parentFrame the parent frame above which the JDialo appears
     * @param game the current game instance
     */
    public LevelCompleteDialog(final JFrame parentFrame, final Game game) {
        super(parentFrame, "Level Completed!", true);
        this.game = game;

        this.continueButton = loadImage("/img/ContinueButton.png");
        this.backgroundImage = loadImage("/img/Menu.png");
        initDialog();
    }

    /**
     * Initializes the dialog UI.
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
        try (InputStream is = getClass().getResourceAsStream(path)) {
            if (is != null) {
                return ImageIO.read(is);
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return null;
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