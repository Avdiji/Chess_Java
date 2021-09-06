package Chess;

import Chess.Game.GUI.GameWindow;
import Chess.Game.Logic.ChessField;


public class Main_Game {

    public static void main(String... args) {
        ChessField chessField = new ChessField();
        chessField.initField("src/main/resources/initilization/initilization_default.csv");
        GameWindow a = new GameWindow(chessField);


    }
}
