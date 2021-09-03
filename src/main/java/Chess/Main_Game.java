package Chess;

import Chess.Game.GUI.GameWindow;
import Chess.Game.Logic.Pieces.EChessPieces;
import Chess.Game.Logic.Pieces.Rules.IChessFieldRules;
import Chess.Game.Logic.Pieces.Rules.PawnRules;
import Chess.Game.Logic.Position;

public class Main_Game {

    public static void main(String... args){
//        GameWindow a = new GameWindow();

        Position pos1 = new Position('a',2);
        Position pos2 = new Position('h', 2);
        Position pos3 = new Position('d', 4);
        IChessFieldRules pawn = new PawnRules();

        System.out.println(pawn.getPotentialPositions_white(pos3));


    }
}
