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
    private static final int PORT = 80;

    /** Random object **/
    private final static Random random = new Random();

    /** Constructor **/
    public HTTP_Server() {
    }

    public void runServer() throws IOException, InterruptedException {
        System.out.println("Starting the Server...");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

//            EPlayerColor color1 = random.nextInt(2) == 0 ? EPlayerColor.WHITE : EPlayerColor.BLACK;
            EPlayerColor color1 = EPlayerColor.WHITE;
            EPlayerColor color2 = color1 == EPlayerColor.WHITE ? EPlayerColor.BLACK : EPlayerColor.WHITE;

            HTTP_ServerHandler player1 = new HTTP_ServerHandler(color1, serverSocket);

            System.out.println();
            System.out.println("STARTING GET REQUEST");
            player1.handleGetRequest(color1.toString());


            player1.start();
            player1.join();

        }
    }
}
