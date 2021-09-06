package Chess.Game.Logic.Pieces;

import Chess.Game.Logic.ChessField;
import Chess.Game.Logic.Position;

public interface IChessPieceTest {

    ChessField chessField = new ChessField();

    // corners white
    public static final Position pos_white_cornerLeft = new Position('A', 1);
    public static final Position pos_white_cornerRight = new Position('H', 1);
    // corners black
    public static final Position pos_black_cornerLeft = new Position('A', 8);
    public static final Position pos_black_cornerRight = new Position('H', 8);
    // middle
    public static final Position pos_middle = new Position('D', 4);

    /** Test the Constructor of the Subclasses of IChessPiece **/
    public void test_Constructor();
    /** Test the Method getPotentialPositions from the Interface IChessPiece **/
    public void test_getPotentialPositions();
    /** Test the Method canCapturePosition from the Interface IChessPiece **/
    public void test_canCapturePosition();
    /** Test the Method canActuallyCapturePosition from the Interface IChessPiece **/
    public void test_canActuallyCapturePosition();

}
