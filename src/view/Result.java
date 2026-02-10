package view;

import java.util.Objects;

public final class Result {

    private final boolean success;
    private final String errorMessage;
    private final String scramble;
    private final String edgeAlgorithmOutput;
    private final String cornerAlgorithmOutput;

    private Result(
            boolean success,
            String errorMessage,
            String scramble,
            String edgeAlgorithmOutput,
            String cornerAlgorithmOutput
    ) {
        this.success = success;
        this.errorMessage = Objects.requireNonNull(errorMessage);
        this.scramble = Objects.requireNonNull(scramble);
        this.edgeAlgorithmOutput = Objects.requireNonNull(edgeAlgorithmOutput);
        this.cornerAlgorithmOutput = Objects.requireNonNull(cornerAlgorithmOutput);
    }

    public static Result none() {
        return new Result(true, "", "", "", "");
    }

    public static Result scramble(String scramble) {
        return new Result(true, "", scramble, "", "");
    }

    public static Result edge(String edgeAlgorithmOutput) {
        return new Result(true, "", "", edgeAlgorithmOutput, "");
    }

    public static Result corner(String cornerAlgorithmOutput) {
        return new Result(true, "", "", "", cornerAlgorithmOutput);
    }

    public static Result error(String message) {
        return new Result(false, message, "", "", "");
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
}
