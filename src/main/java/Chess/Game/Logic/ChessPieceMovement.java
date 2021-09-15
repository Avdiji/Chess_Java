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
import java.util.concurrent.atomic.AtomicBoolean;

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
    private Set<Position> getActualMoves(final Position currentPosition, final EPlayerColor currentPlayerColor, final List<ChessFieldButton> field, final EChessPieces currentPiece) {
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
     * The Method checks, whether the Player is in a check or not
     *
     * @param kingsPosition      current position of the King
     * @param currentPlayerColor current Color of the King
     * @param field              field the King is located in
     * @return
     */
    public boolean isCheck(final EPlayerColor currentPlayerColor, final List<ChessFieldButton> field) {
        AtomicBoolean result = new AtomicBoolean(false);
        Position kingsPosition = field.stream()
                .filter(button -> button.getPlayerColor() == currentPlayerColor)
                .filter(button -> button.getType() == EChessPieces.KING_WHITE || button.getType() == EChessPieces.KING_BLACK)
                .map(ChessFieldButton::getPosition)
                .findAny().get();

        field.stream()
                .filter(button -> button.getPlayerColor() != currentPlayerColor)
                .filter(button -> button.getPlayerColor() != EPlayerColor.NONE)
                .filter(button -> button.getType() != EChessPieces.KING_WHITE && button.getType() != EChessPieces.KING_BLACK)
                .forEach(button -> {
                    Set<Position> moves = getActualMoves(button.getPosition(), button.getPlayerColor(), field, button.getType());
                    if (moves.contains(kingsPosition))
                        result.set(true);
                });
        return result.get();
    }

    /**
     * The Method calculates, whether the Player with the currentColor has lost (checkmate)
     *
     * @param currentColor current Color of the Player
     * @param field        field the Game is being played in
     * @return true if checkmate
     */
    public boolean isCheckMate(final EPlayerColor currentColor, final List<ChessFieldButton> field) {
        Set<Position> allPossibleMoves = new HashSet<>();
        field.stream()
                .filter(button -> button.getPlayerColor() == currentColor)
                .forEach(button -> allPossibleMoves.addAll(getSafePositions(button.getPosition(), currentColor, field)));

        return (allPossibleMoves.size() == 0 && isCheck(currentColor, field));
    }

    /**
     * The Method checks, whether the Game is a draw
     *
     * @param currentColor current Color of the Player
     * @param field        field the game is being played in
     * @return true if stalemate
     */
    public boolean isStalemate(final EPlayerColor currentColor, final List<ChessFieldButton> field) {
        Set<Position> allPossibleMoves = new HashSet<>();
        field.stream()
                .filter(button -> button.getPlayerColor() == currentColor)
                .forEach(button -> allPossibleMoves.addAll(getSafePositions(button.getPosition(), currentColor, field)));

        int amountOfPieces = (int) field.stream()
                .filter(button -> button.getType() != EChessPieces.KING_WHITE && button.getType() != EChessPieces.KING_BLACK)
                .filter(button -> button.getPlayerColor() != EPlayerColor.NONE)
                .count();

        return (allPossibleMoves.size() == 0 && !isCheck(currentColor, field) || amountOfPieces == 0);
    }

    /**
     * The Method, returns a Set of Positions, the Piece can move to
     *
     * @param currentPosition    current Position of the Piece
     * @param currentPlayerColor current Color of the Piece
     * @param field              field the Piece is located in
     * @return a Set of save positions the piece can move to
     */
    public Set<Position> getSafePositions(final Position currentPosition, final EPlayerColor currentPlayerColor, final List<ChessFieldButton> field) {
        ChessFieldButton currentPiece = field.stream().filter(button -> button.getPosition().equals(currentPosition)).findAny().get();
        Set<Position> result = getActualMoves(currentPiece.getPosition(), currentPlayerColor, field, currentPiece.getType());
        Set<Position> toRemove = new HashSet<>();

        if (!(currentPiece.getType() == EChessPieces.KING_WHITE || currentPiece.getType() == EChessPieces.KING_BLACK)) {
        result.forEach(position -> {

            ChessFieldButton capturedPiece = field.stream().filter(button -> button.getPosition().equals(position)).findAny().get();

            EChessPieces capturedType = capturedPiece.getType();
            EPlayerColor capturedColor = capturedPiece.getPlayerColor();
            EChessPieces currentType = currentPiece.getType();

            currentPiece.setType(EChessPieces.EMPTY);
            currentPiece.setPlayerColor(EPlayerColor.NONE);

            capturedPiece.setType(currentType);
            capturedPiece.setPlayerColor(currentPlayerColor);

            if (isCheck(currentPlayerColor, field)) {
                toRemove.add(position);
            }

            currentPiece.setType(currentType);
            currentPiece.setPlayerColor(currentPlayerColor);
            capturedPiece.setType(capturedType);
            capturedPiece.setPlayerColor(capturedColor);
        });
        result.removeAll(toRemove);
        } else {
            result = getKingsSaveMoves(currentPosition, currentPlayerColor, field);
        }
        return result;
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
        //TODO: cleanup this mess
        Set<Position> result = king.getActualPositions(currentPosition, currentPlayerColor, field);

        if(!isCheck(currentPlayerColor, field))
            result.addAll(((King)king).getPositionsRochade(currentPosition, currentPlayerColor, field));

        Set<Position> enemyPositions = new HashSet<>();

        ChessFieldButton currentButton = field.stream().filter(button -> button.getPosition().equals(currentPosition)).findAny().get();
        // for each ChessFieldButton the king could reach
        field.stream()
                .filter(button -> king.getActualPositions(currentPosition, currentPlayerColor, field).contains(button.getPosition()))
                .forEach(button -> {
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

    /**
     * Method checks, whether a rochade has been selected, and executes it if true
     *
     * @param capturedButton button that has been captured
     * @param markedButton   button that has executed the move
     * @return true if a rochade can be executed
     */
    public boolean executeRochade(final ChessFieldButton capturedButton, final ChessFieldButton markedButton, final List<ChessFieldButton> field) {
        boolean result = false;
        boolean rightRochade;
        int steps;

        steps = Math.abs(markedButton.getPosition().getRow() - capturedButton.getPosition().getRow());
        if ((markedButton.getType() == EChessPieces.KING_WHITE || markedButton.getType() == EChessPieces.KING_BLACK) && steps > 1) {
            result = true;

            capturedButton.setType(markedButton.getType());
            capturedButton.setPlayerColor(markedButton.getPlayerColor());
            markedButton.setType(EChessPieces.EMPTY);
            markedButton.setPlayerColor(EPlayerColor.NONE);

            rightRochade = markedButton.getPosition().getRow() < capturedButton.getPosition().getRow();
            ChessFieldButton rook;
            ChessFieldButton newRook;
            Position newRookPosition;

            if (rightRochade) {
                rook = field.stream()
                        .filter(button -> button.getPlayerColor() == capturedButton.getPlayerColor())
                        .filter(button -> button.getPosition().getRow() > markedButton.getPosition().getRow())
                        .filter(button -> button.getType() == EChessPieces.ROOK_WHITE || button.getType() == EChessPieces.ROOK_BLACK)
                        .findAny().get();
                newRookPosition = new Position((char) (capturedButton.getPosition().getRow() - 1), capturedButton.getPosition().getColumn());
                newRook = field.stream().filter(button -> button.getPosition().equals(newRookPosition)).findAny().get();
            } else {
                rook = field.stream()
                        .filter(button -> button.getPlayerColor() == capturedButton.getPlayerColor())
                        .filter(button -> button.getPosition().getRow() < markedButton.getPosition().getRow())
                        .filter(button -> button.getType() == EChessPieces.ROOK_WHITE || button.getType() == EChessPieces.ROOK_BLACK)
                        .findAny().get();
                newRookPosition = new Position((char) (capturedButton.getPosition().getRow() + 1), capturedButton.getPosition().getColumn());
                newRook = field.stream().filter(button -> button.getPosition().equals(newRookPosition)).findAny().get();
            }
            newRook.setType(rook.getType());
            newRook.setPlayerColor(rook.getPlayerColor());
            rook.setType(EChessPieces.EMPTY);
            rook.setPlayerColor(EPlayerColor.NONE);
        }
        return result;
    }

    /**
     * The Method removes the redundant Pawn in case of an enPassant
     *
     * @param captured captured button
     */
    public void removeRedundantPiece(final ChessFieldButton captured, final List<ChessFieldButton> field) {
        // check whether the captured piece was a pawn or not (if it was a pawn a enPassant has been executed
        if (captured.getType() == EChessPieces.PAWN_WHITE || captured.getType() == EChessPieces.PAWN_BLACK) {
            Position toRemove = new Position(captured.getPosition().getRow(),
                    captured.getPlayerColor() == EPlayerColor.WHITE ?
                            captured.getPosition().getColumn() - 1 :
                            captured.getPosition().getColumn() + 1);

            ChessFieldButton actuallyCaptured = field.stream()
                    .filter(button -> button.getPosition().equals(toRemove))
                    .findAny().get();
            actuallyCaptured.setType(EChessPieces.EMPTY);
            actuallyCaptured.setPlayerColor(EPlayerColor.NONE);
        }
    }
}
