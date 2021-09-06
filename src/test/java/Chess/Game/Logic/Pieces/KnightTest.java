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
    public void test_Constructor() {

    }

    @Test
    @Override
    public void test_getPotentialPositions() {
        Set<Position> expected = new HashSet<>();
        //test white corner left
        knight = new Knight(pos_white_cornerLeft, EChessPieces.KNIGHT_WHITE, chessField);
        expected.add(new Position('B', 3));
        expected.add(new Position('C', 2));
        Assertions.assertEquals(expected, knight.getPotentialPositions());
        expected.clear();
        //test white corner right
        knight = new Knight(pos_white_cornerRight, EChessPieces.KNIGHT_WHITE, chessField);
        expected.add(new Position('G', 3));
        expected.add(new Position('F', 2));
        Assertions.assertEquals(expected, knight.getPotentialPositions());
        expected.clear();
        //test middle
        knight = new Knight(pos_middle, EChessPieces.KNIGHT_WHITE, chessField);
        expected.add(new Position('C', 6));
        expected.add(new Position('E', 6));
        expected.add(new Position('C', 2));
        expected.add(new Position('E', 2));
        expected.add(new Position('F', 5));
        expected.add(new Position('F', 3));
        expected.add(new Position('B', 5));
        expected.add(new Position('B', 3));
        Assertions.assertEquals(expected, knight.getPotentialPositions());
        expected.clear();

        //test black corner left
        knight = new Knight(pos_black_cornerLeft, EChessPieces.KNIGHT_BLACK, chessField);
        expected.add(new Position('B', 6));
        expected.add(new Position('C', 7));
        Assertions.assertEquals(expected, knight.getPotentialPositions());
        expected.clear();
        //test black corner right
        knight = new Knight(pos_black_cornerRight, EChessPieces.KNIGHT_BLACK, chessField);
        expected.add(new Position('G', 6));
        expected.add(new Position('F', 7));
        Assertions.assertEquals(expected, knight.getPotentialPositions());
        expected.clear();
        //test middle
        knight = new Knight(pos_middle, EChessPieces.KNIGHT_BLACK, chessField);
        expected.add(new Position('C', 6));
        expected.add(new Position('E', 6));
        expected.add(new Position('C', 2));
        expected.add(new Position('E', 2));
        expected.add(new Position('F', 5));
        expected.add(new Position('F', 3));
        expected.add(new Position('B', 5));
        expected.add(new Position('B', 3));
        Assertions.assertEquals(expected, knight.getPotentialPositions());
    }

    @Test
    @Override
    public void test_canCapturePosition() {
        Position possible = new Position('C', 6);
        Position impossible = new Position('A', 1);

        knight = new Knight(pos_middle, EChessPieces.KNIGHT_WHITE, chessField);
        Assertions.assertTrue(knight.canCapturePosition(possible));
        Assertions.assertFalse(knight.canCapturePosition(impossible));

        knight = new Knight(pos_middle, EChessPieces.KNIGHT_BLACK, chessField);
        Assertions.assertTrue(knight.canCapturePosition(possible));
        Assertions.assertFalse(knight.canCapturePosition(impossible));
    }

    @Test
    @Override
    public void test_canActuallyCapturePosition() {

    }
}