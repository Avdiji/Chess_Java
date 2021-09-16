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
 * Class represents a Rook-Piece
 */
public class Rook implements IChessPiece {

    /**
     * Default Constructor
     **/
    public Rook() {
    }

    @Override
    public Set<Position> getActualPositions(Position currentPosition, EPlayerColor currentPlayerColor, List<ChessFieldButton> field) {
        Set<Position> result = new HashSet<>();
        int directionX[] = {0, 0, 1, -1};
        int directionY[] = {1, -1, 0, 0};

        for (int i = 0; i < 7; ++i) {
            for (int j = 0; j < directionX.length; ++j) {
                char row = (char) (currentPosition.getRow() + directionX[j]);
                int column = currentPosition.getColumn() + directionY[j];

                directionX[j] += Integer.compare(directionX[j], 0);
                directionY[j] += Integer.compare(directionY[j], 0);

                try {
                    Position hlp = new Position(row, column);
                    result.add(hlp);

                    ChessFieldButton button = field.stream().filter(piece -> piece.getPosition().equals(hlp)).findAny().get();
                    if (button.getType() != EChessPieces.EMPTY) {
                        directionX[j] = 0;
                        directionY[j] = 0;
                    }
                } catch (IllegalArgumentException e) {
                    continue;
                }
            }
        }

        result.removeAll(field.stream()
                .filter(button -> result.contains(button.getPosition()))
                .filter(button -> button.getPlayerColor() == currentPlayerColor)
                .map(ChessFieldButton::getPosition)
                .collect(Collectors.toSet()));

        return result;
    }
}
