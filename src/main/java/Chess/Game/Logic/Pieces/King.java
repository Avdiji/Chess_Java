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
    public Set<Position> getActualPositions(final Position currentPosition, final EPlayerColor currentPlayerColor, final List<ChessFieldButton> field) {
        Set<Position> result = getPotentialPositions(currentPosition);
        Set<Position> toRemove = field.stream()
                .filter(button -> result.contains(button.getPosition()))
                .filter(button -> button.getPlayerColor() == currentPlayerColor)
                .map(ChessFieldButton::getPosition)
                .collect(Collectors.toSet());

        result.removeAll(toRemove);
        return result;
    }

    /**
     * The Method determines, whether the King can execute a Rochade (ignoring, whether the king is endangered or not)
     *
     * @param currentPosition    current Position of the King
     * @param currentPlayerColor current Color of the King
     * @param field              field the King is located in
     * @return a Set of new Positions the King could move to (Rochade)
     */
    public Set<Position> getPositionsRochade(final Position currentPosition, final EPlayerColor currentPlayerColor, final List<ChessFieldButton> field) {
        Set<Position> result = new HashSet<>();
        ChessFieldButton currentKing = field.stream().filter(button -> button.getPosition().equals(currentPosition)).findAny().get();

        boolean canRochade_right;
        boolean canRochade_left;

        if (!currentKing.hasKingMoved()) {
            Set<ChessFieldButton> rooks = field.stream()
                    .filter(button -> button.getPlayerColor() == currentPlayerColor)
                    .filter(button -> button.getType() == EChessPieces.ROOK_WHITE || button.getType() == EChessPieces.ROOK_BLACK)
                    .collect(Collectors.toSet());
            rooks.removeAll(rooks.stream().filter(ChessFieldButton::hasRookMoved).collect(Collectors.toSet()));

            ChessFieldButton rookRight = rooks.stream()
                    .filter(rook -> rook.getPosition().getRow() > currentPosition.getRow()).findAny().orElse(null);
            ChessFieldButton rookLeft = rooks.stream()
                    .filter(rook -> rook.getPosition().getRow() < currentPosition.getRow()).findAny().orElse(null);

            canRochade_left = rookLeft == null ? false : true;
            canRochade_right = rookRight == null ? false : true;

            int pos_right[] = {1, 2, 1};
            int pos_left[] = {-1, -2, -3};

            for (int i = 0; i < pos_right.length; ++i) {
                ChessFieldButton left_right_button;
                if (canRochade_right) {
                    char posX_right = (char) (currentPosition.getRow() + pos_right[i]);
                    left_right_button = field.stream()
                            .filter(button -> button.getPosition().equals(new Position(posX_right, currentPosition.getColumn())))
                            .findAny().get();
                    canRochade_right = left_right_button.getType() == EChessPieces.EMPTY ? true : false;
                }
                if (canRochade_left) {
                    char posX_left = (char) (currentPosition.getRow() + pos_left[i]);
                    left_right_button = field.stream()
                            .filter(button -> button.getPosition().equals(new Position(posX_left, currentPosition.getColumn())))
                            .findAny().get();
                    canRochade_left = left_right_button.getType() == EChessPieces.EMPTY ? true : false;
                }
            }
            if (canRochade_right) {
                result.add(new Position((char) (currentPosition.getRow() + 2), currentPosition.getColumn()));
            }
            if (canRochade_left) {
                result.add(new Position((char) (currentPosition.getRow() - 2), currentPosition.getColumn()));
            }
        }
        return result;
    }
}
