package model;

/**
 * Signals domain-specific validation or parsing errors during cube operations.
 */
public class GameArgumentException extends Exception {

    /**
     * Creates a new checked exception with a human-readable message.
     *
     * @param message error description
     */
    public GameArgumentException(String message) {
        super(message);
    }
}
