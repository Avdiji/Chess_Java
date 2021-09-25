package Chess.Client;

import Chess.Game.GUI.ChessboardGUI.GameWindow;
import Chess.Game.Logic.Player.EPlayerColor;
import Chess.Game.Logic.Player.Player;

import java.io.BufferedReader;
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
     * BufferedWriter/Reader of this Client
     **/
    private final BufferedWriter bw;
    private final BufferedReader br;

    /**
     * Hostname of the correspnding server
     **/
    private String hostname;

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
    public HTTP_ClientHandler(final GameWindow gameWindow, final Player clientPlayer, final BufferedWriter bw, final BufferedReader br) {
        this.gameWindow = gameWindow;
        this.clientPlayer = clientPlayer;
        this.bw = bw;
        this.br = br;

        executedMove = false;
        endedGame = false;
    }

    /**
     * Setter for {@link #hostname}
     *
     * @param hostname
     */
    protected void setHostname(final String hostname) {
        this.hostname = hostname;
    }

    /**
     * Setter for {@link #executedMove}
     *
     * @param value new value for executedMove
     */
    public void setExecutedMove(final boolean value) {
        executedMove = value;
    }

    /**
     * Setter for {@link #endedGame}
     *
     * @param value new value for endedGame
     */
    public void setEndedGame(final boolean value) {
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
    private void sendPutRequest(String message) throws IOException {
        message += "\r\n";
        bw.write("POST / HTTP/1.1\r\n");
        bw.write("Host: " + hostname + "\r\n");
        bw.write("Content-Type: text/plain\r\n");
        bw.write("Content-Length: " + message.length() + "\r\n");
        bw.write("\r\n");
        bw.write(message + "\r\n");
        bw.write("\r\n");
        bw.flush();

        System.out.println();
        String line;
        while ((line = br.readLine()) != null){
            System.out.println(line);
        }

    }

    /** Method sends Get request **/
    private void sendGetRequest() throws IOException {
        bw.write("GET / HTTP/1.1\r\n");
        bw.write("Host: " + hostname + "\r\n");
        bw.write("\r\n");
        bw.flush();

        System.out.println();
        String line;
        while ((line = br.readLine()) != null){
            System.out.println(line);
        }
    }

    @Override
    public void run() {
        while (!endedGame) {
            try {
                if(clientPlayer.getPlayerColor() == EPlayerColor.BLACK){
                    sendGetRequest();
                }

                synchronized (this) {
                    this.wait();
                }

                if (executedMove) {
                    sendPutRequest(clientPlayer.getLastMove());
                    executedMove = false;
                }
            } catch (InterruptedException | IOException e) {
            }
        }

    }
}
