package Chess.Game.Logic.Pieces;

import Chess.Game.Logic.ChessField;
import Chess.Game.Logic.Position;

import java.util.Set;

/**
 * @author Fitor Avdiji
 * <p>
 * Class represents a Queen-Piece
 */
public class Queen implements IChessPiece {


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
}
