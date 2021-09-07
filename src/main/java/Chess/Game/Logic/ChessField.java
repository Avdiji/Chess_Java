package Chess.Game.Logic;

import Chess.Game.Logic.Pieces.EChessPieces;
import Chess.Game.Logic.Pieces.IChessPiece;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Fitor Avdiji
 * <p>
 * Class contains the Whole Game Logic and can be used for the GUI
 */
public class ChessField {

    /** field of the Chess Game **/
    private List<ChessFieldButton> field;
    /** Movements of the Pieces **/
    private ChessPieceMovement movement;
    /** Listener for all the Pieces **/
    private ActionListener pieceListener;

    /**
     * Constructor initializes {@link #movement}
     */
    public ChessField() {
        movement = new ChessPieceMovement();
    }

    /**
     * Getter for {@link #field}
     * @return field
     */
    public List<ChessFieldButton> getField() {
        return field;
    }

    //TODO
    private void swapPieces(ChessFieldButton currentButton) {
        ChessFieldButton markedButton = field.stream().filter(ChessFieldButton::isMarked).findAny().get();
        EChessPieces tmp = markedButton.getType();

        markedButton.setType(currentButton.getType());
        currentButton.setType(tmp);

        field.stream().filter(ChessFieldButton::isMarked).forEach(piece -> piece.setMarked(false));
        field.stream().filter(ChessFieldButton::isEndangered).forEach(piece -> piece.setEndangered(false));
        field.forEach(ChessFieldButton::renderPiece);
    }

    /**
     * Method marks every endangered button, depending on the currently marked button
     *
     * @param currentButton Button that has been clicked
     */
    private void markButtons(ChessFieldButton currentButton) {
        field.stream().filter(ChessFieldButton::isMarked).forEach(piece -> piece.setMarked(false));
        field.stream().filter(ChessFieldButton::isEndangered).forEach(piece -> piece.setEndangered(false));
        currentButton.setMarked(true);
        field.stream().filter(button -> movement
                .getPotentialMoves(
                        currentButton.getPosition(),
                        currentButton.getType())
                .contains(button.getPosition()))
                .forEach(match -> match.setEndangered(true));
    }

    /** Method initializes {@link #pieceListener} */
    private void initPieceListener() {
        pieceListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChessFieldButton currentButton = (ChessFieldButton) e.getSource();
                if (currentButton.isEndangered()) {
                    swapPieces(currentButton);
                } else {
                    markButtons(currentButton);
                }
            }
        };
    }

    /**
     * Method takes a Path with a database of fields, which is used for initialization
     *
     * @param initPath Path of the csv file with the infos for initialization
     */
    public void initField(final String initPath) {
        initPieceListener();

        field = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(initPath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String values[] = line.split(";");
                char tmp_row = values[0].length() > 1 ? values[0].charAt(1) : values[0].charAt(0);
                int tmp_column = Integer.parseInt(values[1]);

                Color tmp_background = switch (values[3]) {
                    case "WHITE" -> IChessPiece.COLOR_FIELD_WHITE;
                    case "BLACK" -> IChessPiece.COLOR_FIELD_BLACK;
                    default -> throw new IllegalStateException("Unexpected value: " + values[3]);
                };
                ChessFieldButton tmp_button = new ChessFieldButton(new Position(tmp_row, tmp_column), EChessPieces.valueOf(values[2]), tmp_background);
                tmp_button.initPiece();
                tmp_button.addActionListener(pieceListener);
                field.add(tmp_button);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
