package view;

import model.CubeState;

import java.util.Objects;

public final class Result {

    private final boolean success;
    private final String errorMessage;
    private final String scramble;
    private final String edgeAlgorithmOutput;
    private final String cornerAlgorithmOutput;
    private final CubeState cubeState;

    private Result(
            boolean success,
            String errorMessage,
            String scramble,
            String edgeAlgorithmOutput,
            String cornerAlgorithmOutput,
            CubeState cubeState
    ) {
        this.success = success;
        this.errorMessage = Objects.requireNonNull(errorMessage);
        this.scramble = Objects.requireNonNull(scramble);
        this.edgeAlgorithmOutput = Objects.requireNonNull(edgeAlgorithmOutput);
        this.cornerAlgorithmOutput = Objects.requireNonNull(cornerAlgorithmOutput);
        this.cubeState = cubeState;
    }

    public static Result none(CubeState cubeState) {
        return new Result(true, "", "", "", "", cubeState);
    }

    public static Result scramble(String scramble, CubeState cubeState) {
        return new Result(true, "", scramble, "", "", cubeState);
    }

    public static Result edge(String edgeAlgorithmOutput, CubeState cubeState) {
        return new Result(true, "", "", edgeAlgorithmOutput, "", cubeState);
    }

    public static Result corner(String cornerAlgorithmOutput, CubeState cubeState) {
        return new Result(true, "", "", "", cornerAlgorithmOutput, cubeState);
    }

    public static Result error(String message) {
        return new Result(false, message, "", "", "", null);
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

    public CubeState cubeState() {
        return cubeState;
    }
}
