package Chess.Game.GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

/**
 * @author Fitor Avdiji
 * <p>
 * Main Menu of the Game
 */
public class MainMenu extends JFrame implements IChessFrame {

    /** Currently Selected Colors **/
    public static Color COLOR_BACKGROUND = COLOR_BACKGROUND_DARKMODE;
    public static Color COLOR_LABEL = COLOR_LABEL_DARKMODE;
    public static Color COLOR_BUTTON_BACKGROUND = COLOR_BUTTON_BACKGROUND_DARKMODE;
    public static Color COLOR_FIELD_WHITE = COLOR_FIELD_WHITE_DARKMODE;
    public static Color COLOR_FIELD_BLACK = COLOR_FIELD_BLACK_DARKMODE;
    public static Color COLOR_FIELD_MARKED = COLOR_FIELD_MARKED_DARKMODE;
    public final static Color COLOR_FIELD_ENDANGERED = new Color(0xec7c26);

    /** Sizes, used in the MainMenu **/
    private static final int SIZE_WIDTH = 1000;
    private static final int SIZE_LENGTH = 700;
    public static final int SIZE_BUTTON_MAINMENU = 30;

    /** Margin of the Title **/
    private static final int MARGIN_TITLE_MAINMENU[] = {50, 0, 0, 0};

    /** Text of the Title **/
    private static final String STRING_TITLE = "Avdiji's Chess";

    /** JLabel, containing the title **/
    private JLabel label_title;

    /** Panels of the Main Menu **/
    private MainMenu_Panel_LHS panel_LHS;
    private MainMenu_panel_RHS panel_RHS;

    /** corresponding scoreboard of this Menu **/
    private Scoreboard scoreboard;

    /** Constructor **/
    public MainMenu() {
        initComponents();
        initMainFrame();
        addComponents();

        this.setUndecorated(true);
        this.setVisible(true);
    }

    /**
     * Getter for {@link #scoreboard}
     *
     * @return scoreboard
     */
    public Scoreboard getScoreboard() {
        return this.scoreboard;
    }

    /** Method initializes {@link #label_title} **/
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
        scoreboard = new Scoreboard(this);
        panel_LHS = new MainMenu_Panel_LHS(this);
        panel_RHS = new MainMenu_panel_RHS(this);
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
