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
import Chess.Game.Logic.Player.EPlayerColor;

import java.util.List;
import java.util.Set;

/**
 * @author Fitor Avdiji
 * <p>
 * Class calculates the movement of a Piece
 */
public class ChessPieceMovement {

    /**
     * All the possible pieces
     **/
    private final IChessPiece pawn;
    private final IChessPiece rook;
    private final IChessPiece bishop;
    private final IChessPiece knight;
    private final IChessPiece king;
    private final IChessPiece queen;
    private final IChessPiece empty;

    /**
     * Constructor Initializes:<br>
     * <p>
     * {@link #pawn}<br>
     * {@link #rook}<br>
     * {@link #bishop}<br>
     * {@link #knight}<br>
     * {@link #king}<br>
     * {@link #queen}<br>
     * {@link #empty}
     */
    public ChessPieceMovement() {
        pawn = new Pawn();
        rook = new Rook();
        bishop = new Bishop();
        knight = new Knight();
        king = new King();
        queen = new Queen();
        empty = new Empty();
    }

    /**
     * Method returns a Set of potential moves thePiece could make
     *
     * @param currentPosition    currentPosition of the Piece
     * @param currentPlayerColor Player Color of the Piece (Black/White)
     * @param field              Field the piece is located in
     * @param currentPiece       type of the current Piece
     * @return A Set of possible moves of the selected Piece
     */
    public Set<Position> getActualMoves(final Position currentPosition, final EPlayerColor currentPlayerColor, final List<ChessFieldButton> field, final EChessPieces currentPiece) {
        if (currentPiece == EChessPieces.PAWN_WHITE || currentPiece == EChessPieces.PAWN_BLACK)
            ((Pawn) pawn).setType(currentPiece);
        return switch (currentPiece) {
            case PAWN_WHITE, PAWN_BLACK -> pawn.getActualPositions(currentPosition, currentPlayerColor, field);
            case ROOK_WHITE, ROOK_BLACK -> rook.getActualPositions(currentPosition, currentPlayerColor, field);
            case BISHOP_WHITE, BISHOP_BLACK -> bishop.getActualPositions(currentPosition, currentPlayerColor, field);
            case KNIGHT_WHITE, KNIGHT_BLACK -> knight.getActualPositions(currentPosition, currentPlayerColor, field);
            case KING_WHITE, KING_BLACK -> king.getActualPositions(currentPosition, currentPlayerColor, field);
            case QUEEN_WHITE, QUEEN_BLACK -> queen.getActualPositions(currentPosition, currentPlayerColor, field);
            case EMPTY -> empty.getActualPositions(currentPosition, currentPlayerColor, field);
        };
    }


}
