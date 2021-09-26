package Chess.Client;

import Chess.Game.GUI.ChessboardGUI.GameWindow;
import Chess.Game.Logic.ChessFieldButton;
import Chess.Game.Logic.Pieces.EChessPieces;
import Chess.Game.Logic.Player.EPlayerColor;
import Chess.Game.Logic.Player.Player;
import Chess.Game.Logic.Position;

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
     * Signal to continue without altering the game
     **/
    public static final String SIGNAL_CONTINUE = "CONTINUE";
    private static final String SIGNAL_REPEAT = "HTTP/1.1 200 OK";

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
     * Method decodes and executes the move, received from the server
     *
     * @param move last move Sent from the server
     */
    private void executeClientMove(final String move, final GameWindow gameWindow) {
        String moveValues[] = move.split(";");

        char row_marked = moveValues[0].charAt(0);
        int column_marked = Integer.parseInt(moveValues[0].split("-")[1]);

        char row_captured = moveValues[1].charAt(0);
        int column_captured = Integer.parseInt(moveValues[1].split("-")[1]);

        Position pos_marked = new Position(row_marked, column_marked);
        Position pos_captured = new Position(row_captured, column_captured);

        ChessFieldButton marked = gameWindow.getChessField().getField().stream().filter(button -> button.getPosition().equals(pos_marked)).findAny().get();
        ChessFieldButton captured = gameWindow.getChessField().getField().stream().filter(button -> button.getPosition().equals(pos_captured)).findAny().get();

        if (!moveValues[2].equals(EChessPieces.EMPTY.toString())) {
            marked.setType(EChessPieces.valueOf(moveValues[2]));
        }
        gameWindow.movePiece(captured, marked);
    }


    /**
     * Method sends a message to the Server
     *
     * @param message
     */
    private void sendPostRequest(String message) throws IOException {
        message += "\r\n";
        bw.write("POST / HTTP/1.1\r\n");
        bw.write("Host: " + hostname + "\r\n");
        bw.write("Content-Type: text/plain\r\n");
        bw.write("Content-Length: " + message.length() + "\r\n");
        bw.write("\r\n");
        bw.write(message + "\r\n");
        bw.write("\r\n");
        bw.flush();
    }

    /**
     * Method extracts the Body from a request
     **/
    private String extractBody() throws IOException {
        System.out.println();
        System.out.println("IN");
        String result;
        while (!(result = br.readLine()).isEmpty()) {
            System.out.println(result);
        }
        result = br.readLine();
        System.out.println(result);
        System.out.println("OUT");
        System.out.println();
        if (result.equals(SIGNAL_REPEAT))
            result = extractBody();
        return result;
    }

    /**
     * Method sends Get request
     **/
    private void sendGetRequest() throws IOException {
        bw.write("GET / HTTP/1.1\r\n");
        bw.write("Host: " + hostname + "\r\n");
        bw.write("\r\n");
        bw.flush();
    }

    @Override
    public void run() {
        boolean first = true;
        while (!endedGame) {
            try {
                synchronized (this) {
                    this.wait(1000);
                }
                if (clientPlayer.getPlayerColor() == EPlayerColor.BLACK) {
                    sendGetRequest();
                    extractBody();
                    sendGetRequest();
                    executeClientMove(extractBody(), gameWindow);
                }

                synchronized (this) {
                    this.wait();
                }
                if (executedMove) {
                    sendPostRequest(clientPlayer.getLastMove());
                    extractBody();
                    executedMove = false;
                }

                if (clientPlayer.getPlayerColor() == EPlayerColor.WHITE) {
                    sendGetRequest();
                    extractBody();
                    sendGetRequest();
                    executeClientMove(extractBody(), gameWindow);
                }

            } catch (InterruptedException | IOException e) {
            }
        }
    }
}
