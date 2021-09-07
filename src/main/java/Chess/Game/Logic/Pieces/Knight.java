package Chess.Game.Logic.Pieces;

import Chess.Game.Logic.ChessField;
import Chess.Game.Logic.Position;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Fitor Avdiji
 * <p>
 * Class represents a Knight-Piece
 */
public class Knight implements IChessPiece {

    public Knight() {
    }

    @Override
    public Set<Position> getPotentialPositions(final Position currentPosition) {
        Set<Position> result = new HashSet<>();

        int directionX[] = {2, 2, -2, -2, 1, -1, 1, -1};
        int directionY[] = {1, -1, 1, -1, 2, 2, -2, -2};

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
}
