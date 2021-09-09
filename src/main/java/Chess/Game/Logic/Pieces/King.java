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
 * Class represents a King-Piece
 */
public class King implements IChessPiece {

    /**
     * Default Constructor
     **/
    public King() {
    }

    /**
     * Method return all moves the King could potentially execute
     *
     * @param currentPosition current Position of the King
     * @return all Moves the King can execute from his current Position, while ignoring all other pieces
     */
    private Set<Position> getPotentialPositions(final Position currentPosition) {
        Set<Position> result = new HashSet<>();

        int directionX[] = {0, 1, 1, 1, 0, -1, -1, -1};
        int directionY[] = {1, 1, 0, -1, -1, -1, 0, 1};

        for (int i = 0; i < directionX.length; ++i) {

            char posX = (char) (currentPosition.getRow() + directionX[i]);
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
