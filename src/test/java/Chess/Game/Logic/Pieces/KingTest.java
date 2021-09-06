package Chess.Game.Logic.Pieces;

import Chess.Game.Logic.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Fitor Avdiji
 * <p>
 * Tests for the Class King
 */
class KingTest implements IChessPieceTest {

    IChessPiece king;

    @Test
    @Override
    public void test_Constructor() {

    }

    @Test
    @Override
    public void test_getPotentialPositions() {
        Set<Position> expected = new HashSet<>();
        //test white corner left
        king = new King(pos_white_cornerLeft, EChessPieces.KING_WHITE, chessField);
        expected.add(new Position('a', 2));
        expected.add(new Position('b', 2));
        expected.add(new Position('b', 1));
        Assertions.assertEquals(expected, king.getPotentialPositions());
        expected.clear();
        //test white corner right
        king = new King(pos_white_cornerRight, EChessPieces.KING_WHITE, chessField);
        expected.add(new Position('h', 2));
        expected.add(new Position('g', 2));
        expected.add(new Position('g', 1));
        Assertions.assertEquals(expected, king.getPotentialPositions());
        expected.clear();
        // test middle
        king = new King(pos_middle, EChessPieces.KING_WHITE, chessField);
        expected.add(new Position('d', 5));
        expected.add(new Position('e', 5));
        expected.add(new Position('e', 4));
        expected.add(new Position('e', 3));
        expected.add(new Position('d', 3));
        expected.add(new Position('c', 3));
        expected.add(new Position('c', 4));
        expected.add(new Position('c', 5));
        Assertions.assertEquals(expected, king.getPotentialPositions());
        expected.clear();

        //test black corner left
        king = new King(pos_black_cornerLeft, EChessPieces.KING_BLACK, chessField);
        expected.add(new Position('a', 7));
        expected.add(new Position('b', 7));
        expected.add(new Position('b', 8));
        Assertions.assertEquals(expected, king.getPotentialPositions());
        expected.clear();
        //test black corner right
        king = new King(pos_black_cornerRight, EChessPieces.KING_BLACK, chessField);
        expected.add(new Position('h', 7));
        expected.add(new Position('g', 7));
        expected.add(new Position('g', 8));
        Assertions.assertEquals(expected, king.getPotentialPositions());
        expected.clear();
        // test middle
        king = new King(pos_middle, EChessPieces.KING_BLACK, chessField);
        expected.add(new Position('d', 5));
        expected.add(new Position('e', 5));
        expected.add(new Position('e', 4));
        expected.add(new Position('e', 3));
        expected.add(new Position('d', 3));
        expected.add(new Position('c', 3));
        expected.add(new Position('c', 4));
        expected.add(new Position('c', 5));
        Assertions.assertEquals(expected, king.getPotentialPositions());
    }

    @Test
    @Override
    public void test_canCapturePosition() {

        Position possible = new Position('d', 5);
        Position impossible = new Position('a', 5);

        king = new King(pos_middle, EChessPieces.KING_WHITE, chessField);
        Assertions.assertTrue(king.canCapturePosition(possible));
        Assertions.assertFalse(king.canCapturePosition(impossible));

        king = new King(pos_middle, EChessPieces.KING_BLACK, chessField);
        Assertions.assertTrue(king.canCapturePosition(possible));
        Assertions.assertFalse(king.canCapturePosition(impossible));
    }

    @Test
    @Override
    public void test_canActuallyCapturePosition() {

    }
}