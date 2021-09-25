package Chess.Game.GUI.ClientGUI;

import Chess.Client.HTTP_Client;
import Chess.Game.GUI.IChessFrame;
import Chess.Game.GUI.MainMenu;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Fitor Avdiji
 * <p>
 * Frame to Log into the server
 */
public class ServerLogin extends JFrame implements IChessFrame {

    /**
     * Sizes used in the ServerLogin
     **/
    private static final int SIZE_WIDTH = 500;
    private static final int SIZE_HEIGHT = 250;

    /**
     * Sizes of the Textfields
     **/
    private static final int SIZE_WIDTH_TEXTFIELD = 300;
    private static final int SIZE_HEIGHT_TEXTFIELD = 25;

    /**
     * margins of this class
     **/
    private static final int MARGIN_LABEL[] = {20, 30, 20, 30};
    private static final int MARGIN_BUTTONS[] = {25, 0, 0, 0};

    /**
     * Strings constants for this class
     **/
    private static final String STRING_HOSTNAME = "Hostname:";
    private static final String STRING_PORT = "Port:";
    private static final String STRING_BUTTON_MENU = "Back to Menu";
    private static final String STRING_BUTTON_START = "Start Game";

    /**
     * Strings for the Submit Screen
     **/
    public static final String STRING_ERROR_LABEL = "Invalid Input!";
    public static final String STRING_ERROR_BUTTON = "Back to Login";

    /**
     * GridBagConstraints for the Layout of the ServerLogin
     **/
    private GridBagConstraints gbc;

    /**
     * JLabels for the Layout of this class
     **/
    private JLabel label_hostname;
    private JLabel label_port;

    /**
     * Textfields containing the hostname and the port
     **/
    private JTextField textfield_hostname;
    private JTextField textfield_port;

    /**
     * Buttons to start the game/go back to the main menu
     **/
    private JPanel panel_buttons;
    private JButton button_startGame;
    private JButton button_menu;

    /**
     * Main Menu
     **/
    private final MainMenu mainMenu;

    /**
     * Constructor
     **/
    public ServerLogin(final MainMenu mm) {
        this.mainMenu = mm;
        initComponents();
        initMainFrame();
        addComponents();
        this.setVisible(true);
    }

    /**
     * Getter for {@link #mainMenu}
     *
     * @return mainMenu
     */
    public MainMenu getMainMenu() {
        return mainMenu;
    }

    /**
     * Getter for this
     *
     * @return this
     */
    private ServerLogin getServerLogin() {
        return this;
    }

    /**
     * Getter for the portnumber from the GUI
     *
     * @return Portnumber
     */
    public int getPort() {
        int result = 0;
        try {
            result = Integer.parseInt(textfield_port.getText());
        } catch (NumberFormatException e) {
        }
        return result;
    }

    /**
     * Getter for the Hostname from the GUI
     *
     * @return Hostname
     */
    public String getHostname() {
        return textfield_hostname.getText();
    }

    /**
     * Button initializes {@link #button_startGame}
     */
    private void initButton_startGame() {
        button_startGame = new JButton(STRING_BUTTON_START);
        button_startGame.setBackground(MainMenu.COLOR_BUTTON_BACKGROUND);
        button_startGame.setFont(new Font(FONT, FONT_TYPE, SIZE_BUTTON_LABEL));
        button_startGame.setForeground(MainMenu.COLOR_LABEL);

        button_startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread clientThread = new Thread(new HTTP_Client(getServerLogin()));
                clientThread.start();
            }
        });
    }

    /**
     * Button initializes {@link #button_menu}
     */
    private void initButton_menu() {
        button_menu = new JButton(STRING_BUTTON_MENU);
        button_menu.setBackground(MainMenu.COLOR_BUTTON_BACKGROUND);
        button_menu.setFont(new Font(FONT, FONT_TYPE, SIZE_BUTTON_LABEL));
        button_menu.setForeground(MainMenu.COLOR_LABEL);

        button_menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                mainMenu.setVisible(true);
            }
        });
    }

    @Override
    public void initMainFrame() {
        this.setSize(SIZE_WIDTH, SIZE_HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(MainMenu.COLOR_BACKGROUND);
        this.setLayout(new GridBagLayout());
        this.setUndecorated(true);
    }

    @Override
    public void initComponents() {
        initButton_startGame();
        initButton_menu();

        label_hostname = new JLabel(STRING_HOSTNAME);
        label_hostname.setForeground(MainMenu.COLOR_LABEL);
        label_hostname.setBackground(MainMenu.COLOR_BACKGROUND);
        label_hostname.setFont(new Font(FONT, FONT_TYPE, SIZE_BUTTON_LABEL));

        label_port = new JLabel(STRING_PORT);
        label_port.setForeground(MainMenu.COLOR_LABEL);
        label_port.setBackground(MainMenu.COLOR_BACKGROUND);
        label_port.setFont(new Font(FONT, FONT_TYPE, SIZE_BUTTON_LABEL));

        textfield_hostname = new JTextField();
        textfield_hostname.setPreferredSize(new Dimension(SIZE_WIDTH_TEXTFIELD, SIZE_HEIGHT_TEXTFIELD));
        textfield_hostname.setForeground(MainMenu.COLOR_LABEL);
        textfield_hostname.setBackground(MainMenu.COLOR_BACKGROUND);
        textfield_port = new JTextField();
        textfield_port = new JTextField();
        textfield_port.setPreferredSize(new Dimension(SIZE_WIDTH_TEXTFIELD, SIZE_HEIGHT_TEXTFIELD));
        textfield_port.setForeground(MainMenu.COLOR_LABEL);
        textfield_port.setBackground(MainMenu.COLOR_BACKGROUND);

        panel_buttons = new JPanel();
        panel_buttons.setLayout(new GridBagLayout());
        panel_buttons.setBackground(MainMenu.COLOR_BACKGROUND);
    }

    @Override
    public void addComponents() {
        gbc = new GridBagConstraints();

        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(MARGIN_LABEL[0], MARGIN_LABEL[1], MARGIN_LABEL[2], MARGIN_LABEL[3]);
        this.add(label_hostname, gbc);
        gbc.gridy = 1;
        this.add(label_port, gbc);

        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.gridx = 1;
        this.add(textfield_port, gbc);
        gbc.gridy = 0;
        this.add(textfield_hostname, gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel_buttons.add(button_startGame, gbc);
        gbc.gridx = 1;
        panel_buttons.add(button_menu, gbc);

        gbc.insets = new Insets(MARGIN_BUTTONS[0], MARGIN_BUTTONS[1], MARGIN_BUTTONS[2], MARGIN_BUTTONS[3]);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 2;
        this.add(panel_buttons, gbc);
    }

    @Override
    public void reColor() {
        this.getContentPane().setBackground(MainMenu.COLOR_BACKGROUND);

        label_hostname.setForeground(MainMenu.COLOR_LABEL);
        label_hostname.setBackground(MainMenu.COLOR_BACKGROUND);

        label_port.setForeground(MainMenu.COLOR_LABEL);
        label_port.setBackground(MainMenu.COLOR_BACKGROUND);

        textfield_hostname.setForeground(MainMenu.COLOR_LABEL);
        textfield_hostname.setBackground(MainMenu.COLOR_BACKGROUND);

        textfield_port.setForeground(MainMenu.COLOR_LABEL);
        textfield_port.setBackground(MainMenu.COLOR_BACKGROUND);

        panel_buttons.setBackground(MainMenu.COLOR_BACKGROUND);
    }
}
