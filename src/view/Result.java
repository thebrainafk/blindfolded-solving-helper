package view;

import model.CubeState;

public final class Result {

    private final boolean success;
    private final String message;
    private final CubeState cubeState;

    private Result(boolean success, String message, CubeState cubeState) {
        this.success = success;
        this.message = message;
        this.cubeState = cubeState;
    }

    public static Result ok(CubeState cubeState) {
        return new Result(true, null, cubeState);
    }

    public static Result error(String message) {
        return new Result(false, message, null);
    }

    public boolean success() {
        return success;
    }

    public String message() {
        return message;
    }

    public CubeState cubeState() {
        return cubeState;
    }
}

