package Chess.Game.GUI.ChessboardGUI;

import Chess.Game.GUI.IChessFrame;
import Chess.Game.Logic.ChessField;
import Chess.Game.Logic.ChessFieldButton;
import Chess.Game.Logic.Pieces.EChessPieces;
import Chess.Game.Logic.Pieces.IChessPiece;
import Chess.Game.Logic.Player.EPlayerColor;
import Chess.Game.Logic.Position;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Font;
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
    protected static final Position idlePosition = new Position('A', 1);

    /**
     * Sizes used for the GUI in the Game Window
     **/
    protected static final int MARGIN_BORDER_GRAVE[] = {50, 50, 100, 150};
    private static final int MARGIN_TITLE[] = {30, 30, 0, 30};
    private static final int SIZE_TITLE = 50;
    protected static final int SIZE_GRAVE_LABEL = 15;

    /**
     * Strings used for the GUI in the Game Window
     **/
    private static final String TITLE = "Chess";

    /**
     * Label with the Title of the Game Window
     **/
    private JLabel title;

    /**
     * Graves of the Players
     **/
    private Grave_White panel_LHS;
    private Grave_Black panel_RHS;

    /**
     * Panel with the board and the player indicator
     **/
    private BoardWrapper board_wrapper;

    /**
     * ChessField the Game is being played on
     **/
    private ChessField chessField;

    /**
     * ActionListener to add Piece to the Grave
     **/
    private ActionListener buttonListener;

    /**
     * ActionListener to upgrade a Pawn
     **/
    private UpgradePawn panel_upgradePawn;
    private ActionListener upgradeListener;

    /**
     * Constructor
     *
     * @param chessField chessField with all the Logic
     */
    public GameWindow(final ChessField chessField) {
        this.chessField = chessField;
        panel_LHS = new Grave_White();
        panel_RHS = new Grave_Black();
        board_wrapper = new BoardWrapper(this.chessField);
        initComponents();
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
     * Method adds a Piece to the corresponding Grave
     *
     * @param type  type of the captured button
     * @param color color of the captured button
     */
    private void addToGrave(final EChessPieces type, final EPlayerColor color) {
        if (color == EPlayerColor.WHITE) {
            ChessFieldButton tmp = (ChessFieldButton) Arrays.stream(panel_LHS.getPanel_GraveButtons().getComponents())
                    .filter(button -> ((ChessFieldButton) button).getType() == EChessPieces.EMPTY)
                    .findAny().get();
            tmp.setType(type);
            tmp.renderPiece();
        } else if (color == EPlayerColor.BLACK) {
            ChessFieldButton tmp = (ChessFieldButton) Arrays.stream(panel_RHS.getPanel_GraveButtons().getComponents())
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
            board_wrapper.setIndicator_white(IChessFrame.COLOR_BACKGROUND);
            board_wrapper.setIndicator_black(IChessPiece.COLOR_FIELD_MARKED);
        } else {
            board_wrapper.setIndicator_black(IChessFrame.COLOR_BACKGROUND);
            board_wrapper.setIndicator_white(IChessPiece.COLOR_FIELD_MARKED);
        }
    }

    /**
     * Method returns true if a Pawn (markedButton) has been upgraded to another Piece
     *
     * @param capturedButton button that has been captured
     * @param markedButton   button that has been moved
     **/
    private boolean hasPawnUpgraded(final ChessFieldButton capturedButton, final ChessFieldButton markedButton) {
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
        changePlayerIndicator(); // change the Player Indicator
        chessField.getMovement().updateEnPassant(markedButton, capturedButton, chessField.getField()); // update the enPassant
        chessField.setCurrentPlayerColor(chessField.getCurrentPlayerColor() == chessField.getPlayer1().getPlayerColor() ?
                chessField.getPlayer2().getPlayerColor() :
                chessField.getPlayer1().getPlayerColor()); // the the new Playercolor
        chessField.removeMarkings(); // remove all the markings

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
        // mark rooks and kings (to prevent illegal rochade
        if (markedButton.getType() == EChessPieces.ROOK_WHITE || markedButton.getType() == EChessPieces.ROOK_BLACK) {
            capturedButton.setRookMoved(true);
        } else if (markedButton.getType() == EChessPieces.KING_WHITE || markedButton.getType() == EChessPieces.KING_BLACK) {
            capturedButton.setKingMoved(true);
        }

        // check whether the move is a castle and execute it if so
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

            // remove a redundant pawn (method removeRedundantPiecee checks, whether another pawn has been captured or not
            if (empty_fields_preMove == empty_fields_postMove) {
                chessField.getMovement().removeRedundantPiece(capturedButton, chessField.getField());
            }
        }
        adjustAfterMove(capturedButton, markedButton);
    }


    /**
     * The Method creates and returns a new Thread, that executes a move
     * (used an individual thread to enable the pawn upgrade)
     *
     * @param capturedButton the captured button
     * @return A new Thread, that executes a Move
     * @throws IllegalStateException if the captured button is not in an endangered state
     */
    private Thread executeMoveThread(final ChessFieldButton capturedButton) {
        if (!capturedButton.isEndangered()) {
            throw new IllegalStateException("The capturedButton must be in an endangered state!");
        }

        Thread result = new Thread(new Runnable() {
            @Override
            public void run() {
                // there must have been a marked button, if the selected one was endangered
                ChessFieldButton markedButton = chessField.getField().stream().filter(ChessFieldButton::isMarked).findAny().get();

                // check whether a pawn has been upgraded
                if (hasPawnUpgraded(capturedButton, markedButton)) {

                    // set the color of the current Player and render panel_upgradePawn
                    panel_upgradePawn.setPlayerColor(chessField.getCurrentPlayerColor());
                    panel_upgradePawn.render_buttonPieces();

                    // disable title visibility/ enable panel_upgradePawn visibility
                    title.setVisible(false);
                    panel_upgradePawn.setVisible(true);

                    // acquire the monitor lock (to prevent IllegalMonitorStateException)
                    synchronized (gameFrame) {
                        try {
                            // stop gameFrame until it gets notified
                            gameFrame.wait();
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }
                    }
                    // set the type of the marked button after the gameFrame has been notified
                    markedButton.setType(panel_upgradePawn.getSelectedType());
                }
                // finally move the pawn
                MovePiece(capturedButton, markedButton);
            }
        });
        return result;
    }

    /**
     * Method initializes the Listener to add the Piece to the Grave
     */
    private void initButtonListener() {
        buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!panel_upgradePawn.isVisible()) {
                    ChessFieldButton selectedButton = (ChessFieldButton) e.getSource();
                    // if the selected button was endangered
                    if (selectedButton.isEndangered()) {
                        executeMoveThread(selectedButton).start();
                    } else {
                        // if the selected button was not endangered mark all of it's pathing options
                        if (selectedButton.getPlayerColor() == chessField.getCurrentPlayerColor())
                            chessField.markButtons(selectedButton);
                    }
                }
            }
        };
    }

    /**
     * Method receives input, which changes the type of the class UpgradePawn (to upgrade the Pawn that is waiting in initButtonListener)
     */
    private void initUpgradeListener() {
        upgradeListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // set the type of panel_upgradePawn (pawn will be upgraded to this type
                panel_upgradePawn.setSelectedType(((ChessFieldButton) e.getSource()).getType());
                // disable panel_upgradePawn visibility/ enable title visibility
                panel_upgradePawn.setVisible(false);
                title.setVisible(true);
                synchronized (gameFrame) {
                    gameFrame.notify(); // notify the gameFrame
                }
            }
        };
    }

    @Override
    public void initComponents() {
        initButtonListener();
        initUpgradeListener();
        initTitle();
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
