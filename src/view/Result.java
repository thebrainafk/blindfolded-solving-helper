package view;

import java.util.Objects;

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

    public static Result none() {
        return new Result(true, "", "", "", "", "", "");
    }

    public static Result scramble(String scramble) {
        return new Result(true, "", scramble, "", "", "", "");
    }

    public static Result edge(String edgeAlgorithmOutput, String edgeSetupMovesOutput) {
        return new Result(true, "", "", edgeAlgorithmOutput, "", edgeSetupMovesOutput, "");
    }

    public static Result corner(String cornerAlgorithmOutput, String cornerSetupMovesOutput) {
        return new Result(true, "", "", "", cornerAlgorithmOutput, "", cornerSetupMovesOutput);
    }

    public static Result error(String message) {
        return new Result(false, message, "", "", "", "", "");
    }

    public boolean success() {
        return success;
    }

    public String errorMessage() {
        return errorMessage;
    }

    public String scramble() {
        return scramble;
    }

    public String edgeAlgorithmOutput() {
        return edgeAlgorithmOutput;
    }

    public String cornerAlgorithmOutput() {
        return cornerAlgorithmOutput;
    }

    public String edgeSetupMovesOutput() {
        return edgeSetupMovesOutput;
    }

    public String cornerSetupMovesOutput() {
        return cornerSetupMovesOutput;
    }
}
