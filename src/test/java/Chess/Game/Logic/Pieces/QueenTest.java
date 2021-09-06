package Chess.Game.Logic.Pieces;

import Chess.Game.Logic.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Fitor Avdiji
 *
 * Tests for the Class Queen
 */
class QueenTest implements IChessPieceTest {

    IChessPiece queen;
    IChessPiece rook;
    IChessPiece bishop;

    @Test
    @Override
    public void test_getPotentialPositions() {
        Set<Position> expected = new HashSet<>();
        // test white corner left
        rook = new Rook(pos_white_cornerLeft, EChessPieces.ROOK_WHITE,chessField);
        bishop = new Bishop(pos_white_cornerLeft, EChessPieces.BISHOP_WHITE,chessField);
        queen = new Queen(pos_white_cornerLeft,EChessPieces.QUEEN_WHITE, chessField);
        expected.addAll(rook.getPotentialPositions());
        expected.addAll(bishop.getPotentialPositions());
        Assertions.assertEquals(expected, queen.getPotentialPositions());
        expected.clear();
        // test white corner right
        rook = new Rook(pos_white_cornerRight, EChessPieces.ROOK_WHITE,chessField);
        bishop = new Bishop(pos_white_cornerRight, EChessPieces.BISHOP_WHITE,chessField);
        queen = new Queen(pos_white_cornerRight,EChessPieces.QUEEN_WHITE, chessField);
        expected.addAll(rook.getPotentialPositions());
        expected.addAll(bishop.getPotentialPositions());
        Assertions.assertEquals(expected, queen.getPotentialPositions());
        expected.clear();
        // test middle
        rook = new Rook(pos_middle, EChessPieces.ROOK_WHITE,chessField);
        bishop = new Bishop(pos_middle, EChessPieces.BISHOP_WHITE,chessField);
        queen = new Queen(pos_middle,EChessPieces.QUEEN_WHITE, chessField);
        expected.addAll(rook.getPotentialPositions());
        expected.addAll(bishop.getPotentialPositions());
        Assertions.assertEquals(expected, queen.getPotentialPositions());
        expected.clear();

        // test white corner left
        rook = new Rook(pos_black_cornerLeft, EChessPieces.ROOK_BLACK,chessField);
        bishop = new Bishop(pos_black_cornerLeft, EChessPieces.BISHOP_BLACK,chessField);
        queen = new Queen(pos_black_cornerLeft,EChessPieces.QUEEN_BLACK, chessField);
        expected.addAll(rook.getPotentialPositions());
        expected.addAll(bishop.getPotentialPositions());
        Assertions.assertEquals(expected, queen.getPotentialPositions());
        expected.clear();
        // test white corner right
        rook = new Rook(pos_black_cornerRight, EChessPieces.ROOK_BLACK,chessField);
        bishop = new Bishop(pos_black_cornerRight, EChessPieces.BISHOP_BLACK,chessField);
        queen = new Queen(pos_black_cornerRight,EChessPieces.QUEEN_BLACK, chessField);
        expected.addAll(rook.getPotentialPositions());
        expected.addAll(bishop.getPotentialPositions());
        Assertions.assertEquals(expected, queen.getPotentialPositions());
        expected.clear();
        // test middle
        rook = new Rook(pos_middle, EChessPieces.ROOK_BLACK,chessField);
        bishop = new Bishop(pos_middle, EChessPieces.BISHOP_BLACK,chessField);
        queen = new Queen(pos_middle,EChessPieces.QUEEN_BLACK, chessField);
        expected.addAll(rook.getPotentialPositions());
        expected.addAll(bishop.getPotentialPositions());
        Assertions.assertEquals(expected, queen.getPotentialPositions());
    }

    @Test
    @Override
    public void test_canCapturePosition() {

        Position possible = new Position('a', 1);
        Position impossible = new Position('h', 1);

        queen = new Queen(pos_middle, EChessPieces.QUEEN_WHITE, chessField);
        Assertions.assertTrue(queen.canCapturePosition(possible));
        Assertions.assertFalse(queen.canCapturePosition(impossible));

        queen = new Queen(pos_middle, EChessPieces.QUEEN_BLACK, chessField);
        Assertions.assertTrue(queen.canCapturePosition(possible));
        Assertions.assertFalse(queen.canCapturePosition(impossible));
    }

    @Override
    public void test_canActuallyCapturePosition() {

    }
}