package Chess.Game.Logic.Pieces;

import Chess.Game.Logic.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Fitor Avdiji
 *
 * Tests for the Class Pawn
 */
class PawnTest implements IChessPieceTest{

    IChessPiece pawn = new Pawn(pos_white_leftPawnStart, EChessPieces.PAWN_WHITE, chessField);

    // positions (from whites perspective)
    private static final Position pos_white_leftPawnStart = new Position('a', 2);
    private static final Position pos_white_rightPawnStart = new Position('h', 2);
    private static final Position pos_white_midPawnStart = new Position('d', 2);
    private static final Position pos_white_endPosition = new Position('d', 8);

    // positions (from blacks perspective)
    private static final Position pos_black_leftPawnStart = new Position('a', 7);
    private static final Position pos_black_rightPawnStart = new Position('h', 7);
    private static final Position pos_black_midPawnStart = new Position('d', 7);
    private static final Position pos_black_endPosition = new Position('d', 1);

    @Test
    @Override
    public void test_getPotentialPositions() {
        Set<Position> expected = new HashSet<>();
        // left pawn start
        expected.add(new Position('a', 3));
        expected.add(new Position('b', 3));
        expected.add(new Position('a', 4));
        Assertions.assertEquals(expected, pawn.getPotentialPositions());
        expected.clear();
        //right pawn start
        pawn = new Pawn(pos_white_rightPawnStart, EChessPieces.PAWN_WHITE, chessField);
        expected.add(new Position('g', 3));
        expected.add(new Position('h', 4));
        expected.add(new Position('h', 3));
        Assertions.assertEquals(expected, pawn.getPotentialPositions());
        expected.clear();
        //middle pawn start
        pawn = new Pawn(pos_white_midPawnStart, EChessPieces.PAWN_WHITE, chessField);
        expected.add(new Position('d', 3));
        expected.add(new Position('d', 4));
        expected.add(new Position('c', 3));
        expected.add(new Position('e', 3));
        Assertions.assertEquals(expected, pawn.getPotentialPositions());
        expected.clear();
        // middle
        pawn = new Pawn(pos_middle, EChessPieces.PAWN_WHITE, chessField);
        expected.add(new Position('e', 5));
        expected.add(new Position('c', 5));
        expected.add(new Position('d', 5));
        Assertions.assertEquals(expected, pawn.getPotentialPositions());
        expected.clear();
        //end
        pawn = new Pawn(pos_white_endPosition, EChessPieces.PAWN_WHITE, chessField);
        Assertions.assertEquals(expected, pawn.getPotentialPositions());

        // left pawn start
        pawn = new Pawn(pos_black_leftPawnStart, EChessPieces.PAWN_BLACK, chessField);
        expected.add(new Position('a', 6));
        expected.add(new Position('a', 5));
        expected.add(new Position('b', 6));
        Assertions.assertEquals(expected, pawn.getPotentialPositions());
        expected.clear();
        // right pawn start
        pawn = new Pawn(pos_black_rightPawnStart, EChessPieces.PAWN_BLACK, chessField);
        expected.add(new Position('h', 6));
        expected.add(new Position('h', 5));
        expected.add(new Position('g', 6));
        Assertions.assertEquals(expected, pawn.getPotentialPositions());
        expected.clear();
        // middle pawn start
        pawn = new Pawn(pos_black_midPawnStart, EChessPieces.PAWN_BLACK, chessField);
        expected.add(new Position('d', 6));
        expected.add(new Position('d', 5));
        expected.add(new Position('e', 6));
        expected.add(new Position('c', 6));
        Assertions.assertEquals(expected, pawn.getPotentialPositions());
        expected.clear();
        // middle
        pawn = new Pawn(pos_middle, EChessPieces.PAWN_BLACK, chessField);
        expected.add(new Position('d', 3));
        expected.add(new Position('e', 3));
        expected.add(new Position('c', 3));
        Assertions.assertEquals(expected, pawn.getPotentialPositions());
        expected.clear();
        // end
        pawn = new Pawn(pos_black_endPosition, EChessPieces.PAWN_BLACK, chessField);
        Assertions.assertEquals(expected, pawn.getPotentialPositions());
    }

    @Test
    @Override
    public void test_canCapturePosition() {
        Position possibleWhite = new Position('d', 3);
        Position possibleBlack = new Position('d', 6);

        pawn = new Pawn(pos_white_midPawnStart, EChessPieces.PAWN_WHITE, chessField);
        Assertions.assertTrue(pawn.canCapturePosition(possibleWhite));
        pawn = new Pawn(pos_black_midPawnStart, EChessPieces.PAWN_WHITE, chessField);
        Assertions.assertFalse(pawn.canCapturePosition(possibleWhite));

        pawn = new Pawn(pos_black_midPawnStart, EChessPieces.PAWN_BLACK, chessField);
        Assertions.assertTrue(pawn.canCapturePosition(possibleBlack));
        pawn = new Pawn(pos_white_midPawnStart, EChessPieces.PAWN_BLACK, chessField);
        Assertions.assertFalse(pawn.canCapturePosition(possibleBlack));
    }

    //TODO
    @Override
    public void test_canActuallyCapturePosition() {

    }
}