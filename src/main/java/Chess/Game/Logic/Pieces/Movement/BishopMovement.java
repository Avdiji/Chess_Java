package Chess.Game.Logic.Pieces.Movement;

import Chess.Game.Logic.Pieces.EChessPieces;
import Chess.Game.Logic.Position;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Fitor Avdiji
 * <p>
 * Class implements every move the Bishop could potentially execute
 */
public class BishopMovement implements IChessFieldMovement {
    @Override
    public Set<Position> getPotentialPositions_white(Position currentPosition) {
        Set<Position> result = new HashSet<>();

        // combination of every direction the bishop could go
        int directionX[] = {1, -1, 1, -1};
        int directionY[] = {1, -1, -1, 1};

        // 7 is the max amount of fields the bishop can pass in one direction
        for (int i = 0; i < 7; ++i) {
            for (int j = 0; j < directionX.length; ++j) {

                // x,y coordinate
                char posX = (char) (currentPosition.getRow() + directionX[j]);
                int posY = currentPosition.getColumn() + directionY[j];

                // increment/decrement the direction value so that the bishop goes to the next field in the next iteration
                directionX[j] += directionX[j] > 0 ? 1 : -1;
                directionY[j] += directionY[j] > 0 ? 1 : -1;

                // in case of an invalid position
                try {
                    // create the position (if possible)
                    Position possiblePos = new Position(posX, posY);
                    // add the position (if it isn't equal to the current position)
                    result.add(possiblePos);
                } catch (IllegalArgumentException e) {
                    continue;
                }
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
        if (currentPiece != EChessPieces.BISHOP_WHITE && currentPiece != EChessPieces.BISHOP_BLACK) {
            throw new IllegalArgumentException("The currentPiece must be a Bishop!");
        }
        return getPotentialPositions_white(currentPosition).contains(target);
    }
}
