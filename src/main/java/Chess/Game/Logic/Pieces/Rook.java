package Chess.Game.Logic.Pieces;

import Chess.Game.Logic.ChessField;
import Chess.Game.Logic.Position;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Fitor Avdiji
 *
 * Class represents a Rook-Piece
 */
public class Rook extends AChessPiece {

    /**
     * Constructor
     *
     * @param position   position of the Rook
     * @param piece      type of the Rook (black/white)
     * @param chessField chessField the Rook is located in
     * @throws IllegalArgumentException if the piece has a invalid type
     */
    public Rook(Position position, EChessPieces piece, ChessField chessField) {
        super(position, piece, chessField);

        if(piece != EChessPieces.ROOK_WHITE && piece != EChessPieces.ROOK_BLACK){
            throw new IllegalArgumentException("The type of piece must be a Rook!");
        }
    }

    @Override
    public Set<Position> getPotentialPositions() {
        Set<Position> result = new HashSet<>();

        for (int i = 1; i <= 8; ++i) {
            if (i != super.getPosition().getColumn())
                result.add(new Position(super.getPosition().getRow(), i));
            if ('A' + i - 1 != super.getPosition().getRow())
                result.add(new Position((char) ('A' + i - 1), super.getPosition().getColumn()));
        }
        return result;
    }

    @Override
    public boolean canActuallyCapturePosition(Position target) {
        return false;
    }
}
