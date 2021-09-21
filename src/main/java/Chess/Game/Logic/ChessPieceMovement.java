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
import java.util.NoSuchElementException;
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
    private static final IChessPiece PAWN = new Pawn();
    private static final IChessPiece ROOK = new Rook();
    private static final IChessPiece BISHOP = new Bishop();
    private static final IChessPiece KNIGHT = new Knight();
    private static final IChessPiece KING = new King();
    private static final IChessPiece QUEEN = new Queen();
    private static final IChessPiece EMPTY = new Empty();

    /**
     * Default Constructor
     **/
    public ChessPieceMovement() {
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
    private static Set<Position> getActualMoves(final Position currentPosition, final EPlayerColor currentPlayerColor, final List<ChessFieldButton> field, final EChessPieces currentPiece) {
        return switch (currentPiece) {
            case PAWN_WHITE, PAWN_BLACK -> PAWN.getActualPositions(currentPosition, currentPlayerColor, field);
            case ROOK_WHITE, ROOK_BLACK -> ROOK.getActualPositions(currentPosition, currentPlayerColor, field);
            case BISHOP_WHITE, BISHOP_BLACK -> BISHOP.getActualPositions(currentPosition, currentPlayerColor, field);
            case KNIGHT_WHITE, KNIGHT_BLACK -> KNIGHT.getActualPositions(currentPosition, currentPlayerColor, field);
            case KING_WHITE, KING_BLACK -> KING.getActualPositions(currentPosition, currentPlayerColor, field);
            case QUEEN_WHITE, QUEEN_BLACK -> QUEEN.getActualPositions(currentPosition, currentPlayerColor, field);
            case EMPTY -> EMPTY.getActualPositions(currentPosition, currentPlayerColor, field);
        };
    }

    /**
     * Method sets enPassant for currentButton
     *
     * @param currentButton the currentButton
     * @param destination   destination of currentButton
     * @param field         field the game is being played in
     */
    public static void updateEnPassant(final ChessFieldButton currentButton, final ChessFieldButton destination, final List<ChessFieldButton> field) {
        ((Pawn) PAWN).enableEnPassant(currentButton, destination, field);
    }

    /**
     * The Method checks, whether the Player is in a check or not
     *
     * @param kingsPosition      current position of the King
     * @param currentPlayerColor current Color of the King
     * @param field              field the King is located in
     * @return
     */
    public static final boolean isCheck(final EPlayerColor currentPlayerColor, final List<ChessFieldButton> field) {
        AtomicBoolean result = new AtomicBoolean(false);
            Position kingsPosition = field.stream()
                    .filter(button -> button.getPlayerColor() == currentPlayerColor)
                    .filter(button -> button.getType() == EChessPieces.KING_WHITE || button.getType() == EChessPieces.KING_BLACK)
                    .map(ChessFieldButton::getPosition)
                    .findAny().get();

            field.stream()
                    .filter(button -> button.getPlayerColor() != currentPlayerColor)
                    .filter(button -> button.getPlayerColor() != EPlayerColor.NONE)
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
    public static final boolean isCheckMate(final EPlayerColor currentColor, final List<ChessFieldButton> field) {
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
    public static final boolean isStalemate(final EPlayerColor currentColor, final List<ChessFieldButton> field) {
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
    public static Set<Position> getSafePositions(final Position currentPosition, final EPlayerColor currentPlayerColor, final List<ChessFieldButton> field) {
        ChessFieldButton currentPiece = EMPTY.getCorrespondingButton(currentPosition, field);
        Set<Position> result = getActualMoves(currentPiece.getPosition(), currentPlayerColor, field, currentPiece.getType());

        if ((!isCheck(currentPlayerColor, field)) && (currentPiece.getType() == EChessPieces.KING_WHITE || currentPiece.getType() == EChessPieces.KING_BLACK))
            result.addAll(((King) KING).getPositionsRochade(currentPosition, currentPlayerColor, field));

        Set<Position> toRemove = new HashSet<>();

        result.forEach(position -> {
            ChessFieldButton capturedPiece = EMPTY.getCorrespondingButton(position, field);

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
        return result;
    }

    /**
     * Method checks, whether a rochade has been selected, and executes it if true
     *
     * @param capturedButton button that has been captured
     * @param markedButton   button that has executed the move
     * @return true if a rochade can be executed
     */
    public static boolean executeRochade(final ChessFieldButton capturedButton, final ChessFieldButton markedButton, final List<ChessFieldButton> field) {
        boolean result = false;
        int steps;

        steps = Math.abs(markedButton.getPosition().getRow() - capturedButton.getPosition().getRow());
        if ((markedButton.getType() == EChessPieces.KING_WHITE || markedButton.getType() == EChessPieces.KING_BLACK) && steps > 1) {
            result = true;

            int direction = Integer.compare(capturedButton.getPosition().getRow(), markedButton.getPosition().getRow());
            int column = markedButton.getPlayerColor() == EPlayerColor.WHITE ? 1 : 8;

            ChessFieldButton rook = direction == -1 ?
                    field.stream().filter(button -> button.getPosition().equals(new Position('A', column))).findAny().get() :
                    field.stream().filter(button -> button.getPosition().equals(new Position('H', column))).findAny().get();

            ChessFieldButton newRook = direction == -1 ?
                    field.stream().filter(button -> button.getPosition().equals(new Position(((char) (capturedButton.getPosition().getRow() + 1)), column))).findAny().get() :
                    field.stream().filter(button -> button.getPosition().equals(new Position(((char) (capturedButton.getPosition().getRow() - 1)), column))).findAny().get();

            capturedButton.setType(markedButton.getType());
            capturedButton.setPlayerColor(markedButton.getPlayerColor());

            newRook.setType(rook.getType());
            newRook.setPlayerColor(rook.getPlayerColor());

            markedButton.setType(EChessPieces.EMPTY);
            markedButton.setPlayerColor(EPlayerColor.NONE);

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
    public static ChessFieldButton findRedundantPiece(final ChessFieldButton captured, final List<ChessFieldButton> field) {
        ChessFieldButton result = null;
        // check whether the captured piece was a pawn or not (if it was a pawn a enPassant has been executed
        if (captured.getType() == EChessPieces.PAWN_WHITE || captured.getType() == EChessPieces.PAWN_BLACK) {
            Position toRemove = new Position(captured.getPosition().getRow(),
                    captured.getPlayerColor() == EPlayerColor.WHITE ?
                            captured.getPosition().getColumn() - 1 :
                            captured.getPosition().getColumn() + 1);

            ChessFieldButton actuallyCaptured = field.stream()
                    .filter(button -> button.getPosition().equals(toRemove))
                    .findAny().get();
            result = actuallyCaptured;
        }
        return result;
    }
}
