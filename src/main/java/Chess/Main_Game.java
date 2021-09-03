package Chess;

import Chess.Game.Logic.Pieces.Movement.IChessFieldMovement;
import Chess.Game.Logic.Pieces.Movement.RookMovement;
import Chess.Game.Logic.Position;

public class Main_Game {

    public static void main(String... args) {
//        GameWindow a = new GameWindow();

        Position pos1 = new Position('a', 1);
        Position pos2 = new Position('h', 2);
        Position pos3 = new Position('a', 4);
        IChessFieldMovement pawn = new RookMovement();

        pawn.getPotentialPositions_white(pos1).forEach(System.out::println);
        System.out.println(pawn.getPotentialPositions_white(pos1).size());


    }
}
