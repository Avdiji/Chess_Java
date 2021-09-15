package Chess.Game.GUI.ChessboardGUI;

import Chess.Game.GUI.IChessFrame;
import Chess.Game.Logic.ChessFieldButton;
import Chess.Game.Logic.Pieces.EChessPieces;
import Chess.Game.Logic.Pieces.IChessPiece;
import Chess.Game.Logic.Player.EPlayerColor;
import Chess.Game.Logic.Position;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

/**
 * @author Fitor Avdiji
 * <p>
 * The class draws the Panel, the Upgrades of the Pawn will be located in
 */
public class UpgradePawn extends JPanel implements IChessFrame {

    /**
     * Array of all the Pieces the pawn can be upgraded to
     **/
    private ChessFieldButton button_pieces[];
    /**
     * Type the player has selected
     **/
    private EChessPieces selectedType;
    /**
     * Color of the currentPlayer
     **/
    private EPlayerColor playerColor;

    /**
     * Constructor
     **/
    public UpgradePawn(final ActionListener al) {
        initMainFrame();
        initComponents();
        addComponents();

        for (int i = 0; i < 4; ++i) {
            button_pieces[i].addActionListener(al);
        }
    }

    /**
     * Getter for {@link #selectedType}
     *
     * @return selectedType
     */
    public EChessPieces getSelectedType() {
        return selectedType;
    }

    /**
     * Getter for {@link #playerColor}
     *
     * @return playerColor
     */
    public EPlayerColor getPlayerColor() {
        return playerColor;
    }

    /**
     * Setter for {@link #selectedType}
     *
     * @param selectedType
     */
    public void setSelectedType(final EChessPieces selectedType) {
        this.selectedType = selectedType;
    }

    /**
     * Setter for {@link #playerColor}
     *
     * @param playerColor
     */
    public void setPlayerColor(final EPlayerColor playerColor) {
        this.playerColor = playerColor;
    }

    /**
     * Method renders the buttons pieces (draws new icons depending on the value of {@link #selectedType}
     */
    public void render_buttonPieces() {
        button_pieces[0].setType(playerColor == EPlayerColor.WHITE ?
                EChessPieces.ROOK_WHITE :
                EChessPieces.ROOK_BLACK);
        button_pieces[1].setType(playerColor == EPlayerColor.WHITE ?
                EChessPieces.BISHOP_WHITE :
                EChessPieces.BISHOP_BLACK);
        button_pieces[2].setType(playerColor == EPlayerColor.WHITE ?
                EChessPieces.KNIGHT_WHITE :
                EChessPieces.KNIGHT_BLACK);
        button_pieces[3].setType(playerColor == EPlayerColor.WHITE ?
                EChessPieces.QUEEN_WHITE :
                EChessPieces.QUEEN_BLACK);
        for (int i = 0; i < 4; ++i) {
            button_pieces[i].renderPiece();
        }
    }

    @Override
    public void initMainFrame() {
        this.setLayout(new GridLayout(1, 4));
        this.setPreferredSize(new Dimension(100, 100));
    }

    @Override
    public void initComponents() {
        button_pieces = new ChessFieldButton[4];
        button_pieces[0] = new ChessFieldButton(GameWindow.idlePosition,
                playerColor == EPlayerColor.WHITE ?
                        EChessPieces.ROOK_WHITE :
                        EChessPieces.ROOK_BLACK,
                IChessPiece.COLOR_FIELD_MARKED);

        button_pieces[1] = new ChessFieldButton(GameWindow.idlePosition,
                playerColor == EPlayerColor.WHITE ?
                        EChessPieces.BISHOP_WHITE :
                        EChessPieces.BISHOP_BLACK,
                IChessPiece.COLOR_FIELD_MARKED);

        button_pieces[2] = new ChessFieldButton(GameWindow.idlePosition,
                playerColor == EPlayerColor.WHITE ?
                        EChessPieces.KNIGHT_WHITE :
                        EChessPieces.KNIGHT_BLACK,
                IChessPiece.COLOR_FIELD_MARKED);

        button_pieces[3] = new ChessFieldButton(GameWindow.idlePosition,
                playerColor == EPlayerColor.WHITE ?
                        EChessPieces.QUEEN_WHITE :
                        EChessPieces.QUEEN_BLACK,
                IChessPiece.COLOR_FIELD_MARKED);
    }

    @Override
    public void addComponents() {
        for (int i = 0; i < 4; ++i) {
            button_pieces[i].initPiece();
            this.add(button_pieces[i]);
        }
    }
}
