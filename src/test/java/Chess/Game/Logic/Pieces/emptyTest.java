package Chess.Game.Logic.Pieces;

import Chess.Game.Logic.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Fitor Avdiji
 *
 * Tests for the Class Empty
 */
class emptyTest implements IChessPieceTest{

    IChessPiece empty = new Empty(pos_middle, EChessPieces.EMPTY, chessField);
    private Set<Position> emptySet = new HashSet<>();

    @Test
    @Override
    public void test_Constructor() {

    }

    @Test
    @Override
    public void test_getPotentialPositions() {
        Assertions.assertEquals(emptySet, empty.getPotentialPositions());
    }

    @Test
    @Override
    public void test_canCapturePosition() {
        Assertions.assertFalse(empty.canCapturePosition(new Position('d', 5)));
    }

    @Override
    public void test_canActuallyCapturePosition() {

    }
}