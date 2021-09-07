package Chess.Game.Logic.Pieces;

import Chess.Game.Logic.ChessField;
import Chess.Game.Logic.Position;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Fitor Avdiji
 *
 * Class represents a King-Piece
 */
public class King implements IChessPiece{

    public King() {
    }

    @Override
    public Set<Position> getPotentialPositions(final Position currentPosition) {
        Set<Position> result = new HashSet<>();

        int directionX[] = {0, 1, 1, 1, 0, -1, -1, -1};
        int directionY[] = {1, 1, 0, -1, -1, -1, 0, 1};

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
}
