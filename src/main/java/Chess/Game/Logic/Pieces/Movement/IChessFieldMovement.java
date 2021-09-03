package Chess.Game.Logic.Pieces.Movement;

import Chess.Game.Logic.Pieces.EChessPieces;
import Chess.Game.Logic.Position;

import java.util.Set;

/**
 * @author Fitor Avdiji
 * <p>
 * Interface contains all the rules a Chess - Piece needs to have,
 * to be able to function independently from other pieces
 */
public interface IChessFieldMovement {

    /**
     * Method return a list of positions that could theoretically be reached by the white piece,
     * ignoring the other pieces
     *
     * @param currentPosition the current Position of the Piece
     * @return List of positions the piece could move to, if the field was empty
     */
    public Set<Position> getPotentialPositions_white(final Position currentPosition);

    /**
     * Method return a list of positions that could theoretically be reached by the black piece,
     * ignoring the other pieces
     *
     * @param currentPosition the current Position of the Piece
     * @return a List of positions the piece could move to, if the field was empty
     */
    public Set<Position> getPotentialPositions_black(final Position currentPosition);

    /**
     * Method returns true if the piece would be theoretically able to somehow reach the target within 1 round from its current position.
     *
     * @param currentPosition the current Position of the Piece
     * @param target          the position of the target of the Piece
     * @return true if the targeted position can be occupied by the by the piece on the current position
     * @throws IllegalArgumentException if the EChessPiece doesn't fit to the current class
     */
    public boolean canOccupyPosition(final Position currentPosition, final EChessPieces currentPiece, final Position target);
}
