package Chess.Client;

import Chess.Game.GUI.ChessboardGUI.GameWindow;
import Chess.Game.GUI.Scoreboard;
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
     * Time to wait after a response has been read
     **/
    private static final int TIMER_WAIT = 500;

    /**
     * Strings for the Scoreboard
     **/
    private static final String STRING_FF_WHITE = "WHITE gave up";
    private static final String STRING_FF_BLACK = "BLACK gave up";
    public static final String STRING_WIN_WHITE = "WHITE Won";
    public static final String STRING_WIN_BLACK = "BLACK Won";

    /**
     * Signal to continue without altering the game
     **/
    public static final String SIGNAL_CONTINUE = "CONTINUE";
    public static final String SIGNAL_FF_WHITE = "FF_WHITE";
    public static final String SIGNAL_FF_BLACK = "FF_BLACK";
    private static final String SIGNAL_REPEAT = "HTTP/1.1 200 OK";

    /**
     * Corresponding gameWindow of the Client
     **/
    private final GameWindow gameWindow;

    /**
     * Corresponding Scoreboard
     **/
    private final Scoreboard scoreboard;

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
    public HTTP_ClientHandler(final GameWindow gameWindow,
                              final Scoreboard scoreboard,
                              final Player clientPlayer,
                              final BufferedWriter bw,
                              final BufferedReader br) {
        this.gameWindow = gameWindow;
        this.scoreboard = scoreboard;
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
     * Method decodes and executes the move, received from the server
     *
     * @param move last move Sent from the server
     */
    private void executeClientMove(final String move, final GameWindow gameWindow) {
        try {
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
        } catch (IndexOutOfBoundsException e) {
        }
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
        String result;
        while (!br.readLine().isEmpty()) ;
        result = br.readLine();
        if (result.equals(SIGNAL_REPEAT) || result.equals(SIGNAL_CONTINUE))
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

    /**
     * Method pauses the game for a bit (to prevent issues)
     **/
    private void pause() throws InterruptedException {
        synchronized (this) {
            this.wait(TIMER_WAIT);
        }

    }

    /**
     * Method executes the Move of the Player and determines, wheter the Game should end or not
     *
     * @param signal  signal the client shall look for
     * @param message message displayed on the scoreboard
     * @return true if the loop shall be breaked
     * @throws InterruptedException
     * @throws IOException
     */
    private boolean playerHandling(final String signal, final String message) throws InterruptedException, IOException {
        String lastMove;
        boolean result = false;
        pause();
        sendGetRequest();
        sendGetRequest();
        lastMove = extractBody();
        if (lastMove.equals(signal) || lastMove.equals(STRING_WIN_WHITE) || lastMove.equals(STRING_WIN_BLACK)) {
            gameWindow.disposeGameWindow();
            switch (lastMove) {
                case STRING_WIN_WHITE -> scoreboard.setMessage(STRING_WIN_WHITE);
                case STRING_WIN_BLACK -> scoreboard.setMessage(STRING_WIN_BLACK);
            }
            if (lastMove.equals(signal))
                scoreboard.setMessage(message);
            scoreboard.setVisible(true);
            result = true;
        } else {
            executeClientMove(lastMove, gameWindow);
        }
        return result;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (clientPlayer.getPlayerColor() == EPlayerColor.BLACK) {
                    if (playerHandling(SIGNAL_FF_WHITE, STRING_FF_WHITE)) {
                        break;
                    }
                }

                synchronized (this) {
                    this.wait();
                }
                if (executedMove) {
                    if (!gameWindow.getVisible()) {
                        sendPostRequest(scoreboard.getWinner());
                        break;
                    } else {
                        sendPostRequest(clientPlayer.getLastMove());
                    }
                    extractBody();
                    executedMove = false;
                } else if (endedGame) {
                    if (clientPlayer.getPlayerColor() == EPlayerColor.WHITE) {
                        sendPostRequest(SIGNAL_FF_WHITE);
                    } else if (clientPlayer.getPlayerColor() == EPlayerColor.BLACK) {
                        sendPostRequest(SIGNAL_FF_BLACK);
                    }
                    break;
                }

                if (clientPlayer.getPlayerColor() == EPlayerColor.WHITE) {
                    if (playerHandling(SIGNAL_FF_BLACK, STRING_FF_WHITE)) {
                        break;
                    }
                }

            } catch (InterruptedException | IOException e) {
            }
        }
    }
}
