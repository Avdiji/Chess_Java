package Chess.Game.GUI.ClientGUI;

import Chess.Game.GUI.IChessFrame;
import Chess.Game.GUI.MainMenu;

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
 * Submit Window (its either used as a loading screen, or as an error screen)
 **/

public class SubmitScreen extends JFrame implements IChessFrame {

    /** WIDTH and HEIGHT of this screen **/
    private final int SIZE_WIDTH = 300;
    private final int SIZE_HEIGHT = 200;

    /** Margin of the Components of this class **/
    private final int MARGIN_COMPONENTS[] = {10,10,10,10};

    /** Components of this Frame **/
    private JLabel label_message;
    private JButton button;

    /** GridBagConstraints for the Layout of this Frame **/
    private GridBagConstraints gbc;

    /** Login screen of this SubmitScreen **/
    private final ServerLogin sl;

    /**
     * Constructor
     */
    public SubmitScreen(final ServerLogin sl) {
        this.sl = sl;
        initComponents();
        initMainFrame();
        addComponents();

        this.setVisible(true);
    }

    /**
     * Setter for the text of {@link #label_message}
     * @param string string displayed in label
     */
    public void setString_label(final String string){
        label_message.setText(string);
    }

    /**
     * Setter for the text of {@link #button}
     * @param string string displayed in the button
     */
    public void setString_button(final String string){
        button.setText(string);
    }

    /**
     * Method initializes {@link #button}
     */
    private void initButton(){
        button = new JButton();
        button.setFont(new Font(FONT, FONT_TYPE, SIZE_BUTTON_LABEL));
        button.setForeground(MainMenu.COLOR_LABEL);
        button.setBackground(MainMenu.COLOR_BUTTON_BACKGROUND);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                sl.setVisible(true);
            }
        });
    }

    /**
     * Method removes the Button from the screen
     */
    public void removeButton(){
        this.remove(button);
    }

    @Override
    public void initMainFrame() {
        this.setSize(SIZE_WIDTH, SIZE_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setBackground(MainMenu.COLOR_BACKGROUND);
        this.setUndecorated(true);
    }

    @Override
    public void initComponents() {
        label_message = new JLabel();
        label_message.setFont(new Font(FONT, FONT_TYPE, SIZE_BUTTON_LABEL));
        label_message.setForeground(MainMenu.COLOR_LABEL);
        label_message.setBackground(MainMenu.COLOR_BACKGROUND);
        label_message.setHorizontalAlignment(JLabel.HORIZONTAL);

        initButton();
    }

    @Override
    public void addComponents() {
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(MARGIN_COMPONENTS[0], MARGIN_COMPONENTS[1], MARGIN_COMPONENTS[2], MARGIN_COMPONENTS[3]);

        this.add(label_message, gbc);

        gbc.gridy = 1;
        this.add(button, gbc);

    }

    @Override
    public void reColor() {
        this.getContentPane().setBackground(MainMenu.COLOR_BACKGROUND);
        label_message.setForeground(MainMenu.COLOR_LABEL);
        label_message.setBackground(MainMenu.COLOR_BACKGROUND);
        button.setForeground(MainMenu.COLOR_LABEL);
        button.setBackground(MainMenu.COLOR_BUTTON_BACKGROUND);
    }
}
