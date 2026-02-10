package resources;

import model.GameArgumentException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MemoryWordTable {
    private final String[][] words;

    public MemoryWordTable(InputStream input) throws GameArgumentException {
        this.words = new String[24][24];
        this.load(input);
    }

    public void setWord(int row, int column, String word) {
        words[row][column] = word;
    }

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
