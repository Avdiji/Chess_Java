package Chess.Game.Logic.Pieces.Movement;

import Chess.Game.Logic.Pieces.EChessPieces;
import Chess.Game.Logic.Position;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Fitor Avdiji
 * <p>
 * Class implements every move the pawn could potentially execute
 */
public class PawnMovement implements IChessFieldMovement {

    @Override
    public Set<Position> getPotentialPositions_white(final Position currentPosition) {
        Set<Position> result = new HashSet<>();

        int directionX[] = {0, 1, -1};
        int directionY[] = {1, 1, 1};

        if (currentPosition.getColumn() == 2)
            result.add(new Position(currentPosition.getRow(), currentPosition.getColumn() + 2));

        for (int i = 0; i < directionX.length; ++i) {

            char posX = (char) (currentPosition.getRow() + directionX[i]);
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
        Set<Position> result = new HashSet<>();

        int directionX[] = {0, 1, -1};
        int directionY[] = {-1, -1, -1};

        if (currentPosition.getColumn() == 7)
            result.add(new Position(currentPosition.getRow(), currentPosition.getColumn() - 2));

        for (int i = 0; i < directionX.length; ++i) {

            char posX = (char) (currentPosition.getRow() + directionX[i]);
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
    public boolean canOccupyPosition(Position currentPosition, EChessPieces currentPiece, Position target) {
        if (currentPiece != EChessPieces.PAWN_WHITE && currentPiece != EChessPieces.PAWN_BLACK) {
            throw new IllegalArgumentException("currentPiece must be a Pawn!");
        }
        boolean result = false;
        switch (currentPiece) {
            case PAWN_WHITE -> result = getPotentialPositions_white(currentPosition).contains(target);
            case PAWN_BLACK -> result = getPotentialPositions_black(currentPosition).contains(target);
        }
        return result;
    }
}
