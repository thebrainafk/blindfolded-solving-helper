package app;

import model.CubeManager;
import view.commands.Command;
import view.commands.GenerateCornersPochmann;
import view.commands.GenerateEdgesM2;
import view.commands.GenerateEdgesPochmann;
import view.commands.GenerateScramble;
import view.commands.ResetCube;
import view.commands.ScrambleCube;
import view.commands.ToggleMemoryHelper;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Registry for all executable commands of the application layer.
 */
public class CommandRegistry {
    private final Map<String, Command> commands;

    /**
     * Creates and pre-registers all built-in commands.
     *
     * @param cubeManager shared cube manager used by command implementations
     */
    public CommandRegistry(CubeManager cubeManager) {
        Objects.requireNonNull(cubeManager);
        this.commands = new HashMap<>();
        register(new GenerateCornersPochmann(cubeManager));
        register(new GenerateEdgesM2(cubeManager));
        register(new GenerateEdgesPochmann(cubeManager));
        register(new GenerateScramble(cubeManager));
        register(new ResetCube(cubeManager));
        register(new ScrambleCube(cubeManager));
        register(new ToggleMemoryHelper(cubeManager));
    }

    /**
     * Registers a command by its technical command name.
     *
     * @param command command instance to register
     */
    public void register(Command command) {
        commands.put(command.getCommandName(), command);
    }

    /**
     * Looks up a command by name.
     *
     * @param name command name from the request
     * @return registered command or {@code null} if unknown
     */
    public Command get(String name) {
        return commands.get(name);
    }

    /**
     * Returns all currently registered command names.
     *
     * @return unmodifiable set of command names
     */
    public Set<String> listNames() {
        return Collections.unmodifiableSet(commands.keySet());
    }
}
