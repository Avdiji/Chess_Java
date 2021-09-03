package Chess.Game.Logic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Fitor Avdiji
 * <p>
 * Testclass for Position
 */
class PositionTest {

    Position pos1 = new Position('a', 1);

    @Test
    public void test_PositionConstructor() {
        // IllegalArgumentException
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Position lessThan_a = new Position((char) ('a' - 1), 1);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Position greaterThan_h = new Position((char) ('h' + 1), 1);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Position lessThan_1 = new Position('a', 0);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Position greaterThan_8 = new Position('a', 9);
        });
    }

    @Test
    public void test_getRow() {
        Assertions.assertEquals('a', pos1.getRow());
    }

    @Test
    public void test_getColumn() {
        Assertions.assertEquals(1, pos1.getColumn());
    }

    @Test
    public void test_toString() {
        Assertions.assertEquals("(Row: a, Column: 1)", pos1.toString());
    }

}