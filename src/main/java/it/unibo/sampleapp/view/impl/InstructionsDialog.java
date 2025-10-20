package it.unibo.sampleapp.view.impl;

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
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Screen with game instructions.
 */

public class InstructionsDialog extends JDialog {

    private static final long serialVersionUID = 1L;

    private static final int DIALOG_WIDTH = 550;
    private static final int DIALOG_HEIGHT = 350;

    private static final int TITLE_WIDTH = 200;
    private static final int TITLE_HEIGHT = 55;
    private static final int TITLE_Y = 35;

    private static final int TEXT_X = 45;
    private static final int TEXT_Y = 90;
    private static final int TEXT_WIDTH = 560;
    private static final int TEXT_HEIGHT = 180;

    private static final int BUTTON_WIDTH = 150;
    private static final int BUTTON_HEIGHT = 50;
    private static final int BUTTON_Y = 260;

    private final transient BufferedImage backImage;
    private final transient BufferedImage continueButton;
    private final transient BufferedImage instructionsTitle; 

    /**
     * Constructor of the instruction dialog.
     *
     * @param parentFrame the parent frame above which the JDialog appears.
     */
    public InstructionsDialog(final JFrame parentFrame) {
        super(parentFrame, "Istruzioni", true);

        this.backImage = loadImage("/img/Menu.png");
        this.continueButton = loadImage("/img/ContinueButton.png");
        this.instructionsTitle = loadImage("/img/InstructionsTitle.png");
    }

    /**
     * Creates and initializes an instruction dialog tied to the frame.
     *
     * @param parentFrame the parent frame
     * @return the instruction dialog created
     */
    public static InstructionsDialog create(final JFrame parentFrame) {
        final InstructionsDialog dialog = new InstructionsDialog(parentFrame);
        dialog.initDialog(parentFrame);
        return dialog;
    }

    /**
     * Initializes the dialog UI.
     *
     * @param parentFrame the parent frame
     */
    private void initDialog(final JFrame parentFrame) {
        setUndecorated(true);
        setResizable(false);
        setLayout(new BorderLayout());
        setBackground(new Color(0, 0, 0, 0));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        final JPanel imagePanel = new JPanel() {
            @Override
            protected void paintComponent(final Graphics g) {
                super.paintComponent(g);
                if (backImage != null) {
                    g.drawImage(backImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        imagePanel.setLayout(null); 
        imagePanel.setOpaque(false);
        imagePanel.setPreferredSize(new Dimension(DIALOG_WIDTH, DIALOG_HEIGHT));

        final JLabel htmlLabel = new JLabel(
                "<html><div style='color:black; font-size:12px; font-weight:bold; "
                + "font-family: 'Trebuchet MS', 'Verdana', sans-serif;'>"
                + "Use teamwork between the two characters to successfully<br>"
                + "complete each level by reaching the two doors.<br>"
                + "Activate the buttons and move the objects while collecting<br>"
                + "gems and helping both the Fireboy and the Watergirl<br>"
                + "escape each level.<br>"
                + "Use the arrows controls to move Watergirl.<br>"
                + "Use the WAD controls to move Fireboy.<br>"
                + "</div></html>");
        htmlLabel.setBounds(TEXT_X, TEXT_Y, TEXT_WIDTH, TEXT_HEIGHT);
        htmlLabel.setOpaque(false);
        imagePanel.add(htmlLabel);

        final JButton continueBtn = new JButton();
        continueBtn.setBorderPainted(false);
        continueBtn.setContentAreaFilled(false);
        continueBtn.setFocusPainted(false);

        if (instructionsTitle != null) {
            final JLabel titleLabel = new JLabel(new ImageIcon(instructionsTitle.getScaledInstance(TITLE_WIDTH, TITLE_HEIGHT, 
                                    java.awt.Image.SCALE_SMOOTH)));
            final int titleX = (DIALOG_WIDTH - TITLE_WIDTH) / 2;
            titleLabel.setBounds(titleX, TITLE_Y, TITLE_WIDTH, TITLE_HEIGHT);
            imagePanel.add(titleLabel);
        }

        if (continueButton != null) {
            continueBtn.setIcon(new ImageIcon(continueButton.getScaledInstance(BUTTON_WIDTH, BUTTON_HEIGHT, 
                                    java.awt.Image.SCALE_SMOOTH)));
        } else {
            continueBtn.setText("Continue");
        }
        final int btnX = (DIALOG_WIDTH - BUTTON_WIDTH) / 2;
        continueBtn.setBounds(btnX, BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);

        continueBtn.addActionListener(e -> dispose());
        imagePanel.add(continueBtn);

        add(imagePanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(parentFrame);

    }

    /**
     * Loads an image from the path.
     *
     * @param path image path
     * @return loaded image
     */
    private BufferedImage loadImage(final String path) {
        final InputStream is = InstructionsDialog.class.getResourceAsStream(path);
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
     * @param onClose Action to execute when the dialog closes.
     */
    public void showPopup(final Runnable onClose) {
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(final java.awt.event.WindowEvent e) {
                if (onClose != null) {
                    onClose.run();
                }
            }
        });
        setVisible(true);
    }
}
