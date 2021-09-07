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
public class Empty implements IChessPiece{

    public Empty() {
    }

    @Override
    public Set<Position> getPotentialPositions(final Position currentPosition) {
        return new HashSet<>();
    }

}
