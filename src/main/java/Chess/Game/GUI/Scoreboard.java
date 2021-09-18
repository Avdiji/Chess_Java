package Chess.Game.GUI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

/**
 * @author Fitor Avdiji
 * <p>
 * GUI of the scoreboard to show the players, when the game ends
 */
public class Scoreboard extends JFrame implements IChessFrame {

    /**
     * Width and Length of the scoreboard
     **/
    private static final int SIZE_WIDTH = 400;
    private static final int SIZE_LENGTH = 450;

    /**
     * Margin of the Components in this Frame
     **/
    private static final int MARGIN_BUTTONS[] = {10, 40, 10, 40};
    private static final int MARGIN_MESSAGE[] = {0, 50, 50, 50};

    /**
     * Font Size of the Buttons and the Message
     **/
    private static final int SIZE_FONT = 40;

    /**
     * Strings for the Buttons on the Scoreboard
     **/
    private static final String TEXT_MENU = "Back to Menu";
    private static final String TEXT_EXIT = "Exit Game";

    /** GridBagConstraints for the Design **/
    private GridBagConstraints gbc;

    /**
     * JLabel containing the message of the scoreboard
     **/
    private JLabel label_message;

    /**
     * Buttons to return to the Menu, or to exit the game
     **/
    private JButton button_menu;
    private JButton button_exit;

    public Scoreboard() {

        initComponents();
        initMainFrame();
        addComponents();

        this.setUndecorated(true);
        this.setVisible(true);
    }

    /**
     * Setter for {@link #message}
     * @param message newMessage of the scoreboard
     */
    public void setMessage(final String message){
        label_message.setText(message);
    }

    /**
     * Method sets the Actionslistener for {@link #button_menu}
     * @param al ActionListener for button_menu
     */
    protected void addAL_button_menu(final ActionListener al){
        button_menu.addActionListener(al);
    }

    /**
     * Meethod sets the Actionslistener for {@link #button_exit}
     * @param al Actionlistener for button_exit
     */
    protected void addAL_button_exit(final ActionListener al){
        button_exit.addActionListener(al);
    }

    @Override
    public void initComponents() {
        gbc = new GridBagConstraints();

        label_message = new JLabel();
        label_message.setFont(new Font(FONT, FONT_TYPE, SIZE_FONT));
        label_message.setForeground(COLOR_LABEL);
        label_message.setHorizontalAlignment(JLabel.CENTER);

        button_menu = new JButton(TEXT_MENU);
        button_exit = new JButton(TEXT_EXIT);
        button_menu.setBackground(COLOR_BUTTON_BACKGROUND);
        button_exit.setBackground(COLOR_BUTTON_BACKGROUND);
        button_menu.setFont(new Font(FONT, FONT_TYPE, SIZE_BUTTON_LABEL));
        button_exit.setFont(new Font(FONT, FONT_TYPE, SIZE_BUTTON_LABEL));
        button_menu.setForeground(COLOR_LABEL);
        button_exit.setForeground(COLOR_LABEL);
    }

    @Override
    public void addComponents() {
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(MARGIN_MESSAGE[0],MARGIN_MESSAGE[1],MARGIN_MESSAGE[2],MARGIN_MESSAGE[3]);
        this.add(label_message, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(MARGIN_BUTTONS[0],MARGIN_BUTTONS[1],MARGIN_BUTTONS[2],MARGIN_BUTTONS[3]);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(button_menu, gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets(MARGIN_BUTTONS[0],MARGIN_BUTTONS[1],MARGIN_BUTTONS[2],MARGIN_BUTTONS[3]);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(button_exit, gbc);
    }

    @Override
    public void initMainFrame() {
        this.setSize(SIZE_WIDTH, SIZE_LENGTH);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setBackground(COLOR_BACKGROUND);
    }
}
