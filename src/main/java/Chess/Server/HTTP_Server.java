package Chess.Server;

import Chess.Game.Logic.Player.EPlayerColor;

import java.io.IOException;
import java.net.ServerSocket;
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
            HTTP_ServerHandler player2 = new HTTP_ServerHandler(color2, serverSocket);

            player1.handleGetRequest(color1.toString());
            player2.handleGetRequest(color2.toString());

            player2.establishConnection();
            player1.establishConnection();
            player2.handleGetRequest("HALLOREN");
            player1.handlePostRequest();

            player2.establishConnection();
            player2.handleGetRequest(player1.getLastMoveReceived());


            while (true);
        }
    }
}
