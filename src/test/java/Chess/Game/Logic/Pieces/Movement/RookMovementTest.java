package Chess.Game.Logic.Pieces.Movement;

import Chess.Game.Logic.Pieces.EChessPieces;
import Chess.Game.Logic.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Fitor Avdiji
 * <p>
 * Class to test the Rook Moveset
 */
class RookMovementTest implements IPieceMovementTest {

    IChessFieldMovement rook = new RookMovement();

    @Test
    @Override
    public void test_getPotentialPosition_white() {
        Set<Position> expected = new HashSet<>();
        //test white corner left
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
        Assertions.assertEquals(expected, rook.getPotentialPositions_white(pos_white_cornerLeft));
        expected.clear();
        //test white corner right
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
        Assertions.assertEquals(expected, rook.getPotentialPositions_white(pos_white_cornerRight));
        expected.clear();
        // test middle
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
        Assertions.assertEquals(expected, rook.getPotentialPositions_white(pos_middle));
    }

    @Test
    @Override
    public void test_getPotentialPosition_black() {
        Set<Position> expected = new HashSet<>();
        //test black corner left
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
        Assertions.assertEquals(expected, rook.getPotentialPositions_black(pos_black_cornerLeft));
        expected.clear();
        //test black corner right
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
        Assertions.assertEquals(expected, rook.getPotentialPositions_black(pos_black_cornerRight));
        expected.clear();
        // test middle
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
        Assertions.assertEquals(expected, rook.getPotentialPositions_black(pos_middle));
    }

    @Test
    @Override
    public void test_canOccupyPosition() {

        Position possible = new Position('g', 4);
        Position impossible = new Position('a', 1);

        Assertions.assertTrue(rook.canOccupyPosition(pos_middle, EChessPieces.ROOK_WHITE, possible));
        Assertions.assertFalse(rook.canOccupyPosition(pos_middle, EChessPieces.ROOK_WHITE, impossible));

        Assertions.assertTrue(rook.canOccupyPosition(pos_middle, EChessPieces.ROOK_BLACK, possible));
        Assertions.assertFalse(rook.canOccupyPosition(pos_middle, EChessPieces.ROOK_BLACK, impossible));

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            rook.canOccupyPosition(pos_middle, EChessPieces.EMPTY, possible);
        });
    }
}
