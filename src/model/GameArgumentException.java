package model;

/**
 * This exception is used to symbolize a semantic error within the games' execution.
 *
 * @author Programmieren-Team
 */
public class GameArgumentException extends Exception {

    public GameArgumentException(String message) {
        super(message);
    }
}
