package view;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MovesParser {
    public enum Moves {
        R,
        L,
        U,
        D,
        F,
        B;

        public static Moves getMovesFromChar(char moveChar) {
            for (Moves move : Moves.values()) {
                if (move.name().equals(String.valueOf(moveChar))) {
                    return move;
                }
            }
            return null;
        }
    }

    public enum MoveDirections {
        CLOCKWISE("", 1),
        DOUBLE("2", 2),
        COUNTER_CLOCKWISE("'", 3);

        private final String direction;
        private final int turns;

        MoveDirections(String direction, int turns) {
            this.direction = direction;
            this.turns = turns;
        }


        public static MoveDirections getDirection(String direction) {
            for (MoveDirections directions : MoveDirections.values()) {
                if (directions.direction.equals(direction)) {
                    return directions;
                }
            }
            return null;
        }
    }

    public enum AllMoves {
        R_CW(Moves.R, MoveDirections.CLOCKWISE), L_CW(Moves.L, MoveDirections.CLOCKWISE), U_CW(Moves.U, MoveDirections.CLOCKWISE),
        D_CW(Moves.D, MoveDirections.CLOCKWISE), F_CW(Moves.F, MoveDirections.CLOCKWISE), B_CW(Moves.B, MoveDirections.CLOCKWISE),
        R_CCW(Moves.R, MoveDirections.COUNTER_CLOCKWISE), L_CCW(Moves.L, MoveDirections.COUNTER_CLOCKWISE), U_CCW(Moves.U, MoveDirections.COUNTER_CLOCKWISE),
        D_CCW(Moves.D, MoveDirections.COUNTER_CLOCKWISE), F_CCW(Moves.F, MoveDirections.COUNTER_CLOCKWISE), B_CCW(Moves.B, MoveDirections.COUNTER_CLOCKWISE),
        R2(Moves.R, MoveDirections.DOUBLE), L2(Moves.L, MoveDirections.DOUBLE), U2(Moves.U, MoveDirections.DOUBLE),
        D2(Moves.D, MoveDirections.DOUBLE), F2(Moves.F, MoveDirections.DOUBLE), B2(Moves.B, MoveDirections.DOUBLE);

        private final Moves move;
        private final MoveDirections direction;

        AllMoves(Moves move, MoveDirections direction) {
            this.move = move;
            this.direction = direction;
        }

        public static AllMoves getAllMovesFromMoveAndDirection(Moves move, MoveDirections direction) {
            for (AllMoves allMoves : AllMoves.values()) {
                if (allMoves.move.equals(move) && allMoves.direction.equals(direction)) {
                    return allMoves;
                }
            }
            return null;
        }

        public int getTurns() {
            return this.direction.turns;
        }

        public Moves getMove() {
            return this.move;
        }
    }

    private static List<String> parseScramble(String input) {
        input = input.replaceAll("\\s+", "");
        Pattern movePattern = Pattern.compile("[RLFBUD][2']?");
        Matcher matcher = movePattern.matcher(input);
        List<String> moves = new ArrayList<>();
        while (matcher.find()) {
            moves.add(matcher.group());
        }
        return moves;
    }

    public static List<AllMoves> getMovesFromScramble(String scramble) {
        List<AllMoves> allMoves = new ArrayList<>();
        for (String wholeMove : parseScramble(scramble)) {
            Moves move = Moves.getMovesFromChar(wholeMove.charAt(0));
            MoveDirections direction = MoveDirections.CLOCKWISE;
            if (wholeMove.length() == 2) {
                direction = MoveDirections.getDirection(wholeMove.substring(1));
            }
            allMoves.add(AllMoves.getAllMovesFromMoveAndDirection(move, direction));
        }
        return allMoves;
    }


}
