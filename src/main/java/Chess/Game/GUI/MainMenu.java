package Chess.Game.GUI;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class MainMenu extends JFrame implements IChessFrame {

    /**
     * Sizes, used in the MainMenu
     **/
    private static final int SIZE_WIDTH = 1000;
    private static final int SIZE_LENGTH = 700;
    private static final int SIZE_BUTTON_MAINMENU = 30;

    /**
     * Margins of the Components in the Main Menu
     **/
    private static final int MARGIN_TITLE_MAINMENU[] = {50, 0, 0, 0};
    private static final int MARGIN_BUTTONS_MAINMENU[] = {20, 70, 10, 10};
    private static final int MARGIN_PANEL_COLORMODES[] = {0, 0, 135, 50};
    private static final int MARGIN_LABEL_IMAGE[] = {95, 10, 10, 60};

    private static final String IMAGE_PATH = "src/main/resources/Images/MainMenuImage_Green.png";

    /**
     * Strings, needed for the GUI
     **/
    private static final String STRING_TITLE = "Avdiji's Chess";
    private static final String STRING_BUTTON_LOCAL = "Multiplayer Local";
    private static final String STRING_BUTTON_ONLINE = "Multiplayer Online";
    private static final String STRING_BUTTON_AI = "Player vs AI";
    private static final String STRING_BUTTON_EXIT = "Exit";
    private static final String STRING_BUTTON_LIGHTMODE = "Lightmode";
    private static final String STRING_BUTTON_DARKMODE = "Darkmode";
    private static final String STRING_BUTTON_RED = "Red";
    private static final String STRING_BUTTON_GREEN = "Green";

    /**
     * GridBagConstraints for the Layout
     **/
    private GridBagConstraints gbc;

    /**
     * JLabel, containing the title
     **/
    private JLabel label_title;

    /**
     * Panel containing following components: <br>
     * * {@link #button_local}<br>
     * * {@link #button_online}<br>
     * * {@link #button_ai}<br>
     * * {@link #button_exit}
     **/
    private JPanel panel_LHS;
    private JButton button_local;
    private JButton button_online;
    private JButton button_ai;
    private JButton button_exit;

    /**
     * Panel containing following components:<br>
     * * {@link #label_image}<br>
     * * {@link #panel_colorModes}<br>
     * * {@link #button_lightmode}<br>
     * * {@link #button_darkmode}<br>
     * * {@link #button_red}<br>
     * * {@link #button_green}<br>
     * * {@link #panel_RHS}
     **/
    private JPanel panel_RHS;
    private JLabel label_image;
    private JPanel panel_colorModes;
    private JButton button_lightmode;
    private JButton button_darkmode;
    private JButton button_red;
    private JButton button_green;

    public MainMenu() {
        gbc = new GridBagConstraints();

        initComponents();
        initMainFrame();
        addComponents();

        this.setUndecorated(true);
        this.setVisible(true);
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

    /**
     * Method initializes all the components of {@link #panel_LHS}
     */
    private void initButtons_LHS() {
        button_local = new JButton(STRING_BUTTON_LOCAL);
        button_online = new JButton(STRING_BUTTON_ONLINE);
        button_ai = new JButton(STRING_BUTTON_AI);
        button_exit = new JButton(STRING_BUTTON_EXIT);

        button_local.setFont(new Font(FONT, FONT_TYPE, SIZE_BUTTON_MAINMENU));
        button_online.setFont(new Font(FONT, FONT_TYPE, SIZE_BUTTON_MAINMENU));
        button_ai.setFont(new Font(FONT, FONT_TYPE, SIZE_BUTTON_MAINMENU));
        button_exit.setFont(new Font(FONT, FONT_TYPE, SIZE_BUTTON_MAINMENU));

        button_local.setForeground(COLOR_LABEL);
        button_online.setForeground(COLOR_LABEL);
        button_ai.setForeground(COLOR_LABEL);
        button_exit.setForeground(COLOR_LABEL);

        button_local.setBackground(COLOR_BUTTON_BACKGROUND);
        button_online.setBackground(COLOR_BUTTON_BACKGROUND);
        button_ai.setBackground(COLOR_BUTTON_BACKGROUND);
        button_exit.setBackground(COLOR_BUTTON_BACKGROUND);
    }

    /**
     * Method initializes following variables:<br>
     * {@link #panel_LHS}<br>
     * {@link #button_local}<br>
     * {@link #button_online}<br>
     * {@link #button_ai}<br>
     * {@link #button_exit}
     */
    private void initPanel_LHS() {
        panel_LHS = new JPanel();
        panel_LHS.setLayout(new GridBagLayout());
        panel_LHS.setBackground(COLOR_BACKGROUND);
        initButtons_LHS();

        gbc.insets = new Insets(MARGIN_BUTTONS_MAINMENU[0], MARGIN_BUTTONS_MAINMENU[1], MARGIN_BUTTONS_MAINMENU[2], MARGIN_BUTTONS_MAINMENU[3]);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel_LHS.add(button_local, gbc);

        gbc.gridy = 1;
        panel_LHS.add(button_online, gbc);

        gbc.gridy = 2;
        panel_LHS.add(button_ai, gbc);

        gbc.insets = new Insets(MARGIN_BUTTONS_MAINMENU[0] * 3, MARGIN_BUTTONS_MAINMENU[1], MARGIN_BUTTONS_MAINMENU[2], MARGIN_BUTTONS_MAINMENU[3]);
        gbc.gridy = 3;
        panel_LHS.add(button_exit, gbc);

        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.fill = 0;
    }

    /**
     * Method initializes all the Components of {@link #panel_colorModes}
     */
    private void initButtons_RHS() {
        button_lightmode = new JButton(STRING_BUTTON_LIGHTMODE);
        button_darkmode = new JButton(STRING_BUTTON_DARKMODE);
        button_red = new JButton(STRING_BUTTON_RED);
        button_green = new JButton(STRING_BUTTON_GREEN);

        button_lightmode.setFont(new Font(FONT, FONT_TYPE, SIZE_BUTTON_LABEL));
        button_darkmode.setFont(new Font(FONT, FONT_TYPE, SIZE_BUTTON_LABEL));
        button_red.setFont(new Font(FONT, FONT_TYPE, SIZE_BUTTON_LABEL));
        button_green.setFont(new Font(FONT, FONT_TYPE, SIZE_BUTTON_LABEL));

        button_lightmode.setForeground(COLOR_LABEL);
        button_darkmode.setForeground(COLOR_LABEL);
        button_red.setForeground(COLOR_LABEL);
        button_green.setForeground(COLOR_LABEL);

        button_lightmode.setBackground(COLOR_BUTTON_BACKGROUND);
        button_darkmode.setBackground(COLOR_BUTTON_BACKGROUND);
        button_red.setBackground(COLOR_BUTTON_BACKGROUND);
        button_green.setBackground(COLOR_BUTTON_BACKGROUND);
    }

    /**
     * Method initializes following variables:<br>
     * {@link #panel_RHS}<br>
     * {@link #label_image}<br>
     * {@link #panel_colorModes}<br>
     * {@link #button_lightmode}<br>
     * {@link #button_darkmode}<br>
     * {@link #button_red}<br>
     * {@link #button_green}<br>
     * {@link #panel_RHS}<br>
     */
    private void initPanel_RHS() {
        panel_RHS = new JPanel();
        panel_RHS.setLayout(new BorderLayout());
        panel_RHS.setBackground(COLOR_BACKGROUND);
        label_image = new JLabel(new ImageIcon(IMAGE_PATH));
        label_image.setBorder(new EmptyBorder(MARGIN_LABEL_IMAGE[0], MARGIN_LABEL_IMAGE[1], MARGIN_LABEL_IMAGE[2], MARGIN_LABEL_IMAGE[3]));

        panel_colorModes = new JPanel();
        panel_colorModes.setLayout(new GridBagLayout());
        panel_colorModes.setBackground(COLOR_BACKGROUND);
        panel_colorModes.setBorder(new EmptyBorder(MARGIN_PANEL_COLORMODES[0], MARGIN_PANEL_COLORMODES[1], MARGIN_PANEL_COLORMODES[2], MARGIN_PANEL_COLORMODES[3]));

        initButtons_RHS();
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel_colorModes.add(button_lightmode, gbc);
        gbc.gridx = 1;
        panel_colorModes.add(button_darkmode, gbc);
        gbc.gridx = 2;
        panel_colorModes.add(button_red, gbc);
        gbc.gridx = 3;
        panel_colorModes.add(button_green, gbc);

        panel_RHS.add(label_image, BorderLayout.CENTER);
        panel_RHS.add(panel_colorModes, BorderLayout.SOUTH);
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
        initPanel_LHS();
        initPanel_RHS();
    }

    @Override
    public void addComponents() {
        this.add(label_title, BorderLayout.NORTH);
        this.add(panel_LHS, BorderLayout.WEST);
        this.add(panel_RHS, BorderLayout.EAST);
    }
}
