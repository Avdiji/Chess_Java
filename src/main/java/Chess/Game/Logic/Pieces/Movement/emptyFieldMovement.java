package Chess.Game.Logic.Pieces.Movement;

import Chess.Game.Logic.Pieces.EChessPieces;
import Chess.Game.Logic.Position;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Fitor Avdiji
 * <p>
 * Class implements every move the emptyField could potentially execute
 */
public class emptyFieldMovement implements IChessFieldMovement {

    @Override
    public Set<Position> getPotentialPositions_white(Position currentPosition) {
        return new HashSet<>();
    }

    @Override
    public Set<Position> getPotentialPositions_black(Position currentPosition) {
        return new HashSet<>();
    }

    @Override
    public boolean canOccupyPosition(Position currentPosition, EChessPieces currentPiece, Position target) {
        if (currentPiece != EChessPieces.EMPTY) {
            throw new IllegalArgumentException("currentPiece must be EMPTY!");
        }
        return false;
    }
}
