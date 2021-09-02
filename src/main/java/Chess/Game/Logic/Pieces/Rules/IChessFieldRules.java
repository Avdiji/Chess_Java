package Chess.Game.Logic.Pieces.Rules;

/** @author Fitor Avdiji
 *
 * Interface contains all necessary Rules/Checks every Piece needs
 */
public interface IChessFieldRules {

    /**
     * Method checks, whether a Piece can move from its current Position to a desired Position,
     * without considering the other Pieces (the whole field of pieces)
     * @param desiredPosition
     * @return true if the piece is able to move from currentPosition to desiredPosition
     */
    boolean canMoveFromTo(String desiredPosition);

}
