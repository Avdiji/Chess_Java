package Chess.Game.Logic.Pieces;

import Chess.Game.Logic.ChessFieldButton;
import Chess.Game.Logic.Player.EPlayerColor;
import Chess.Game.Logic.Position;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Fitor Avdiji
 * <p>
 * Class represents a Knight-Piece
 */
public class Knight implements IChessPiece {

    /**
     * Default Constructor
     **/
    public Knight() {
    }

    /**
     * Method returns a Set of Positions the Knight could theoretically execute
     *
     * @param currentPosition current Position of the Knight
     * @return All the Moves the knight could theoretically execute
     */
    private Set<Position> getPotentialPositions(final Position currentPosition) {
        Set<Position> result = new HashSet<>();

        int directionX[] = {2, 2, -2, -2, 1, -1, 1, -1};
        int directionY[] = {1, -1, 1, -1, 2, 2, -2, -2};

        for (int i = 0; i < directionX.length; ++i) {

            char posX = (char) ((currentPosition.getRow() + directionX[i]));
            int posY = currentPosition.getColumn() + directionY[i];

            try {
                result.add(new Position(posX, posY));
            } catch (IllegalArgumentException e) {
                continue;
            }
        }
        return result;
    }

    @Override
    public Set<Position> getActualPositions(Position currentPosition, EPlayerColor currentPlayerColor, List<ChessFieldButton> field) {
        Set<Position> result = getPotentialPositions(currentPosition);
        Set<Position> toRemove = field.stream()
                .filter(button -> result.contains(button.getPosition()))
                .filter(button -> button.getPlayerColor() == currentPlayerColor)
                .map(ChessFieldButton::getPosition)
                .collect(Collectors.toSet());

        result.removeAll(toRemove);
        return result;
    }
}
