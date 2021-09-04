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
 * Class to test Knight Moveset
 */
class KnightMovementTest implements IPieceMovementTest {

    IChessFieldMovement knight = new KnightMovement();

    @Test
    @Override
    public void test_getPotentialPosition_white() {
        Set<Position> expected = new HashSet<>();
        //test white corner left
        expected.add(new Position('b', 3));
        expected.add(new Position('c', 2));
        Assertions.assertEquals(expected, knight.getPotentialPositions_white(pos_white_cornerLeft));
        expected.clear();
        //test white corner right
        expected.add(new Position('g', 3));
        expected.add(new Position('f', 2));
        Assertions.assertEquals(expected, knight.getPotentialPositions_white(pos_white_cornerRight));
        expected.clear();
        //test middle
        expected.add(new Position('c', 6));
        expected.add(new Position('e', 6));
        expected.add(new Position('c', 2));
        expected.add(new Position('e', 2));
        expected.add(new Position('f', 5));
        expected.add(new Position('f', 3));
        expected.add(new Position('b', 5));
        expected.add(new Position('b', 3));
        Assertions.assertEquals(expected, knight.getPotentialPositions_white(pos_middle));
    }

    @Test
    @Override
    public void test_getPotentialPosition_black() {
        Set<Position> expected = new HashSet<>();
        //test black corner left
        expected.add(new Position('b', 6));
        expected.add(new Position('c', 7));
        Assertions.assertEquals(expected, knight.getPotentialPositions_black(pos_black_cornerLeft));
        expected.clear();
        //test black corner right
        expected.add(new Position('g', 6));
        expected.add(new Position('f', 7));
        Assertions.assertEquals(expected, knight.getPotentialPositions_black(pos_black_cornerRight));
        expected.clear();
        //test middle
        expected.add(new Position('c', 6));
        expected.add(new Position('e', 6));
        expected.add(new Position('c', 2));
        expected.add(new Position('e', 2));
        expected.add(new Position('f', 5));
        expected.add(new Position('f', 3));
        expected.add(new Position('b', 5));
        expected.add(new Position('b', 3));
        Assertions.assertEquals(expected, knight.getPotentialPositions_black(pos_middle));
    }

    @Test
    @Override
    public void test_canOccupyPosition() {

        Position possible = new Position('c', 6);
        Position impossible = new Position('a', 1);

        Assertions.assertTrue(knight.canOccupyPosition(pos_middle, EChessPieces.KNIGHT_WHITE, possible));
        Assertions.assertFalse(knight.canOccupyPosition(pos_middle, EChessPieces.KNIGHT_WHITE, impossible));

        Assertions.assertTrue(knight.canOccupyPosition(pos_middle, EChessPieces.KNIGHT_BLACK, possible));
        Assertions.assertFalse(knight.canOccupyPosition(pos_middle, EChessPieces.KNIGHT_BLACK, impossible));

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            knight.canOccupyPosition(pos_middle, EChessPieces.EMPTY, possible);
        });
    }
}
