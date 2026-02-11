package view;

import java.util.Objects;

/**
 * Represents the Result class.
 */
public final class Result {

    private final boolean success;
    private final String errorMessage;
    private final String scramble;
    private final String edgeAlgorithmOutput;
    private final String cornerAlgorithmOutput;
    private final String edgeSetupMovesOutput;
    private final String cornerSetupMovesOutput;

    private Result(
            boolean success,
            String errorMessage,
            String scramble,
            String edgeAlgorithmOutput,
            String cornerAlgorithmOutput,
            String edgeSetupMovesOutput,
            String cornerSetupMovesOutput
    ) {
        this.success = success;
        this.errorMessage = Objects.requireNonNull(errorMessage);
        this.scramble = Objects.requireNonNull(scramble);
        this.edgeAlgorithmOutput = Objects.requireNonNull(edgeAlgorithmOutput);
        this.cornerAlgorithmOutput = Objects.requireNonNull(cornerAlgorithmOutput);
        this.edgeSetupMovesOutput = Objects.requireNonNull(edgeSetupMovesOutput);
        this.cornerSetupMovesOutput = Objects.requireNonNull(cornerSetupMovesOutput);
    }

    /**
     * Executes none.
     */
    public static Result none() {
        return new Result(true, "", "", "", "", "", "");
    }

    /**
     * Executes scramble.
     */
    public static Result scramble(String scramble) {
        return new Result(true, "", scramble, "", "", "", "");
    }

    /**
     * Executes edge.
     */
    public static Result edge(String edgeAlgorithmOutput, String edgeSetupMovesOutput) {
        return new Result(true, "", "", edgeAlgorithmOutput, "", edgeSetupMovesOutput, "");
    }

    /**
     * Executes corner.
     */
    public static Result corner(String cornerAlgorithmOutput, String cornerSetupMovesOutput) {
        return new Result(true, "", "", "", cornerAlgorithmOutput, "", cornerSetupMovesOutput);
    }

    /**
     * Executes error.
     */
    public static Result error(String message) {
        return new Result(false, message, "", "", "", "", "");
    }

    /**
     * Executes success.
     */
    public boolean success() {
        return success;
    }

    /**
     * Executes errorMessage.
     */
    public String errorMessage() {
        return errorMessage;
    }

    /**
     * Executes scramble.
     */
    public String scramble() {
        return scramble;
    }

    /**
     * Executes edgeAlgorithmOutput.
     */
    public String edgeAlgorithmOutput() {
        return edgeAlgorithmOutput;
    }

    /**
     * Executes cornerAlgorithmOutput.
     */
    public String cornerAlgorithmOutput() {
        return cornerAlgorithmOutput;
    }

    /**
     * Executes edgeSetupMovesOutput.
     */
    public String edgeSetupMovesOutput() {
        return edgeSetupMovesOutput;
    }

    /**
     * Executes cornerSetupMovesOutput.
     */
    public String cornerSetupMovesOutput() {
        return cornerSetupMovesOutput;
    }
}
