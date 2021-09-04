package Chess.Game.Logic.Pieces.Movement;

import Chess.Game.Logic.Position;
import org.junit.jupiter.api.Test;

/**
 * @author Fitor Avdiji
 * <p>
 * Interface to test the functions from IChessFieldRules for every Piece in the Game
 */
public interface IPieceMovementTest {

    // corners white
    public static final Position pos_white_cornerLeft = new Position('a', 1);
    public static final Position pos_white_cornerRight = new Position('h', 1);
    // corners black
    public static final Position pos_black_cornerLeft = new Position('a', 8);
    public static final Position pos_black_cornerRight = new Position('h', 8);
    // middle
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
