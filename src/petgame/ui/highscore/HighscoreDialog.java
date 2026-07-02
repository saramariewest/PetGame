package petgame.ui.highscore;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import petgame.domain.highscore.HighscoreEntry;

public final class HighscoreDialog {

    private HighscoreDialog() {
    }

    public static void show(Component parent, List<HighscoreEntry> highscores) {
        JTextArea textArea = new JTextArea(buildTable(highscores));
        textArea.setEditable(false);
        textArea.setCaretPosition(0);
        textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(520, 320));

        JOptionPane.showMessageDialog(parent, scrollPane, "Highscores", JOptionPane.INFORMATION_MESSAGE);
    }

    private static String buildTable(List<HighscoreEntry> highscores) {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%-4s %-16s %-8s %-12s %-8s%n",
                "#", "Name", "Level", "Survival", "Score"));
        builder.append("------------------------------------------------------------\n");

        if (highscores.isEmpty()) {
            builder.append("No highscores saved yet.\n");
            return builder.toString();
        }

        int index = 1;
        for (HighscoreEntry entry : highscores) {
            builder.append(String.format("%-4d %-16s %-8d %-12s %-8d%n",
                    index++,
                    trimName(entry.getName(), 16),
                    entry.getLevel(),
                    entry.formatDuration(),
                    entry.getScore()));
        }

        builder.append("\nScore formula: level * 1000 + survived seconds\n");
        return builder.toString();
    }

    private static String trimName(String name, int maxLength) {
        if (name == null) {
            return "";
        }
        if (name.length() <= maxLength) {
            return name;
        }
        return name.substring(0, Math.max(0, maxLength - 3)) + "...";
    }
}
