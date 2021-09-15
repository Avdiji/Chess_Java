package Chess.Game.GUI;

import Chess.Game.Logic.ChessField;
import Chess.Game.Logic.ChessFieldButton;
import Chess.Game.Logic.Pieces.EChessPieces;
import Chess.Game.Logic.Pieces.IChessPiece;
import Chess.Game.Logic.Player.EPlayerColor;
import Chess.Game.Logic.Position;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 * @author Fitor Avdiji
 * <p>
 * Class includes all the GUI for the Game Window
 */
public class GameWindow implements IChessFrame {

    private JFrame gameFrame;

    /**
     * idle position to initialize the graves
     **/
    private static final Position idlePosition = new Position('A', 1);

    /**
     * Sizes used for the GUI in the Game Window
     **/
    private static final int MARGIN_BORDER_GRAVE[] = {50, 50, 100, 150};
    private static final int MARGIN_BOARD[] = {50, 160, 100, 160};
    private static final int MARGIN_TITLE[] = {30, 30, 0, 30};
    private static final int SIZE_TITLE = 50;
    private static final int SIZE_GRAVE_LABEL = 15;

    /**
     * Strings used for the GUI in the Game Window
     **/
    private static final String TITLE = "Chess";
    private static final String FORFEIT_WHITE = "Forfeit White";
    private static final String FORFEIT_BLACK = "Forfeit Black";

    /**
     * Label with the Title of the Game Window
     **/
    private JLabel title;

    /**
     * Board with all the Pieces and the Positions (only way to make it work with javaswing)
     **/
    private JPanel board_wrapper;
    /**
     * Panels to indicate, which players turn it is
     **/
    private JPanel indicator_black;
    private JPanel indicator_white;

    /**
     * Board with all the Pieces
     **/
    private JPanel panel_chessBoard;
    /**
     * The Chess Board with the pieces only
     **/
    private JPanel panel_chessPieces;

    /**
     * RHS of the Game Window
     **/
    private JPanel panel_LHS;
    /**
     * The Grave containing the white pieces
     **/
    private JPanel grave_white;
    /**
     * Surrender button white player
     **/
    private JButton ff_white;

    /**
     * LHS of the Game Window
     **/
    private JPanel panel_RHS;
    /**
     * The Grave containing the black pieces
     **/
    private JPanel grave_black;
    /**
     * Surrender button black player
     **/
    private JButton ff_black;

    /**
     * ChessField the Game is being played on
     **/
    private ChessField chessField;
    /**
     * ActionListener to add Piece to the Grave
     **/
    private ActionListener buttonListener;

    private UpgradePawn panel_upgradePawn;
    private ActionListener upgradeListener;

    /**
     * Constructor
     *
     * @param chessField chessField with all the Logic
     */
    public GameWindow(final ChessField chessField) {
        this.chessField = chessField;

        initComponents();
        indicator_white.setBackground(IChessPiece.COLOR_FIELD_MARKED);
        indicator_black.setBackground(IChessFrame.COLOR_BACKGROUND);
        initMainFrame();
        addComponents();
    }

