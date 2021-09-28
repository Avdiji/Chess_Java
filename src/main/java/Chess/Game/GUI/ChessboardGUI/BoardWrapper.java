package Chess.Game.GUI.ChessboardGUI;

import Chess.Game.GUI.IChessFrame;
import Chess.Game.GUI.MainMenu;
import Chess.Game.Logic.ChessField;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

/**
 * @author Fitor Avdiji
 * <p>
 * Class implements the entire chessBoard of the Game (with player indicator etc...)
 */
public class BoardWrapper extends JPanel implements IChessFrame {

    /** Margin of the Board Wrapper **/
    private static final int MARGIN_BOARD[] = {50, 160, 100, 160};

    /** Panels to indicate, which players turn it is **/
    private JPanel indicator_black;
    private JPanel indicator_white;

    /** Wrapper Panel, which contains the panel with the chessfield **/
    private JPanel panel_chessBoard;

    /** Panel, which containss the chessfield **/
    private JPanel panel_chessPieces;

    /** ChessField with all the Buttons **/
    private ChessField chessField;

    /**
     * Constructor
     *
     * @param chessField chessfield with all the pieces
     */
    public BoardWrapper(final ChessField chessField) {
        this.chessField = chessField;
        initMainFrame();
        initComponents();
        addComponents();
    }

    /**
     * Setter for {@link #indicator_white}
     *
     * @param color new Color for the white indicator
     */
    public void setIndicator_white(final Color color) {
        indicator_white.setBackground(color);
    }

    /**
     * Setter for {@link #indicator_black}
     *
     * @param color new Color for the white indicator
     */
    public void setIndicator_black(final Color color) {
        indicator_black.setBackground(color);
    }

    @Override
    public void initMainFrame() {
        this.setLayout(new BorderLayout());
        this.setBackground(MainMenu.COLOR_BACKGROUND);
        this.setBorder(new EmptyBorder(MARGIN_BOARD[0], MARGIN_BOARD[1], MARGIN_BOARD[2], MARGIN_BOARD[3]));
    }

    @Override
    public void initComponents() {
        indicator_white = new JPanel();
        indicator_black = new JPanel();
        indicator_white.setBackground(MainMenu.COLOR_FIELD_MARKED);
        indicator_black.setBackground(MainMenu.COLOR_BACKGROUND);

        panel_chessBoard = new JPanel();
        panel_chessBoard.setBackground(MainMenu.COLOR_BACKGROUND);
        panel_chessPieces = new JPanel();
        panel_chessPieces.setLayout(new GridLayout(8, 8));
        chessField.getField().forEach(piece -> panel_chessPieces.add(piece));
        panel_chessBoard.add(panel_chessPieces);
    }

    @Override
    public void addComponents() {
        this.add(panel_chessBoard, BorderLayout.CENTER);
        this.add(indicator_white, BorderLayout.SOUTH);
        this.add(indicator_black, BorderLayout.NORTH);
    }

    @Override
    public void reColor() {}
}
