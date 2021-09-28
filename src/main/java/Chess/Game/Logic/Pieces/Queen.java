package Chess.Game.Logic.Pieces;

import Chess.Game.Logic.ChessFieldButton;
import Chess.Game.Logic.Player.EPlayerColor;
import Chess.Game.Logic.Position;

import java.util.List;
import java.util.Set;

/**
 * @author Fitor Avdiji
 * <p>
 * Class represents a Queen-Piece
 */
public class Queen implements IChessPiece {

    /** Default Constructor **/
    public Queen() {}

    @Override
    public Set<Position> getActualPositions(Position currentPosition, EPlayerColor currentPlayerColor, List<ChessFieldButton> field) {
        IChessPiece rook = new Rook();
        IChessPiece bishop = new Bishop();
        Set<Position> result = rook.getActualPositions(currentPosition, currentPlayerColor, field);
        result.addAll(bishop.getActualPositions(currentPosition, currentPlayerColor, field));

        return result;
    }
}
