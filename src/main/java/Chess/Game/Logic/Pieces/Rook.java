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
public class Rook implements IChessPiece {

    public Rook() {
    }

    @Override
    public Set<Position> getPotentialPositions(final Position currentPosition) {
        Set<Position> result = new HashSet<>();

        for (int i = 1; i <= 8; ++i) {
            if (i != currentPosition.getColumn())
                result.add(new Position(currentPosition.getRow(), i));
            if ('A' + i - 1 != currentPosition.getRow())
                result.add(new Position((char) ('A' + i - 1), currentPosition.getColumn()));
        }
        return result;
    }
}
