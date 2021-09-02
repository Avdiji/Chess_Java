package Chess.Game.Logic;

/**
 * @author Fitor Avdiji
 * <p>
 * class Represents a position on the Game Field from a1 - h8
 */
public class Position {

    /**
     * column of the current Position (j-value)
     **/
    private final char column;
    /**
     * row of the current Position (i - value)
     **/
    private final int row;

    /**
     * Constructor initializes {@link #column}
     * and {@link #row}, in this order.
     *
     * @param column
     * @param row
     * @throws IllegalArgumentException if column is not a alphanumerical,
     *                                  lowercase letter from a - h, or row is not a number from 1 - 8
     */
    public Position(final char column, final int row) {
        if (column < 'a' || column > 'h')
            throw new IllegalArgumentException("The column must be between a - h inclusive!");
        else if (row < 1 || row > 8)
            throw new IllegalArgumentException("The row must be between 1 - 8 inclusive!");

        this.column = column;
        this.row = row;
    }

    /**
     * Getter for {@link #column}
     *
     * @return column
     */
    public char getColumn() {
        return column;
    }

    /**
     * Getter for {@link #row}
     *
     * @return row
     */
    public int getRow() {
        return row;
    }

}
