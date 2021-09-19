package Chess.Game.GUI;

import Chess.Game.GUI.ChessboardGUI.GameWindow;
import Chess.Game.Logic.ChessField;
import Chess.Game.Logic.Player.EPlayerColor;
import Chess.Game.Logic.Player.Player;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Fitor Avdiji
 * <p>
 * Main Menu of the Game
 */
public class MainMenu extends JFrame implements IChessFrame {

    /**
     * Path to the Image of the Main Menu
     **/
    private static final String IMAGE_PATH_DARKMODE = "src/main/resources/Images/MainMenuImage_Darkmode.png";
    private static final String IMAGE_PATH_LIGHTMODE = "src/main/resources/Images/MainMenuImage_Lightmode.png";
    private static final String IMAGE_PATH_BLUE = "src/main/resources/Images/MainMenuImage_Blue.png";
    private static final String IMAGE_PATH_GREEN = "src/main/resources/Images/MainMenuImage_Green.png";

    /**
     * Currently Selected Colors
     **/
    public static Color COLOR_BACKGROUND = COLOR_BACKGROUND_DARKMODE;
    public static Color COLOR_LABEL = COLOR_LABEL_DARKMODE;
    public static Color COLOR_BUTTON_BACKGROUND = COLOR_BUTTON_BACKGROUND_DARKMODE;
    public static Color COLOR_FIELD_WHITE = COLOR_FIELD_WHITE_DARKMODE;
    public static Color COLOR_FIELD_BLACK = COLOR_FIELD_BLACK_DARKMODE;
    public static Color COLOR_FIELD_MARKED = COLOR_FIELD_MARKED_DARKMODE;
    public final static Color COLOR_FIELD_ENDANGERED = new Color(0xec7c26);

    /**
     * String used in all the different Gamemodes
     **/
    protected static final String[] STRING_GAMEMODES = {"Classic", "Rook/Bishop Only", "Pawn/Knight Only"};
    private static final String STRING_GAMEMODE_PATH[] = {
            "src/main/resources/initilization/init_default.csv",
            "src/main/resources/initilization/init_rook_bishop.csv",
            "src/main/resources/initilization/init_pawn_knight.csv"};

    /**
     * Sizes, used in the MainMenu
     **/
    private static final int SIZE_WIDTH = 1000;
    private static final int SIZE_LENGTH = 700;
    public static final int SIZE_BUTTON_MAINMENU = 30;

    /**
     * Margin of the Title
     **/
    private static final int MARGIN_TITLE_MAINMENU[] = {50, 0, 0, 0};
    /**
     * Text of the Title
     **/
    private static final String STRING_TITLE = "Avdiji's Chess";

    /**
     * JLabel, containing the title
     **/
    private JLabel label_title;

    /**
     * Panels of the Main Menu
     **/
    private MainMenu_Panel_LHS panel_LHS;
    private MainMenu_panel_RHS panel_RHS;

    /**
     * Objects to start the Game
     **/
    private Player player1;
    private Player player2;
    private ChessField chessField;

    /**
     * Scoreboard
     **/
    private Scoreboard scoreboard;

    /**
     * ActionsListeners for the Components of {@link #panel_LHS}
     **/
    private ActionListener AL_local;
    private ActionListener AL_online;
    private ActionListener AL_ai;
    private ActionListener AL_exit;

    /**
     * ActionListeners for the Components of {@link #panel_RHS}
     **/
    private ActionListener AL_lightmode;
    private ActionListener AL_darkmode;
    private ActionListener AL_blue;
    private ActionListener AL_green;

    /**
     * ActionsListeners for the scoreboard
     **/
    private ActionListener AL_scoreboard_mainmenu;
    private ActionListener AL_scoreboard_exit;

    /**
     * Constructor
     */
    public MainMenu() {
        initComponents();
        initMainFrame();
        addComponents();

        this.setUndecorated(true);
        this.setVisible(true);
    }

    //////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////// AL LHS  //////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////

