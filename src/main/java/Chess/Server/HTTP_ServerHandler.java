package Chess.Server;

import Chess.Game.Logic.Player.EPlayerColor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Fitor Avdiji
 * <p>
 * Class represents a Client Connection (an online Player)
 */
public class HTTP_ServerHandler extends Thread {

    /** Color of the Player **/
    private final EPlayerColor playerColor;

    /** Corresponding Serversocket **/
    private final ServerSocket serverSocket;

    /** Socket of the Player **/
    private Socket playerSocket;
    private BufferedReader br;
    private BufferedWriter bw;

    /** Enemy Player **/
    private HTTP_ServerHandler enemy;

    /**
     * Constructor
     *
     * @param playerColor  color of the Player
     * @param playerSocket socket of the Player
     */
    public HTTP_ServerHandler(final EPlayerColor playerColor, final ServerSocket serverSocket) {
        this.playerColor = playerColor;
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {

    }
}