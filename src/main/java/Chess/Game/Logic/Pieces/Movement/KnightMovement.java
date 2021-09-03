package Chess.Game.Logic.Pieces.Movement;

import Chess.Game.Logic.Pieces.EChessPieces;
import Chess.Game.Logic.Position;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Fitor Avdiji
 * <p>
 * Class implements every move the Knight could potentially execute
 */
public class KnightMovement implements IChessFieldMovement {
    @Override
    public Set<Position> getPotentialPositions_white(Position currentPosition) {
        Set<Position> result = new HashSet<>();

        int directionX[] = {-1, 1, 2, 2, 1, -1, -2, -2};
        int directionY[] = {2, 2, 1, -1, -2, -2, 1, -1};

        for (int i = 0; i < directionX.length; ++i) {

            char posX = (char) ((currentPosition.getRow() + directionX[i]));
            int posY = currentPosition.getColumn() + directionY[i];

            try {
                result.add(new Position(posX, posY));
            } catch (IllegalArgumentException e) {
                continue;
            }

        }
        return result;
    }

    @Override
    public Set<Position> getPotentialPositions_black(Position currentPosition) {
        return getPotentialPositions_white(currentPosition);
    }

    @Override
    public boolean canOccupyPosition(Position currentPosition, EChessPieces currentPiece, Position target) {
        if (currentPiece != EChessPieces.KNIGHT_WHITE && currentPiece != EChessPieces.KNIGHT_BLACK) {
            throw new IllegalArgumentException("The currentPiece must be a Knight!");
        }
        return getPotentialPositions_white(currentPosition).contains(target);
    }
}
