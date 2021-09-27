package Chess.Game.GUI;

import Chess.Game.GUI.ChessboardGUI.GameWindow;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
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

    /**
     * GridBagConstraints for the Design
     **/
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

    /**
     * MainMenu of this Scoreboard
     **/
    private final MainMenu mainMenu;

    /**
     * Constructor
     **/
    public Scoreboard(final MainMenu mm) {
        mainMenu = mm;

        initComponents();
        initMainFrame();
        addComponents();
        this.setUndecorated(true);
    }

    /** Method makes the Scoreboard visible and plays a sound **/
    public void setScoreboardVisible(){
        this.setVisible(true);
        GameWindow.playSound(GameWindow.SOUND_END);
    }

    /**
     * Getter for the Text of {@link #label_message}
     *
     * @return label_message.getText
     */
    public String getWinner() {
        return label_message.getText();
    }

    /**
     * Setter for {@link #message}
     *
     * @param message newMessage of the scoreboard
     */
    public void setMessage(final String message) {
        label_message.setText(message);
    }

    /**
     * Method initializes {@link #button_menu}
     **/
    private void initButton_menu() {
        button_menu = new JButton(TEXT_MENU);
        button_menu.setBackground(MainMenu.COLOR_BUTTON_BACKGROUND);
        button_menu.setFont(new Font(FONT, FONT_TYPE, SIZE_BUTTON_LABEL));
        button_menu.setForeground(MainMenu.COLOR_LABEL);

        button_menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                mainMenu.setVisible(true);
            }
        });
    }

    /**
     * Method initializes {@link #button_exit}
     */
    private void initButton_exit() {
        button_exit = new JButton(TEXT_EXIT);
        button_exit.setBackground(MainMenu.COLOR_BUTTON_BACKGROUND);
        button_exit.setFont(new Font(FONT, FONT_TYPE, SIZE_BUTTON_LABEL));
        button_exit.setForeground(MainMenu.COLOR_LABEL);

        button_exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                mainMenu.setVisible(false);
                mainMenu.dispose();
            }
        });
    }

    @Override
    public void initComponents() {
        gbc = new GridBagConstraints();

        label_message = new JLabel();
        label_message.setFont(new Font(FONT, FONT_TYPE, SIZE_FONT));
        label_message.setForeground(MainMenu.COLOR_LABEL);
        label_message.setHorizontalAlignment(JLabel.CENTER);

        initButton_menu();
        initButton_exit();
    }

    @Override
    public void addComponents() {
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(MARGIN_MESSAGE[0], MARGIN_MESSAGE[1], MARGIN_MESSAGE[2], MARGIN_MESSAGE[3]);
        this.add(label_message, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(MARGIN_BUTTONS[0], MARGIN_BUTTONS[1], MARGIN_BUTTONS[2], MARGIN_BUTTONS[3]);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(button_menu, gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets(MARGIN_BUTTONS[0], MARGIN_BUTTONS[1], MARGIN_BUTTONS[2], MARGIN_BUTTONS[3]);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(button_exit, gbc);
    }

    @Override
    public void initMainFrame() {
        this.setSize(SIZE_WIDTH, SIZE_LENGTH);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setBackground(MainMenu.COLOR_BACKGROUND);
    }

    @Override
    public void reColor() {
        this.getContentPane().setBackground(MainMenu.COLOR_BACKGROUND);
        label_message.setForeground(MainMenu.COLOR_LABEL);

        button_menu.setBackground(MainMenu.COLOR_BUTTON_BACKGROUND);
        button_exit.setBackground(MainMenu.COLOR_BUTTON_BACKGROUND);

        button_menu.setForeground(MainMenu.COLOR_LABEL);
        button_exit.setForeground(MainMenu.COLOR_LABEL);
    }
}
