package Chess;

import Chess.Game.GUI.ChessboardGUI.GameWindow;
import Chess.Game.GUI.Scoreboard;
import Chess.Game.Logic.ChessField;
import Chess.Game.Logic.Player.EPlayerColor;
import Chess.Game.Logic.Player.Player;

public class Main_Game {

    public static void main(String... args) {
        Player player1 = new Player(EPlayerColor.WHITE);
        Player player2 = new Player(EPlayerColor.BLACK);

        ChessField chessField = new ChessField(player1, player2);
//        chessField.initField("src/main/resources/initilization/initilization_default.csv");
//        chessField.initField("src/main/resources/initilization/testInit.csv");
//        chessField.initField("src/main/resources/initilization/checkmate.csv");
//        chessField.initField("src/main/resources/initilization/stalemate.csv");
//        chessField.initField("src/main/resources/initilization/rochade.csv");
//        chessField.initField("src/main/resources/initilization/pawnUpgrade.csv");

//        GameWindow a = new GameWindow(chessField);

        Scoreboard sb = new Scoreboard("White Gave up");
    }
}