    /**
     * Method initializes {@link #title}
     **/
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
     * - {@link #grave_black} <br>
     * - {@link #ff_black}
     */
    private void initPanel_RHS() {
        panel_RHS = new JPanel();
        panel_RHS.setBackground(COLOR_BACKGROUND);
        panel_RHS.setBorder(new EmptyBorder(MARGIN_BORDER_GRAVE[0], MARGIN_BORDER_GRAVE[1], MARGIN_BORDER_GRAVE[2], MARGIN_BORDER_GRAVE[3]));
        panel_RHS.setLayout(new BorderLayout());
        grave_black = new JPanel();
        grave_black.setLayout(new GridLayout(8, 2));
        grave_black.setBackground(IChessFrame.COLOR_BACKGROUND);
        for (int i = 0; i < 8 * 2; ++i) {
            ChessFieldButton tmp_button = new ChessFieldButton(idlePosition, EChessPieces.EMPTY, IChessPiece.COLOR_FIELD_WHITE);
            tmp_button.initPiece();
            grave_black.add(tmp_button);
        }
        ff_black = new JButton(FORFEIT_BLACK);
        ff_black.setPreferredSize(new Dimension(2 * IChessPiece.SIZE_FIELD, IChessPiece.SIZE_FIELD / 2));
        ff_black.setFont(new Font("Arial Black", Font.BOLD, SIZE_GRAVE_LABEL));
        ff_black.setBackground(COLOR_BUTTON_BACKGROUND);
        ff_black.setForeground(COLOR_LABEL);
        panel_RHS.add(grave_black, BorderLayout.CENTER);
        panel_RHS.add(ff_black, BorderLayout.SOUTH);
    }

    /**
     * Method initializes and puts together following components: <br>
     * - {@link #panel_LHS} <br>
     * - {@link #grave_white} <br>
     * - {@link #ff_white}
     */
    private void initPanel_LHS() {
        panel_LHS = new JPanel();
        panel_LHS.setBackground(COLOR_BACKGROUND);
        panel_LHS.setBorder(new EmptyBorder(MARGIN_BORDER_GRAVE[0], MARGIN_BORDER_GRAVE[3], MARGIN_BORDER_GRAVE[2], MARGIN_BORDER_GRAVE[1]));
        panel_LHS.setLayout(new BorderLayout());
        grave_white = new JPanel();
        grave_white.setLayout(new GridLayout(8, 2));
        grave_white.setBackground(IChessFrame.COLOR_BACKGROUND);
        for (int i = 0; i < 8 * 2; ++i) {
            ChessFieldButton tmp_button = new ChessFieldButton(idlePosition, EChessPieces.EMPTY, IChessPiece.COLOR_FIELD_BLACK);
            tmp_button.initPiece();
            grave_white.add(tmp_button);
        }
        ff_white = new JButton(FORFEIT_WHITE);
        ff_white.setPreferredSize(new Dimension(2 * IChessPiece.SIZE_FIELD, IChessPiece.SIZE_FIELD / 2));
        ff_white.setFont(new Font("Arial Black", Font.BOLD, SIZE_GRAVE_LABEL));
        ff_white.setBackground(COLOR_BUTTON_BACKGROUND);
        ff_white.setForeground(COLOR_LABEL);
        panel_LHS.add(grave_white, BorderLayout.CENTER);
        panel_LHS.add(ff_white, BorderLayout.SOUTH);
    }

    /**
     * Method initializes following components of the Chess Board:<br>
     * {@link #panel_chessBoard}<br>
     * {@link #panel_chessPieces}
     */
    private void initChessBoard() {
        board_wrapper = new JPanel();
        board_wrapper.setLayout(new BorderLayout());
        board_wrapper.setBackground(IChessFrame.COLOR_BACKGROUND);
        board_wrapper.setBorder(new EmptyBorder(MARGIN_BOARD[0], MARGIN_BOARD[1], MARGIN_BOARD[2], MARGIN_BOARD[3]));

        indicator_white = new JPanel();
        indicator_black = new JPanel();

        panel_chessBoard = new JPanel();
        panel_chessBoard.setBackground(COLOR_BACKGROUND);
        panel_chessPieces = new JPanel();
        panel_chessPieces.setLayout(new GridLayout(8, 8));
        chessField.getField().forEach(piece -> panel_chessPieces.add(piece));
        panel_chessBoard.add(panel_chessPieces);

        board_wrapper.add(panel_chessBoard, BorderLayout.CENTER);
        board_wrapper.add(indicator_white, BorderLayout.SOUTH);
        board_wrapper.add(indicator_black, BorderLayout.NORTH);
    }

    /**
     * Method adds a Piece to the corresponding Grave
     *
     * @param type  type of the captured button
     * @param color color of the captured button
     */
    private void addToGrave(final EChessPieces type, final EPlayerColor color) {
        if (color == EPlayerColor.WHITE) {
            ChessFieldButton tmp = (ChessFieldButton) Arrays.stream(grave_white.getComponents())
                    .filter(button -> ((ChessFieldButton) button).getType() == EChessPieces.EMPTY)
                    .findAny().get();
            tmp.setType(type);
            tmp.renderPiece();
        } else if (color == EPlayerColor.BLACK) {
            ChessFieldButton tmp = (ChessFieldButton) Arrays.stream(grave_black.getComponents())
                    .filter(button -> ((ChessFieldButton) button).getType() == EChessPieces.EMPTY)
                    .findAny().get();
            tmp.setType(type);
            tmp.renderPiece();
        }
    }

    /**
     * Method changes the Color of the player indicators accordingly to the currentPlayer
     */
    private void changePlayerIndicator() {
        if (chessField.getCurrentPlayerColor() == chessField.getPlayer1().getPlayerColor()) {
            indicator_white.setBackground(IChessFrame.COLOR_BACKGROUND);
            indicator_black.setBackground(IChessPiece.COLOR_FIELD_MARKED);
        } else {
            indicator_black.setBackground(IChessFrame.COLOR_BACKGROUND);
            indicator_white.setBackground(IChessPiece.COLOR_FIELD_MARKED);
        }
    }

    /**
     * Method returns true if the Pawn can be upgrade to another Piece
     *
     * @param capturedButton button that has been captured
     * @param markedButton   button that has been moved
     **/
    private boolean pawnUpgrade(final ChessFieldButton capturedButton, final ChessFieldButton markedButton) {
        boolean result = false;
        if (markedButton.getType() == EChessPieces.PAWN_WHITE) {
            result = capturedButton.getPosition().getColumn() == 8;
        } else if (markedButton.getType() == EChessPieces.PAWN_BLACK) {
            result = capturedButton.getPosition().getColumn() == 1;
        }
        return result;
    }

    /**
     * Method adjusts the Game's variables after a move has been executed
     */
    private void adjustAfterMove(final ChessFieldButton capturedButton, final ChessFieldButton markedButton) {
        changePlayerIndicator();
        chessField.getMovement().updateEnPassant(markedButton, capturedButton, chessField.getField());
        chessField.setCurrentPlayerColor(chessField.getCurrentPlayerColor() == chessField.getPlayer1().getPlayerColor() ?
                chessField.getPlayer2().getPlayerColor() :
                chessField.getPlayer1().getPlayerColor());
        chessField.removeMarkings();

        System.out.println(String.format("Check %s: %b", chessField.getCurrentPlayerColor(), chessField.getMovement().isCheck(chessField.getCurrentPlayerColor(), chessField.getField())));
        System.out.println(String.format("Checkmate %s: %b", chessField.getCurrentPlayerColor(), chessField.getMovement().isCheckMate(chessField.getCurrentPlayerColor(), chessField.getField())));
        System.out.println(String.format("Stalemate %s: %b", chessField.getCurrentPlayerColor(), chessField.getMovement().isStalemate(chessField.getCurrentPlayerColor(), chessField.getField())));
        System.out.println();
    }

    /**
     * The Method Moves the marked button to the captured Button (used to make the client work)
     *
     * @param capturedButton button that has been captured
     * @param markedButton   button that has been moved
     */
    public void MovePiece(final ChessFieldButton capturedButton, final ChessFieldButton markedButton) {
        if (markedButton.getType() == EChessPieces.ROOK_WHITE || markedButton.getType() == EChessPieces.ROOK_BLACK) {
            capturedButton.setRookMoved(true);
        } else if (markedButton.getType() == EChessPieces.KING_WHITE || markedButton.getType() == EChessPieces.KING_BLACK) {
            capturedButton.setKingMoved(true);
        }
        // if the move wasn't a castle (rochade)
        if (!chessField.getMovement().executeRochade(capturedButton, markedButton, chessField.getField())) {
            // check how many empty pieces there are pre execution
            int empty_fields_preMove = (int) chessField.getField().stream()
                    .filter(button -> button.getType() == EChessPieces.EMPTY).count();

            addToGrave(capturedButton.getType(), capturedButton.getPlayerColor());

            capturedButton.setType(markedButton.getType());
            capturedButton.setPlayerColor(markedButton.getPlayerColor());

            markedButton.setType(EChessPieces.EMPTY);
            markedButton.setPlayerColor(EPlayerColor.NONE);

            // check how many empty pieces there are post execution
            int empty_fields_postMove = (int) chessField.getField().stream()
                    .filter(button -> button.getType() == EChessPieces.EMPTY).count();

            if (empty_fields_preMove == empty_fields_postMove) {
                chessField.getMovement().removeRedundantPiece(capturedButton, chessField.getField());
            }
        }
        adjustAfterMove(capturedButton, markedButton);
    }

    /**
     * Method initializes the Listener to add the Piece to the Grave
     */
    private void initButtonListener() {
        buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread executeThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ChessFieldButton selectedButton = (ChessFieldButton) e.getSource();
                        if (selectedButton.isEndangered()) {
                            ChessFieldButton markedButton = chessField.getField().stream().filter(ChessFieldButton::isMarked).findAny().get();
                            if (pawnUpgrade(selectedButton, markedButton)) {
                                panel_upgradePawn.setPlayerColor(chessField.getCurrentPlayerColor());
                                panel_upgradePawn.render_buttonPieces();
                                title.setVisible(false);
                                panel_upgradePawn.setVisible(true);
                                synchronized (gameFrame) {
                                    try {
                                        gameFrame.wait();
                                    } catch (InterruptedException interruptedException) {
                                        interruptedException.printStackTrace();
                                    }
                                }
                                markedButton.setType(panel_upgradePawn.getSelectedType());
                            }
                            MovePiece(selectedButton, markedButton);
                        } else {
                            if (selectedButton.getPlayerColor() == chessField.getCurrentPlayerColor())
                                chessField.markButtons(selectedButton);
                        }
                    }
                });
                executeThread.start();
            }
        };
    }

    private void initUpgradeListener() {
        upgradeListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel_upgradePawn.setSelectedType(((ChessFieldButton) e.getSource()).getType());
                panel_upgradePawn.setVisible(false);
                title.setVisible(true);
                synchronized (gameFrame) {
                    gameFrame.notify();
                }
            }
        };
    }

    @Override
    public void initComponents() {
        initButtonListener();
        initUpgradeListener();
        initTitle();
        initPanel_RHS();
        initPanel_LHS();
        initChessBoard();
        panel_upgradePawn = new UpgradePawn(upgradeListener);
        panel_upgradePawn.setVisible(false);
    }

    @Override
    public void addComponents() {
        gameFrame.add(title, BorderLayout.NORTH);
        gameFrame.add(panel_RHS, BorderLayout.EAST);
        gameFrame.add(panel_LHS, BorderLayout.WEST);
        gameFrame.add(board_wrapper, BorderLayout.CENTER);
        gameFrame.add(panel_upgradePawn, BorderLayout.SOUTH);
        chessField.getField().forEach(button -> button.addActionListener(buttonListener));
    }

    @Override
    public void initMainFrame() {
        gameFrame = new JFrame();
        gameFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        gameFrame.getContentPane().setBackground(COLOR_BACKGROUND);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setLayout(new BorderLayout());
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setUndecorated(true);
        gameFrame.setVisible(true);
    }
}
