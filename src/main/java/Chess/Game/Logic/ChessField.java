package Chess.Game.Logic;

import Chess.Game.Logic.Pieces.AChessPiece;
import Chess.Game.Logic.Pieces.Bishop;
import Chess.Game.Logic.Pieces.EChessPieces;
import Chess.Game.Logic.Pieces.Empty;
import Chess.Game.Logic.Pieces.IChessPiece;
import Chess.Game.Logic.Pieces.King;
import Chess.Game.Logic.Pieces.Knight;
import Chess.Game.Logic.Pieces.Pawn;
import Chess.Game.Logic.Pieces.Queen;
import Chess.Game.Logic.Pieces.Rook;
import Chess.Game.Logic.Player.EPlayerColor;
import Chess.Game.Logic.Player.Player;

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

    /**
     * field of the Chess Game
     **/
    private List<AChessPiece> field;
    /**
     * Players
     **/
    private Player[] players;
    /**
     * Color of the current Player
     **/
    private EPlayerColor currentTurn;
    /**
     * Listener for all the Pieces
     **/
    private ActionListener pieceListener;

    /**
     * Constructor
     */
    public ChessField() {
    }

    /**
     * Getter for {@link #field}
     *
     * @return field
     */
    public List<AChessPiece> getField() {
        return field;
    }

    /**
     * Method initializes {@link #pieceListener}
     */
    private void initPieceListener() {
        pieceListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                field.stream().filter(piece -> piece.isMarked() || piece.isEndangered()).forEach(piece -> piece.setMarked(false));
                field.stream().filter(piece -> piece.isMarked() || piece.isEndangered()).forEach(piece -> piece.setEndangered(false));

                ((AChessPiece) e.getSource()).setMarked(true);
                ((AChessPiece) e.getSource()).getPotentialPositions().stream()
                        .forEach(endangeredPos -> field.stream()
                                .filter(f -> f.getPosition().equals(endangeredPos))
                                .forEach(match -> match.setEndangered(true)));

            }
        };
    }

    /**
     * Method returns a Piece corresponding to the parameters
     *
     * @param pieceString String representing the piece
     * @param pos         position of the Piece to be returned
     * @param field       field the piece will be located in
     * @return A Piece corresponding to the parameters
     */
    public AChessPiece getPieceObject(final String pieceString, final Position pos) {
        char tmp_row = pos.getRow();
        int tmp_column = pos.getColumn();
        return switch (pieceString) {
            case "PAWN_WHITE" -> new Pawn(new Position(tmp_row, tmp_column), EChessPieces.PAWN_WHITE, this);
            case "PAWN_BLACK" -> new Pawn(new Position(tmp_row, tmp_column), EChessPieces.PAWN_BLACK, this);
            case "ROOK_WHITE" -> new Rook(new Position(tmp_row, tmp_column), EChessPieces.ROOK_WHITE, this);
            case "ROOK_BLACK" -> new Rook(new Position(tmp_row, tmp_column), EChessPieces.ROOK_BLACK, this);
            case "BISHOP_WHITE" -> new Bishop(new Position(tmp_row, tmp_column), EChessPieces.BISHOP_WHITE, this);
            case "BISHOP_BLACK" -> new Bishop(new Position(tmp_row, tmp_column), EChessPieces.BISHOP_BLACK, this);
            case "KNIGHT_WHITE" -> new Knight(new Position(tmp_row, tmp_column), EChessPieces.KNIGHT_WHITE, this);
            case "KNIGHT_BLACK" -> new Knight(new Position(tmp_row, tmp_column), EChessPieces.KNIGHT_BLACK, this);
            case "KING_WHITE" -> new King(new Position(tmp_row, tmp_column), EChessPieces.KING_WHITE, this);
            case "KING_BLACK" -> new King(new Position(tmp_row, tmp_column), EChessPieces.KING_BLACK, this);
            case "QUEEN_WHITE" -> new Queen(new Position(tmp_row, tmp_column), EChessPieces.QUEEN_WHITE, this);
            case "QUEEN_BLACK" -> new Queen(new Position(tmp_row, tmp_column), EChessPieces.QUEEN_BLACK, this);
            case "EMPTY" -> new Empty(new Position(tmp_row, tmp_column), EChessPieces.EMPTY, this);
            default -> throw new IllegalStateException("Unexpected value: " + pieceString);
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
                AChessPiece tmp_field = getPieceObject(values[2],new Position(tmp_row, tmp_column));
                tmp_field.initPiece(tmp_background);
                tmp_field.addActionListener(pieceListener);
                field.add(tmp_field);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
