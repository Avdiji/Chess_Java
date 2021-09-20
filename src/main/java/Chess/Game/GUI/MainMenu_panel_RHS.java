package Chess.Game.GUI;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Fitor Avdiji
 * <p>
 * Panel in the Main Menu, that contains the Image and the Colortheme Buttons (right side of the Main Menu)
 */
public class MainMenu_panel_RHS extends JPanel implements IChessFrame {

    /**
     * Path to the Image of the Main Menu
     **/
    private static final String IMAGE_PATH_DARKMODE = "src/main/resources/Images/MainMenuImage_Darkmode.png";
    private static final String IMAGE_PATH_LIGHTMODE = "src/main/resources/Images/MainMenuImage_Lightmode.png";
    private static final String IMAGE_PATH_BLUE = "src/main/resources/Images/MainMenuImage_Blue.png";
    private static final String IMAGE_PATH_GREEN = "src/main/resources/Images/MainMenuImage_Green.png";

    /**
     * Margins of the components of this panel
     **/
    private static final int MARGIN_PANEL_COLORMODES[] = {0, 0, 135, 50};
    private static final int MARGIN_LABEL_IMAGE[] = {135, 10, 10, 60};


    /**
     * Text of the corresponding buttons
     **/
    private static final String STRING_BUTTON_LIGHTMODE = "Lightmode";
    private static final String STRING_BUTTON_DARKMODE = "Darkmode";
    private static final String STRING_BUTTON_blue = "Blue";
    private static final String STRING_BUTTON_GREEN = "Green";

    /**
     * Components of this class
     **/
    private JLabel label_image;
    private JPanel panel_colorModes;
    private JButton button_lightmode;
    private JButton button_darkmode;
    private JButton button_blue;
    private JButton button_green;

    /**
     * MainMenu of this panel
     **/
    private final MainMenu mainMenu;

    /**
     * GridBagConstraints for the Layout of this Panel
     **/
    private GridBagConstraints gbc;

    /**
     * Constructor
     **/
    public MainMenu_panel_RHS(final MainMenu mm) {
        initMainFrame();
        initComponents();
        addComponents();

        this.mainMenu = mm;
    }

