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
 * Class to test the Bishop Moveset
 */
class BishopMovementTest implements IPieceMovementTest {

    IChessFieldMovement bishop = new BishopMovement();

    @Test
    @Override
    public void test_getPotentialPosition_white() {
        Set<Position> expected = new HashSet<>();
        // test white corner left
        expected.add(new Position('b', 2));
        expected.add(new Position('c', 3));
        expected.add(new Position('d', 4));
        expected.add(new Position('e', 5));
        expected.add(new Position('f', 6));
        expected.add(new Position('g', 7));
        expected.add(new Position('h', 8));
        Assertions.assertEquals(expected, bishop.getPotentialPositions_white(pos_white_cornerLeft));
        expected.clear();
        // test white corner right
        expected.add(new Position('g', 2));
        expected.add(new Position('f', 3));
        expected.add(new Position('e', 4));
        expected.add(new Position('d', 5));
        expected.add(new Position('c', 6));
        expected.add(new Position('b', 7));
        expected.add(new Position('a', 8));
        Assertions.assertEquals(expected, bishop.getPotentialPositions_white(pos_white_cornerRight));
        expected.clear();
        // test middle
        expected.add(new Position('a', 1));
        expected.add(new Position('b', 2));
        expected.add(new Position('c', 3));
        expected.add(new Position('e', 5));
        expected.add(new Position('f', 6));
        expected.add(new Position('g', 7));
        expected.add(new Position('h', 8));
        expected.add(new Position('a', 7));
        expected.add(new Position('b', 6));
        expected.add(new Position('c', 5));
        expected.add(new Position('e', 3));
        expected.add(new Position('f', 2));
        expected.add(new Position('g', 1));
        Assertions.assertEquals(expected, bishop.getPotentialPositions_white(pos_middle));
    }

    @Test
    @Override
    public void test_getPotentialPosition_black() {
        Set<Position> expected = new HashSet<>();
        // test black corner left
        expected.add(new Position('b', 7));
        expected.add(new Position('c', 6));
        expected.add(new Position('d', 5));
        expected.add(new Position('e', 4));
        expected.add(new Position('f', 3));
        expected.add(new Position('g', 2));
        expected.add(new Position('h', 1));
        Assertions.assertEquals(expected, bishop.getPotentialPositions_black(pos_black_cornerLeft));
        expected.clear();
        // test black corner right
        expected.add(new Position('g', 7));
        expected.add(new Position('f', 6));
        expected.add(new Position('e', 5));
        expected.add(new Position('d', 4));
        expected.add(new Position('c', 3));
        expected.add(new Position('b', 2));
        expected.add(new Position('a', 1));
        Assertions.assertEquals(expected, bishop.getPotentialPositions_black(pos_black_cornerRight));
        expected.clear();
        // test middle
        expected.add(new Position('a', 1));
        expected.add(new Position('b', 2));
        expected.add(new Position('c', 3));
        expected.add(new Position('e', 5));
        expected.add(new Position('f', 6));
        expected.add(new Position('g', 7));
        expected.add(new Position('h', 8));
        expected.add(new Position('a', 7));
        expected.add(new Position('b', 6));
        expected.add(new Position('c', 5));
        expected.add(new Position('e', 3));
        expected.add(new Position('f', 2));
        expected.add(new Position('g', 1));
        Assertions.assertEquals(expected, bishop.getPotentialPositions_black(pos_middle));
    }

    @Test
    @Override
    public void test_canOccupyPosition() {
        Position possible = new Position('a', 1);
        Position impossible = new Position('h', 1);

        Assertions.assertTrue(bishop.canOccupyPosition(pos_middle, EChessPieces.BISHOP_WHITE, possible));
        Assertions.assertFalse(bishop.canOccupyPosition(pos_middle, EChessPieces.BISHOP_WHITE, impossible));

        Assertions.assertTrue(bishop.canOccupyPosition(pos_middle, EChessPieces.BISHOP_BLACK, possible));
        Assertions.assertFalse(bishop.canOccupyPosition(pos_middle, EChessPieces.BISHOP_BLACK, impossible));

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            bishop.canOccupyPosition(pos_middle, EChessPieces.EMPTY, possible);
        });
    }
}
