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
 * Class to test the King Moveset
 */
class KingMovementTest implements IPieceMovementTest {

    IChessFieldMovement king = new KingMovement();

    @Test
    @Override
    public void test_getPotentialPosition_white() {
        Set<Position> expected = new HashSet<>();
        //test white corner left
        expected.add(new Position('a', 2));
        expected.add(new Position('b', 2));
        expected.add(new Position('b', 1));
        Assertions.assertEquals(expected, king.getPotentialPositions_white(pos_white_cornerLeft));
        expected.clear();
        //test white corner right
        expected.add(new Position('h', 2));
        expected.add(new Position('g', 2));
        expected.add(new Position('g', 1));
        Assertions.assertEquals(expected, king.getPotentialPositions_white(pos_white_cornerRight));
        expected.clear();
        // test middle
        expected.add(new Position('d', 5));
        expected.add(new Position('e', 5));
        expected.add(new Position('e', 4));
        expected.add(new Position('e', 3));
        expected.add(new Position('d', 3));
        expected.add(new Position('c', 3));
        expected.add(new Position('c', 4));
        expected.add(new Position('c', 5));
        Assertions.assertEquals(expected, king.getPotentialPositions_white(pos_middle));
    }

    @Test
    @Override
    public void test_getPotentialPosition_black() {
        Set<Position> expected = new HashSet<>();
        //test black corner left
        expected.add(new Position('a', 7));
        expected.add(new Position('b', 7));
        expected.add(new Position('b', 8));
        Assertions.assertEquals(expected, king.getPotentialPositions_black(pos_black_cornerLeft));
        expected.clear();
        //test black corner right
        expected.add(new Position('h', 7));
        expected.add(new Position('g', 7));
        expected.add(new Position('g', 8));
        Assertions.assertEquals(expected, king.getPotentialPositions_black(pos_black_cornerRight));
        expected.clear();
        // test middle
        expected.add(new Position('d', 5));
        expected.add(new Position('e', 5));
        expected.add(new Position('e', 4));
        expected.add(new Position('e', 3));
        expected.add(new Position('d', 3));
        expected.add(new Position('c', 3));
        expected.add(new Position('c', 4));
        expected.add(new Position('c', 5));
        Assertions.assertEquals(expected, king.getPotentialPositions_black(pos_middle));
    }

    @Test
    @Override
    public void test_canOccupyPosition() {

        Position possible = new Position('d', 5);
        Position impossible = new Position('a', 5);

        Assertions.assertTrue(king.canOccupyPosition(pos_middle, EChessPieces.KING_WHITE, possible));
        Assertions.assertFalse(king.canOccupyPosition(pos_middle, EChessPieces.KING_WHITE, impossible));

        Assertions.assertTrue(king.canOccupyPosition(pos_middle, EChessPieces.KING_BLACK, possible));
        Assertions.assertFalse(king.canOccupyPosition(pos_middle, EChessPieces.KING_BLACK, impossible));

        Assertions.assertThrows(IllegalArgumentException.class, () ->{
           king.canOccupyPosition(pos_middle, EChessPieces.PAWN_WHITE, possible);
        });

    }
}
