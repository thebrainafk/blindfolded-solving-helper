package view;

import java.util.Objects;

/**
 * Immutable command result object used by service and web layers.
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
     * Creates a successful empty result used as default placeholder.
     *
     * @return successful empty result
     */
    public static Result none() {
        return new Result(true, "", "", "", "", "", "");
    }

    /**
     * Creates a result containing only a generated scramble.
     *
     * @param scramble generated scramble string
     * @return successful scramble result
     */
    public static Result scramble(String scramble) {
        return new Result(true, "", scramble, "", "", "", "");
    }

    /**
     * Creates a result containing edge translation and setup moves.
     *
     * @param edgeAlgorithmOutput edge pair output
     * @param edgeSetupMovesOutput edge setup-move output
     * @return successful edge result
     */
    public static Result edge(String edgeAlgorithmOutput, String edgeSetupMovesOutput) {
        return new Result(true, "", "", edgeAlgorithmOutput, "", edgeSetupMovesOutput, "");
    }

    /**
     * Creates a result containing corner translation and setup moves.
     *
     * @param cornerAlgorithmOutput corner pair output
     * @param cornerSetupMovesOutput corner setup-move output
     * @return successful corner result
     */
    public static Result corner(String cornerAlgorithmOutput, String cornerSetupMovesOutput) {
        return new Result(true, "", "", "", cornerAlgorithmOutput, "", cornerSetupMovesOutput);
    }

    /**
     * Creates a failed result with an error message.
     *
     * @param message human-readable error
     * @return failed result
     */
    public static Result error(String message) {
        return new Result(false, message, "", "", "", "", "");
    }

    /**
     * Indicates whether command execution succeeded.
     *
     * @return {@code true} if successful
     */
    public boolean success() {
        return success;
    }

    /**
     * Returns the error message for failed executions.
     *
     * @return error message or empty string
     */
    public String errorMessage() {
        return errorMessage;
    }

    /**
     * Returns the currently stored scramble text.
     *
     * @return scramble string or empty string
     */
    public String scramble() {
        return scramble;
    }

    /**
     * Returns rendered edge pair output text.
     *
     * @return edge output text
     */
    public String edgeAlgorithmOutput() {
        return edgeAlgorithmOutput;
    }

    /**
     * Returns rendered corner pair output text.
     *
     * @return corner output text
     */
    public String cornerAlgorithmOutput() {
        return cornerAlgorithmOutput;
    }

    /**
     * Returns rendered edge setup-move text.
     *
     * @return edge setup-move output
     */
    public String edgeSetupMovesOutput() {
        return edgeSetupMovesOutput;
    }

    /**
     * Returns rendered corner setup-move text.
     *
     * @return corner setup-move output
     */
    public String cornerSetupMovesOutput() {
        return cornerSetupMovesOutput;
    }
}
