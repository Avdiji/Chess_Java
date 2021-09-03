package Chess.Game.Logic.Pieces.Rules;

import Chess.Game.Logic.Pieces.EChessPieces;
import Chess.Game.Logic.Position;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Fitor Avdiji
 * <p>
 * Class implements every move the pawn could potentially execute
 */
public class PawnRules implements IChessFieldRules {

    @Override
    public Set<Position> getPotentialPositions_white(final Position currentPosition) {
        Set<Position> result = new HashSet<>();

        if (currentPosition.getRow() != 8)
            result.add(new Position(currentPosition.getRow(), currentPosition.getColumn() + 1));
        if (currentPosition.getColumn() == 2)
            result.add(new Position(currentPosition.getRow(), currentPosition.getColumn() + 2));
        if (currentPosition.getRow() == 'a')
            result.add(new Position((char) (currentPosition.getRow() + 1), currentPosition.getColumn() + 1));
        else if (currentPosition.getRow() == 'h')
            result.add(new Position((char) (currentPosition.getRow() - 1), currentPosition.getColumn() + 1));
        else {
            result.add(new Position((char) (currentPosition.getRow() + 1), currentPosition.getColumn() + 1));
            result.add(new Position((char) (currentPosition.getRow() - 1), currentPosition.getColumn() + 1));
        }
        return result;
    }

    @Override
    public Set<Position> getPotentialPositions_black(Position currentPosition) {
        Set<Position> result = new HashSet<>();

        if (currentPosition.getColumn() != 1)
            result.add(new Position(currentPosition.getRow(), currentPosition.getColumn() - 1));
        if (currentPosition.getColumn() == 7)
            result.add(new Position(currentPosition.getRow(), currentPosition.getColumn() - 2));
        if (currentPosition.getRow() == 'a')
            result.add(new Position((char) (currentPosition.getRow() + 1), currentPosition.getColumn() - 1));
        else if (currentPosition.getRow() == 'h')
            result.add(new Position((char) (currentPosition.getRow() - 1), currentPosition.getColumn() - 1));
        else {
            result.add(new Position((char) (currentPosition.getRow() + 1), currentPosition.getColumn() - 1));
            result.add(new Position((char) (currentPosition.getRow() - 1), currentPosition.getColumn() - 1));
        }
        return result;
    }

    @Override
    public boolean canOccupyPosition(Position currentPosition, EChessPieces currentPiece, Position target) {
        if (currentPiece != EChessPieces.PAWN_WHITE || currentPiece != EChessPieces.PAWN_BLACK) {
            throw new IllegalArgumentException("currentPiece must be a Pawn!");
        }
        boolean result = false;
        switch (currentPiece) {
            case PAWN_WHITE -> result = getPotentialPositions_white(currentPosition).contains(target);
            case PAWN_BLACK -> result = getPotentialPositions_black(currentPosition).contains(target);
        }
        return result;
    }
}
