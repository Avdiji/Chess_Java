package Chess.Game.GUI;

import Chess.Game.Logic.ChessField;
import Chess.Game.Logic.Pieces.IChessPiece;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

/**
 * @author Fitor Avdiji
 * <p>
 * Class includes all the GUI for the Game Window
 */
public class GameWindow extends JFrame implements IChessFrame {

    /** Sizes used for the GUI in the Game Window **/
    private static final int MARGIN_BORDER_GRAVE[] = {100, 100, 100, 100};
    private static final int MARGIN_BOARD[] = {70, 40, 40, 40};
    private static final int MARGIN_TITLE[] = {30, 30, 0, 30};
    private static final int SIZE_TITLE = 50;
    private static final int SIZE_GRAVE_LABEL = 20;

    /** Strings used for the GUI in the Game Window **/
    private static final String TITLE = "Avdiji's Chess";
    private static final String GRAVE_WHITE_LABEL = "Grave White";
    private static final String GRAVE_BLACK_LABEL = "Grave Black";
    private static final String FORFEIT_WHITE = "Forfeit White";
    private static final String FORFEIT_BLACK = "Forfeit Black";

    /** Label with the Title of the Game Window **/
    private JLabel title;

    /** Board with all positions and the Pieces **/
    private JPanel panel_chessBoard;
    /** The Chess Board with the pieces only **/
    private JPanel panel_chessPieces;

    /** RHS of the Game Window **/
    private JPanel panel_LHS;
    /** Label of the grave white **/
    private JLabel grave_label_white;
    /** The Grave containing the white pieces **/
    private JPanel grave_white;
    /** Surrender button white player **/
    private JButton ff_white;

    /** LHS of the Game Window **/
    private JPanel panel_RHS;
    /** Label of the grave black **/
    private JLabel grave_label_black;
    /** The Grave containing the black pieces **/
    private JPanel grave_black;
    /** Surrender button black player **/
    private JButton ff_black;
    /** ChessField the Game is being played on **/
    private ChessField chessField;

    /** Constructor */
    public GameWindow(final ChessField chessField) {
        this.chessField = chessField;
        initComponents();
        initMainFrame();
        addComponents();
    }

    /** Method initializes {@link #title} **/
    private void initTitle() {
        title = new JLabel(TITLE);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setForeground(COLOR_LABEL);
        title.setFont(new Font("Arial Black", Font.BOLD, SIZE_TITLE));
        title.setBorder(new EmptyBorder(MARGIN_TITLE[0], MARGIN_TITLE[1], MARGIN_TITLE[2], MARGIN_TITLE[3]));
    }

    /**
     * Method initializes and puts together following components: <br>
     * - {@link #panel_RHS} <br>
     * - {@link #grave_label_black} <br>
     * - {@link #grave_black} <br>
     * - {@link #ff_black} <br>
     */
    private void initPanel_RHS() {
        panel_RHS = new JPanel();
        panel_RHS.setBackground(COLOR_BACKGROUND);
        panel_RHS.setBorder(new EmptyBorder(MARGIN_BORDER_GRAVE[0], MARGIN_BORDER_GRAVE[1], MARGIN_BORDER_GRAVE[2], MARGIN_BORDER_GRAVE[3]));
        panel_RHS.setLayout(new BorderLayout());

        grave_label_black = new JLabel(GRAVE_BLACK_LABEL);
        grave_label_black.setForeground(COLOR_LABEL);
        grave_label_black.setHorizontalAlignment(JLabel.CENTER);
        grave_label_black.setVerticalAlignment(JLabel.CENTER);
        grave_label_black.setFont(new Font("Arial Black", Font.BOLD, SIZE_GRAVE_LABEL));

        grave_black = new JPanel();
        grave_black.setLayout(new GridLayout(8, 2));
        grave_black.setBackground(IChessPiece.COLOR_FIELD_WHITE);

        ff_black = new JButton(FORFEIT_BLACK);
        ff_black.setPreferredSize(new Dimension(2 * IChessPiece.SIZE_FIELD, IChessPiece.SIZE_FIELD));
        ff_black.setBackground(COLOR_BUTTON_BACKGROUND);
        ff_black.setForeground(COLOR_LABEL);

        panel_RHS.add(grave_label_black, BorderLayout.NORTH);
        panel_RHS.add(grave_black, BorderLayout.CENTER);
        panel_RHS.add(ff_black, BorderLayout.SOUTH);
    }

    /**
     * Method initializes and puts together following components: <br>
     * - {@link #panel_LHS} <br>
     * - {@link #grave_label_white} <br>
     * - {@link #grave_white} <br>
     * - {@link #ff_white} <br>
     */
    private void initPanel_LHS() {
        panel_LHS = new JPanel();
        panel_LHS.setBackground(COLOR_BACKGROUND);
        panel_LHS.setBorder(new EmptyBorder(MARGIN_BORDER_GRAVE[0], MARGIN_BORDER_GRAVE[1], MARGIN_BORDER_GRAVE[2], MARGIN_BORDER_GRAVE[3]));
        panel_LHS.setLayout(new BorderLayout());

        grave_label_white = new JLabel(GRAVE_WHITE_LABEL);
        grave_label_white.setForeground(COLOR_LABEL);
        grave_label_white.setHorizontalAlignment(JLabel.CENTER);
        grave_label_white.setVerticalAlignment(JLabel.CENTER);
        grave_label_white.setFont(new Font("Arial Black", Font.BOLD, SIZE_GRAVE_LABEL));

        grave_white = new JPanel();
        grave_white.setLayout(new GridLayout(8, 2));
        grave_white.setBackground(IChessPiece.COLOR_FIELD_BLACK);

        ff_white = new JButton(FORFEIT_WHITE);
        ff_white.setPreferredSize(new Dimension(2 * IChessPiece.SIZE_FIELD, IChessPiece.SIZE_FIELD));
        ff_white.setBackground(COLOR_BUTTON_BACKGROUND);
        ff_white.setForeground(COLOR_LABEL);

        panel_LHS.add(grave_label_white, BorderLayout.NORTH);
        panel_LHS.add(grave_white, BorderLayout.CENTER);
        panel_LHS.add(ff_white, BorderLayout.SOUTH);
    }

    /**
     * Method initializes following components of the Chess Board:<br>
     * {@link #panel_chessBoard}<br>
     * {@link #panel_chessPieces}
     */
    private void initChessBoard() {
        panel_chessBoard = new JPanel();
        panel_chessBoard.setBackground(COLOR_BACKGROUND);
        panel_chessBoard.setBorder(new EmptyBorder(MARGIN_BOARD[0], MARGIN_BOARD[1], MARGIN_BOARD[2], MARGIN_BOARD[3]));

        panel_chessPieces = new JPanel();
        panel_chessPieces.setLayout(new GridLayout(8, 8));
        chessField.getField().forEach(piece -> panel_chessPieces.add(piece));
        panel_chessBoard.add(panel_chessPieces);
    }

    @Override
    public void initComponents() {
        initTitle();
        initPanel_RHS();
        initPanel_LHS();
        initChessBoard();
    }

    @Override
    public void addComponents() {
        this.add(title, BorderLayout.NORTH);
        this.add(panel_RHS, BorderLayout.EAST);
        this.add(panel_LHS, BorderLayout.WEST);
        this.add(panel_chessBoard, BorderLayout.CENTER);
    }

    @Override
    public void initMainFrame() {
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.getContentPane().setBackground(COLOR_BACKGROUND);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.setVisible(true);
    }


}
