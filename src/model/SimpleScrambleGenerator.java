package model;

import java.util.Random;

/**
 * Represents the SimpleScrambleGenerator class.
 */
public class SimpleScrambleGenerator {
    private static final String[] SCRAMBLE_FACES = {"U", "D", "L", "R", "F", "B"};
    private static final String[] SCRAMBLE_SUFFIXES = {"", "'", "2"};

    private final Random random;

    /**
     * Creates a new SimpleScrambleGenerator instance.
     */
    public SimpleScrambleGenerator() {
        this.random = new Random();
    }

    /**
     * Executes generateLocalScramble.
     */
    public String generateLocalScramble(int length) {
        StringBuilder builder = new StringBuilder();
        String lastFace = "";
        for (int i = 0; i < length; i++) {
            String face = SCRAMBLE_FACES[random.nextInt(SCRAMBLE_FACES.length)];
            while (face.equals(lastFace)) {
                face = SCRAMBLE_FACES[random.nextInt(SCRAMBLE_FACES.length)];
            }
            String suffix = SCRAMBLE_SUFFIXES[random.nextInt(SCRAMBLE_SUFFIXES.length)];
            if (i > 0) {
                builder.append(' ');
            }
            builder.append(face).append(suffix);
            lastFace = face;
        }
        return builder.toString();
    }
}
