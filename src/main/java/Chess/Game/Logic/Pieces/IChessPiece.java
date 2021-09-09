package Chess.Game.Logic.Pieces;

import Chess.Game.Logic.ChessFieldButton;
import Chess.Game.Logic.Player.EPlayerColor;
import Chess.Game.Logic.Position;

import java.awt.Color;
import java.util.List;
import java.util.Set;

/**
 * @author Fitor Avdiji
 * <p>
 * Interface, that declares most of the Methods a Piece needs
 */
public interface IChessPiece {

    /** Size of a Field in the Chess Board in px **/
    public static final int SIZE_FIELD = 100;

    /** Color for the white field **/
    public static final Color COLOR_FIELD_WHITE = new Color(0x8fbc8f);
    /** Color for the black field **/
    public static final Color COLOR_FIELD_BLACK = new Color(0x1E7B56);
    /** Color of a marked field **/
    public static final Color COLOR_FIELD_MARKED = new Color(0x039be5);
    /** Color of an endangered field **/
    public static final Color COLOR_FIELD_ENDANGERED = new Color(0xec7c26);

    /**
     * Method returns a Set of Positions the Piece on the current Position can actually capture
     *
     * @param currentPosition    Position of this Piece
     * @param currentPlayerColor Color of this Piece (white/black)
     * @param field              Field the Piece is located in
     * @return a Set of Positions the Piece can travel
     */
    public Set<Position> getActualPositions(final Position currentPosition, final EPlayerColor currentPlayerColor, final List<ChessFieldButton> field);

}
