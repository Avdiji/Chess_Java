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
import java.awt.event.ActionListener;

/**
 * @author Fitor Avdiji
 * <p>
 * Panel in the Main Menu, that contains the Image and the Colortheme Buttons (right side of the Main Menu)
 */
public class MainMenu_panel_RHS extends JPanel implements IChessFrame {

    /** Margins of the components of this panel **/
    private static final int MARGIN_PANEL_COLORMODES[] = {0, 0, 135, 50};
    private static final int MARGIN_LABEL_IMAGE[] = {135, 10, 10, 60};


    /** Text of the corresponding buttons **/
    private static final String STRING_BUTTON_LIGHTMODE = "Lightmode";
    private static final String STRING_BUTTON_DARKMODE = "Darkmode";
    private static final String STRING_BUTTON_blue = "Blue";
    private static final String STRING_BUTTON_GREEN = "Green";

    /** Components of this class **/
    private JLabel label_image;
    private JPanel panel_colorModes;
    private JButton button_lightmode;
    private JButton button_darkmode;
    private JButton button_blue;
    private JButton button_green;

    /** GridBagConstraints for the Layout of this Panel **/
    private GridBagConstraints gbc;

    /** Constructor **/
    public MainMenu_panel_RHS(){
        initMainFrame();
        initComponents();
        addComponents();
    }

    /**
     * Method adds ActionListener to {@link #button_lightmode}
     * @param al ActionListener to enable the Lightmode
     */
    public void addAL_button_lightmode(final ActionListener al){
        button_lightmode.addActionListener(al);
    }

    /**
     * Method adds ActionListener to {@link #button_darkmode}
     * @param al ActionListener to enable the Darkmode
     */
    public void addAL_button_darkmode(final ActionListener al){
        button_darkmode.addActionListener(al);
    }

    /**
     * Method adds ActionListener to {@link #button_blue}
     * @param al ActionListener to enable the blue Colormode
     */
    public void addAL_button_blue(final ActionListener al){
        button_blue.addActionListener(al);
    }

    /**
     * Method adds ActionListener to {@link #button_green}
     * @param al ActionListener to enable the green Colormode
     */
    public void addAL_button_green(final ActionListener al){
        button_green.addActionListener(al);
    }

    /** Method sets an Image for {@link #label_image} **/
    public void setPanelImage(final String imagePath){
        label_image.setIcon(new ImageIcon(imagePath));
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
        //
        panel_colorModes = new JPanel();
        panel_colorModes.setLayout(new GridBagLayout());
        panel_colorModes.setBackground(MainMenu.COLOR_BACKGROUND);
        panel_colorModes.setBorder(new EmptyBorder(MARGIN_PANEL_COLORMODES[0], MARGIN_PANEL_COLORMODES[1], MARGIN_PANEL_COLORMODES[2], MARGIN_PANEL_COLORMODES[3]));
        //
        button_lightmode = new JButton(STRING_BUTTON_LIGHTMODE);
        button_darkmode = new JButton(STRING_BUTTON_DARKMODE);
        button_blue = new JButton(STRING_BUTTON_blue);
        button_green = new JButton(STRING_BUTTON_GREEN);

        button_lightmode.setFont(new Font(FONT, FONT_TYPE, SIZE_BUTTON_LABEL));
        button_darkmode.setFont(new Font(FONT, FONT_TYPE, SIZE_BUTTON_LABEL));
        button_blue.setFont(new Font(FONT, FONT_TYPE, SIZE_BUTTON_LABEL));
        button_green.setFont(new Font(FONT, FONT_TYPE, SIZE_BUTTON_LABEL));

        button_lightmode.setForeground(COLOR_LABEL_LIGHTMODE);
        button_darkmode.setForeground(COLOR_LABEL_DARKMODE);
        button_blue.setForeground(COLOR_LABEL_BLUE);
        button_green.setForeground(COLOR_LABEL_GREEN);

        button_lightmode.setBackground(COLOR_BUTTON_BACKGROUND_LIGHTMODE);
        button_darkmode.setBackground(COLOR_BUTTON_BACKGROUND_DARKMODE);
        button_blue.setBackground(COLOR_BUTTON_BACKGROUND_BLUE);
        button_green.setBackground(COLOR_BUTTON_BACKGROUND_GREEN);
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
