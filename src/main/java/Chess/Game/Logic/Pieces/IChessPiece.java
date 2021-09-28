package Chess.Game.Logic.Pieces;

import Chess.Game.Logic.ChessFieldButton;
import Chess.Game.Logic.Player.EPlayerColor;
import Chess.Game.Logic.Position;

import java.util.List;
import java.util.Set;

/**
 * @author Fitor Avdiji
 * <p>
 * Interface, that declares most of the Methods a Piece needs
 */
public interface IChessPiece {

    /** Size of a Field in the Chess Board in px **/
    int SIZE_FIELD = 100;

    /**
     * Method returns a Set of Positions the Piece on the current Position can actually capture
     *
     * @param currentPosition    Position of this Piece
     * @param currentPlayerColor Color of this Piece (white/black)
     * @param field              Field the Piece is located in
     * @return a Set of Positions the Piece can travel
     */
    Set<Position> getActualPositions(final Position currentPosition, final EPlayerColor currentPlayerColor, final List<ChessFieldButton> field);

    /**
     * Method finds and returns a button with the same Position (in field) as currentPosition
     *
     * @param currentPosition currentPosition of the button
     * @param field           field the button is located in
     * @return button with the same position as currentPosition
     */
    default ChessFieldButton getCorrespondingButton(final Position currentPosition, final List<ChessFieldButton> field) {
        return field.stream()
                .filter(button -> button.getPosition().equals(currentPosition))
                .findAny().get();
    }

}
