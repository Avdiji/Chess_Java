package Chess.Game.GUI;

import java.awt.Color;
import java.awt.Font;

/**
 * @author Fitor Avdiji
 * <p>
 * Interface for all the windows of the GUI
 **/
public interface IChessFrame {

    /** Options for the Font **/
    public static final String FONT = "Arial Black";
    public static final int FONT_TYPE = Font.BOLD;
    public static final int SIZE_BUTTON_LABEL = 15;
    public static final int SIZE_TITLE = 50;

    /** Darkmode Colors **/
    public static final Color COLOR_BACKGROUND_DARKMODE = new Color(0x444444);
    public static final Color COLOR_LABEL_DARKMODE = new Color(0xFFFFFF);
    public static final Color COLOR_BUTTON_BACKGROUND_DARKMODE = new Color(0x828282);
    public static final Color COLOR_FIELD_WHITE_DARKMODE = new Color(0xD2D2D2);
    public static final Color COLOR_FIELD_BLACK_DARKMODE = new Color(0x757575);
    public static final Color COLOR_FIELD_MARKED_DARKMODE = new Color(0x000000);

    /** Lightmode Colors **/
    public static final Color COLOR_BACKGROUND_LIGHTMODE = new Color(0xF1F1F1);
    public static final Color COLOR_LABEL_LIGHTMODE = new Color(0x2d2d2d);
    public static final Color COLOR_BUTTON_BACKGROUND_LIGHTMODE = new Color(0xBDBDBD);
    public static final Color COLOR_FIELD_WHITE_LIGHTMODE = new Color(0xE4FFE4);
    public static final Color COLOR_FIELD_BLACK_LIGHTMODE = new Color(0x58E3AC);
    public static final Color COLOR_FIELD_MARKED_LIGHTMODE = new Color(0x8CEF39);

    /** Blue Colors **/
    public static final Color COLOR_BACKGROUND_BLUE = new Color(0x36648b);
    public static final Color COLOR_LABEL_BLUE = new Color(0xe8ffff);
    public static final Color COLOR_BUTTON_BACKGROUND_BLUE = new Color(0x4eadaf);
    public static final Color COLOR_FIELD_WHITE_BLUE = new Color(0x88deb0);
    public static final Color COLOR_FIELD_BLACK_BLUE = new Color(0x4eadaf);
    public static final Color COLOR_FIELD_MARKED_BLUE = new Color(0x0101B4);

    /** Green Colors **/
    public static final Color COLOR_BACKGROUND_GREEN = new Color(0x3f6f21);
    public static final Color COLOR_LABEL_GREEN = new Color(0xd6ecd2);
    public static final Color COLOR_BUTTON_BACKGROUND_GREEN = new Color(0x61bd4f);
    public static final Color COLOR_FIELD_WHITE_GREEN = new Color(0x8fbc8f);
    public static final Color COLOR_FIELD_BLACK_GREEN = new Color(0x1E7B56);
    public static final Color COLOR_FIELD_MARKED_GREEN = new Color(0xe0e8d7);

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

    /** Method recolors all the Components of the frame and the frame itself **/
    void reColor();
}
