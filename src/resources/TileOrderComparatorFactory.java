package resources;

import model.GameArgumentException;
import model.cube.Tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.Map;

/**
 * Creates tile comparators from externally configured tile orders.
 */
public final class TileOrderComparatorFactory {

    private TileOrderComparatorFactory() {
    }

    /**
     * Builds a comparator that sorts tiles by the order listed in a resource file.
     *
     * @param resourcePath path to the tile-order definition
     * @return comparator that applies configured priority first
     * @throws GameArgumentException if the resource is missing or invalid
     */
    public static Comparator<Tile> fromResource(String resourcePath) throws GameArgumentException {
        Map<Tile, Integer> priorityMap = new EnumMap<>(Tile.class);
        int priority = 0;

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

    private static InputStream openResource(String resourcePath) throws IOException {
        InputStream classpathInput = TileOrderComparatorFactory.class.getClassLoader().getResourceAsStream(resourcePath);
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