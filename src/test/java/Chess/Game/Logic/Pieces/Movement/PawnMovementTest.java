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
 * Class to test the pawn rules
 */
class PawnMovementTest implements IPieceMovementTest {

    private PawnMovement pawnRules = new PawnMovement();

    @Override
    @Test
    public void test_getPotentialPosition_white() {
        Set<Position> expected = new HashSet<>();
        // left pawn start
        expected.add(new Position('a', 3));
        expected.add(new Position('b', 3));
        expected.add(new Position('a', 4));
        Assertions.assertEquals(expected, pawnRules.getPotentialPositions_white(pos_white_leftPawnStart));
        expected.clear();
        //right pawn start
        expected.add(new Position('g', 3));
        expected.add(new Position('h', 4));
        expected.add(new Position('h', 3));
        Assertions.assertEquals(expected, pawnRules.getPotentialPositions_white(pos_white_rightPawnStart));
        expected.clear();
        //middle pawn start
        expected.add(new Position('d', 3));
        expected.add(new Position('d', 4));
        expected.add(new Position('c', 3));
        expected.add(new Position('e', 3));
        Assertions.assertEquals(expected, pawnRules.getPotentialPositions_white(pos_white_midPawnStart));
        expected.clear();
        // middle
        expected.add(new Position('e', 5));
        expected.add(new Position('c', 5));
        expected.add(new Position('d', 5));
        Assertions.assertEquals(expected, pawnRules.getPotentialPositions_white(pos_middle));
        expected.clear();
        //end
        Assertions.assertEquals(expected, pawnRules.getPotentialPositions_white(pos_white_endPosition));

    }

    @Test
    @Override
    public void test_getPotentialPosition_black() {
        Set<Position> expected = new HashSet<>();
        // left pawn start
        expected.add(new Position('a',6));
        expected.add(new Position('a',5));
        expected.add(new Position('b',6));
        Assertions.assertEquals(expected, pawnRules.getPotentialPositions_black(pos_black_leftPawnStart));
        expected.clear();
        // right pawn start
        expected.add(new Position('h',6));
        expected.add(new Position('h',5));
        expected.add(new Position('g',6));
        Assertions.assertEquals(expected, pawnRules.getPotentialPositions_black(pos_black_rightPawnStart));
        expected.clear();
        // middle pawn start
        expected.add(new Position('d',6));
        expected.add(new Position('d',5));
        expected.add(new Position('e',6));
        expected.add(new Position('c',6));
        Assertions.assertEquals(expected, pawnRules.getPotentialPositions_black(pos_black_midPawnStart));
        expected.clear();
        // middle
        expected.add(new Position('d', 3));
        expected.add(new Position('e', 3));
        expected.add(new Position('c', 3));
        Assertions.assertEquals(expected, pawnRules.getPotentialPositions_black(pos_middle));
        expected.clear();
        // end
        Assertions.assertEquals(expected, pawnRules.getPotentialPositions_black(pos_black_endPosition));


    }

    @Test
    @Override
    public void test_canOccupyPosition() {

        Position possibleWhite = new Position('d', 3);
        Position possibleBlack = new Position('d', 6);

        Assertions.assertTrue(pawnRules.canOccupyPosition(pos_white_midPawnStart, EChessPieces.PAWN_WHITE, possibleWhite));
        Assertions.assertFalse(pawnRules.canOccupyPosition(pos_black_midPawnStart, EChessPieces.PAWN_WHITE, possibleWhite));

        Assertions.assertTrue(pawnRules.canOccupyPosition(pos_black_midPawnStart, EChessPieces.PAWN_BLACK, possibleBlack));
        Assertions.assertFalse(pawnRules.canOccupyPosition(pos_white_midPawnStart, EChessPieces.PAWN_BLACK, possibleBlack));

        Assertions.assertThrows(IllegalArgumentException.class, () ->{
           pawnRules.canOccupyPosition(pos_white_midPawnStart, EChessPieces.EMPTY, possibleWhite);
        });
    }
}