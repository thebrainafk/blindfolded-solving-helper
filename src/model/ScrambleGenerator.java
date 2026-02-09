package model;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;

public class ScrambleGenerator {
    private static final List<URI> SCRAMBLE_URIS = List.of(
            URI.create("https://api.cubing.net/v0/scramble?event=333"),
            URI.create("https://api.cubing.net/v0/scramble/333"),
            URI.create("https://api.cubing.net/v0/scramble?event=333&count=1")
    );
    private final HttpClient httpClient;

    public ScrambleGenerator() {
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .build();
    }

    public String generate() throws GameArgumentException {
        String lastError = null;
        try {
            for (URI uri : SCRAMBLE_URIS) {
                HttpRequest request = HttpRequest.newBuilder(uri)
                        .timeout(Duration.ofSeconds(10))
                        .header("User-Agent", "Blindfolded-Solution-Generator")
                        .GET()
                        .build();
                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                if (response.statusCode() != 200) {
                    lastError = "Scramble service returned status " + response.statusCode();
                    continue;
                }
                String body = response.body();
                if (body == null || body.isBlank()) {
                    lastError = "Scramble service returned an empty response";
                    continue;
                }
                String scramble = parseScramble(body);
                if (scramble == null || scramble.isBlank()) {
                    lastError = "Scramble service returned unexpected content";
                    continue;
                }
                return scramble;
            }
            throw new GameArgumentException(lastError == null ? "Scramble service failed" : lastError);
        } catch (IOException error) {
            throw new GameArgumentException("Unable to reach scramble service: " + error.getMessage());
        } catch (InterruptedException error) {
            Thread.currentThread().interrupt();
            throw new GameArgumentException("Unable to reach scramble service: " + error.getMessage());
        }
    }

    private String parseScramble(String body) {
        String trimmed = body.trim();
        if (trimmed.startsWith("{")) {
            return extractJsonValue(trimmed, "scramble");
        }
        if (trimmed.startsWith("[")) {
            return extractJsonValueFromArray(trimmed, "scramble");
        }
        if (trimmed.startsWith("<!DOCTYPE") || trimmed.startsWith("<html")) {
            return null;
        }
        return trimmed;
    }

    private String extractJsonValueFromArray(String json, String key) {
        int objectStart = json.indexOf('{');
        int objectEnd = json.indexOf('}', objectStart + 1);
        if (objectStart < 0 || objectEnd < 0) {
            return null;
        }
        return extractJsonValue(json.substring(objectStart, objectEnd + 1), key);
    }

    private String extractJsonValue(String json, String key) {
        String needle = "\"" + key + "\":";
        int index = json.indexOf(needle);
        if (index < 0) {
            return null;
        }
        int start = json.indexOf('"', index + needle.length());
        if (start < 0) {
            return null;
        }
        int end = json.indexOf('"', start + 1);
        if (end < 0) {
            return null;
        }
        return json.substring(start + 1, end);
    }
}