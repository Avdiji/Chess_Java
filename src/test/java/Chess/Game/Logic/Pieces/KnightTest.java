package Chess.Game.Logic.Pieces;

import Chess.Game.Logic.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Fitor Avdiji
 * <p>
 * Tests for the Class Knight
 */
class KnightTest implements IChessPieceTest {

    IChessPiece knight;

    @Test
    @Override
    public void test_getPotentialPositions() {
        Set<Position> expected = new HashSet<>();
        //test white corner left
        knight = new Knight(pos_white_cornerLeft, EChessPieces.KNIGHT_WHITE, chessField);
        expected.add(new Position('b', 3));
        expected.add(new Position('c', 2));
        Assertions.assertEquals(expected, knight.getPotentialPositions());
        expected.clear();
        //test white corner right
        knight = new Knight(pos_white_cornerRight, EChessPieces.KNIGHT_WHITE, chessField);
        expected.add(new Position('g', 3));
        expected.add(new Position('f', 2));
        Assertions.assertEquals(expected, knight.getPotentialPositions());
        expected.clear();
        //test middle
        knight = new Knight(pos_middle, EChessPieces.KNIGHT_WHITE, chessField);
        expected.add(new Position('c', 6));
        expected.add(new Position('e', 6));
        expected.add(new Position('c', 2));
        expected.add(new Position('e', 2));
        expected.add(new Position('f', 5));
        expected.add(new Position('f', 3));
        expected.add(new Position('b', 5));
        expected.add(new Position('b', 3));
        Assertions.assertEquals(expected, knight.getPotentialPositions());
        expected.clear();

        //test black corner left
        knight = new Knight(pos_black_cornerLeft, EChessPieces.KNIGHT_BLACK, chessField);
        expected.add(new Position('b', 6));
        expected.add(new Position('c', 7));
        Assertions.assertEquals(expected, knight.getPotentialPositions());
        expected.clear();
        //test black corner right
        knight = new Knight(pos_black_cornerRight, EChessPieces.KNIGHT_BLACK, chessField);
        expected.add(new Position('g', 6));
        expected.add(new Position('f', 7));
        Assertions.assertEquals(expected, knight.getPotentialPositions());
        expected.clear();
        //test middle
        knight = new Knight(pos_middle, EChessPieces.KNIGHT_BLACK, chessField);
        expected.add(new Position('c', 6));
        expected.add(new Position('e', 6));
        expected.add(new Position('c', 2));
        expected.add(new Position('e', 2));
        expected.add(new Position('f', 5));
        expected.add(new Position('f', 3));
        expected.add(new Position('b', 5));
        expected.add(new Position('b', 3));
        Assertions.assertEquals(expected, knight.getPotentialPositions());
    }

    @Test
    @Override
    public void test_canCapturePosition() {
        Position possible = new Position('c', 6);
        Position impossible = new Position('a', 1);

        knight = new Knight(pos_middle, EChessPieces.KNIGHT_WHITE, chessField);
        Assertions.assertTrue(knight.canCapturePosition(possible));
        Assertions.assertFalse(knight.canCapturePosition(impossible));

        knight = new Knight(pos_middle, EChessPieces.KNIGHT_BLACK, chessField);
        Assertions.assertTrue(knight.canCapturePosition(possible));
        Assertions.assertFalse(knight.canCapturePosition(impossible));
    }

    @Override
    public void test_canActuallyCapturePosition() {

    }
}