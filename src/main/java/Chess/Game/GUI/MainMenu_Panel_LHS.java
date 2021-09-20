package Chess.Game.GUI;

import Chess.Game.GUI.ChessboardGUI.GameWindow;
import Chess.Game.GUI.ClientGUI.ServerLogin;
import Chess.Game.Logic.ChessField;
import Chess.Game.Logic.Player.EPlayerColor;
import Chess.Game.Logic.Player.Player;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 * @author Fitor Avdiji
 * <p>
 * Panel in the Main Menu, that contains all the Buttons (left side of the main menu)
 */
public class MainMenu_Panel_LHS extends JPanel implements IChessFrame {

    /**
     * Margin of the Buttons of this Panel
     **/
    private static final int MARGIN_BUTTONS_MAINMENU[] = {20, 70, 10, 10};

    /**
     * Strings, needed for the GUI
     **/
    private static final String STRING_BUTTON_LOCAL = "Multiplayer Local";
    private static final String STRING_BUTTON_ONLINE = "Online (Only Classic)";
    private static final String STRING_BUTTON_AI = "Player vs AI";
    private static final String STRING_BUTTON_EXIT = "Exit";

    /**
     * String used in all the different Gamemodes
     **/
    private static final String[] STRING_GAMEMODES = {"Classic", "Rook/Bishop Only", "Pawn/Knight Only"};
    private static final String STRING_GAMEMODE_PATH[] = {
            "src/main/resources/initilization/init_default.csv",
            "src/main/resources/initilization/init_rook_bishop.csv",
            "src/main/resources/initilization/init_pawn_knight.csv"};

    /**
     * Components of this Panel
     **/
    private JButton button_local;
    private JButton button_online;
    private JButton button_ai;
    private JButton button_exit;

    /**
     * MainMenu of this Panel
     **/
    private MainMenu mainMenu;

    /**
     * Scoreboard of the corresponding panel
     **/
    private Scoreboard scoreboard;

    /**
     * Combobox with all the different Gamemodes
     **/
    private JComboBox<String> combobox_gamemodes;

    /**
     * GridBagConstraints for the Layout
     **/
    private GridBagConstraints gbc;

    /**
     * Constructor
     *
     * @param mm mainMenu
     */
    public MainMenu_Panel_LHS(final MainMenu mm, final Scoreboard scoreboard) {
        this.mainMenu = mm;
        this.scoreboard = scoreboard;

        initMainFrame();
        initComponents();
        addComponents();
    }

    /**
     * Method initializes {@link #button_local}
     */
    private void initButton_local() {
        button_local = new JButton(STRING_BUTTON_LOCAL);
        button_local.setFont(new Font(FONT, FONT_TYPE, MainMenu.SIZE_BUTTON_MAINMENU));
        button_local.setForeground(MainMenu.COLOR_LABEL);
        button_local.setBackground(MainMenu.COLOR_BUTTON_BACKGROUND);

        button_local.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChessField chessField = new ChessField(new Player(EPlayerColor.WHITE), new Player(EPlayerColor.BLACK));
                chessField.initField(STRING_GAMEMODE_PATH[combobox_gamemodes.getSelectedIndex()]);
                new GameWindow(chessField, scoreboard);
                mainMenu.setVisible(false);
            }
        });
    }

    /**
     * Method initializes {@link #button_online
     **/
    private void initButton_online() {
        button_online = new JButton(STRING_BUTTON_ONLINE);
        button_online.setFont(new Font(FONT, FONT_TYPE, MainMenu.SIZE_BUTTON_MAINMENU));
        button_online.setForeground(MainMenu.COLOR_LABEL);
        button_online.setBackground(MainMenu.COLOR_BUTTON_BACKGROUND);

        button_online.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainMenu.setVisible(false);
                new ServerLogin(mainMenu);
            }
        });

    }

    /**
     * Method initializes {@link #button_ai}
     */
    private void initButton_ai() {
        button_ai = new JButton(STRING_BUTTON_AI);
        button_ai.setFont(new Font(FONT, FONT_TYPE, MainMenu.SIZE_BUTTON_MAINMENU));
        button_ai.setForeground(MainMenu.COLOR_LABEL);
        button_ai.setBackground(MainMenu.COLOR_BUTTON_BACKGROUND);
    }

    /**
     * Method initializes {@link #button_exit}
     */
    private void initButton_exit() {
        button_exit = new JButton(STRING_BUTTON_EXIT);
        button_exit.setFont(new Font(FONT, FONT_TYPE, MainMenu.SIZE_BUTTON_MAINMENU));
        button_exit.setForeground(MainMenu.COLOR_LABEL);
        button_exit.setBackground(MainMenu.COLOR_BUTTON_BACKGROUND);

        button_exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scoreboard.dispose();
                mainMenu.setVisible(false);
                mainMenu.dispose();
            }
        });
    }

    @Override
    public void initMainFrame() {
        this.setLayout(new GridBagLayout());
        this.setBackground(MainMenu.COLOR_BACKGROUND);
    }

    @Override
    public void initComponents() {
        combobox_gamemodes = new JComboBox<>();
        Arrays.stream(STRING_GAMEMODES).forEach(value -> combobox_gamemodes.addItem(value));
        combobox_gamemodes.setFont(new Font(FONT, FONT_TYPE, MainMenu.SIZE_BUTTON_MAINMENU));
        combobox_gamemodes.setBackground(MainMenu.COLOR_BUTTON_BACKGROUND);
        combobox_gamemodes.setForeground(MainMenu.COLOR_LABEL);
        ((JLabel) combobox_gamemodes.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        initButton_local();
        initButton_online();
        initButton_ai();
        initButton_exit();
    }

    @Override
    public void addComponents() {
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(MARGIN_BUTTONS_MAINMENU[0], MARGIN_BUTTONS_MAINMENU[1], MARGIN_BUTTONS_MAINMENU[2], MARGIN_BUTTONS_MAINMENU[3]);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(button_local, gbc);
        gbc.gridy = 1;
        this.add(button_online, gbc);
        gbc.gridy = 2;
        this.add(button_ai, gbc);
        gbc.gridy = 3;
        this.add(combobox_gamemodes, gbc);
        gbc.insets = new Insets(MARGIN_BUTTONS_MAINMENU[0] * 3, MARGIN_BUTTONS_MAINMENU[1], MARGIN_BUTTONS_MAINMENU[2], MARGIN_BUTTONS_MAINMENU[3]);
        gbc.gridy = 4;
        this.add(button_exit, gbc);
    }

    @Override
    public void reColor() {
        this.setBackground(MainMenu.COLOR_BACKGROUND);
        combobox_gamemodes.setBackground(MainMenu.COLOR_BUTTON_BACKGROUND);
        combobox_gamemodes.setForeground(MainMenu.COLOR_LABEL);
        button_local.setForeground(MainMenu.COLOR_LABEL);
        button_online.setForeground(MainMenu.COLOR_LABEL);
        button_ai.setForeground(MainMenu.COLOR_LABEL);
        button_exit.setForeground(MainMenu.COLOR_LABEL);
        button_local.setBackground(MainMenu.COLOR_BUTTON_BACKGROUND);
        button_online.setBackground(MainMenu.COLOR_BUTTON_BACKGROUND);
        button_ai.setBackground(MainMenu.COLOR_BUTTON_BACKGROUND);
        button_exit.setBackground(MainMenu.COLOR_BUTTON_BACKGROUND);
    }
}
