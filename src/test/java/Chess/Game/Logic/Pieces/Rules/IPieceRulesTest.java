package Chess.Game.Logic.Pieces.Rules;

import Chess.Game.Logic.Position;
import org.junit.jupiter.api.Test;

/**
 * @author Fitor Avdiji
 * <p>
 * Interface to test the functions from IChessFieldRules for every Piece in the Game
 */
public interface IPieceRulesTest {

    // positions (from whites perspective)
    public static final Position pos_white_leftPawnStart = new Position('a', 2);
    public static final Position pos_white_rightPawnStart = new Position('h', 2);
    public static final Position pos_white_midPawnStart = new Position('d', 2);

    // positions (from blacks perspective)
    public static final Position pos_black_leftPawnStart = new Position('a', 7);
    public static final Position pos_black_rightPawnStart = new Position('h', 7);
    public static final Position pos_black_midPawnStart = new Position('d', 7);

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
