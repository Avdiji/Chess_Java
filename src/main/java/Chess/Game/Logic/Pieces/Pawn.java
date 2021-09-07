package Chess.Game.Logic.Pieces;

import Chess.Game.Logic.ChessFieldButton;
import Chess.Game.Logic.Player.EPlayerColor;
import Chess.Game.Logic.Position;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Fitor Avdiji
 * <p>
 * Class represents a Pawn-Piece
 */
public class Pawn implements IChessPiece {

    /**
     * Type of the Pawn (black/white)
     **/
    private EChessPieces type;

    /**
     * Default Constructor
     **/
    public Pawn() {
    }

    /**
     * Setter for {@link #type}
     *
     * @param type new type of the Piece
     * @throws IllegalArgumentException if the type is not a Pawn
     */
    public void setType(final EChessPieces type) {
        if (type != EChessPieces.PAWN_WHITE && type != EChessPieces.PAWN_BLACK) {
            throw new IllegalArgumentException("The Piece must be a Pawn!");
        }
        this.type = type;
    }

    //TODO get rid of the spaghetti code
    @Override
    public Set<Position> getPotentialPositions(final Position currentPosition) {
        Set<Position> result = new HashSet<>();

        int directionX[] = {0, 1, -1};
        int directionY_white[] = {1, 1, 1};
        int directionY_black[] = {-1, -1, -1};

        if (type == EChessPieces.PAWN_WHITE) {
            if (currentPosition.getColumn() == 2)
                result.add(new Position(currentPosition.getRow(), currentPosition.getColumn() + 2));

            for (int i = 0; i < directionX.length; ++i) {
                char posX = (char) (currentPosition.getRow() + directionX[i]);
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

            for (int i = 0; i < directionX.length; ++i) {
                char posX = (char) (currentPosition.getRow() + directionX[i]);
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

    //TODO
    @Override
    public Set<Position> getActualPositions(Position currentPosition, EPlayerColor currentPlayerColor, List<ChessFieldButton> field) {
        return getPotentialPositions(currentPosition);
    }

}
