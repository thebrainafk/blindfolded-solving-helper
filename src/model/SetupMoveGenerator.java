package model;

import model.cube.Tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * Resolves setup-move strings for translated tile sequences.
 */
public class SetupMoveGenerator {

    private final Map<Tile, String> setupMoves;

    /**
     * Loads setup-move definitions from a resource file.
     *
     * @param resourcePath classpath or filesystem path to the setup-move table
     * @throws GameArgumentException if the table cannot be loaded or parsed
     */
    public SetupMoveGenerator(String resourcePath) throws GameArgumentException {
        this.setupMoves = this.loadSetupMoves(resourcePath);
    }

    /**
     * Converts a tile sequence into a printable list of setup-move lines.
     *
     * @param tileSequence translated tile sequence
     * @return multi-line setup-move output
     * @throws GameArgumentException if a tile pair has no setup-move definition
     */
    public String generateFromTileSequence(List<Tile> tileSequence) {
        StringBuilder setupMovesBuilder = new StringBuilder();
        for (Tile tile : tileSequence) {
            String mappedMoves = this.setupMoves.get(tile);
            if (mappedMoves == null || mappedMoves.isBlank()) {
                setupMovesBuilder.append("[").append(tile).append("]");
            } else {
                setupMovesBuilder.append(mappedMoves);
            }
            setupMovesBuilder.append(System.lineSeparator());
        }
        setupMovesBuilder.setLength(setupMovesBuilder.length() - System.lineSeparator().length());
        return setupMovesBuilder.toString();
    }

    private Map<Tile, String> loadSetupMoves(String resourcePath) throws GameArgumentException {
        Map<Tile, String> setupMoveMap = new EnumMap<>(Tile.class);

        try (InputStream inputStream = openResource(resourcePath)) {
            if (inputStream == null) {
                throw new GameArgumentException(resourcePath + " not found");
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String value = line.trim();
                    if (value.isEmpty() || value.startsWith("#")) {
                        continue;
                    }

                    String[] parts = value.split("\\s*=\\s*", 2);
                    if (parts.length != 2 || parts[1].isBlank()) {
                        throw new GameArgumentException("Invalid setup move entry in " + resourcePath + ": " + value);
                    }

                    Tile tile;
                    try {
                        tile = Tile.valueOf(parts[0]);
                    } catch (IllegalArgumentException error) {
                        throw new GameArgumentException("Invalid tile in " + resourcePath + ": " + parts[0]);
                    }

                    if (setupMoveMap.containsKey(tile)) {
                        throw new GameArgumentException("Duplicate tile in " + resourcePath + ": " + tile);
                    }

                    setupMoveMap.put(tile, parts[1].trim());
                }
            }
        } catch (IOException error) {
            throw new GameArgumentException("Unable to load " + resourcePath + ": " + error.getMessage());
        }

        return setupMoveMap;
    }

    private InputStream openResource(String resourcePath) throws IOException {
        InputStream classpathInput = SetupMoveGenerator.class.getClassLoader().getResourceAsStream(resourcePath);
        if (classpathInput != null) {
            return classpathInput;
        }

        Path srcRelativePath = Path.of("src", resourcePath);
        if (Files.exists(srcRelativePath)) {
            return Files.newInputStream(srcRelativePath);
        }

        Path directPath = Path.of(resourcePath);
        if (Files.exists(directPath)) {
            return Files.newInputStream(directPath);
        }

        return null;
    }
}