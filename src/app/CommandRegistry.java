package app;

import model.CubeManager;
import view.commands.Command;
import view.commands.GenerateScramble;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Keeps the available commands for the application layer.
 */
public class CommandRegistry {
    private final Map<String, Command> commands;

    public CommandRegistry(CubeManager cubeManager) {
        Objects.requireNonNull(cubeManager);
        this.commands = new HashMap<>();
        register(new GenerateScramble(cubeManager));
    }

    public void register(Command command) {
        commands.put(command.getCommandName(), command);
    }

    public Command get(String name) {
        return commands.get(name);
    }

    public Set<String> listNames() {
        return Collections.unmodifiableSet(commands.keySet());
    }
}
