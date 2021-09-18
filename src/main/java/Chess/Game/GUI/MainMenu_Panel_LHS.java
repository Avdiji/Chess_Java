package Chess.Game.GUI;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
    private static final String STRING_BUTTON_ONLINE = "Multiplayer Online";
    private static final String STRING_BUTTON_AI = "Player vs AI";
    private static final String STRING_BUTTON_EXIT = "Exit";

    /**
     * Components of this Panel
     **/
    private JButton button_local;
    private JButton button_online;
    private JButton button_ai;
    private JButton button_exit;

    /**
     * Combobox with all the different Gamemodes
     **/
    private JComboBox<String> combobox_gamemodes;

    /**
     * GridBagConstraints for the Layout
     **/
    private GridBagConstraints gbc;

    public MainMenu_Panel_LHS() {
        initMainFrame();
        initComponents();
        addComponents();
    }

    /**
     * Getter for {@link #combobox_gamemodes}
     *
     * @return combobox_gamemodes
     */
    protected JComboBox<String> getCombobox_gamemodes() {
        return combobox_gamemodes;
    }

    /**
     * Method adds an ActionListener to {@link #button_local}
     *
     * @param al ActionListener for the Button to start the local game
     */
    public void addAL_button_local(final ActionListener al) {
        button_local.addActionListener(al);
    }

    @Override
    public void initMainFrame() {
        this.setLayout(new GridBagLayout());
        this.setBackground(COLOR_BACKGROUND);
    }

    @Override
    public void initComponents() {
        combobox_gamemodes = new JComboBox<>();
        Arrays.stream(MainMenu.STRING_GAMEMODES).forEach(value -> combobox_gamemodes.addItem(value));
        combobox_gamemodes.setFont(new Font(FONT, FONT_TYPE, MainMenu.SIZE_BUTTON_MAINMENU));
        combobox_gamemodes.setBackground(COLOR_BUTTON_BACKGROUND);
        combobox_gamemodes.setForeground(COLOR_LABEL);

        button_local = new JButton(STRING_BUTTON_LOCAL);
        button_online = new JButton(STRING_BUTTON_ONLINE);
        button_ai = new JButton(STRING_BUTTON_AI);
        button_exit = new JButton(STRING_BUTTON_EXIT);

        button_local.setFont(new Font(FONT, FONT_TYPE, MainMenu.SIZE_BUTTON_MAINMENU));
        button_online.setFont(new Font(FONT, FONT_TYPE, MainMenu.SIZE_BUTTON_MAINMENU));
        button_ai.setFont(new Font(FONT, FONT_TYPE, MainMenu.SIZE_BUTTON_MAINMENU));
        button_exit.setFont(new Font(FONT, FONT_TYPE, MainMenu.SIZE_BUTTON_MAINMENU));

        button_local.setForeground(COLOR_LABEL);
        button_online.setForeground(COLOR_LABEL);
        button_ai.setForeground(COLOR_LABEL);
        button_exit.setForeground(COLOR_LABEL);

        button_local.setBackground(COLOR_BUTTON_BACKGROUND);
        button_online.setBackground(COLOR_BUTTON_BACKGROUND);
        button_ai.setBackground(COLOR_BUTTON_BACKGROUND);
        button_exit.setBackground(COLOR_BUTTON_BACKGROUND);
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
}
