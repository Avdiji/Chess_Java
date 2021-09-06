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
public class King extends AChessPiece{

    /**
     * Constructor
     *
     * @param position   position of the King
     * @param piece      type of the King (black/white)
     * @param chessField chessField the King is located in
     * @throws IllegalArgumentException if the piece has a invalid type
     */
    public King(Position position, EChessPieces piece, ChessField chessField) {
        super(position, piece, chessField);

        if (piece != EChessPieces.KING_WHITE && piece != EChessPieces.KING_BLACK) {
            throw new IllegalArgumentException("The type of piece must be a King!");
        }
    }

    @Override
    public Set<Position> getPotentialPositions() {
        Set<Position> result = new HashSet<>();

        int directionX[] = {0, 1, 1, 1, 0, -1, -1, -1};
        int directionY[] = {1, 1, 0, -1, -1, -1, 0, 1};

        for (int i = 0; i < directionX.length; ++i) {

            char posX = (char) (super.getPosition().getRow() + directionX[i]);
            int posY = super.getPosition().getColumn() + directionY[i];

            try {
                result.add(new Position(posX, posY));
            } catch (IllegalArgumentException e) {
                continue;
            }
        }
        return result;
    }


    @Override
    public boolean canActuallyCapturePosition(Position target) {
        return false;
    }
}
