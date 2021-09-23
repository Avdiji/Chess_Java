package Chess.Game.Logic.Player;

import Chess.Game.Logic.ChessFieldButton;
import Chess.Game.Logic.Pieces.EChessPieces;

/**
 * @author Fitor Avdiji
 * <p>
 * Class represents a Player
 */
public class Player {

    /**
     * Color of the Player
     **/
    private final EPlayerColor playerColor;
    /**
     * Last move the Player executed e.g.: "A1:B3"
     **/
    private String lastMove;

    /**
     * Constructor initializes:<br>
     * {@link #playerColor}<br>
     *
     * @param playerColor Color of the Player
     *                    if playerColor is NONE -> init for online Multiplayer
     */
    public Player(final EPlayerColor playerColor) {
        this.playerColor = playerColor;
    }

    /**
     * Getter for {@link #lastMove}
     *
     * @return the last move this Player executed
     */
    public String getLastMove() {
        return lastMove;
    }

    /**
     * Getter for {@link #playerColor}
     *
     * @return playerColor
     */
    public EPlayerColor getPlayerColor() {
        return playerColor;
    }

    /**
     * Method sets the last move e.g: "A-1;B-3;EMPTY"
     *
     * @param marked   button that has been moved
     * @param captured button that has been captured
     * @param upgraded true if the marked button has been upgraded (pawn upgraded)
     */
    public void setLastMove(final ChessFieldButton marked, final ChessFieldButton captured, final EChessPieces upgradedType) {
        lastMove = String.format("%c-%d;%c-%d;%s",
                marked.getPosition().getRow(), marked.getPosition().getColumn(),
                captured.getPosition().getRow(), captured.getPosition().getColumn(),
                upgradedType);
    }
}
