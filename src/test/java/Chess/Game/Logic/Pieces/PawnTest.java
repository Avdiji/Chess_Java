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
    private static final Position pos_white_leftPawnStart = new Position('A', 2);
    private static final Position pos_white_rightPawnStart = new Position('H', 2);
    private static final Position pos_white_midPawnStart = new Position('D', 2);
    private static final Position pos_white_endPosition = new Position('D', 8);

    // positions (from blacks perspective)
    private static final Position pos_black_leftPawnStart = new Position('A', 7);
    private static final Position pos_black_rightPawnStart = new Position('H', 7);
    private static final Position pos_black_midPawnStart = new Position('D', 7);
    private static final Position pos_black_endPosition = new Position('D', 1);

    @Test
    @Override
    public void test_Constructor() {

    }

    @Test
    @Override
    public void test_getPotentialPositions() {
        Set<Position> expected = new HashSet<>();
        // left pawn start
        expected.add(new Position('A', 3));
        expected.add(new Position('B', 3));
        expected.add(new Position('A', 4));
        Assertions.assertEquals(expected, pawn.getPotentialPositions());
        expected.clear();
        //right pawn start
        pawn = new Pawn(pos_white_rightPawnStart, EChessPieces.PAWN_WHITE, chessField);
        expected.add(new Position('G', 3));
        expected.add(new Position('H', 4));
        expected.add(new Position('H', 3));
        Assertions.assertEquals(expected, pawn.getPotentialPositions());
        expected.clear();
        //middle pawn start
        pawn = new Pawn(pos_white_midPawnStart, EChessPieces.PAWN_WHITE, chessField);
        expected.add(new Position('D', 3));
        expected.add(new Position('D', 4));
        expected.add(new Position('C', 3));
        expected.add(new Position('E', 3));
        Assertions.assertEquals(expected, pawn.getPotentialPositions());
        expected.clear();
        // middle
        pawn = new Pawn(pos_middle, EChessPieces.PAWN_WHITE, chessField);
        expected.add(new Position('E', 5));
        expected.add(new Position('C', 5));
        expected.add(new Position('D', 5));
        Assertions.assertEquals(expected, pawn.getPotentialPositions());
        expected.clear();
        //end
        pawn = new Pawn(pos_white_endPosition, EChessPieces.PAWN_WHITE, chessField);
        Assertions.assertEquals(expected, pawn.getPotentialPositions());

        // left pawn start
        pawn = new Pawn(pos_black_leftPawnStart, EChessPieces.PAWN_BLACK, chessField);
        expected.add(new Position('A', 6));
        expected.add(new Position('A', 5));
        expected.add(new Position('B', 6));
        Assertions.assertEquals(expected, pawn.getPotentialPositions());
        expected.clear();
        // right pawn start
        pawn = new Pawn(pos_black_rightPawnStart, EChessPieces.PAWN_BLACK, chessField);
        expected.add(new Position('H', 6));
        expected.add(new Position('H', 5));
        expected.add(new Position('G', 6));
        Assertions.assertEquals(expected, pawn.getPotentialPositions());
        expected.clear();
        // middle pawn start
        pawn = new Pawn(pos_black_midPawnStart, EChessPieces.PAWN_BLACK, chessField);
        expected.add(new Position('D', 6));
        expected.add(new Position('D', 5));
        expected.add(new Position('E', 6));
        expected.add(new Position('C', 6));
        Assertions.assertEquals(expected, pawn.getPotentialPositions());
        expected.clear();
        // middle
        pawn = new Pawn(pos_middle, EChessPieces.PAWN_BLACK, chessField);
        expected.add(new Position('D', 3));
        expected.add(new Position('E', 3));
        expected.add(new Position('C', 3));
        Assertions.assertEquals(expected, pawn.getPotentialPositions());
        expected.clear();
        // end
        pawn = new Pawn(pos_black_endPosition, EChessPieces.PAWN_BLACK, chessField);
        Assertions.assertEquals(expected, pawn.getPotentialPositions());
    }

    @Test
    @Override
    public void test_canCapturePosition() {
        Position possibleWhite = new Position('D', 3);
        Position possibleBlack = new Position('D', 6);

        pawn = new Pawn(pos_white_midPawnStart, EChessPieces.PAWN_WHITE, chessField);
        Assertions.assertTrue(pawn.canCapturePosition(possibleWhite));
        pawn = new Pawn(pos_black_midPawnStart, EChessPieces.PAWN_WHITE, chessField);
        Assertions.assertFalse(pawn.canCapturePosition(possibleWhite));

        pawn = new Pawn(pos_black_midPawnStart, EChessPieces.PAWN_BLACK, chessField);
        Assertions.assertTrue(pawn.canCapturePosition(possibleBlack));
        pawn = new Pawn(pos_white_midPawnStart, EChessPieces.PAWN_BLACK, chessField);
        Assertions.assertFalse(pawn.canCapturePosition(possibleBlack));
    }

    @Test
    @Override
    public void test_canActuallyCapturePosition() {

    }
}