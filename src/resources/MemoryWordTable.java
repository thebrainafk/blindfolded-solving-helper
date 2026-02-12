package resources;

import model.GameArgumentException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Two-dimensional lookup table for mnemonic words indexed by letter pairs.
 */
public class MemoryWordTable {
    private final String[][] words;

    /**
     * Loads memory words from an input stream.
     *
     * @param input source that contains pair-to-word mappings
     * @throws GameArgumentException if reading fails
     */
    public MemoryWordTable(InputStream input) throws GameArgumentException {
        this.words = new String[24][24];
        this.load(input);
    }

    /**
     * Stores a memory word at the given pair coordinates.
     *
     * @param row first-letter index
     * @param column second-letter index
     * @param word mnemonic word
     */
    public void setWord(int row, int column, String word) {
        words[row][column] = word;
    }

    /**
     * Returns the memory word stored for the given coordinates.
     *
     * @param row first-letter index
     * @param column second-letter index
     * @return mnemonic word or {@code null} if undefined
     */
    public String getWord(int row, int column) {
        return words[row][column];
    }

    private void load(InputStream input) throws GameArgumentException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) continue;

                String[] parts = line.split("\\s+");
                String pair = parts[0];
                String word = parts[1];

                int row = pair.charAt(0) - 'A';
                int col = pair.charAt(1) - 'A';

                this.setWord(row, col, word);
            }
        } catch (IOException e) {
            throw new GameArgumentException(e.getMessage());
        }
    }
}
