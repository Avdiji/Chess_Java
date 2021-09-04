package Chess;

import Chess.Game.GUI.GameWindow;
import Chess.Game.Logic.Pieces.Movement.BishopMovement;
import Chess.Game.Logic.Pieces.Movement.IChessFieldMovement;
import Chess.Game.Logic.Pieces.Movement.KingMovement;
import Chess.Game.Logic.Pieces.Movement.KnightMovement;
import Chess.Game.Logic.Position;

public class Main_Game {

    public static void main(String... args) {
        GameWindow a = new GameWindow();

        Position pos1 = new Position('a', 1);
        Position pos2 = new Position('h', 2);
        Position pos3 = new Position('d', 4);

        IChessFieldMovement knight = new KnightMovement();

        System.out.println(knight.getPotentialPositions_white(pos1));
        System.out.println(knight.getPotentialPositions_white(pos1).size());

    }
}
