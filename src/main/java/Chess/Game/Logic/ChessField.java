package Chess.Game.Logic;

import Chess.Game.GUI.MainMenu;
import Chess.Game.Logic.Pieces.EChessPieces;
import Chess.Game.Logic.Player.EPlayerColor;
import Chess.Game.Logic.Player.Player;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Fitor Avdiji
 * <p>
 * Class contains the Whole Game Logic and can be used for the GUI
 */
public class ChessField {

    /** Field of the chess game **/
    private List<ChessFieldButton> field;

    /** Players **/
    private final Player player1;
    private final Player player2;

    /** Color to determine which players turn it is **/
    private EPlayerColor currentPlayerColor;

    /**
     * Constructor initializes:<br>
     * {@link #player1} <br>
     * {@link #player2} <br>
     *
     * @param player1 player1
     * @param player2 player2
     */
    public ChessField(final Player player1, final Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        currentPlayerColor = player1.getPlayerColor();
    }

    //GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER //
    //GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER //
    /**
     * Getter for {@link #field}
     *
     * @return field
     */
    public List<ChessFieldButton> getField() {
        return field;
    }

    /**
     * Getter for {@link #currentPlayerColor}
     *
     * @return currentPlayerColor
     */
    public EPlayerColor getCurrentPlayerColor() {
        return currentPlayerColor;
    }

    /**
     * Getter for {@link #player1}
     *
     * @return player1
     */
    public Player getPlayer1() {
        return player1;
    }

    /**
     * Getter for {@link #player2}
     *
     * @return player2
     */
    public Player getPlayer2() {
        return player2;
    }
    //GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER //
    //GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER GETTER //

    /**
     * Setter for {@link #currentPlayerColor}
     *
     * @param currentPlayerColor change the Color of the current Player to this param
     */
    public void setCurrentPlayerColor(final EPlayerColor currentPlayerColor) {
        this.currentPlayerColor = currentPlayerColor;
    }

    /** Method removes all disables every marking on the field **/
    public void removeMarkings() {
        field.stream().filter(ChessFieldButton::isMarked).forEach(piece -> piece.setMarked(false));
        field.stream().filter(ChessFieldButton::isEndangered).forEach(piece -> piece.setEndangered(false));
        field.stream().forEach(ChessFieldButton::renderPiece);
    }

    /** Method resets the Borders of all the fields **/
    public void resetBorders() {
        Border defaultBorder = new LineBorder(MainMenu.COLOR_FIELD_MARKED, 0);
        field.stream().forEach(button -> button.setBorder(defaultBorder));
    }

    /**
     * Method marks every endangered button, depending on the currently marked button
     *
     * @param currentButton Button that has been clicked
     */
    public void markButtons(ChessFieldButton currentButton) {
        removeMarkings();
        currentButton.setMarked(true);
        field.stream().filter(button -> ChessPieceMovement
                        .getSafePositions(
                                currentButton.getPosition(),
                                currentButton.getPlayerColor(),
                                field)
                        .contains(button.getPosition()))
                .forEach(match -> match.setEndangered(true));
    }

    /**
     * Method takes a Path with a database of fields, which is used for initialization
     *
     * @param initPath Path of the csv file with the infos for initialization
     */
    public void initField(final String initPath) {
        field = new ArrayList<>();
        InputStream is = getClass().getResourceAsStream(initPath);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"))) {

            String line;
            while ((line = br.readLine()) != null) {

                String values[] = line.split(";");
                char tmp_row = values[0].length() > 1 ? values[0].charAt(1) : values[0].charAt(0);
                int tmp_column = Integer.parseInt(values[1]);
                Color tmp_background = switch (values[3]) {
                    case "WHITE" -> MainMenu.COLOR_FIELD_WHITE;
                    case "BLACK" -> MainMenu.COLOR_FIELD_BLACK;
                    default -> throw new IllegalStateException("Unexpected value: " + values[3]);
                };
                ChessFieldButton tmp_button = new ChessFieldButton(new Position(tmp_row, tmp_column), EChessPieces.valueOf(values[2]), tmp_background);
                tmp_button.initPiece();
                field.add(tmp_button);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
