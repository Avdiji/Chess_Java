package Chess.Game.GUI.ClientGUI;

import Chess.Game.GUI.IChessFrame;
import Chess.Game.GUI.MainMenu;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ServerLogin extends JFrame implements IChessFrame {

    /**
     * Sizes used in the ServerLogin
     **/
    private static final int SIZE_WIDTH = 600;
    private static final int SIZE_LENGTH = 500;

    /** Strings constants for this class **/
    private static final String STRING_HOSTNAME = "Hostname:";
    private static final String STRING_PORT = "Port:";

    /** JLabels for the Layout of this class **/
    private JLabel label_hostname;
    private JLabel label_port;

    /** Textfields containing the hostname and the port **/
    private JTextField textfield_hostname;
    private JTextField textfield_port;

    /** Buttons to start the game/go back to the main menu **/
    private JButton button_startGame;
    private JButton button_menu;

    /**
     * Constructor
     **/
    public ServerLogin() {
        initComponents();
        initMainFrame();
        addComponents();
    }

    @Override
    public void initMainFrame() {
        this.setSize(SIZE_WIDTH, SIZE_LENGTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.getContentPane().setBackground(MainMenu.COLOR_BACKGROUND);
        this.setUndecorated(true);
    }

    @Override
    public void initComponents() {

    }

    @Override
    public void addComponents() {

    }

    @Override
    public void reColor() {
        this.getContentPane().setBackground(MainMenu.COLOR_BACKGROUND);
    }
}
