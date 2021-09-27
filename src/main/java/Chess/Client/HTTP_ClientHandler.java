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
 * Class is responsible to communicate with the corresponding server
 */
public class HTTP_ClientHandler implements Runnable {

    /** Time to wait after a response has been read **/
    private static final int TIMER_WAIT = 500;

    /** Strings to end the game and initialize the scoreboard **/
    private static final String STRING_FF_WHITE = "WHITE gave up";
    private static final String STRING_FF_BLACK = "BLACK gave up";
    public static final String STRING_WIN_WHITE = "WHITE Won";
    public static final String STRING_WIN_BLACK = "BLACK Won";
    public static final String STRING_STALEMATE = "STALEMATE";

    /** Signals, to look out for **/
    public static final String SIGNAL_CONTINUE = "CONTINUE";
    public static final String SIGNAL_FF_WHITE = "FF_WHITE";
    public static final String SIGNAL_FF_BLACK = "FF_BLACK";
    private static final String SIGNAL_REPEAT = "HTTP/1.1 200 OK";

    /** Corresponding gameWindow of the client **/
    private final GameWindow gameWindow;

    /** Corresponding scoreboard **/
    private final Scoreboard scoreboard;

    /** Player of this client **/
    private final Player clientPlayer;

    /** BufferedWriter/Reader of this client **/
    private final BufferedWriter bw;
    private final BufferedReader br;

    /** Hostname of the corresponding server **/
    private String hostname;

    /** True if the game is over **/
    private boolean endedGame;

    /**
     * Constructor
     *
     * @param gameWindow gamewindow of this client
     * @param scoreboard scoreboard of this client (needed to end a game)
     * @param clientPlayer player of this client
     * @param bw bufferedwriter to communicate with the server
     * @param br bufferedreader to communicate with the server
     */
    public HTTP_ClientHandler(final GameWindow gameWindow,
                              final Scoreboard scoreboard,
                              final Player clientPlayer,
                              final BufferedWriter bw,
                              final BufferedReader br)
    {
        this.gameWindow = gameWindow;
        this.scoreboard = scoreboard;
        this.clientPlayer = clientPlayer;
        this.bw = bw;
        this.br = br;

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
     * Setter for {@link #endedGame}
     *
     * @param value new value for endedGame
     */
    public void setEndedGame(final boolean value) {
        endedGame = value;
    }

    /**
     * The Method decodes and executes the move received
     *
     * @param move move to be executed
     * @param gameWindow gamewindow, in which the move will be executed
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
     * Method sends a post request to the server,
     * which contains the move, that the player has made
     *
     * @param message
     * @throws IOException
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
     * Method reads from the BufferedReader, and returns the last line of it,
     * which contains a command for this client
     *
     * @return The last line in the BufferedReader
     * @throws IOException
     */
    private String extractBody() throws IOException {
        String result;
        while (!br.readLine().isEmpty()) ;
        result = br.readLine();
        if (result.equals(SIGNAL_REPEAT) || result.equals(SIGNAL_CONTINUE))
            result = extractBody();
        return result;
    }

    /**
     * Method sends a get request to the server
     *
     * @throws IOException
     */
    private void sendGetRequest() throws IOException {
        bw.write("GET / HTTP/1.1\r\n");
        bw.write("Host: " + hostname + "\r\n");
        bw.write("\r\n");
        bw.flush();
    }

    /**
     * Method pauses this thread (to prevent bugs)
     *
     * @throws InterruptedException
     */
    private void pause() throws InterruptedException {
        synchronized (this) {
            this.wait(TIMER_WAIT);
        }
    }

    /**
     * Method executes the move of the player and determines, whether the game should end or not
     *
     * @param signal  signal the client shall look for
     * @param message message displayed on the scoreboard
     * @return true if this thread shall be terminated
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
        if (lastMove.equals(signal) || lastMove.equals(STRING_WIN_WHITE) || lastMove.equals(STRING_WIN_BLACK) || lastMove.equals(STRING_STALEMATE)) {
            gameWindow.disposeGameWindow();
            switch (lastMove) {
                case STRING_WIN_WHITE -> scoreboard.setMessage(STRING_WIN_WHITE);
                case STRING_WIN_BLACK -> scoreboard.setMessage(STRING_WIN_BLACK);
                case STRING_STALEMATE -> scoreboard.setMessage(STRING_STALEMATE);
            }
            if (lastMove.equals(signal))
                scoreboard.setMessage(message);
            scoreboard.setScoreboardVisible();
            result = true;
        } else {
            executeClientMove(lastMove, gameWindow);
        }
        return result;
    }

    @Override
    public void run() {

        while (true) { // Method is kinda clumsy because I used ngrok, which forces me to reconnect after every response
            try {
                if (clientPlayer.getPlayerColor() == EPlayerColor.BLACK) {
                    if (playerHandling(SIGNAL_FF_WHITE, STRING_FF_WHITE)) {
                        break;
                    }
                }

                synchronized (this) { this.wait(); } // Wait until this thread gets notified (happens when moving a piece)

                if (endedGame) { // one of the players has surrendered
                    if (clientPlayer.getPlayerColor() == EPlayerColor.WHITE) {
                        sendPostRequest(SIGNAL_FF_WHITE);
                    } else if (clientPlayer.getPlayerColor() == EPlayerColor.BLACK) {
                        sendPostRequest(SIGNAL_FF_BLACK);
                    }
                    break;
                } else if (!gameWindow.getVisible()) { // the game came to an end naturally
                    sendPostRequest(scoreboard.getWinner());
                    break;
                } else {
                    sendPostRequest(clientPlayer.getLastMove()); // the game has not ended
                }
                extractBody();

                if (clientPlayer.getPlayerColor() == EPlayerColor.WHITE) {
                    if (playerHandling(SIGNAL_FF_BLACK, STRING_FF_BLACK)) {
                        break;
                    }
                }

            } catch (InterruptedException | IOException e) {
            }
        }
    }
}
