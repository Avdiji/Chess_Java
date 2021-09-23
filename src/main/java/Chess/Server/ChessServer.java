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
public class ChessServer{


    /**
     * Port of this Server
     **/
    private static final int PORT = 4711;

    /** Random object **/
    private final static Random random = new Random();

    /** Constructor **/
    public ChessServer() {
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

                EPlayerColor color1 = random.nextInt(2) == 0 ? EPlayerColor.WHITE : EPlayerColor.BLACK;
                EPlayerColor color2 = color1 == EPlayerColor.WHITE ? EPlayerColor.BLACK : EPlayerColor.WHITE;

                Server_Player player1 = new Server_Player(color1, socket1, br1, bw1);
                Server_Player player2 = new Server_Player(color2, socket2, br2, bw2);

                player1.setEnemy(player2);
                player2.setEnemy(player1);

                player1.send_color();
                player2.send_color();

                Thread p1 = new Thread(player1);
                Thread p2 = new Thread(player2);
                p1.start();
                p2.start();
                p1.join();
                p2.join();
            }
        }
    }
}
