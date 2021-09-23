package Chess.Server;

import Chess.Game.Logic.Player.EPlayerColor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;

/**
 * @author Fitor Avdiji
 * <p>
 * Class represents a Client Connection (an online Player)
 */
public class Server_Player implements Runnable {

    /**
     * Color of the Player
     **/
    private final EPlayerColor playerColor;
    /**
     * Socket of the Player
     **/
    private final Socket playerSocket;

    /**
     * Buffered Reader/Writer of the Player
     **/
    private final BufferedReader br;
    private final BufferedWriter bw;

    /**
     * Enemy Player
     **/
    private Server_Player enemy;

    /**
     * Constructor
     *
     * @param playerColor  color of the Player
     * @param playerSocket socket of the Player
     */
    public Server_Player(final EPlayerColor playerColor, final Socket playerSocket, final BufferedReader br, final BufferedWriter bw) {
        this.playerColor = playerColor;
        this.playerSocket = playerSocket;
        this.br = br;
        this.bw = bw;
    }

    /**
     * Setter for {@link #enemy}
     *
     * @param enemyPlayer
     */
    protected void setEnemy(final Server_Player enemyPlayer) {
        this.enemy = enemyPlayer;
    }

    /**
     * Getter for {@link #bw}
     * @return BufferedWriter of this class
     */
    protected BufferedWriter getBufferedWriter(){
        return bw;
    }

    /**
     * Method sends the Color of the Player to the Client
     **/
    protected void send_color() throws IOException {
        bw.write(playerColor + "\r\n");
        bw.flush();
    }

    /**
     * Method sends the move to be executed to the corresponding client
     *
     * @param move last move executed by the enemy player
     */
    protected void sendMoveToEnemy(final String move) throws IOException {
        enemy.getBufferedWriter().write(move + "\r\n");
        enemy.getBufferedWriter().flush();
    }

    @Override
    public void run() {
        String tmp_lastMove;
        String tmp_line;
        while (true) {
            try {
                // scan line until the client has sent a signal
                if ((tmp_line = br.readLine()) != null && !tmp_line.isEmpty()) {
                    tmp_lastMove = tmp_line;
                    sendMoveToEnemy(tmp_lastMove); // forward the signal to the enemy client
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}