package Chess.Game.Logic.Pieces.Movement;

import Chess.Game.Logic.Pieces.EChessPieces;
import Chess.Game.Logic.Position;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Fitor Avdiji
 * <p>
 * Class implements every move the Rook could potentially execute
 */
public class RookMovement implements IChessFieldMovement {

    @Override
    public Set<Position> getPotentialPositions_white(Position currentPosition) {
        Set<Position> result = new HashSet<>();

        for (int i = 1; i < 9; ++i) {
            if (i != currentPosition.getColumn())
                result.add(new Position(currentPosition.getRow(), i));
            if ('a' + i - 1 != currentPosition.getRow())
                result.add(new Position((char) ('a' + i - 1), currentPosition.getColumn()));
        }
        return result;
    }

    @Override
    public Set<Position> getPotentialPositions_black(Position currentPosition) {
        return getPotentialPositions_white(currentPosition);
    }

    @Override
    public boolean canOccupyPosition(Position currentPosition, EChessPieces currentPiece, Position target) {
        if (currentPiece != EChessPieces.ROOK_WHITE && currentPiece != EChessPieces.ROOK_BLACK) {
            throw new IllegalArgumentException("currentPiece must be a Rook!");
        }
        return getPotentialPositions_white(currentPosition).contains(target);
    }
}
