package Chess.Game.Logic;

import Chess.Game.GUI.MainMenu;
import Chess.Game.Logic.Pieces.EChessPieces;
import Chess.Game.Logic.Pieces.IChessPiece;
import Chess.Game.Logic.Player.EPlayerColor;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Fitor Avdiji
 * <p>
 * Class represents a Button in the Chess Field
 */
public class ChessFieldButton extends JButton {

    /** Constant int for the thickness of a button in a chessfield **/
    private static final int BORDER_THICKNESS = 3;

    /** Position of the Button **/
    private Position position;

    /** Background Color of the button **/
    private final Color backgroundColor;

    /** Type of the Piece **/
    private EChessPieces type;

    /** EPlayerColor of the Piece in this Button **/
    private EPlayerColor playerColor;

    /** Booleans to determine whether the button is marked or endangered **/
    private boolean marked;
    private boolean endangered;

    /**
     * True if a Pawn has moved next to another Pawn,
     * in a way that enables the other Pawn to execute the EnPassant
     **/
    private boolean enPassant;

    /** True if this button is a King/Rook and has moved **/
    private boolean kingMoved;
    private boolean rookMoved;

    /**
     * Constructor initializes following variables:<br>
     * <p>
     * {@link #position}<br>
     * {@link #type}<br>
     * {@link #backgroundColor}<br>
     * {@link #marked}<br>
     * {@link #endangered}<br>
     *
     * @param position        position of the Button
     * @param type            type of the Button
     * @param backgroundColor background Color of the Button
     */
    public ChessFieldButton(final Position position, final EChessPieces type, final Color backgroundColor) {
        this.position = position;
        this.type = type;
        this.backgroundColor = backgroundColor;
        marked = false;
        endangered = false;
        enPassant = false;
        kingMoved = false;
        rookMoved = false;
        playerColor = type.toString().contains("WHITE") ?
                EPlayerColor.WHITE : (type.toString().contains("BLACK") ?
                EPlayerColor.BLACK :
                EPlayerColor.NONE);
    }

    //GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER //
    //GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER //
    /**
     * Getter for {@link #marked}
     *
     * @return marked
     */
    public boolean isMarked() {
        return marked;
    }

    /**
     * Getter for {@link #endangered}
     *
     * @return endangered
     */
    public boolean isEndangered() {
        return endangered;
    }

    /**
     * Getter for {@link #position}
     *
     * @return position
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Getter for {@link #type}
     *
     * @return type
     */
    public EChessPieces getType() {
        return type;
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
     * Getter for {@link #enPassant}
     * If true this Piece has enabled the Pawn next to it to execute the enPassant
     *
     * @return enPassant
     */
    public boolean enabledEnPassant() {
        return enPassant;
    }

    /**
     * Getter for{@link #kingMoved}
     *
     * @return kingMoved
     */
    public boolean hasKingMoved() {
        return kingMoved;
    }

    /**
     * Getter for {@link #rookMoved}
     *
     * @return rookMoved
     */
    public boolean hasRookMoved() {
        return rookMoved;
    }
    //GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER //
    //GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER //

    //SETTER SETTER SETTER SETTER SETTER SETTER SETTER SETTER SETTER SETTER SETTER SETTER SETTER SETTER //
    //SETTER SETTER SETTER SETTER SETTER SETTER SETTER SETTER SETTER SETTER SETTER SETTER SETTER SETTER //
    /**
     * Method sets {@link #marked} to the given parameter and fills the background
     *
     * @param marked
     */
    public void setMarked(final boolean marked) {
        this.marked = marked;
        this.setBackground(marked ? MainMenu.COLOR_FIELD_MARKED : backgroundColor);
    }

    /**
     * Method sets {@link #endangered} to the given parameter and fills the background
     *
     * @param endangered
     */
    public void setEndangered(final boolean endangered) {
        this.endangered = endangered;
        if (type == EChessPieces.EMPTY) {
            if (endangered) {
                setPieceIcon(EChessPieces.EMPTY.getPath());
            } else {
                this.setIcon(new ImageIcon());
            }
        } else {
            this.setBackground(endangered ? MainMenu.COLOR_FIELD_ENDANGERED : backgroundColor);
        }
    }

    /**
     * Setter for {@link #type}
     *
     * @param type
     */
    public void setType(final EChessPieces type) {
        this.type = type;
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
     * Setter for {@link #enPassant}
     *
     * @param value new value for enPassant
     */
    public void setEnPassant(final boolean value) {
        enPassant = value;
    }

    /**
     * Setter for {@link #kingMoved}
     *
     * @param value new value for kingMoved
     */
    public void setKingMoved(final boolean value) {
        this.kingMoved = value;
    }

    /**
     * Setter for {@link #rookMoved}
     *
     * @param value new value for rookMoved
     */
    public void setRookMoved(final boolean value) {
        this.rookMoved = value;
    }
    //SETTER SETTER SETTER SETTER SETTER SETTER SETTER SETTER SETTER SETTER SETTER SETTER SETTER SETTER //
    //SETTER SETTER SETTER SETTER SETTER SETTER SETTER SETTER SETTER SETTER SETTER SETTER SETTER SETTER //

    /**
     * Method sets the icon of a Piece (needed for jar)
     *
     * @param path path of the corresponding Image
     */
    private void setPieceIcon(final String path) {
        InputStream is = getClass().getResourceAsStream(path);
        try {
            this.setIcon(new ImageIcon(ImageIO.read(is)));
        } catch (IOException e) {
        }
    }

    /** Method thickens the Frame of a button **/
    public void thickenFrame(final Color color) {
        Border border = new LineBorder(color, BORDER_THICKNESS);
        this.setBorder(border);
    }

    /** Method initializes the Button **/
    public void initPiece() {
        this.setPreferredSize(new Dimension(IChessPiece.SIZE_FIELD, IChessPiece.SIZE_FIELD));
        this.setBackground(backgroundColor);
        renderPiece();
    }

    /** Method renders the Button (sets the icon) **/
    public void renderPiece() {
        if (type == EChessPieces.EMPTY) {
            this.setIcon(new ImageIcon());
        } else {
            setPieceIcon(type.getPath());
        }
    }
}
