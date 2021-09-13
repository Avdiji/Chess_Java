package Chess.Game.Logic.Player;

import Chess.Game.Logic.ChessFieldButton;

/**
 * @author Fitor Avdiji
 * <p>
 * Class represents a Player
 */
public class Player {

    /** Color of the Player **/
    private final EPlayerColor playerColor;
    /** Last move the Player executed e.g.: "A1:B3" **/
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
     * @return playerColor
     */
    public EPlayerColor getPlayerColor(){
        return playerColor;
    }

    /**
     * Method sets the last move e.g: "A-1;B-3"
     *
     * @param marked   button that has been moved
     * @param captured button that has been captured
     */
    public void setLastMove(final ChessFieldButton marked, final ChessFieldButton captured) {
        lastMove = String.format("%c%d;%c%d",
                marked.getPosition().getRow(), marked.getPosition().getColumn(),
                captured.getPosition().getRow(), captured.getPosition().getColumn());
    }
}
