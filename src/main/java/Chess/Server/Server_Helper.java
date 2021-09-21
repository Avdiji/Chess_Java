package Chess.Server;
import java.util.Random;

/**
 * @author Fitor Avdiji
 * <p>
 * Helper class to support the server
 */
public class Server_Helper {

    /**
     * Signals
     **/
    protected static final String SIGNAL_PLAYER1 = "PLAYER1";
    protected static final String SIGNAL_PLAYER2 = "PLAYER2";

    /**
     * Random object
     **/
    private Random random;


    /**
     * Constructor
     **/
    public Server_Helper() {
        random = new Random();
    }

    /**
     * Method randomly determines a Player
     **/
    protected final String determinePlayer() {
        String result;
        int randomInt = random.nextInt(2);
        result = randomInt == 0 ? SIGNAL_PLAYER1 : SIGNAL_PLAYER2;
        return result;
    }

}
