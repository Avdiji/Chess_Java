package Chess.Game.Logic.Pieces;

import Chess.Game.Logic.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Fitor Avdiji
 * <p>
 * Tests for the Class Bishop
 */
class BishopTest implements IChessPieceTest {

    IChessPiece bishop;

    @Test
    @Override
    public void test_Constructor() {

    }

    @Test
    @Override
    public void test_getPotentialPositions() {
        test_getPotentialPositions_white();
        test_getPotentialPositions_black();
    }

    private void test_getPotentialPositions_white() {
        Set<Position> expected = new HashSet<>();
        // test white corner left
        bishop = new Bishop(pos_white_cornerLeft, EChessPieces.BISHOP_WHITE, chessField);
        expected.add(new Position('B', 2));
        expected.add(new Position('C', 3));
        expected.add(new Position('D', 4));
        expected.add(new Position('E', 5));
        expected.add(new Position('F', 6));
        expected.add(new Position('G', 7));
        expected.add(new Position('H', 8));
        Assertions.assertEquals(expected, bishop.getPotentialPositions());
        expected.clear();
        // test white corner right
        bishop = new Bishop(pos_white_cornerRight, EChessPieces.BISHOP_WHITE, chessField);
        expected.add(new Position('G', 2));
        expected.add(new Position('F', 3));
        expected.add(new Position('E', 4));
        expected.add(new Position('D', 5));
        expected.add(new Position('C', 6));
        expected.add(new Position('B', 7));
        expected.add(new Position('A', 8));
        Assertions.assertEquals(expected, bishop.getPotentialPositions());
        expected.clear();
        // test middle
        bishop = new Bishop(pos_middle, EChessPieces.BISHOP_WHITE, chessField);
        expected.add(new Position('A', 1));
        expected.add(new Position('B', 2));
        expected.add(new Position('C', 3));
        expected.add(new Position('E', 5));
        expected.add(new Position('F', 6));
        expected.add(new Position('G', 7));
        expected.add(new Position('H', 8));
        expected.add(new Position('A', 7));
        expected.add(new Position('B', 6));
        expected.add(new Position('C', 5));
        expected.add(new Position('E', 3));
        expected.add(new Position('F', 2));
        expected.add(new Position('G', 1));
        Assertions.assertEquals(expected, bishop.getPotentialPositions());
    }

    private void test_getPotentialPositions_black() {
        Set<Position> expected = new HashSet<>();
        // test black corner left
        bishop = new Bishop(pos_black_cornerLeft, EChessPieces.BISHOP_BLACK, chessField);
        expected.add(new Position('B', 7));
        expected.add(new Position('C', 6));
        expected.add(new Position('D', 5));
        expected.add(new Position('E', 4));
        expected.add(new Position('F', 3));
        expected.add(new Position('G', 2));
        expected.add(new Position('H', 1));
        Assertions.assertEquals(expected, bishop.getPotentialPositions());
        expected.clear();
        // test black corner right
        bishop = new Bishop(pos_black_cornerRight, EChessPieces.BISHOP_BLACK, chessField);
        expected.add(new Position('G', 7));
        expected.add(new Position('F', 6));
        expected.add(new Position('E', 5));
        expected.add(new Position('D', 4));
        expected.add(new Position('C', 3));
        expected.add(new Position('B', 2));
        expected.add(new Position('A', 1));
        Assertions.assertEquals(expected, bishop.getPotentialPositions());
        expected.clear();
        // test middle
        bishop = new Bishop(pos_middle, EChessPieces.BISHOP_BLACK, chessField);
        expected.add(new Position('A', 1));
        expected.add(new Position('B', 2));
        expected.add(new Position('C', 3));
        expected.add(new Position('E', 5));
        expected.add(new Position('F', 6));
        expected.add(new Position('G', 7));
        expected.add(new Position('H', 8));
        expected.add(new Position('A', 7));
        expected.add(new Position('B', 6));
        expected.add(new Position('C', 5));
        expected.add(new Position('E', 3));
        expected.add(new Position('F', 2));
        expected.add(new Position('G', 1));
        Assertions.assertEquals(expected, bishop.getPotentialPositions());
    }

    @Test
    @Override
    public void test_canCapturePosition() {
        Position possible = new Position('A', 1);
        Position impossible = new Position('H', 1);

        bishop = new Bishop(pos_middle, EChessPieces.BISHOP_WHITE, chessField);
        Assertions.assertTrue(bishop.canCapturePosition(possible));
        Assertions.assertFalse(bishop.canCapturePosition(impossible));

        bishop = new Bishop(pos_middle, EChessPieces.BISHOP_BLACK, chessField);
        Assertions.assertTrue(bishop.canCapturePosition(possible));
        Assertions.assertFalse(bishop.canCapturePosition(impossible));
    }

    @Test
    @Override
    public void test_canActuallyCapturePosition() {

    }
}