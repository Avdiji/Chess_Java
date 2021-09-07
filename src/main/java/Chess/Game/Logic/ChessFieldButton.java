package Chess.Game.Logic;

import Chess.Game.Logic.Pieces.EChessPieces;
import Chess.Game.Logic.Pieces.IChessPiece;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;

/**
 * @author Fitor Avdiji
 *
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


    public ChessFieldButton(final Position position, final EChessPieces type, final Color backgroundColor){
        this.position = position;
        this.type = type;
        this.backgroundColor = backgroundColor;

        marked = false;
        endangered = false;
    }

    public void initPiece(){
        this.setPreferredSize(new Dimension(IChessPiece.SIZE_FIELD, IChessPiece.SIZE_FIELD));
        this.setBackground(backgroundColor);
        renderPiece();
    }

    public void renderPiece(){
        this.setIcon(new ImageIcon(type.getPath()));
    }


    public boolean isMarked(){
        return marked;
    }

    public boolean isEndangered(){
        return endangered;
    }

    public Position getPosition(){
        return position;
    }

    public EChessPieces getType(){
        return type;
    }

    public void setMarked(final boolean marked){
        this.marked = marked;
        this.setBackground(marked ? IChessPiece.COLOR_FIELD_MARKED : backgroundColor);
    }
    public void setEndangered(final boolean endangered){
        this.endangered = endangered;
        this.setBackground(endangered ? IChessPiece.COLOR_FIELD_ENDANGERED : backgroundColor);
    }

    public void setType(final EChessPieces type){
        this.type = type;
    }



}
