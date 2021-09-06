package Chess.Game.Logic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Fitor Avdiji
 * <p>
 * Testclass for Position
 */
class PositionTest {

    Position pos1 = new Position('A', 1);

    @Test
    public void test_PositionConstructor() {
        // IllegalArgumentException
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Position lessThan_a = new Position((char) ('A' - 1), 1);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Position greaterThan_h = new Position((char) ('H' + 1), 1);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Position lessThan_1 = new Position('A', 0);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Position greaterThan_8 = new Position('A', 9);
        });
    }

    @Test
    public void test_getRow() {
        Assertions.assertEquals('A', pos1.getRow());
    }

    @Test
    public void test_getColumn() {
        Assertions.assertEquals(1, pos1.getColumn());
    }

    @Test
    public void test_toString() {
        Assertions.assertEquals("(Row: A, Column: 1)", pos1.toString());
    }

    @Test
    public void test_equals() {
        //TODO
    }

    @Test
    public void test_hashcode() {
        //TODO
    }

}