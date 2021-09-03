package Chess.Game.Logic.Pieces.Movement;

import Chess.Game.Logic.Pieces.EChessPieces;
import Chess.Game.Logic.Position;

import java.util.Set;

/**
 * @author Fitor Avdiji
 * <p>
 * Class implements every move the Queen could potentially execute
 */
public class QueenMovement implements IChessFieldMovement {
    @Override
    public Set<Position> getPotentialPositions_white(Position currentPosition) {
        Set<Position> result;

        // since the queen is basically a combination of rook and bishop we I use the already defined movement Sets
        BishopMovement bishopHlp = new BishopMovement();
        RookMovement rookHlp = new RookMovement();

        result = bishopHlp.getPotentialPositions_white(currentPosition);
        result.addAll(rookHlp.getPotentialPositions_white(currentPosition));
        return result;
    }

    @Override
    public Set<Position> getPotentialPositions_black(Position currentPosition) {
        return getPotentialPositions_white(currentPosition);
    }

    @Override
    public boolean canOccupyPosition(Position currentPosition, EChessPieces currentPiece, Position target) {
        if (currentPiece != EChessPieces.QUEEN_WHITE && currentPiece != EChessPieces.QUEEN_BLACK) {
            throw new IllegalArgumentException("The current Piece must be a Queen!");
        }
        return getPotentialPositions_white(currentPosition).contains(target);
    }
}
