package app;

/**
 * Request to run a command in the application layer.
 *
 * @param name command identifier
 * @param arguments optional arguments for the command
 */
public record CommandRequest(String name, String[] arguments) {
}
