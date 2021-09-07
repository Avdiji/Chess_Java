package Chess.Game.Logic.Pieces;

import Chess.Game.Logic.Position;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Fitor Avdiji
 * <p>
 * Class represents an Empty Field on the ChessField
 */
public class Empty implements IChessPiece {

    /** Default Constructor **/
    public Empty() {
    }

    @Override
    public Set<Position> getPotentialPositions(final Position currentPosition) {
        return new HashSet<>();
    }
}
