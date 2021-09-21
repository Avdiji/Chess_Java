package Chess.Server;

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
 * Server, that communicates with the ChessClient
 */
public class ChessServer implements Runnable {


    /**
     * Port of this Server
     **/
    private static final int PORT = 4711;

    /**
     * Server Helper
     **/
    private final Server_Helper server_helper;

    public ChessServer() {
        server_helper = new Server_Helper();
    }


    @Override
    public void run() {
        System.out.println("Starting the Server...");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

            try (Socket socket1 = serverSocket.accept();
                 Socket socket2 = serverSocket.accept();
                 BufferedReader br1 = new BufferedReader(new InputStreamReader(socket1.getInputStream()));
                 BufferedReader br2 = new BufferedReader(new InputStreamReader(socket2.getInputStream()));
                 BufferedWriter bw1 = new BufferedWriter(new OutputStreamWriter(socket1.getOutputStream()));
                 BufferedWriter bw2 = new BufferedWriter(new OutputStreamWriter(socket2.getOutputStream()));
            ) {

                String player1 = server_helper.determinePlayer();
                String player2 = player1.equals(Server_Helper.SIGNAL_PLAYER1) ? Server_Helper.SIGNAL_PLAYER2 : Server_Helper.SIGNAL_PLAYER1;

                bw1.write(player1 + "\r\n\r\n");
                bw1.flush();
                bw2.write(player2 + "\r\n\r\n");
                bw2.flush();

                while (true);

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
