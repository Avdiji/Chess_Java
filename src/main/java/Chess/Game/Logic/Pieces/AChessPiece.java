package Chess.Game.Logic.Pieces;

import Chess.Game.Logic.ChessField;
import Chess.Game.Logic.Position;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

/**
 * @author Fitor Avdiji
 * <p>
 * Base class for all the chess Pieces.
 */
public abstract class AChessPiece extends JButton implements IChessPiece {

    /** position of the current Piece **/
    private final Position position;
    /** Type of the current Piece **/
    private final EChessPieces piece;
    /** Chess Field the piece is located in **/
    private final ChessField chessField;
    /** backgroundColor of the Piece **/
    private Color backgroundColor;

    /** boolean to give information about whether the piece is marked **/
    private boolean marked;
    /** boolean to give information about whether the piece is endangered **/
    private boolean endangered;

    /**
     * Constructor initializes following parameter:<br>
     * {@link #position}<br>
     * {@link #piece}<br>
     * {@link #chessFIeld}
     *
     * @param position   position of the current Piece
     * @param piece      type of the current Piece
     * @param chessField chess Field the Piece is located in
     * @throws IllegalArgumentException if the piece has an invalid type
     */
    public AChessPiece(final Position position, final EChessPieces piece, final ChessField chessField) {
        this.position = position;
        this.piece = piece;
        this.chessField = chessField;
    }

    /**
     * Copy Constructor (deep) to create a new AChessPiece with a custom position
     *  (Background Color stays the same)
     * @param other         Another Chess Piece
     * @param otherPosition position to init this.position with
     */
    public AChessPiece(final AChessPiece other, final Position position) {
        this.position = new Position(position.getRow(), position.getColumn());
        this.piece = other.piece;
        this.chessField = other.chessField;
    }

    /**
     * Getter for {@link #position}
     * @return position of this Piece
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Getter for {@link #piece}
     * @return the type of this Piece
     */
    public EChessPieces getPiece() {
        return piece;
    }

    /**
     * Getter for {@link #chessField}
     * @return the Chessfield
     */
    public ChessField getChessField() {
        return chessField;
    }

    /**
     * Getter for {@link #marked}
     * @return marked
     */
    public boolean isMarked(){ return marked; }

    /**
     * Getter for {@link #endangered}
     * @return endangered
     */
    public boolean isEndangered(){ return endangered; }

    /**
     * Setter for {@link #marked}
     * @param marked true = marked, false = not marked
     */
    public void setMarked(final boolean marked){
        if(marked) {
            this.marked = true;
            this.setBackground(COLOR_FIELD_MARKED);
        }else{
            this.marked = false;
            this.setBackground(backgroundColor);
        }
    }

    /**
     * Setter for {@link #endangered}
     * @param endangered true = endangered, false = not endangered
     */
    public void setEndangered(final boolean endangered){
        if(endangered) {
            this.endangered = true;
            this.setBackground(COLOR_FIELD_ENDANGERED);
        }else{
            this.endangered = false;
            this.setBackground(backgroundColor);
        }
    }

    @Override
    public boolean canCapturePosition(Position target) {
        return getPotentialPositions().contains(target);
    }

    @Override
    public void initPiece(final Color backgroundColor){
        this.backgroundColor = backgroundColor;

        this.setBackground(this.backgroundColor);
        this.setPreferredSize(new Dimension(SIZE_FIELD, SIZE_FIELD));
        Icon icon = new ImageIcon(piece.getPath());
        this.setIcon(icon);
    }
}
