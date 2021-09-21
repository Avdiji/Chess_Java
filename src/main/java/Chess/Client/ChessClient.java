package Chess.Client;

import Chess.Game.GUI.ChessboardGUI.GameWindow;
import Chess.Game.GUI.ClientGUI.ServerLogin;
import Chess.Game.GUI.ClientGUI.SubmitScreen;
import Chess.Game.Logic.ChessField;
import Chess.Game.Logic.Player.EPlayerColor;
import Chess.Game.Logic.Player.Player;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * @author Fitor Avdiji
 * <p>
 * Client, that communicates with the ChessServer
 */
public class ChessClient implements Runnable {

    /**
     * Signals
     **/
    private static final String SIGNAL_PLAYER1 = "PLAYER1";

    /**
     * initialization path of classic chess
     **/
    private static final String PATH_INIT = "src/main/resources/initilization/init_default.csv";

    /**
     * Strings for the submit screen if successfull
     **/
    private static final String STRING_NOERROR_LABEL = "Waiting for other Player...";
    private static final String STRING_NOERROR_BUTTON = "Cancel";

    /**
     * Port used to connect to server
     **/
    private final int port;
    /**
     * Hostname of the Server this client shall connect with
     **/
    private final String hostname;

    /**
     * LoginScreen of this client
     **/
    private final ServerLogin serverLogin;

    /**
     * Players
     **/
    private Player player1;
    private Player player2;

    /**
     * Constructor
     *
     * @param serverLogin
     */
    public ChessClient(final ServerLogin serverLogin) {
        this.serverLogin = serverLogin;
        port = serverLogin.getPort();
        hostname = serverLogin.getHostname();
    }

    /**
     * Method initializes:<br>
     * {@link #player1}
     * {@link #player2}
     *
     * @param playerSignal signal from the server
     */
    private void initPlayers(final String playerSignal) {
        player1 = new Player(playerSignal.equals(SIGNAL_PLAYER1) ? EPlayerColor.WHITE : EPlayerColor.NONE);
        player2 = new Player(playerSignal.equals(SIGNAL_PLAYER1) ? EPlayerColor.NONE : EPlayerColor.BLACK);
    }

    @Override
    public void run() {
        try (Socket socket = new Socket(hostname, port);
             BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        ) {

            SubmitScreen ss = new SubmitScreen(serverLogin);
            ss.setString_label(STRING_NOERROR_LABEL);
            ss.setString_button(STRING_NOERROR_BUTTON);
            serverLogin.setVisible(false);

            String playersignal;
            while (!(playersignal = br.readLine()).isEmpty()) {
                if (playersignal.length() > 0) {
                    initPlayers(playersignal);
                }
            }
            ChessField chessField = new ChessField(player1, player2);
            chessField.initField(PATH_INIT);
            GameWindow gameWindow = new GameWindow(chessField, serverLogin.getMainMenu().getScoreboard());


        } catch (IOException e) {
            SubmitScreen ss = new SubmitScreen(serverLogin);
            ss.setString_button(ServerLogin.STRING_ERROR_BUTTON);
            ss.setString_label(ServerLogin.STRING_ERROR_LABEL);
            serverLogin.setVisible(false);
        }
    }
}
