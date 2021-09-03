package Chess.Game.Logic.Pieces.Movement;

import Chess.Game.Logic.Position;
import org.junit.jupiter.api.Test;

/**
 * @author Fitor Avdiji
 * <p>
 * Interface to test the functions from IChessFieldRules for every Piece in the Game
 */
public interface IPieceMovementTest {

    // positions (from whites perspective)
    public static final Position pos_white_leftPawnStart = new Position('a', 2);
    public static final Position pos_white_rightPawnStart = new Position('h', 2);
    public static final Position pos_white_midPawnStart = new Position('d', 2);
    public static final Position pos_white_endPosition = new Position('d', 8);

    // positions (from blacks perspective)
    public static final Position pos_black_leftPawnStart = new Position('a', 7);
    public static final Position pos_black_rightPawnStart = new Position('h', 7);
    public static final Position pos_black_midPawnStart = new Position('d', 7);
    public static final Position pos_black_endPosition = new Position('d', 1);

    // positions for both
    public static final Position pos_middle = new Position('d', 4);

    /**
     * Method tests, whether the Method getPotentialPosition_white from Interface IChessFieldRules works as expected
     */
    @Test
    public void test_getPotentialPosition_white();

    /**
     * Method tests, whether the Method getPotentialPosition_black from Interface IChessFieldRules works as expected
     */
    @Test
    public void test_getPotentialPosition_black();

    /**
     * Method tests, whether the Method canOccupyPosition from Interface IChessFieldRules works as expected
     */
    @Test
    public void test_canOccupyPosition();

}
