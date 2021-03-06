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
public class HTTP_Client implements Runnable {

    /** initialization path of classic chess **/
    private static final String PATH_INIT = "/initilization/init_default.csv";

    /** Strings for the submit screen if the Connection was Successfull **/
    private static final String STRING_NOERROR_LABEL = "Waiting for other Player...";
    private static final String STRING_NOERROR_BUTTON = "Cancel";

    /** Port used to connect to server **/
    private final int port;

    /** Hostname of the Server this client shall connect with **/
    private final String hostname;

    /** LoginScreen of this client **/
    private final ServerLogin serverLogin;

    /** Player 1 **/
    private Player player1;
    /** Player 2 **/
    private Player player2;

    /** Helper Object, to handle the communication with the server **/
    private HTTP_ClientHandler moveGenerator;

    /**
     * Constructor
     *
     * @param serverLogin login screen
     */
    public HTTP_Client(final ServerLogin serverLogin) {
        this.serverLogin = serverLogin;
        port = serverLogin.getPort();
        hostname = serverLogin.getHostname();
    }

    /**
     * Method initializes:<br>
     * {@link #player1}
     * {@link #player2}
     * according to the received PlayerColor
     *
     * @param playerColor signal from the server
     */
    private void initPlayers(final String playerColor) {
        player1 = new Player(playerColor.equals(EPlayerColor.WHITE.toString()) ? EPlayerColor.WHITE : EPlayerColor.NONE);
        player2 = new Player(playerColor.equals(EPlayerColor.WHITE.toString()) ? EPlayerColor.NONE : EPlayerColor.BLACK);
    }

    /**
     * Method sends an initial Get-request to the server
     *
     * @param bw buffered writer of the client
     * @param hostname hostname of the server
     * @throws IOException
     */
    private void sendGetRequest(final BufferedWriter bw, final String hostname) throws IOException {
        bw.write("GET / HTTP/1.1\r\n");
        bw.write("Host: " + hostname + "\r\n");
        bw.write("\r\n");
        bw.flush();
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
            ss.removeButton();
            serverLogin.setVisible(false);

            sendGetRequest(bw, hostname);

            String line;
            while (!br.readLine().isEmpty()) ;
            line = br.readLine();

            ss.setVisible(false);
            initPlayers(line);
            ChessField chessField = new ChessField(player1, player2);
            chessField.initField(PATH_INIT);
            GameWindow gameWindow = new GameWindow(chessField, serverLogin.getMainMenu().getScoreboard());
            gameWindow.setTitleText(line);
            moveGenerator = new HTTP_ClientHandler(gameWindow,
                    serverLogin.getMainMenu().getScoreboard(),
                    player1.getPlayerColor() == EPlayerColor.NONE ? player2 : player1,
                    bw, br);
            moveGenerator.setHostname(hostname);
            gameWindow.setNotifyClient(moveGenerator);

            Thread clientThread = new Thread(moveGenerator);
            clientThread.start();
            clientThread.join();
            gameWindow.playSound(GameWindow.SOUND_END);
            ss.dispose();

        } catch (IOException | InterruptedException e) {
            SubmitScreen ss = new SubmitScreen(serverLogin);
            ss.setString_button(ServerLogin.STRING_ERROR_BUTTON);
            ss.setString_label(ServerLogin.STRING_ERROR_LABEL);
            serverLogin.setVisible(false);
        }
    }
}
