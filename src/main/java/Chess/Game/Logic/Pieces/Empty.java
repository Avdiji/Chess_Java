package Chess.Game.Logic.Pieces;

import Chess.Game.Logic.ChessField;
import Chess.Game.Logic.Position;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Fitor Avdiji
 *
 * Class represents an Empty Field on the ChessField
 */
public class Empty extends AChessPiece{

    /**
     * Constructor
     *
     * @param position   position of the Empty Field
     * @param piece      type of the Piece (black/white)
     * @param chessField chessField the Empty Field is located in
     * @throws IllegalArgumentException if the piece has a invalid type
     */
    public Empty(Position position, EChessPieces piece, ChessField chessField) {
        super(position, piece, chessField);

        if (piece != EChessPieces.EMPTY && piece != EChessPieces.EMPTY) {
            throw new IllegalArgumentException("The type of piece must be a Empty Field!");
        }
    }

    @Override
    public Set<Position> getPotentialPositions() {
        return new HashSet<>();
    }

    @Override
    public boolean canActuallyCapturePosition(Position target) {
        return false;
    }
}
