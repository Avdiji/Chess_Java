package Chess.Game.GUI;

import Chess.Game.GUI.ChessboardGUI.GameWindow;
import Chess.Game.Logic.ChessField;
import Chess.Game.Logic.Player.EPlayerColor;
import Chess.Game.Logic.Player.Player;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
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

    /** Scoreboard **/
    private Scoreboard scoreboard;

    /** ActionsListeners for the Components of {@link #panel_LHS} **/
    private ActionListener AL_local;
    private ActionListener AL_online;
    private ActionListener AL_ai;
    private ActionListener AL_exit;

    /** ActionsListeners for the scoreboard **/
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
    // TODO outsource actionlisteners to new classes
    /** Method initializes {@link #AL_local} **/
    private void initAL_local(){
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

    /** Method initializes {@link #AL_scoreboard_mainmenu} **/
    private void initAL_scoreboard_mainmenu(){
        AL_scoreboard_mainmenu = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(true);
                scoreboard.setVisible(false);
            }
        };
    }

    /** Method initializes {@link #AL_scoreboard_exit} **/
    private void initAL_scoreboard_exit(){
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

    /**
     * Method initializes {@link #label_title}
     */
    private void initTitle() {
        label_title = new JLabel(STRING_TITLE);
        label_title.setBackground(COLOR_BACKGROUND);
        label_title.setForeground(COLOR_LABEL);
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
        initAL_local();

        initAL_scoreboard_mainmenu();
        initAL_scoreboard_exit();

        panel_LHS = new MainMenu_Panel_LHS();
        panel_RHS = new MainMenu_panel_RHS();

        scoreboard = new Scoreboard();
        scoreboard.setVisible(false);
        scoreboard.addAL_button_menu(AL_scoreboard_mainmenu);
        scoreboard.addAL_button_exit(AL_scoreboard_exit);

        panel_LHS.addAL_button_local(AL_local);
    }

    @Override
    public void addComponents() {
        this.add(label_title, BorderLayout.NORTH);
        this.add(panel_LHS, BorderLayout.WEST);
        this.add(panel_RHS, BorderLayout.EAST);
    }
}
