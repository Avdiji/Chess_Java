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
 * Class tests the method of the class emptyFieldRules
 */
class emptyFieldMovementTest implements IPieceMovementTest {

    private IChessFieldMovement emptyRules = new emptyFieldMovement();
    private Set<Position> emptySet = new HashSet<>();


    @Test
    @Override
    public void test_getPotentialPosition_white() {
        Assertions.assertEquals(emptySet, emptyRules.getPotentialPositions_white(pos_middle));
    }

    @Test
    @Override
    public void test_getPotentialPosition_black() {
        Assertions.assertEquals(emptySet, emptyRules.getPotentialPositions_black(pos_middle));
    }

    @Test
    @Override
    public void test_canOccupyPosition() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            emptyRules.canOccupyPosition(new Position('d', 4), EChessPieces.PAWN_WHITE, new Position('d', 5));
        });
        Assertions.assertFalse(emptyRules.canOccupyPosition(new Position('d', 4), EChessPieces.EMPTY, new Position('d', 5)));
    }
}
