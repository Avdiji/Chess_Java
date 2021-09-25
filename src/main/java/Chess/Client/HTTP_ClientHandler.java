package Chess.Client;

import Chess.Game.GUI.ChessboardGUI.GameWindow;
import Chess.Game.Logic.Player.Player;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * @author Fitor Avdiji
 * <p>
 * Class generates The move to be sent to the server after executing it
 */
public class HTTP_ClientHandler implements Runnable {

    /**
     * Corresponding gameWindow of the Client
     **/
    private final GameWindow gameWindow;
    /**
     * Player of this client
     **/
    private final Player clientPlayer;
    /**
     * BufferedWriter of this Client
     **/
    private final BufferedWriter bw;

    /**
     * True if client has executed a move else wrong
     **/
    private boolean executedMove;

    /**
     * True if the game is over
     **/
    private boolean endedGame;

    /**
     * Constructor
     *
     * @param gameWindow
     */
    public HTTP_ClientHandler(final GameWindow gameWindow, final Player clientPlayer, final BufferedWriter bw) {
        this.gameWindow = gameWindow;
        this.clientPlayer = clientPlayer;
        this.bw = bw;

        executedMove = false;
        endedGame = false;
    }

    /**
     * Setter for {@link #executedMove}
     * @param value new value for executedMove
     */
    public void setExecutedMove(final boolean value){
        executedMove = value;
    }

    /**
     * Setter for {@link #endedGame}
     * @param value new value for endedGame
     */
    public void setEndedGame(final boolean value){
        endedGame = value;
    }

    /**
     * Getter for {@link #endedGame}
     *
     * @return true if the Game has ended
     */
    protected boolean endedGame() {
        return endedGame;
    }

    /**
     * Method sends a message to the Server
     *
     * @param message
     */
    private void sendToServer(final String message) throws IOException {
        bw.write(message + "\r\n");
        bw.flush();
    }

    @Override
    public void run() {
        while (!endedGame) {
            try {
                synchronized (this) {
                    this.wait();
                }

                if (executedMove) {
                    sendToServer(clientPlayer.getLastMove());
                    executedMove = false;
                }
            } catch (InterruptedException | IOException e) {
            }
        }

    }
}
