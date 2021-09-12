package Chess;

import Chess.Game.GUI.GameWindow;
import Chess.Game.Logic.ChessField;
import Chess.Game.Logic.Player.EPlayerColor;
import Chess.Game.Logic.Player.Player;

public class Main_Game {

    public static void main(String... args) {

        Player player1 = new Player(EPlayerColor.WHITE);
        Player player2 = new Player(EPlayerColor.BLACK);

        ChessField chessField = new ChessField(player1, player2);
//        chessField.initField("src/main/resources/initilization/initilization_default.csv");
        chessField.initField("src/main/resources/initilization/testInit.csv");

        GameWindow a = new GameWindow(chessField);
    }
}
