package Chess.Server;

import Chess.Game.Logic.Player.EPlayerColor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Fitor Avdiji
 * <p>
 * Class represents a Client Connection (an online Player)
 */
public class HTTP_ServerHandler {

    /**
     * Color of the Player
     **/
    private final EPlayerColor playerColor;

    /**
     * Corresponding Serversocket
     **/
    private final ServerSocket serverSocket;

    /**
     * Socket of the Player
     **/
    private Socket playerSocket;
    private BufferedReader br;
    private BufferedWriter bw;

    /**
     * Last move received from the Clients
     **/
    private String lastMoveReceived;

    /**
     * Constructor
     *
     * @param playerColor  color of the Player
     * @param playerSocket socket of the Player
     */
    public HTTP_ServerHandler(final EPlayerColor playerColor, final ServerSocket serverSocket) throws IOException {
        this.playerColor = playerColor;
        this.serverSocket = serverSocket;

        establishConnection();
    }

    /**
     * Getter for {@link #lastMoveReceived}
     *
     * @return lastMoveReceived
     */
    protected String getLastMoveReceived() {
        return lastMoveReceived;
    }

    /**
     * Method establishes a new socket connection
     **/
    protected void establishConnection() throws IOException {
        playerSocket = serverSocket.accept();
        br = new BufferedReader(new InputStreamReader(playerSocket.getInputStream()));
        bw = new BufferedWriter(new OutputStreamWriter(playerSocket.getOutputStream()));
    }

    /**
     * Method handles Get requests
     **/
    protected void handleGetRequest(String message) throws IOException {
        System.out.println();
        System.out.println("START GET REQUEST");
        String line;
        while (!(line = br.readLine()).isEmpty()) {
            System.out.println(line);
        }
        message += "\r\n";
        bw.write("HTTP/1.1 200 OK\r\n");
        bw.write("Content-Type: text/plain\r\n");
        bw.write("Content-Length: " + message.length() + "\r\n");
        bw.write("\r\n");
        bw.write(message + "\r\n");
        bw.write("\r\n");
        bw.flush();

        playerSocket.close();
        bw.close();
        br.close();
        System.out.println("ENDING GET REQUEST");
    }

    /**
     * Method handles Put requests
     **/
    protected void handlePostRequest() throws IOException {
        System.out.println();
        System.out.println("START POST REQUEST");
        String line;
        while (!(line = br.readLine()).isEmpty()) {
            System.out.println(line);
        }
        lastMoveReceived = br.readLine();
        bw.write("HTTP/1.1 200 OK\r\n");
        bw.write("Content-Type: text/plain\r\n");
        bw.write("\r\n");
        bw.flush();

        playerSocket.close();
        bw.close();
        br.close();
        System.out.println("ENDING POST REQUEST");
    }
}