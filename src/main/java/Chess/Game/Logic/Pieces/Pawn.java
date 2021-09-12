package Chess.Game.Logic.Pieces;

import Chess.Game.Logic.ChessFieldButton;
import Chess.Game.Logic.Player.EPlayerColor;
import Chess.Game.Logic.Position;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Fitor Avdiji
 * <p>
 * Class represents a Pawn-Piece
 */
public class Pawn implements IChessPiece {

    /**
     * Default Constructor
     **/
    public Pawn() {
    }

    /**
     * Method returns a Set of all moves the Pawn could theoretically execute
     *
     * @param currentPosition current Position of the Pawn
     * @return All Moves the pawn could theoretically execute
     */
    private Set<Position> getPotentialPositions(final Position currentPosition, EPlayerColor playerColor, List<ChessFieldButton> field) {
        Set<Position> result = new HashSet<>();

        int directionX[] = {0, 1, -1};
        int directionY[] = playerColor == EPlayerColor.WHITE ? new int[]{1, 1, 1} : new int[]{-1, -1, -1};
        int base = playerColor == EPlayerColor.WHITE ? 2 : 7;
        int directionFromBase = playerColor == EPlayerColor.WHITE ? 2 : -2;

        if (currentPosition.getColumn() == base)
            result.add(new Position(currentPosition.getRow(), currentPosition.getColumn() + directionFromBase));

        for (int i = 0; i < directionX.length; ++i) {

            char posX = (char) (currentPosition.getRow() + directionX[i]);
            int posY = currentPosition.getColumn() + directionY[i];
            try {
                result.add(new Position(posX, posY));
            } catch (IllegalArgumentException e) {
                continue;
            }
        }
        return result;
    }


    /**
     * The Method gets all the diagonal Position the Pawn could capture (if there was an enemy piece)
     *
     * @param currentPosition    current Position of the Pawn
     * @param currentPlayerColor current Color of the Player
     * @param field              field the Pawn is currently located in
     * @return a Set of the Diagonal Buttons the Pawn could capture
     */
    public Set<Position> getDiagonalPositions(Position currentPosition, EPlayerColor currentPlayerColor, List<ChessFieldButton> field) {
        Set<Position> potential = getPotentialPositions(currentPosition, currentPlayerColor, field);
        Set<Position> result = field.stream()
                .filter(button -> button.getPosition().getRow() != currentPosition.getRow() && potential.contains(button.getPosition()))
                .map(ChessFieldButton::getPosition)
                .collect(Collectors.toSet());

        return result;
    }

    @Override
    public Set<Position> getActualPositions(Position currentPosition, EPlayerColor currentPlayerColor, List<ChessFieldButton> field) {
        Set<Position> result = new HashSet<>();
        Set<Position> potential = getPotentialPositions(currentPosition, currentPlayerColor, field);

        // the Positions in front of the Pawn
        Set<ChessFieldButton> front = field.stream()
                .filter(button -> button.getPosition().getRow() == currentPosition.getRow() && potential.contains(button.getPosition()))
                .collect(Collectors.toSet());
        front.removeAll(front.stream()
                .filter(button -> button.getPlayerColor() != EPlayerColor.NONE)
                .collect(Collectors.toSet()));
        if (front.size() == 1) {
            int diff = Math.abs(front.stream().findFirst().get().getPosition().getColumn() - currentPosition.getColumn());
            if (diff > 1) {
                front.clear();
            }
        }
        // the Positions diagonal to the Pawn
        Set<ChessFieldButton> diagonal = field.stream()
                .filter(button -> button.getPosition().getRow() != currentPosition.getRow() && potential.contains(button.getPosition()))
                .collect(Collectors.toSet());
        diagonal.removeAll(diagonal.stream()
                .filter(button ->
                        button.getPlayerColor() == currentPlayerColor || button.getPlayerColor() == EPlayerColor.NONE)
                .collect(Collectors.toSet()));

        result = front.stream().map(ChessFieldButton::getPosition).collect(Collectors.toSet());
        result.addAll(diagonal.stream().map(ChessFieldButton::getPosition).collect(Collectors.toSet()));

        // The En Passant
        Position enPassant = findEnPassant(currentPosition, currentPlayerColor, field);
        if (findEnPassant(currentPosition, currentPlayerColor, field) != null) {
            result.add(enPassant);
        }
        return result;
    }

    /**
     * The Method finds a Position the Pawn on currentPosition can move to, by using an En Passant.
     * If there is no such position the Method will return null
     *
     * @param currentPosition    current Position of the Pawn
     * @param currentPlayerColor current Color of the Pawn
     * @param field              field the Pawn is located in
     * @return a Position the Pawn can move to using the en Passant or null
     */
    private Position findEnPassant(final Position currentPosition, final EPlayerColor currentPlayerColor, final List<ChessFieldButton> field) {
        Position result = null;
        if (field.stream().filter(ChessFieldButton::enabledEnPassant).count() > 0) {
            try {
                // find the Field that will be captured after executing the En Passant from the current Position, if possible
                Position enPassant = field.stream()
                        .filter(button -> button.enabledEnPassant())
                        .filter(button -> button.getPosition().getColumn() == currentPosition.getColumn())
                        .filter(button ->
                                button.getPosition().getRow() == currentPosition.getRow() + 1 ||
                                        button.getPosition().getRow() == currentPosition.getRow() - 1)
                        .map(ChessFieldButton::getPosition).findFirst().get();
                // find the Actual button, that will be captured after executing the EnPassant
                result = new Position(enPassant.getRow(),
                        currentPlayerColor == EPlayerColor.WHITE ?
                                enPassant.getColumn() + 1 :
                                enPassant.getColumn() - 1);
            } catch (NoSuchElementException e) {
            }
        }
        return result;
    }

    /**
     * Method sets the value enPassant of currentPawn to true,
     * if currentPawn enables another pawn on the field to execute the enPassant
     *
     * @param currentPawn current Pawn
     * @param destination destination of the current Pawn
     * @param field       field the game is being played in
     */
    public void enableEnPassant(final ChessFieldButton currentPawn, final ChessFieldButton destination, final List<ChessFieldButton> field) {
        // disable every enPassant that currently exists
        field.stream()
                .filter(button -> button.enabledEnPassant())
                .filter(button -> button.isMissedEnPassant())
                .forEach(button -> button.setEnPassant(false));

        // save amount of steps travelled by the currentPawn
        int tmp_steps = Math.abs(currentPawn.getPosition().getColumn() - destination.getPosition().getColumn());
        // find all the Pawns that could execute the EnPassant after currentPawn moved to destination
        Set<ChessFieldButton> potentialPawns = field.stream()
                .filter(button -> button.getPlayerColor() != currentPawn.getPlayerColor() && button.getPlayerColor() != EPlayerColor.NONE)
                .filter(button ->
                        button.getPosition().getRow() == destination.getPosition().getRow() + 1 ||
                                button.getPosition().getRow() == destination.getPosition().getRow() - 1)
                .filter(button -> button.getType() == EChessPieces.PAWN_WHITE || button.getType() == EChessPieces.PAWN_BLACK)
                .filter(button -> button.getPosition().getColumn() == destination.getPosition().getColumn())
                .collect(Collectors.toSet());
        // if there are any Pawns that can executed the En Passant within the next move, and the currentPawn has moved two steps forward, enable En Passant
        if (tmp_steps > 1 && potentialPawns.size() > 0) {
            destination.setEnPassant(true);
            destination.setMissedEnPassant(true); // this will turn off the EnPassant as soon as the next Move has been executed
        }
    }

}
