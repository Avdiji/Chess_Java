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
public class Knight extends AChessPiece {

    /**
     * Constructor
     *
     * @param position   position of the Knight
     * @param piece      type of the Knight (black/white)
     * @param chessField chessField the Knight is located in
     * @throws IllegalArgumentException if the piece has a invalid type
     */
    public Knight(Position position, EChessPieces piece, ChessField chessField) {
        super(position, piece, chessField);

        if (piece != EChessPieces.KNIGHT_WHITE && piece != EChessPieces.KNIGHT_BLACK) {
            throw new IllegalArgumentException("The type of piece must be a Knight!");
        }
    }

    @Override
    public Set<Position> getPotentialPositions() {
        Set<Position> result = new HashSet<>();

        int directionX[] = {2, 2, -2, -2, 1, -1, 1, -1};
        int directionY[] = {1, -1, 1, -1, 2, 2, -2, -2};

        for (int i = 0; i < directionX.length; ++i) {

            char posX = (char) ((super.getPosition().getRow() + directionX[i]));
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
