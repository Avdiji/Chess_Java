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

        if (!getCorrespondingButton(currentPosition, field).hasKingMoved()) {
            int column = currentPlayerColor == EPlayerColor.WHITE ? 1 : 8;

            // pieces between the King and the Rook
            char char_left_between[] = {'B', 'C', 'D'};
            char char_right_between[] = {'F', 'G'};

            // boolean to determine, whether a rochade is possible
            boolean canCastleLeft = false;
            boolean canCastleRight = false;

            ChessFieldButton leftRook = getCorrespondingButton(new Position('A', column), field);
            ChessFieldButton rightRook = getCorrespondingButton(new Position('H', column), field);

            // check whether piece is a rook (can never be enemy piece because enemy must move to get to that position)
            if (leftRook.getType() == EChessPieces.ROOK_WHITE || leftRook.getType() == EChessPieces.ROOK_BLACK)
                if (!leftRook.hasRookMoved())
                    canCastleLeft = checkCastle(char_left_between, column, field);

            if (rightRook.getType() == EChessPieces.ROOK_WHITE || rightRook.getType() == EChessPieces.ROOK_BLACK)
                if (!rightRook.hasRookMoved())
                    canCastleRight = checkCastle(char_right_between, column, field);

            if (canCastleLeft)
                result.add(new Position(((char) (currentPosition.getRow() - 2)), currentPosition.getColumn()));
            if (canCastleRight)
                result.add(new Position(((char) (currentPosition.getRow() + 2)), currentPosition.getColumn()));
        }
        return result;
    }

    /**
     * Method checks, whether there are pieces between rook and his corresponding King
     *
     * @param rook   Rook
     * @param rows   rows between rook and his king
     * @param column column of the rook (color)
     * @param field  field the pieces are located in
     * @return true if there are no pieces between rook and the corresponding king
     */
    private boolean checkCastle(final char rows[], final int column, final List<ChessFieldButton> field) {
        boolean result = true;

        for (int i = 0; i < rows.length; ++i) {
            ChessFieldButton nextToKing = getCorrespondingButton(new Position(rows[i], column), field);
            if (nextToKing.getType() != EChessPieces.EMPTY) {
                result = false;
                break;
            }
        }
        return result;
    }
}
