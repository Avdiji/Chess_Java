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
 * Class to test the Queen Moveset
 */
class QueenMovementTest implements IPieceMovementTest {

    IChessFieldMovement queen = new QueenMovement();

    // using rook and bishop since the queen is a combination of the two
    IChessFieldMovement rook = new RookMovement();
    IChessFieldMovement bishop = new BishopMovement();

    @Test
    @Override
    public void test_getPotentialPosition_white() {
        Set<Position> expected = new HashSet<>();
        // test white corner left
        expected.addAll(rook.getPotentialPositions_white(pos_white_cornerLeft));
        expected.addAll(bishop.getPotentialPositions_white(pos_white_cornerLeft));
        Assertions.assertEquals(expected, queen.getPotentialPositions_white(pos_white_cornerLeft));
        expected.clear();
        // test white corner right
        expected.addAll(rook.getPotentialPositions_white(pos_white_cornerRight));
        expected.addAll(bishop.getPotentialPositions_white(pos_white_cornerRight));
        Assertions.assertEquals(expected, queen.getPotentialPositions_white(pos_white_cornerRight));
        expected.clear();
        // test middle
        expected.addAll(rook.getPotentialPositions_white(pos_middle));
        expected.addAll(bishop.getPotentialPositions_white(pos_middle));
        Assertions.assertEquals(expected, queen.getPotentialPositions_white(pos_middle));
    }

    @Test
    @Override
    public void test_getPotentialPosition_black() {
        Set<Position> expected = new HashSet<>();
        // test white corner left
        expected.addAll(rook.getPotentialPositions_black(pos_black_cornerLeft));
        expected.addAll(bishop.getPotentialPositions_black(pos_black_cornerLeft));
        Assertions.assertEquals(expected, queen.getPotentialPositions_black(pos_black_cornerLeft));
        expected.clear();
        // test white corner right
        expected.addAll(rook.getPotentialPositions_black(pos_black_cornerRight));
        expected.addAll(bishop.getPotentialPositions_black(pos_black_cornerRight));
        Assertions.assertEquals(expected, queen.getPotentialPositions_black(pos_black_cornerRight));
        expected.clear();
        // test middle
        expected.addAll(rook.getPotentialPositions_black(pos_middle));
        expected.addAll(bishop.getPotentialPositions_black(pos_middle));
        Assertions.assertEquals(expected, queen.getPotentialPositions_black(pos_middle));
    }

    @Test
    @Override
    public void test_canOccupyPosition() {

        Position possible = new Position('a', 1);
        Position impossible = new Position('h', 1);

        Assertions.assertTrue(queen.canOccupyPosition(pos_middle, EChessPieces.QUEEN_WHITE, possible));
        Assertions.assertFalse(queen.canOccupyPosition(pos_middle, EChessPieces.QUEEN_WHITE, impossible));

        Assertions.assertTrue(queen.canOccupyPosition(pos_middle, EChessPieces.QUEEN_BLACK, possible));
        Assertions.assertFalse(queen.canOccupyPosition(pos_middle, EChessPieces.QUEEN_BLACK, impossible));

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Assertions.assertTrue(queen.canOccupyPosition(pos_middle, EChessPieces.EMPTY, possible));
        });
    }
}
