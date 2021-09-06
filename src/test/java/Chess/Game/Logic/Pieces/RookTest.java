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
        expected.add(new Position('a', 2));
        expected.add(new Position('a', 3));
        expected.add(new Position('a', 4));
        expected.add(new Position('a', 5));
        expected.add(new Position('a', 6));
        expected.add(new Position('a', 7));
        expected.add(new Position('a', 8));
        expected.add(new Position('b', 1));
        expected.add(new Position('c', 1));
        expected.add(new Position('d', 1));
        expected.add(new Position('e', 1));
        expected.add(new Position('f', 1));
        expected.add(new Position('g', 1));
        expected.add(new Position('h', 1));
        Assertions.assertEquals(expected, rook.getPotentialPositions());
        expected.clear();
        //test white corner right
        rook = new Rook(pos_white_cornerRight, EChessPieces.ROOK_WHITE, chessField);
        expected.add(new Position('h', 2));
        expected.add(new Position('h', 3));
        expected.add(new Position('h', 4));
        expected.add(new Position('h', 5));
        expected.add(new Position('h', 6));
        expected.add(new Position('h', 7));
        expected.add(new Position('h', 8));
        expected.add(new Position('g', 1));
        expected.add(new Position('f', 1));
        expected.add(new Position('e', 1));
        expected.add(new Position('d', 1));
        expected.add(new Position('c', 1));
        expected.add(new Position('b', 1));
        expected.add(new Position('a', 1));
        Assertions.assertEquals(expected, rook.getPotentialPositions());
        expected.clear();
        // test middle
        rook = new Rook(pos_middle, EChessPieces.ROOK_WHITE, chessField);
        expected.add(new Position('d', 1));
        expected.add(new Position('d', 2));
        expected.add(new Position('d', 3));
        expected.add(new Position('d', 5));
        expected.add(new Position('d', 6));
        expected.add(new Position('d', 7));
        expected.add(new Position('d', 8));
        expected.add(new Position('a', 4));
        expected.add(new Position('b', 4));
        expected.add(new Position('c', 4));
        expected.add(new Position('e', 4));
        expected.add(new Position('f', 4));
        expected.add(new Position('g', 4));
        expected.add(new Position('h', 4));
        Assertions.assertEquals(expected, rook.getPotentialPositions());
    }

    private void test_getPotentialPositions_black() {
        Set<Position> expected = new HashSet<>();
        //test black corner left
        rook = new Rook(pos_black_cornerLeft, EChessPieces.ROOK_BLACK, chessField);
        expected.add(new Position('a', 2));
        expected.add(new Position('a', 3));
        expected.add(new Position('a', 4));
        expected.add(new Position('a', 5));
        expected.add(new Position('a', 6));
        expected.add(new Position('a', 7));
        expected.add(new Position('a', 1));
        expected.add(new Position('b', 8));
        expected.add(new Position('c', 8));
        expected.add(new Position('d', 8));
        expected.add(new Position('e', 8));
        expected.add(new Position('f', 8));
        expected.add(new Position('g', 8));
        expected.add(new Position('h', 8));
        Assertions.assertEquals(expected, rook.getPotentialPositions());
        expected.clear();
        //test black corner right
        rook = new Rook(pos_black_cornerRight, EChessPieces.ROOK_BLACK, chessField);
        expected.add(new Position('h', 2));
        expected.add(new Position('h', 3));
        expected.add(new Position('h', 4));
        expected.add(new Position('h', 5));
        expected.add(new Position('h', 6));
        expected.add(new Position('h', 7));
        expected.add(new Position('h', 1));
        expected.add(new Position('g', 8));
        expected.add(new Position('f', 8));
        expected.add(new Position('e', 8));
        expected.add(new Position('d', 8));
        expected.add(new Position('c', 8));
        expected.add(new Position('b', 8));
        expected.add(new Position('a', 8));
        Assertions.assertEquals(expected, rook.getPotentialPositions());
        expected.clear();
        // test middle
        rook = new Rook(pos_middle, EChessPieces.ROOK_BLACK, chessField);
        expected.add(new Position('d', 1));
        expected.add(new Position('d', 2));
        expected.add(new Position('d', 3));
        expected.add(new Position('d', 5));
        expected.add(new Position('d', 6));
        expected.add(new Position('d', 7));
        expected.add(new Position('d', 8));
        expected.add(new Position('a', 4));
        expected.add(new Position('b', 4));
        expected.add(new Position('c', 4));
        expected.add(new Position('e', 4));
        expected.add(new Position('f', 4));
        expected.add(new Position('g', 4));
        expected.add(new Position('h', 4));
        Assertions.assertEquals(expected, rook.getPotentialPositions());
    }

    @Test
    @Override
    public void test_canCapturePosition() {
        Position possible = new Position('g', 4);
        Position impossible = new Position('a', 1);

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