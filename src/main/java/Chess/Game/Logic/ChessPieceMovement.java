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

import java.util.HashSet;
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
     * // TODO King can capture piece, which puts him in an endangered state
     * <p>
     * Method returns a Set of potential moves thePiece could make
     *
     * @param currentPosition    currentPosition of the Piece
     * @param currentPlayerColor Player Color of the Piece (Black/White)
     * @param field              Field the piece is located in
     * @param currentPiece       type of the current Piece
     * @return A Set of possible moves of the selected Piece
     */
    public Set<Position> getActualMoves(final Position currentPosition, final EPlayerColor currentPlayerColor, final List<ChessFieldButton> field, final EChessPieces currentPiece) {
        return switch (currentPiece) {
            case PAWN_WHITE, PAWN_BLACK -> pawn.getActualPositions(currentPosition, currentPlayerColor, field);
            case ROOK_WHITE, ROOK_BLACK -> rook.getActualPositions(currentPosition, currentPlayerColor, field);
            case BISHOP_WHITE, BISHOP_BLACK -> bishop.getActualPositions(currentPosition, currentPlayerColor, field);
            case KNIGHT_WHITE, KNIGHT_BLACK -> knight.getActualPositions(currentPosition, currentPlayerColor, field);
            case KING_WHITE, KING_BLACK -> getKingsSaveMoves(currentPosition, currentPlayerColor, field);
            case QUEEN_WHITE, QUEEN_BLACK -> queen.getActualPositions(currentPosition, currentPlayerColor, field);
            case EMPTY -> empty.getActualPositions(currentPosition, currentPlayerColor, field);
        };
    }

    /**
     * Method sets enPassant for currentButton
     *
     * @param currentButton the currentButton
     * @param destination   destination of currentButton
     * @param field         field the game is being played in
     */
    public void updateEnPassant(final ChessFieldButton currentButton, final ChessFieldButton destination, final List<ChessFieldButton> field) {
        ((Pawn) pawn).enableEnPassant(currentButton, destination, field);
    }

    /**
     * The Method returns a Set of Positions the King can move to, without endangering himself
     *
     * @param currentPosition    currentPosition of the King
     * @param currentPlayerColor currentColor of the King
     * @param field              field the King is located in
     * @return A Set of Positions the King is allowed to move to
     */
    private Set<Position> getKingsSaveMoves(final Position currentPosition, final EPlayerColor currentPlayerColor, final List<ChessFieldButton> field) {
        Set<Position> result = king.getActualPositions(currentPosition, currentPlayerColor, field);
        Set<Position> enemyPositions = new HashSet<>();

        ChessFieldButton currentButton = field.stream().filter(button -> button.getPosition().equals(currentPosition)).findAny().get();
        // for each ChessFieldButton the king could reach
        field.stream().filter(button -> king.getActualPositions(currentPosition, currentPlayerColor, field).contains(button.getPosition())).forEach(button -> {
            // save values
            EChessPieces tmp_type = button.getType();
            EPlayerColor tmp_color = button.getPlayerColor();
            EChessPieces king_type = currentButton.getType();
            EPlayerColor king_color = currentButton.getPlayerColor();
            // execute the move
            button.setType(king_type);
            button.setPlayerColor(king_color);
            currentButton.setType(EChessPieces.EMPTY);
            currentButton.setPlayerColor(EPlayerColor.NONE);
            // check, whether the king ends up in an endangered Position
            field.stream()
                    .filter(piece -> piece.getType() != EChessPieces.KING_WHITE && piece.getType() != EChessPieces.KING_BLACK)
                    .filter(piece -> piece.getType() != EChessPieces.PAWN_WHITE && piece.getType() != EChessPieces.PAWN_BLACK)
                    .filter(piece -> piece.getPlayerColor() != currentPlayerColor && piece.getPlayerColor() != EPlayerColor.NONE)
                    .forEach(piece -> enemyPositions.addAll(getActualMoves(piece.getPosition(), piece.getPlayerColor(), field, piece.getType())));
            // extra check for pawn
            field.stream()
                    .filter(piece -> piece.getPlayerColor() != currentPlayerColor && piece.getPlayerColor() != EPlayerColor.NONE)
                    .filter(piece -> piece.getType() == EChessPieces.PAWN_WHITE || piece.getType() == EChessPieces.PAWN_BLACK)
                    .forEach(piece -> enemyPositions.addAll(((Pawn) pawn).getDiagonalPositions(piece.getPosition(), piece.getPlayerColor(), field)));
            // extra check for enemy King
            ChessFieldButton enemyKing = field.stream()
                    .filter(piece -> piece.getPlayerColor() != currentPlayerColor)
                    .filter(piece -> piece.getType() == EChessPieces.KING_WHITE || piece.getType() == EChessPieces.KING_BLACK)
                    .findFirst().get();
            // remove the kings illegal moves
            result.removeAll(king.getActualPositions(enemyKing.getPosition(), enemyKing.getPlayerColor(), field));
            // reset type and color of buttons
            button.setType(tmp_type);
            button.setPlayerColor(tmp_color);
            currentButton.setType(king_type);
            currentButton.setPlayerColor(king_color);
        });
        // remove all other illegal moves
        result.removeAll(enemyPositions);
        return result;
    }
}
