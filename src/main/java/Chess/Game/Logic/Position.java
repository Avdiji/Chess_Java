package Chess.Game.Logic;

import java.util.Objects;

/**
 * @author Fitor Avdiji
 * <p>
 * class Represents a position on the Game Field from a1 - h8
 */
public record Position(char row, int column) {

    /**
     * Constructor initializes {@link #row}
     * and {@link #column}, in this order.
     *
     * @param column column of the position
     * @param row    row of the position
     * @throws IllegalArgumentException if column is not a alphanumerical,
     *                                  lowercase letter from a - h, or row is not a number from 1 - 8
     */
    public Position {
        if (row < 'A' || row > 'H')
            throw new IllegalArgumentException("The column must be between A - H inclusive!");
        else if (column < 1 || column > 8)
            throw new IllegalArgumentException("The row must be between 1 - 8 inclusive!");

    }

    /**
     * Getter for {@link #row}
     *
     * @return column
     */
    public char getRow() {
        return row;
    }

    /**
     * Getter for {@link #column}
     *
     * @return row
     */
    public int getColumn() {
        return column;
    }

    @Override
    public String toString() {
        return String.format("(Row: %c, Column: %d)", row, column);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return row == position.row && column == position.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
