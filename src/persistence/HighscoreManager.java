package persistence;

import java.awt.Component;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.HighscoreEntry;
import model.Pet;

public class HighscoreManager {

    private static final String FILE_NAME = "highscores.ser";
    private static final int MAX_ENTRIES = 10;
    private static final Comparator<HighscoreEntry> HIGHSCORE_ORDER =
            Comparator.comparingLong(HighscoreEntry::getScore).reversed()
                    .thenComparing(Comparator.comparingInt(HighscoreEntry::getLevel).reversed())
                    .thenComparing(Comparator.comparingLong(HighscoreEntry::getSurvivalTimeMillis).reversed());

    private HighscoreManager() {
    }

    public static void addEntry(String name, Pet pet) {
        if (name == null || name.isBlank() || pet == null) {
            return;
        }

        List<HighscoreEntry> highscores = loadHighscores();
        highscores.add(new HighscoreEntry(name.trim(), pet.getLevel(), pet.getSurvivalTimeMillis()));
        highscores.sort(HIGHSCORE_ORDER);

        if (highscores.size() > MAX_ENTRIES) {
            highscores = new ArrayList<>(highscores.subList(0, MAX_ENTRIES));
        }

        saveHighscores(highscores);
    }

    public static void showHighscores(Component parent) {
        List<HighscoreEntry> highscores = loadHighscores();
        highscores.sort(HIGHSCORE_ORDER);
        JTextArea textArea = new JTextArea(buildTable(highscores));
        textArea.setEditable(false);
        textArea.setCaretPosition(0);
        textArea.setFont(new java.awt.Font(java.awt.Font.MONOSPACED, java.awt.Font.PLAIN, 12));

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new java.awt.Dimension(520, 320));

        JOptionPane.showMessageDialog(parent, scrollPane, "Highscores", JOptionPane.INFORMATION_MESSAGE);
    }

    private static List<HighscoreEntry> loadHighscores() {
        try (FileInputStream file = new FileInputStream(FILE_NAME);
                ObjectInputStream in = new ObjectInputStream(file)) {
            Object object = in.readObject();

            if (object instanceof List<?> rawList) {
                List<HighscoreEntry> highscores = new ArrayList<>();

                for (Object entry : rawList) {
                    if (entry instanceof HighscoreEntry highscoreEntry) {
                        highscores.add(highscoreEntry);
                    }
                }

                return highscores;
            }
        } catch (IOException | ClassNotFoundException ex) {
            return new ArrayList<>();
        }

        return new ArrayList<>();
    }

    private static void saveHighscores(List<HighscoreEntry> highscores) {
        try (FileOutputStream file = new FileOutputStream(FILE_NAME);
                ObjectOutputStream out = new ObjectOutputStream(file)) {
            out.writeObject(highscores);
        } catch (IOException ex) {
            System.out.println("IOException is caught");
        }
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
