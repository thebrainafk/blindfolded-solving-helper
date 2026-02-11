package app;

import view.Result;
import view.commands.Command;

import java.util.Objects;

/**
 * Application-layer entry point for executing commands without a CLI.
 */
public class CommandService {
    private final CommandRegistry registry;

    /**
     * Creates a new CommandService instance.
     */
    public CommandService(CommandRegistry registry) {
        this.registry = Objects.requireNonNull(registry);
    }

    /**
     * Executes execute.
     */
    public Result execute(CommandRequest request) {
        if (request == null || request.name() == null || request.name().isBlank()) {
            return Result.error("Command name is required");
        }

        Command command = registry.get(request.name());
        if (command == null) {
            return Result.error("Unknown command");
        }

        return command.execute(request.argument());
    }
}
