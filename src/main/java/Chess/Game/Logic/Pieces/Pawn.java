package Chess.Game.Logic.Pieces;

import Chess.Game.Logic.ChessField;
import Chess.Game.Logic.Position;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Fitor Avdiji
 * <p>
 * Class represents a Pawn-Piece
 */
public class Pawn implements IChessPiece {

    private EChessPieces type;

    public Pawn() {
    }

    public void setType(final EChessPieces type){
        this.type = type;
    }

    @Override
    public Set<Position> getPotentialPositions(final Position currentPosition) {
        Set<Position> result = new HashSet<>();

        int directionX_white[] = {0, 1, -1};
        int directionY_white[] = {1, 1, 1};
        int directionX_black[] = {0, 1, -1};
        int directionY_black[] = {-1, -1, -1};

        if (type == EChessPieces.PAWN_WHITE) {
            if (currentPosition.getColumn() == 2)
                result.add(new Position(currentPosition.getRow(), currentPosition.getColumn() + 2));

            for (int i = 0; i < directionX_white.length; ++i) {

                char posX = (char) (currentPosition.getRow() + directionX_white[i]);
                int posY = currentPosition.getColumn() + directionY_white[i];

                try {
                    result.add(new Position(posX, posY));
                } catch (IllegalArgumentException e) {
                    continue;
                }
            }
        } else {
            if (currentPosition.getColumn() == 7)
                result.add(new Position(currentPosition.getRow(), currentPosition.getColumn() - 2));

            for (int i = 0; i < directionX_black.length; ++i) {

                char posX = (char) (currentPosition.getRow() + directionX_black[i]);
                int posY = currentPosition.getColumn() + directionY_black[i];

                try {
                    result.add(new Position(posX, posY));
                } catch (IllegalArgumentException e) {
                    continue;
                }
            }
        }
        return result;
    }

}
