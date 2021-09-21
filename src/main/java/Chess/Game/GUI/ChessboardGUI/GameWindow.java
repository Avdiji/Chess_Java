package Chess.Game.GUI.ChessboardGUI;

import Chess.Game.GUI.IChessFrame;
import Chess.Game.GUI.MainMenu;
import Chess.Game.GUI.Scoreboard;
import Chess.Game.Logic.ChessField;
import Chess.Game.Logic.ChessFieldButton;
import Chess.Game.Logic.ChessPieceMovement;
import Chess.Game.Logic.Pieces.EChessPieces;
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

    /**
     * Frame the Game is being played in
     **/
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

    /**
     * Strings used for the GUI in the Game Window
     **/
    private static final String TITLE = "Chess";
    private static final String STALEMATE = "STALEMATE";


    /**
     * Label with the Title of the Game Window
     **/
    private JLabel title;

    /**
     * Graves of the Players
     **/
    private Grave_White panel_LHS;
    private Grave_Black panel_RHS;
    private ActionListener AL_graveWhite;
    private ActionListener AL_graveBlack;

    /**
     * Panel with the board and the player indicator
     **/
    private BoardWrapper board_wrapper;

    /**
     * ChessField the Game is being played on
     **/
    private ChessField chessField;

    /**
     * ActionListener for the Buttons on the Field
     **/
    private ActionListener buttonListener;

    /**
     * ActionListener to upgrade a Pawn
     **/
    private UpgradePawn panel_upgradePawn;
    private ActionListener upgradeListener;

    /**
     * Scoreboard of this Game
     **/
    private final Scoreboard scoreboard;

    /**
     * Constructor
     *
     * @param chessField chessField with all the Logic
     */
    public GameWindow(final ChessField chessField, final Scoreboard scoreboard) {
        this.scoreboard = scoreboard;
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
        title.setForeground(MainMenu.COLOR_LABEL);
        title.setFont(new Font(FONT, FONT_TYPE, SIZE_TITLE));
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
            board_wrapper.setIndicator_white(MainMenu.COLOR_BACKGROUND);
            board_wrapper.setIndicator_black(MainMenu.COLOR_FIELD_MARKED);
        } else {
            board_wrapper.setIndicator_black(MainMenu.COLOR_BACKGROUND);
            board_wrapper.setIndicator_white(MainMenu.COLOR_FIELD_MARKED);
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

    /** Method determines the actual enemy color (in case of an online game **/
    private EPlayerColor getActualColor(){
        EPlayerColor result = chessField.getCurrentPlayerColor();
        if(chessField.getCurrentPlayerColor() == EPlayerColor.NONE && chessField.getPlayer1().getPlayerColor() == EPlayerColor.NONE){
            result = EPlayerColor.WHITE;
        }else if(chessField.getCurrentPlayerColor() == EPlayerColor.NONE && chessField.getPlayer2().getPlayerColor() == EPlayerColor.NONE){
            result = EPlayerColor.BLACK;
        }
        return result;
    }

    /**
     * Method adjusts the Game's variables after a move has been executed
     */
    private void adjustPostMove(final ChessFieldButton capturedButton, final ChessFieldButton markedButton) {
        changePlayerIndicator(); // change the Player Indicator
        chessField.setCurrentPlayerColor(chessField.getCurrentPlayerColor() == chessField.getPlayer1().getPlayerColor() ?
                chessField.getPlayer2().getPlayerColor() :
                chessField.getPlayer1().getPlayerColor()); // the the new Playercolor
        chessField.removeMarkings(); // remove all the markings

        if (ChessPieceMovement.isCheckMate(getActualColor(), chessField.getField())) {
            gameFrame.setVisible(false);
            gameFrame.dispose();
            scoreboard.setMessage(String.format("%s Won", Arrays.stream(EPlayerColor.values())
                    .filter(value -> value != getActualColor() && value != EPlayerColor.NONE).findAny().get()));
            scoreboard.setVisible(true);
        } else if (ChessPieceMovement.isStalemate(getActualColor(), chessField.getField())) {
            gameFrame.setVisible(false);
            gameFrame.dispose();
            scoreboard.setMessage(STALEMATE);
            scoreboard.setVisible(true);
        }
    }

    /**
     * Method adjusts the variables of both buttons, so that it enables the Player to execute a Rochade/EnPassant
     * before the move is executed
     *
     * @param capturedButton button that has been captured
     * @param markedButton   button that has executed the move
     */
    private void adjustPreMove(final ChessFieldButton capturedButton, final ChessFieldButton markedButton) {
        try {
            ChessPieceMovement.updateEnPassant(markedButton, capturedButton, chessField.getField()); // update the enPassant
        } catch (IllegalArgumentException e) {
        }
        // mark rooks and kings (to prevent illegal rochade
        if (markedButton.getType() == EChessPieces.ROOK_WHITE || markedButton.getType() == EChessPieces.ROOK_BLACK) {
            capturedButton.setRookMoved(true);
        } else if (markedButton.getType() == EChessPieces.KING_WHITE || markedButton.getType() == EChessPieces.KING_BLACK) {
            capturedButton.setKingMoved(true);
        }
    }

    /**
     * The Method Moves the marked button to the captured Button (used to make the client work)
     *
     * @param capturedButton button that has been captured
     * @param markedButton   button that has been moved
     */
    public void MovePiece(final ChessFieldButton capturedButton, final ChessFieldButton markedButton) {
        adjustPreMove(capturedButton, markedButton);

        // check whether the move is a castle and execute it if so
        if (!ChessPieceMovement.executeRochade(capturedButton, markedButton, chessField.getField())) {
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

            // remove a redundant pawn (method removeRedundantPiece checks, whether another pawn has been captured or not)
            if (empty_fields_preMove == empty_fields_postMove) {
                ChessFieldButton toBury = ChessPieceMovement.findRedundantPiece(capturedButton, chessField.getField());
                if (toBury != null) {
                    addToGrave(toBury.getType(), toBury.getPlayerColor());
                    toBury.setType(EChessPieces.EMPTY);
                    toBury.setPlayerColor(EPlayerColor.NONE);
                }
            }
        }
        adjustPostMove(capturedButton, markedButton);
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
                ChessFieldButton markedButton = chessField.getField().stream().filter(ChessFieldButton::isMarked).findAny().get();
                if (hasPawnUpgraded(capturedButton, markedButton)) {

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
                MovePiece(capturedButton, markedButton);
            }
        });
        return result;
    }

    /**
     * Method initializes the Listener to for the Buttons on the Field
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

    /**
     * Method initializes {@link #AL_graveWhite}
     **/
    private void initAL_graveWhite() {
        AL_graveWhite = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameFrame.setVisible(false);
                gameFrame.dispose();
                scoreboard.setMessage("WHITE gave up!");
                scoreboard.setVisible(true);
            }
        };
    }

    /**
     * Method initializes {@link #AL_graveBlack}
     **/
    private void initAL_graveBlack() {
        AL_graveBlack = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameFrame.setVisible(false);
                gameFrame.dispose();
                scoreboard.setMessage("BLACK gave up!");
                scoreboard.setVisible(true);
            }
        };
    }

    @Override
    public void initComponents() {
        initButtonListener();
        initUpgradeListener();
        initTitle();

        initAL_graveWhite();
        initAL_graveBlack();

        panel_upgradePawn = new UpgradePawn(upgradeListener);
        panel_upgradePawn.setVisible(false);

        panel_LHS.addGraveListener(AL_graveWhite);
        panel_RHS.addGraveListener(AL_graveBlack);
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
        gameFrame.getContentPane().setBackground(MainMenu.COLOR_BACKGROUND);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setLayout(new BorderLayout());
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setUndecorated(true);
        gameFrame.setVisible(true);
    }

    @Override
    public void reColor() {

    }
}
