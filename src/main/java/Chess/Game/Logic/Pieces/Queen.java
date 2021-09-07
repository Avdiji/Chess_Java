package Chess.Game.Logic.Pieces;

import Chess.Game.Logic.ChessField;
import Chess.Game.Logic.ChessFieldButton;
import Chess.Game.Logic.Player.EPlayerColor;
import Chess.Game.Logic.Position;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Fitor Avdiji
 * <p>
 * Class represents a Queen-Piece
 */
public class Queen implements IChessPiece {

    /** Default Constructor **/
    public Queen() {
    }

    @Override
    public Set<Position> getPotentialPositions(final Position currentPosition) {
        Set<Position> result;

        // since the queen is basically a combination of rook and bishop we I use the already defined movement Sets
        IChessPiece bishopHlp = new Bishop();
        IChessPiece rookHlp = new Rook();

        result = bishopHlp.getPotentialPositions(currentPosition);
        result.addAll(rookHlp.getPotentialPositions(currentPosition));
        return result;
    }

    //TODO
    @Override
    public Set<Position> getActualPositions(Position currentPosition, EPlayerColor currentPlayerColor, List<ChessFieldButton> field) {
        Set<Position> result = new HashSet<>();

        return result;
    }
}
