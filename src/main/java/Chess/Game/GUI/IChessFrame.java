package Chess.Game.GUI;

import java.awt.Color;
import java.awt.Font;

/**
 * @author Fitor Avdiji
 * <p>
 * Interface for all the windows of the GUI
 **/
public interface IChessFrame {

    /**
     * Color of the Background of all Windows
     **/
    public static final Color COLOR_BACKGROUND = new Color(0x444444);
    /**
     * Color of all the Labels in the Windows of the GUI
     **/
    public static final Color COLOR_LABEL = new Color(0xFFFFFF);
    /**
     * Color of all the Buttons in the Windows of the GUI
     **/
    public static final Color COLOR_BUTTON_BACKGROUND = new Color(0x828282);

    /** Options for the Font **/
    public static final String FONT = "Arial Black";
    public static final int FONT_TYPE = Font.BOLD;
    public static final int SIZE_BUTTON_LABEL = 15;

    /**
     * Method initializes the Main frame of this Window
     **/
    void initMainFrame();

    /**
     * Method initializes all the Components, that belong into the MainFrame
     **/
    void initComponents();

    /**
     * Method adds all the Components together
     **/
    void addComponents();

}
