package Chess.Game.Logic.Pieces;

import Chess.Game.Logic.ChessFieldButton;
import Chess.Game.Logic.Player.EPlayerColor;
import Chess.Game.Logic.Position;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Fitor Avdiji
 * <p>
 * Class represents an Empty Field on the ChessField
 */
public class Empty implements IChessPiece {

    /** Default Constructor **/
    public Empty() {
    }

    @Override
    public Set<Position> getActualPositions(Position currentPosition, EPlayerColor currentPlayerColor, List<ChessFieldButton> field) {
        return new HashSet<>();
    }
}
