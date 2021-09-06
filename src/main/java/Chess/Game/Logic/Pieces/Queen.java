package Chess.Game.Logic.Pieces;

import Chess.Game.Logic.ChessField;
import Chess.Game.Logic.Position;

import java.util.Set;

/**
 * @author Fitor Avdiji
 * <p>
 * Class represents a Queen-Piece
 */
public class Queen extends AChessPiece {

    /**
     * Constructor
     *
     * @param position   position of the Queen
     * @param piece      type of the Queen (black/white)
     * @param chessField chessField the Queen is located in
     * @throws IllegalArgumentException if the piece has a invalid type
     */
    public Queen(Position position, EChessPieces piece, ChessField chessField) {
        super(position, piece, chessField);

        if (piece != EChessPieces.QUEEN_WHITE && piece != EChessPieces.QUEEN_BLACK) {
            throw new IllegalArgumentException("The type of piece must be a Queen!");
        }
    }

    @Override
    public Set<Position> getPotentialPositions() {
        Set<Position> result;

        // since the queen is basically a combination of rook and bishop we I use the already defined movement Sets
        IChessPiece bishopHlp = new Bishop(super.getPosition(), EChessPieces.BISHOP_WHITE, super.getChessField());
        IChessPiece rookHlp = new Rook(super.getPosition(), EChessPieces.ROOK_WHITE, super.getChessField());

        result = bishopHlp.getPotentialPositions();
        result.addAll(rookHlp.getPotentialPositions());
        return result;
    }

    @Override
    public boolean canActuallyCapturePosition(Position target) {
        return false;
    }
}
