package Chess.Server;

import Chess.Game.Logic.Player.EPlayerColor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/**
 * @author Fitor Avdiji
 * <p>
 * Server, that communicates with the ChessClient
 */
public class HTTP_Server {


    /** Port of this Server **/
    private static final int PORT = 4711;

    /** Random object **/
    private final static Random random = new Random();

    /** Constructor **/
    public HTTP_Server() {
    }

    public void runServer() throws IOException, InterruptedException {
        System.out.println("Starting the Server...");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

            try (Socket socket1 = serverSocket.accept();
                 Socket socket2 = serverSocket.accept();
                 BufferedReader br1 = new BufferedReader(new InputStreamReader(socket1.getInputStream()));
                 BufferedReader br2 = new BufferedReader(new InputStreamReader(socket2.getInputStream()));
                 BufferedWriter bw1 = new BufferedWriter(new OutputStreamWriter(socket1.getOutputStream()));
                 BufferedWriter bw2 = new BufferedWriter(new OutputStreamWriter(socket2.getOutputStream()));
            ) {

            }
        }
    }
}
