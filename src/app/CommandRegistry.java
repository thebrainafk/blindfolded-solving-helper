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
 * Keeps the available commands for the application layer.
 */
public class CommandRegistry {
    private final Map<String, Command> commands;

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
