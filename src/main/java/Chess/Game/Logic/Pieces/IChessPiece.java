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
    public Set<Position> getPotentialPositions();

    /**
     * Method returns true if the piece would be theoretically able to somehow reach the target within 1 round from its current position.
     *
     * @param target          the position of the target of the Piece
     * @return true if the targeted position can be occupied by the by the piece on the current position
     */
    public boolean canCapturePosition(final Position target);

    /**
     * The Method checks, whether the current Piece can actually move to the target, considering every other piece on the gameField
     *
     * @param target          the Position of the target of the Piece
     * @return true if the Piece can move from the currentPosition to the target, while also considering the chessfield
     */
    public boolean canActuallyCapturePosition(final Position target);

    /**
     * The Method initializes the Piece
     *
     * @param backgroundColor
     */
    public void initPiece(final Color backgroundColor);
}
