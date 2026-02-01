package view.commands;

import model.CubeManager;
import model.CubeState;
import view.Result;

import java.util.Objects;

/**
 * asdf.
 *
 * @author Programmieren-Team
 * @author uckhu
 */
public abstract class Command {
    protected final String commandName;
    protected final CubeManager cubeManager;

    protected Command(CubeManager cubeManager, String commandName) {
        this.cubeManager = Objects.requireNonNull(cubeManager);
        this.commandName = Objects.requireNonNull(commandName);
    }

    /**
     * asdf.
     *
     * @return asdf
     */
    public String getCommandName() {
        return commandName;
    }

    public CubeState getCubeState() {
        return new CubeState(cubeManager.getCube());
    }

    /**
     * asdf.
     *
     * @param arguments
     * @return asdf
     */
    public abstract Result execute(String[] arguments);
}
