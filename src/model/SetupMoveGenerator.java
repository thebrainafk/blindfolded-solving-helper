package model;

import model.cube.Tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class SetupMoveGenerator {

    private final Map<Tile, String> setupMoves;

    public SetupMoveGenerator(String resourcePath) throws GameArgumentException {
        this.setupMoves = this.loadSetupMoves(resourcePath);
    }

    public String generateFromTileSequence(List<Tile> tileSequence) {
        StringBuilder setupMovesBuilder = new StringBuilder();
        for (Tile tile : tileSequence) {
            String mappedMoves = this.setupMoves.get(tile);
            if (mappedMoves == null || mappedMoves.isBlank()) {
                setupMovesBuilder.append("[").append(tile).append("]");
            } else {
                setupMovesBuilder.append(mappedMoves);
            }
            setupMovesBuilder.append(" ");
        }
        return setupMovesBuilder.toString().trim();
    }

    private Map<Tile, String> loadSetupMoves(String resourcePath) throws GameArgumentException {
        Map<Tile, String> setupMoveMap = new EnumMap<>(Tile.class);

        try (InputStream inputStream = SetupMoveGenerator.class.getClassLoader().getResourceAsStream(resourcePath)) {
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
}