    /**
     * Method initializes {@link #button_lightmode}
     */
    private void initButton_lightmode() {
        button_lightmode = new JButton(STRING_BUTTON_LIGHTMODE);
        button_lightmode.setFont(new Font(FONT, FONT_TYPE, SIZE_BUTTON_LABEL));
        button_lightmode.setForeground(COLOR_LABEL_LIGHTMODE);
        button_lightmode.setBackground(COLOR_BUTTON_BACKGROUND_LIGHTMODE);

        button_lightmode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenu.COLOR_BACKGROUND = COLOR_BACKGROUND_LIGHTMODE;
                MainMenu.COLOR_LABEL = COLOR_LABEL_LIGHTMODE;
                MainMenu.COLOR_BUTTON_BACKGROUND = COLOR_BUTTON_BACKGROUND_LIGHTMODE;
                MainMenu.COLOR_FIELD_WHITE = COLOR_FIELD_WHITE_LIGHTMODE;
                MainMenu.COLOR_FIELD_BLACK = COLOR_FIELD_BLACK_LIGHTMODE;
                MainMenu.COLOR_FIELD_MARKED = COLOR_FIELD_MARKED_LIGHTMODE;
                label_image.setIcon(new ImageIcon(IMAGE_PATH_LIGHTMODE));
                mainMenu.reColor();
            }
        });
    }

    /**
     * Method initializes {@link #button_darkmode}
     */
    private void initButton_darkmode() {
        button_darkmode = new JButton(STRING_BUTTON_DARKMODE);
        button_darkmode.setFont(new Font(FONT, FONT_TYPE, SIZE_BUTTON_LABEL));
        button_darkmode.setForeground(COLOR_LABEL_DARKMODE);
        button_darkmode.setBackground(COLOR_BUTTON_BACKGROUND_DARKMODE);

        button_darkmode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenu.COLOR_BACKGROUND = COLOR_BACKGROUND_DARKMODE;
                MainMenu.COLOR_LABEL = COLOR_LABEL_DARKMODE;
                MainMenu.COLOR_BUTTON_BACKGROUND = COLOR_BUTTON_BACKGROUND_DARKMODE;
                MainMenu.COLOR_FIELD_WHITE = COLOR_FIELD_WHITE_DARKMODE;
                MainMenu.COLOR_FIELD_BLACK = COLOR_FIELD_BLACK_DARKMODE;
                MainMenu.COLOR_FIELD_MARKED = COLOR_FIELD_MARKED_DARKMODE;
                label_image.setIcon(new ImageIcon(IMAGE_PATH_DARKMODE));
                mainMenu.reColor();
            }
        });
    }

    /**
     * Method initializes {@link #button_blue}
     */
    private void initButton_blue() {
        button_blue = new JButton(STRING_BUTTON_blue);
        button_blue.setFont(new Font(FONT, FONT_TYPE, SIZE_BUTTON_LABEL));
        button_blue.setBackground(COLOR_BUTTON_BACKGROUND_BLUE);

        button_blue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenu.COLOR_BACKGROUND = COLOR_BACKGROUND_BLUE;
                MainMenu.COLOR_LABEL = COLOR_LABEL_BLUE;
                MainMenu.COLOR_BUTTON_BACKGROUND = COLOR_BUTTON_BACKGROUND_BLUE;
                MainMenu.COLOR_FIELD_WHITE = COLOR_FIELD_WHITE_BLUE;
                MainMenu.COLOR_FIELD_BLACK = COLOR_FIELD_BLACK_BLUE;
                MainMenu.COLOR_FIELD_MARKED = COLOR_FIELD_MARKED_BLUE;
                label_image.setIcon(new ImageIcon(IMAGE_PATH_BLUE));
                mainMenu.reColor();
            }
        });
    }

    /**
     * Method initializes {@link #button_green}
     */
    private void initButton_green() {
        button_green = new JButton(STRING_BUTTON_GREEN);
        button_green.setFont(new Font(FONT, FONT_TYPE, SIZE_BUTTON_LABEL));
        button_green.setForeground(COLOR_LABEL_GREEN);
        button_green.setBackground(COLOR_BUTTON_BACKGROUND_GREEN);

        button_green.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenu.COLOR_BACKGROUND = COLOR_BACKGROUND_GREEN;
                MainMenu.COLOR_LABEL = COLOR_LABEL_GREEN;
                MainMenu.COLOR_BUTTON_BACKGROUND = COLOR_BUTTON_BACKGROUND_GREEN;
                MainMenu.COLOR_FIELD_WHITE = COLOR_FIELD_WHITE_GREEN;
                MainMenu.COLOR_FIELD_BLACK = COLOR_FIELD_BLACK_GREEN;
                MainMenu.COLOR_FIELD_MARKED = COLOR_FIELD_MARKED_GREEN;
                label_image.setIcon(new ImageIcon(IMAGE_PATH_GREEN));
                mainMenu.reColor();
            }
        });
    }

    @Override
    public void initMainFrame() {
        this.setLayout(new GridLayout(2, 1));
        this.setBackground(MainMenu.COLOR_BACKGROUND);
    }

    @Override
    public void initComponents() {
        label_image = new JLabel();
        label_image.setBorder(new EmptyBorder(MARGIN_LABEL_IMAGE[0], MARGIN_LABEL_IMAGE[1], MARGIN_LABEL_IMAGE[2], MARGIN_LABEL_IMAGE[3]));
        label_image.setIcon(new ImageIcon(IMAGE_PATH_DARKMODE));

        panel_colorModes = new JPanel();
        panel_colorModes.setLayout(new GridBagLayout());
        panel_colorModes.setBackground(MainMenu.COLOR_BACKGROUND);
        panel_colorModes.setBorder(new EmptyBorder(MARGIN_PANEL_COLORMODES[0], MARGIN_PANEL_COLORMODES[1], MARGIN_PANEL_COLORMODES[2], MARGIN_PANEL_COLORMODES[3]));

        initButton_lightmode();
        initButton_darkmode();
        initButton_blue();
        initButton_green();
    }

    @Override
    public void addComponents() {
        gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel_colorModes.add(button_lightmode, gbc);
        gbc.gridx = 1;
        panel_colorModes.add(button_darkmode, gbc);
        gbc.gridx = 2;
        panel_colorModes.add(button_blue, gbc);
        gbc.gridx = 3;
        panel_colorModes.add(button_green, gbc);

        this.add(label_image);
        this.add(panel_colorModes);
    }

    @Override
    public void reColor() {
        this.setBackground(MainMenu.COLOR_BACKGROUND);
        panel_colorModes.setBackground(MainMenu.COLOR_BACKGROUND);
    }
}
