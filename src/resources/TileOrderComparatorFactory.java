package resources;

import model.GameArgumentException;
import model.cube.Tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.Map;

public final class TileOrderComparatorFactory {

    private TileOrderComparatorFactory() {
    }

    public static Comparator<Tile> fromResource(String resourcePath) throws GameArgumentException {
        Map<Tile, Integer> priorityMap = new EnumMap<>(Tile.class);
        int priority = 0;

        try (InputStream inputStream = TileOrderComparatorFactory.class.getClassLoader().getResourceAsStream(resourcePath)) {
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
                    Tile tile;
                    try {
                        tile = Tile.valueOf(value);
                    } catch (IllegalArgumentException error) {
                        throw new GameArgumentException("Invalid tile in " + resourcePath + ": " + value);
                    }
                    if (priorityMap.containsKey(tile)) {
                        throw new GameArgumentException("Duplicate tile in " + resourcePath + ": " + value);
                    }
                    priorityMap.put(tile, priority);
                    priority++;
                }
            }
        } catch (IOException error) {
            throw new GameArgumentException("Unable to load " + resourcePath + ": " + error.getMessage());
        }

        return Comparator
                .comparingInt((Tile tile) -> priorityMap.getOrDefault(tile, Integer.MAX_VALUE))
                .thenComparing(Enum::name);
    }
}
