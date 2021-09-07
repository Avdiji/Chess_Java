package Chess.Game.Logic;

import Chess.Game.Logic.Pieces.EChessPieces;
import Chess.Game.Logic.Pieces.IChessPiece;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;

/**
 * @author Fitor Avdiji
 * <p>
 * Class represents a Button in the Chess Field
 */
public class ChessFieldButton extends JButton {

    /** Position of the Button **/
    private Position position;
    /** Type of the Piece **/
    private EChessPieces type;
    /** Background Color of the button **/
    private final Color backgroundColor;

    /** booleans to determine whether the button is marked or endangered **/
    private boolean marked;
    private boolean endangered;

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
    }

    //GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER //
    //GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER //
    /**
     * Getter for {@link #marked}
     * @return marked
     */
    public boolean isMarked() {
        return marked;
    }
    /**
     * Getter for {@link #endangered}
     * @return endangered
     */
    public boolean isEndangered() {
        return endangered;
    }
    /**
     * Getter for {@link #position}
     * @return position
     */
    public Position getPosition() {
        return position;
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
        this.setBackground(marked ? IChessPiece.COLOR_FIELD_MARKED : backgroundColor);
    }
    /**
     * Method sets {@link #endangered} to the given parameter and fills the background
     *
     * @param endangered
     */
    public void setEndangered(final boolean endangered) {
        this.endangered = endangered;
        this.setBackground(endangered ? IChessPiece.COLOR_FIELD_ENDANGERED : backgroundColor);
    }
    /**
     * Setter for {@link #type}
     * @param type
     */
    public void setType(final EChessPieces type) {
        this.type = type;
    }

    /**
     * Getter for {@link #type}
     * @return type
     */
    public EChessPieces getType() {
        return type;
    }

    /** Method initializes the Button */
    public void initPiece() {
        this.setPreferredSize(new Dimension(IChessPiece.SIZE_FIELD, IChessPiece.SIZE_FIELD));
        this.setBackground(backgroundColor);
        renderPiece();
    }
    //SETTER SETTER SETTER SETTER SETTER SETTER SETTER SETTER SETTER SETTER SETTER SETTER SETTER SETTER //
    //SETTER SETTER SETTER SETTER SETTER SETTER SETTER SETTER SETTER SETTER SETTER SETTER SETTER SETTER //

    /** Method renders the Button (sets the icon) */
    public void renderPiece() {
        this.setIcon(new ImageIcon(type.getPath()));
    }



}
