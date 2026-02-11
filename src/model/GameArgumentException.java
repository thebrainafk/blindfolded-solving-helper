package model;

/**
 * This exception is used to symbolize a semantic error within the games' execution.
 */
public class GameArgumentException extends Exception {

    /**
     * Creates a new GameArgumentException instance.
     */
    public GameArgumentException(String message) {
        super(message);
    }
}
