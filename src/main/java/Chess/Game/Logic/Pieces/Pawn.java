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
public class Pawn extends AChessPiece {

    /**
     * Constructor
     *
     * @param position   position of the Pawn
     * @param piece      type of the pawn (black/white)
     * @param chessField chessField the pawn is located in
     * @throws IllegalArgumentException if the piece has a invalid type
     */
    public Pawn(Position position, EChessPieces piece, ChessField chessField) {
        super(position, piece, chessField);

        if (piece != EChessPieces.PAWN_WHITE && piece != EChessPieces.PAWN_BLACK) {
            throw new IllegalArgumentException("The type of the Piece must be a Pawn!");
        }
    }

    @Override
    public Set<Position> getPotentialPositions() {
        Set<Position> result = new HashSet<>();

        int directionX_white[] = {0, 1, -1};
        int directionY_white[] = {1, 1, 1};
        int directionX_black[] = {0, 1, -1};
        int directionY_black[] = {-1, -1, -1};

        if (super.getPiece() == EChessPieces.PAWN_WHITE) {
            if (super.getPosition().getColumn() == 2)
                result.add(new Position(super.getPosition().getRow(), super.getPosition().getColumn() + 2));

            for (int i = 0; i < directionX_white.length; ++i) {

                char posX = (char) (super.getPosition().getRow() + directionX_white[i]);
                int posY = super.getPosition().getColumn() + directionY_white[i];

                try {
                    result.add(new Position(posX, posY));
                } catch (IllegalArgumentException e) {
                    continue;
                }
            }
        } else {
            if (super.getPosition().getColumn() == 7)
                result.add(new Position(super.getPosition().getRow(), super.getPosition().getColumn() - 2));

            for (int i = 0; i < directionX_black.length; ++i) {

                char posX = (char) (super.getPosition().getRow() + directionX_black[i]);
                int posY = super.getPosition().getColumn() + directionY_black[i];

                try {
                    result.add(new Position(posX, posY));
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
