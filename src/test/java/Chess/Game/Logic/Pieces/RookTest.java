package Chess.Game.Logic.Pieces;

import Chess.Game.Logic.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Fitor Avdiji
 * <p>
 * Tests for the Class Rook
 */
class RookTest implements IChessPieceTest {

    IChessPiece rook;

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
        //test white corner left
        rook = new Rook(pos_white_cornerLeft, EChessPieces.ROOK_WHITE, chessField);
        expected.add(new Position('A', 2));
        expected.add(new Position('A', 3));
        expected.add(new Position('A', 4));
        expected.add(new Position('A', 5));
        expected.add(new Position('A', 6));
        expected.add(new Position('A', 7));
        expected.add(new Position('A', 8));
        expected.add(new Position('B', 1));
        expected.add(new Position('C', 1));
        expected.add(new Position('D', 1));
        expected.add(new Position('E', 1));
        expected.add(new Position('F', 1));
        expected.add(new Position('G', 1));
        expected.add(new Position('H', 1));
        Assertions.assertEquals(expected, rook.getPotentialPositions());
        expected.clear();
        //test white corner right
        rook = new Rook(pos_white_cornerRight, EChessPieces.ROOK_WHITE, chessField);
        expected.add(new Position('H', 2));
        expected.add(new Position('H', 3));
        expected.add(new Position('H', 4));
        expected.add(new Position('H', 5));
        expected.add(new Position('H', 6));
        expected.add(new Position('H', 7));
        expected.add(new Position('H', 8));
        expected.add(new Position('G', 1));
        expected.add(new Position('F', 1));
        expected.add(new Position('E', 1));
        expected.add(new Position('D', 1));
        expected.add(new Position('C', 1));
        expected.add(new Position('B', 1));
        expected.add(new Position('A', 1));
        Assertions.assertEquals(expected, rook.getPotentialPositions());
        expected.clear();
        // test middle
        rook = new Rook(pos_middle, EChessPieces.ROOK_WHITE, chessField);
        expected.add(new Position('D', 1));
        expected.add(new Position('D', 2));
        expected.add(new Position('D', 3));
        expected.add(new Position('D', 5));
        expected.add(new Position('D', 6));
        expected.add(new Position('D', 7));
        expected.add(new Position('D', 8));
        expected.add(new Position('A', 4));
        expected.add(new Position('B', 4));
        expected.add(new Position('C', 4));
        expected.add(new Position('E', 4));
        expected.add(new Position('F', 4));
        expected.add(new Position('G', 4));
        expected.add(new Position('H', 4));
        Assertions.assertEquals(expected, rook.getPotentialPositions());
    }

    private void test_getPotentialPositions_black() {
        Set<Position> expected = new HashSet<>();
        //test black corner left
        rook = new Rook(pos_black_cornerLeft, EChessPieces.ROOK_BLACK, chessField);
        expected.add(new Position('A', 2));
        expected.add(new Position('A', 3));
        expected.add(new Position('A', 4));
        expected.add(new Position('A', 5));
        expected.add(new Position('A', 6));
        expected.add(new Position('A', 7));
        expected.add(new Position('A', 1));
        expected.add(new Position('B', 8));
        expected.add(new Position('C', 8));
        expected.add(new Position('D', 8));
        expected.add(new Position('E', 8));
        expected.add(new Position('F', 8));
        expected.add(new Position('G', 8));
        expected.add(new Position('H', 8));
        Assertions.assertEquals(expected, rook.getPotentialPositions());
        expected.clear();
        //test black corner right
        rook = new Rook(pos_black_cornerRight, EChessPieces.ROOK_BLACK, chessField);
        expected.add(new Position('H', 2));
        expected.add(new Position('H', 3));
        expected.add(new Position('H', 4));
        expected.add(new Position('H', 5));
        expected.add(new Position('H', 6));
        expected.add(new Position('H', 7));
        expected.add(new Position('H', 1));
        expected.add(new Position('G', 8));
        expected.add(new Position('F', 8));
        expected.add(new Position('E', 8));
        expected.add(new Position('D', 8));
        expected.add(new Position('C', 8));
        expected.add(new Position('B', 8));
        expected.add(new Position('A', 8));
        Assertions.assertEquals(expected, rook.getPotentialPositions());
        expected.clear();
        // test middle
        rook = new Rook(pos_middle, EChessPieces.ROOK_BLACK, chessField);
        expected.add(new Position('D', 1));
        expected.add(new Position('D', 2));
        expected.add(new Position('D', 3));
        expected.add(new Position('D', 5));
        expected.add(new Position('D', 6));
        expected.add(new Position('D', 7));
        expected.add(new Position('D', 8));
        expected.add(new Position('A', 4));
        expected.add(new Position('B', 4));
        expected.add(new Position('C', 4));
        expected.add(new Position('E', 4));
        expected.add(new Position('F', 4));
        expected.add(new Position('G', 4));
        expected.add(new Position('H', 4));
        Assertions.assertEquals(expected, rook.getPotentialPositions());
    }

    @Test
    @Override
    public void test_canCapturePosition() {
        Position possible = new Position('G', 4);
        Position impossible = new Position('A', 1);

        rook = new Rook(pos_middle, EChessPieces.ROOK_WHITE, chessField);
        Assertions.assertTrue(rook.canCapturePosition(possible));
        Assertions.assertFalse(rook.canCapturePosition(impossible));

        rook = new Rook(pos_middle, EChessPieces.ROOK_BLACK, chessField);
        Assertions.assertTrue(rook.canCapturePosition(possible));
        Assertions.assertFalse(rook.canCapturePosition(impossible));

    }

    @Test
    @Override
    public void test_canActuallyCapturePosition() {

    }
}