package Chess.Game.Logic.Pieces;

import Chess.Game.Logic.Position;

import java.awt.Color;
import java.util.Set;

/**
 * @author Fitor Avdiji
 *
 * Interface, that declares most of the Methods a Piece needs
 */
public interface IChessPiece {

    /** Size of a Field in the Chess Board in px **/
    public static final int SIZE_FIELD = 100;

    /** Color for the white field **/
    public static final Color COLOR_FIELD_WHITE = new Color(0x8fbc8f);
    /** Color for the black field **/
    public static final Color COLOR_FIELD_BLACK = new Color(0x166646);
    /** Color of a marked field **/
    public static final Color COLOR_FIELD_MARKED = new Color(0x039be5);
    /** Color of an endangered field **/
    public static final Color COLOR_FIELD_ENDANGERED = new Color(0xec7c26);

    /**
     * Method returns a Set of Positions the Piece on the current Position could theoretically capture at some point of the game.
     *
     * @return A Set of all the Positions the Piece could theoretically capture at some Point of the Game, from the current Position
     */
    public Set<Position> getPotentialPositions(final Position currentPosition);
}
