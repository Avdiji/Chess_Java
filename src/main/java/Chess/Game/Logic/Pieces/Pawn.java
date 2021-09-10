package Chess.Game.Logic.Pieces;

import Chess.Game.Logic.ChessFieldButton;
import Chess.Game.Logic.Player.EPlayerColor;
import Chess.Game.Logic.Position;

import java.util.HashSet;
import java.util.List;
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
     * @throws IllegalArgumentException if playerColor is not black or white
     */
    private Set<Position> getPotentialPositions(final Position currentPosition, EPlayerColor playerColor) {
        if (playerColor != EPlayerColor.WHITE && playerColor != EPlayerColor.BLACK) {
            throw new IllegalArgumentException("The Color of the Pawn must be black or white!");
        }

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

    @Override
    public Set<Position> getActualPositions(Position currentPosition, EPlayerColor currentPlayerColor, List<ChessFieldButton> field) {
        Set<Position> result;
        Set<Position> potential = getPotentialPositions(currentPosition, currentPlayerColor);

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
        Set<ChessFieldButton> diagonal = field.stream()
                .filter(button -> button.getPosition().getRow() != currentPosition.getRow() && potential.contains(button.getPosition()))
                .collect(Collectors.toSet());
        diagonal.removeAll(diagonal.stream()
                .filter(button ->
                        button.getPlayerColor() == currentPlayerColor || button.getPlayerColor() == EPlayerColor.NONE)
                .collect(Collectors.toSet()));

        result = front.stream().map(ChessFieldButton::getPosition).collect(Collectors.toSet());
        result.addAll(diagonal.stream().map(ChessFieldButton::getPosition).collect(Collectors.toSet()));

        // check if there are any enPassant
        if(field.stream().filter(ChessFieldButton::enabledEnPassant).count() > 0){
            Position enPassant = field.stream()
                    .filter(button -> button.enabledEnPassant())
                    .filter(button ->
                            button.getPosition().getRow() == currentPosition.getRow() + 1 ||
                                    button.getPosition().getRow() == currentPosition.getRow() - 1)
                    .map(ChessFieldButton::getPosition).findFirst().get();

            result.add(new Position(enPassant.getRow(),
                    currentPlayerColor == EPlayerColor.WHITE ?
                            enPassant.getColumn() + 1 :
                            enPassant.getColumn() - 1));
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

        field.stream()
                .filter(button -> button.enabledEnPassant())
                .filter(button -> button.isMissedEnPassant())
                .forEach(button -> button.setEnPassant(false));

        int tmp_steps = Math.abs(currentPawn.getPosition().getColumn() - destination.getPosition().getColumn());
            Set<ChessFieldButton> potentialPawns = field.stream()
                    .filter(button -> button.getPlayerColor() != currentPawn.getPlayerColor() && button.getPlayerColor() != EPlayerColor.NONE)
                    .filter(button ->
                            button.getPosition().getRow() == destination.getPosition().getRow() + 1 ||
                                    button.getPosition().getRow() == destination.getPosition().getRow() - 1)
                    .filter(button -> button.getType() == EChessPieces.PAWN_WHITE || button.getType() == EChessPieces.PAWN_BLACK)
                    .filter(button -> button.getPosition().getColumn() == destination.getPosition().getColumn())
                    .collect(Collectors.toSet());

            if (tmp_steps > 1 && potentialPawns.size() > 0) {
                destination.setEnPassant(true);
                destination.setMissedEnPassant(true);
            }
        }

}
