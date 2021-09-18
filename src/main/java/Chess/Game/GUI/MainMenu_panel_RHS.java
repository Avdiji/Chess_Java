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

/**
 * @author Fitor Avdiji
 * <p>
 * Panel in the Main Menu, that contains the Image and the Colortheme Buttons (right side of the Main Menu)
 */
public class MainMenu_panel_RHS extends JPanel implements IChessFrame {

    /** Margins of the components of this panel **/
    private static final int MARGIN_PANEL_COLORMODES[] = {0, 0, 135, 50};
    private static final int MARGIN_LABEL_IMAGE[] = {135, 10, 10, 60};

    /**
     * Path to the Image of the Main Menu
     **/
    private static final String IMAGE_PATH = "src/main/resources/Images/MainMenuImage_Green.png";

    /** Text of the corresponding buttons **/
    private static final String STRING_BUTTON_LIGHTMODE = "Lightmode";
    private static final String STRING_BUTTON_DARKMODE = "Darkmode";
    private static final String STRING_BUTTON_RED = "Red";
    private static final String STRING_BUTTON_GREEN = "Green";

    /** Components of this class **/
    private JLabel label_image;
    private JPanel panel_colorModes;
    private JButton button_lightmode;
    private JButton button_darkmode;
    private JButton button_red;
    private JButton button_green;

    /** GridBagConstraints for the Layout of this Panel **/
    private GridBagConstraints gbc;

    /** Constructor **/
    public MainMenu_panel_RHS(){
        initMainFrame();
        initComponents();
        addComponents();
    }

    @Override
    public void initMainFrame() {
        this.setLayout(new GridLayout(2, 1));
        this.setBackground(COLOR_BACKGROUND);
    }

    @Override
    public void initComponents() {
        label_image = new JLabel(new ImageIcon(IMAGE_PATH));
        label_image.setBorder(new EmptyBorder(MARGIN_LABEL_IMAGE[0], MARGIN_LABEL_IMAGE[1], MARGIN_LABEL_IMAGE[2], MARGIN_LABEL_IMAGE[3]));
        //
        panel_colorModes = new JPanel();
        panel_colorModes.setLayout(new GridBagLayout());
        panel_colorModes.setBackground(COLOR_BACKGROUND);
        panel_colorModes.setBorder(new EmptyBorder(MARGIN_PANEL_COLORMODES[0], MARGIN_PANEL_COLORMODES[1], MARGIN_PANEL_COLORMODES[2], MARGIN_PANEL_COLORMODES[3]));
        //
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

    @Override
    public void addComponents() {
        gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel_colorModes.add(button_lightmode, gbc);
        gbc.gridx = 1;
        panel_colorModes.add(button_darkmode, gbc);
        gbc.gridx = 2;
        panel_colorModes.add(button_red, gbc);
        gbc.gridx = 3;
        panel_colorModes.add(button_green, gbc);

        this.add(label_image);
        this.add(panel_colorModes);
    }
}
