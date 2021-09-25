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
    public HTTP_ServerHandler(final EPlayerColor playerColor, final ServerSocket serverSocket) throws IOException {
        this.playerColor = playerColor;
        this.serverSocket = serverSocket;

        establishConnection();
    }

    /** Method establishes a new socket connection **/
    protected void establishConnection() throws IOException{
            playerSocket = serverSocket.accept();
            br = new BufferedReader(new InputStreamReader(playerSocket.getInputStream()));
            bw = new BufferedWriter(new OutputStreamWriter(playerSocket.getOutputStream()));
    }

    /** Method handles Get requests **/
    protected void handleGetRequest(String message) throws IOException {
        String line;
        while(!(line = br.readLine()).isEmpty()){
            System.out.println(line);
        }
        message += "\r\n";
        bw.write("HTTP/1.1 200 OK\r\n");
        bw.write("Content-Type: text/html; charset=iso-8859-1\r\n");
        bw.write("Content-Length: " + message.length() + "\r\n");
        bw.write("\r\n");
        bw.write(message + "\r\n");
        bw.write("\r\n");
        bw.flush();

        playerSocket.close();
        bw.close();
        br.close();
        System.out.println("ENDING GET REQUEST");

        establishConnection();
    }

    /** Method handles Put requests **/
    protected void handlePutRequest() throws IOException {
        String line;
        while(!(line = br.readLine()).isEmpty()){
            System.out.println(line);
        }
        System.out.println(br.readLine());
        String message = "PUTEN\r\n";
        bw.write("HTTP/1.1 200 OK\r\n");
        bw.write("Content-Type: text/html\r\n");
        bw.write("Content-Length: " + message.length() + "\r\n");
        bw.write("\r\n");
        bw.write(message + "\r\n");
        bw.write("\r\n");
        bw.flush();

        playerSocket.close();
        bw.close();
        br.close();
        System.out.println("ENDING PUT REQUEST");

        //enemy should connect here
    }

    @Override
    public void run() {
        while (serverSocket.isBound() && !serverSocket.isClosed()){
            try {
                String line = br.readLine();

                if(line != null) {
                    if (line.contains("GET")) {
                        System.out.println();
                        System.out.println("STARTING GET REQUEST");
                        System.out.println(line);
                        handleGetRequest(playerColor.toString());
                    }else if(line.contains("PUT")){
                        System.out.println();
                        System.out.println("STARTING PUT REQUEST");
                        System.out.println(line);
                        handlePutRequest();
                    }
                }
            }catch (IOException e){}
        }

    }
}