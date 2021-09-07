package Chess.Game.Logic;

import Chess.Game.Logic.Pieces.Bishop;
import Chess.Game.Logic.Pieces.EChessPieces;
import Chess.Game.Logic.Pieces.Empty;
import Chess.Game.Logic.Pieces.IChessPiece;
import Chess.Game.Logic.Pieces.King;
import Chess.Game.Logic.Pieces.Knight;
import Chess.Game.Logic.Pieces.Pawn;
import Chess.Game.Logic.Pieces.Queen;
import Chess.Game.Logic.Pieces.Rook;

import java.util.Set;

/**
 * @author Fitor Avdiji
 *
 * Class calculates the movement of a Piece
 */
public class ChessPieceMovement {

    /** All the possible pieces **/
    private final IChessPiece pawn;
    private final IChessPiece rook;
    private final IChessPiece bishop;
    private final IChessPiece knight;
    private final IChessPiece king;
    private final IChessPiece queen;
    private final IChessPiece empty;

    /**
     * Constructor Initializes:<br>
     *
     * {@link #pawn}<br>
     * {@link #rook}<br>
     * {@link #bishop}<br>
     * {@link #knight}<br>
     * {@link #king}<br>
     * {@link #queen}<br>
     * {@link #empty}
     */
    public ChessPieceMovement(){
        pawn = new Pawn();
        rook = new Rook();
        bishop = new Bishop();
        knight = new Knight();
        king = new King();
        queen = new Queen();
        empty = new Empty();
    }

    /**
     * Method returns a Set of potential moves the Piece could make
     * @param currentPosition current Position of the Piece
     * @param currentPiece type of the current Piece
     * @return A Set of potential positions
     */
    public Set<Position> getPotentialMoves(final Position currentPosition, final EChessPieces currentPiece){
        ((Pawn)pawn).setType(currentPiece);
        return switch (currentPiece){
            case PAWN_WHITE, PAWN_BLACK -> pawn.getPotentialPositions(currentPosition);
            case ROOK_WHITE, ROOK_BLACK -> rook.getPotentialPositions(currentPosition);
            case BISHOP_WHITE, BISHOP_BLACK -> bishop.getPotentialPositions(currentPosition);
            case KNIGHT_WHITE, KNIGHT_BLACK -> knight.getPotentialPositions(currentPosition);
            case KING_WHITE, KING_BLACK -> king.getPotentialPositions(currentPosition);
            case QUEEN_WHITE, QUEEN_BLACK -> queen.getPotentialPositions(currentPosition);
            case EMPTY -> empty.getPotentialPositions(currentPosition);
        };
    }


}