    /**
     * Method initializes {@link #AL_local}
     **/
    private void initAL_local() {
        AL_local = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player1 = new Player(EPlayerColor.WHITE);
                player2 = new Player(EPlayerColor.BLACK);

                chessField = new ChessField(player1, player2);
                chessField.initField(STRING_GAMEMODE_PATH[panel_LHS.getCombobox_gamemodes().getSelectedIndex()]);

                setVisible(false);
                new GameWindow(chessField, scoreboard);
            }
        };
    }

    /**
     * Method initializes {@link #AL_exit}
     **/
    private void initAL_exit() {
        AL_exit = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scoreboard.dispose();
                setVisible(false);
                dispose();
            }
        };
    }

    //////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////// AL LHS  //////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////// AL RHS  //////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////

    /**
     * Method initializes: <br>
     * {@link #AL_lightmode}<br>
     * {@link #AL_darkmode}<br>
     * {@link #AL_blue}<br>
     * {@link #AL_green}<br>
     **/
    private void initAL_ColorModes() {
        AL_lightmode = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                COLOR_BACKGROUND = COLOR_BACKGROUND_LIGHTMODE;
                COLOR_LABEL = COLOR_LABEL_LIGHTMODE;
                COLOR_BUTTON_BACKGROUND = COLOR_BUTTON_BACKGROUND_LIGHTMODE;
                COLOR_FIELD_WHITE = COLOR_FIELD_WHITE_LIGHTMODE;
                COLOR_FIELD_BLACK = COLOR_FIELD_BLACK_LIGHTMODE;
                COLOR_FIELD_MARKED = COLOR_FIELD_MARKED_LIGHTMODE;
                panel_RHS.setPanelImage(IMAGE_PATH_LIGHTMODE);
                reColor();
            }
        };

        AL_darkmode = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                COLOR_BACKGROUND = COLOR_BACKGROUND_DARKMODE;
                COLOR_LABEL = COLOR_LABEL_DARKMODE;
                COLOR_BUTTON_BACKGROUND = COLOR_BUTTON_BACKGROUND_DARKMODE;
                COLOR_FIELD_WHITE = COLOR_FIELD_WHITE_DARKMODE;
                COLOR_FIELD_BLACK = COLOR_FIELD_BLACK_DARKMODE;
                COLOR_FIELD_MARKED = COLOR_FIELD_MARKED_DARKMODE;
                panel_RHS.setPanelImage(IMAGE_PATH_DARKMODE);
                reColor();
            }
        };

        AL_blue = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                COLOR_BACKGROUND = COLOR_BACKGROUND_BLUE;
                COLOR_LABEL = COLOR_LABEL_BLUE;
                COLOR_BUTTON_BACKGROUND = COLOR_BUTTON_BACKGROUND_BLUE;
                COLOR_FIELD_WHITE = COLOR_FIELD_WHITE_BLUE;
                COLOR_FIELD_BLACK = COLOR_FIELD_BLACK_BLUE;
                COLOR_FIELD_MARKED = COLOR_FIELD_MARKED_BLUE;
                panel_RHS.setPanelImage(IMAGE_PATH_BLUE);
                reColor();
            }
        };

        AL_green = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                COLOR_BACKGROUND = COLOR_BACKGROUND_GREEN;
                COLOR_LABEL = COLOR_LABEL_GREEN;
                COLOR_BUTTON_BACKGROUND = COLOR_BUTTON_BACKGROUND_GREEN;
                COLOR_FIELD_WHITE = COLOR_FIELD_WHITE_GREEN;
                COLOR_FIELD_BLACK = COLOR_FIELD_BLACK_GREEN;
                COLOR_FIELD_MARKED = COLOR_FIELD_MARKED_GREEN;
                panel_RHS.setPanelImage(IMAGE_PATH_GREEN);
                reColor();
            }
        };
    }
    //////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////// AL RHS  //////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////


    /////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////// AL Scoreboard  //////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Method initializes {@link #AL_scoreboard_mainmenu}
     **/
    private void initAL_scoreboard_mainmenu() {
        AL_scoreboard_mainmenu = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(true);
                scoreboard.setVisible(false);
            }
        };
    }

    /**
     * Method initializes {@link #AL_scoreboard_exit}
     **/
    private void initAL_scoreboard_exit() {
        AL_scoreboard_exit = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scoreboard.setVisible(false);
                scoreboard.dispose();
                setVisible(false);
                dispose();
            }
        };
    }
    /////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////// AL Scoreboard  //////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Method initializes {@link #label_title}
     */
    private void initTitle() {
        label_title = new JLabel(STRING_TITLE);
        label_title.setBackground(MainMenu.COLOR_BACKGROUND);
        label_title.setForeground(MainMenu.COLOR_LABEL);
        label_title.setHorizontalAlignment(JLabel.CENTER);
        label_title.setFont(new Font(FONT, FONT_TYPE, SIZE_TITLE));
        label_title.setBorder(new EmptyBorder(MARGIN_TITLE_MAINMENU[0], MARGIN_TITLE_MAINMENU[1], MARGIN_TITLE_MAINMENU[2], MARGIN_TITLE_MAINMENU[3]));
    }

    @Override
    public void initMainFrame() {
        this.setSize(SIZE_WIDTH, SIZE_LENGTH);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setBackground(COLOR_BACKGROUND);
    }

    @Override
    public void initComponents() {
        initTitle();
        initAL_ColorModes();

        initAL_local();
        initAL_exit();

        initAL_scoreboard_mainmenu();
        initAL_scoreboard_exit();

        panel_LHS = new MainMenu_Panel_LHS();
        panel_RHS = new MainMenu_panel_RHS();

        scoreboard = new Scoreboard();
        scoreboard.setVisible(false);
        scoreboard.addAL_button_menu(AL_scoreboard_mainmenu);
        scoreboard.addAL_button_exit(AL_scoreboard_exit);

        panel_LHS.addAL_button_local(AL_local);
        panel_LHS.addAL_button_exit(AL_exit);

        panel_RHS.addAL_button_lightmode(AL_lightmode);
        panel_RHS.addAL_button_darkmode(AL_darkmode);
        panel_RHS.addAL_button_blue(AL_blue);
        panel_RHS.addAL_button_green(AL_green);
        panel_RHS.setPanelImage(IMAGE_PATH_DARKMODE);
    }

    @Override
    public void addComponents() {
        this.add(label_title, BorderLayout.NORTH);
        this.add(panel_LHS, BorderLayout.WEST);
        this.add(panel_RHS, BorderLayout.EAST);
    }

    @Override
    public void reColor() {
        this.getContentPane().setBackground(COLOR_BACKGROUND);

        label_title.setBackground(MainMenu.COLOR_BACKGROUND);
        label_title.setForeground(MainMenu.COLOR_LABEL);

        panel_LHS.reColor();
        panel_RHS.reColor();
        scoreboard.reColor();
    }
}
