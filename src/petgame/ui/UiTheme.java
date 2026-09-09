package petgame.ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicProgressBarUI;

/** Provides the shared visual language for the application. */
public final class UiTheme {

    public static final Color BACKGROUND = new Color(255, 239, 248);
    public static final Color SURFACE = new Color(255, 250, 253);
    public static final Color SURFACE_LIGHT = new Color(240, 218, 234);
    public static final Color TEXT = new Color(68, 39, 70);
    public static final Color MUTED_TEXT = new Color(112, 79, 111);
    public static final Color ACCENT = new Color(245, 181, 205);
    public static final Color ACCENT_DARK = new Color(239, 176, 202);
    public static final Color SECONDARY = new Color(203, 188, 234);
    public static final Color WARNING = new Color(248, 218, 159);
    public static final Color DANGER = new Color(231, 136, 157);
    public static final Font BODY_FONT = new Font("SansSerif", Font.PLAIN, 14);
    public static final Font HEADING_FONT = new Font("SansSerif", Font.BOLD, 18);
    public static final Font TITLE_FONT = new Font("SansSerif", Font.BOLD, 34);

    private UiTheme() {
    }

    public static void install() {
        UIManager.put("Panel.background", BACKGROUND);
        UIManager.put("Label.foreground", TEXT);
        UIManager.put("OptionPane.background", SURFACE);
        UIManager.put("OptionPane.messageForeground", TEXT);
        UIManager.put("TextField.background", SURFACE_LIGHT);
        UIManager.put("TextField.foreground", TEXT);
        UIManager.put("TextField.caretForeground", TEXT);
        UIManager.put("TextArea.background", SURFACE);
        UIManager.put("TextArea.foreground", TEXT);
        UIManager.put("TextArea.caretForeground", TEXT);
        UIManager.put("Button.background", ACCENT_DARK);
        UIManager.put("Button.foreground", TEXT);
        UIManager.put("List.background", SURFACE);
        UIManager.put("List.foreground", TEXT);
        UIManager.put("List.selectionBackground", ACCENT_DARK);
        UIManager.put("List.selectionForeground", TEXT);
        UIManager.put("MenuBar.background", SURFACE);
        UIManager.put("MenuBar.foreground", TEXT);
        UIManager.put("Menu.background", SURFACE);
        UIManager.put("Menu.foreground", TEXT);
        UIManager.put("MenuItem.background", SURFACE);
        UIManager.put("MenuItem.foreground", TEXT);
        UIManager.put("ProgressBar.background", BACKGROUND);
        UIManager.put("ProgressBar.foreground", ACCENT);
        UIManager.put("ProgressBar.selectionForeground", TEXT);
        UIManager.put("ProgressBar.selectionBackground", BACKGROUND);
    }

    public static void stylePrimaryButton(JButton button) {
        styleButton(button, ACCENT_DARK);
    }

    public static void styleSecondaryButton(JButton button) {
        styleButton(button, SECONDARY);
    }

    public static void styleWarningButton(JButton button) {
        styleButton(button, WARNING);
    }

    public static void styleButton(JButton button, Color background) {
        button.setBackground(background);
        button.setForeground(TEXT);
        button.setFont(HEADING_FONT);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(10, 18, 10, 18));
    }

    public static void styleProgressBar(JProgressBar progressBar, Color color) {
        progressBar.setForeground(color);
        progressBar.setBackground(BACKGROUND);
        progressBar.setUI(new BasicProgressBarUI() {
            @Override
            protected Color getSelectionForeground() {
                return TEXT;
            }

            @Override
            protected Color getSelectionBackground() {
                return TEXT;
            }
        });
        progressBar.setBorder(BorderFactory.createEmptyBorder());
        progressBar.setStringPainted(true);
        progressBar.setFont(new Font("SansSerif", Font.BOLD, 11));
        progressBar.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        progressBar.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 22));
    }

    public static void styleCard(JPanel panel) {
        panel.setBackground(SURFACE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(SURFACE_LIGHT, 1, true),
                BorderFactory.createEmptyBorder(14, 14, 14, 14)));
    }

    public static Border createSectionBorder(String title) {
        TitledBorder border = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(SURFACE_LIGHT, 1, true), title);
        border.setTitleColor(MUTED_TEXT);
        border.setTitleFont(new Font("SansSerif", Font.BOLD, 13));
        return BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(8, 8, 8, 8));
    }

    public static javax.swing.JLabel createSectionTitle(String text) {
        javax.swing.JLabel label = new javax.swing.JLabel(text);
        label.setFont(HEADING_FONT);
        label.setForeground(TEXT);
        return label;
    }

}
