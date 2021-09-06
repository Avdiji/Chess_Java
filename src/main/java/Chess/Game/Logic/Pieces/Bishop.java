package Chess.Game.Logic.Pieces;

import Chess.Game.Logic.ChessField;
import Chess.Game.Logic.Position;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Fitor Avdiji
 * <p>
 * Class represents a Bishop-Piece
 */
public class Bishop extends AChessPiece {

    /**
     * Constructor
     *
     * @param position   position of the Bishop
     * @param piece      type of the Bishop (black/white)
     * @param chessField chessField the Bishop is located in
     * @throws IllegalArgumentException if the piece has a invalid type
     */
    public Bishop(Position position, EChessPieces piece, ChessField chessField) {
        super(position, piece, chessField);

        if (piece != EChessPieces.BISHOP_WHITE && piece != EChessPieces.BISHOP_BLACK) {
            throw new IllegalArgumentException("The type of piece must be a Bishop!");
        }
    }

    @Override
    public Set<Position> getPotentialPositions() {
        Set<Position> result = new HashSet<>();

        // combination of every direction the bishop could go
        int directionX[] = {1, -1, 1, -1};
        int directionY[] = {1, -1, -1, 1};

        // 7 is the max amount of fields the bishop can pass in one direction
        for (int i = 0; i < 7; ++i) {
            for (int j = 0; j < directionX.length; ++j) {

                // x,y coordinate
                char posX = (char) (super.getPosition().getRow() + directionX[j]);
                int posY = super.getPosition().getColumn() + directionY[j];

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
    public boolean canActuallyCapturePosition(Position target) {
        return false;
    }
}
