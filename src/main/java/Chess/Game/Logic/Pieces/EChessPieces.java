package Chess.Game.Logic.Pieces;

/**
 * @author Fitor Avdiji
 * <p>
 * Enum contains all the pieces with each color (12 Pieces) and their corresponding path
 */
public enum EChessPieces {

    PAWN_WHITE("/Pieces/White/pawn_white.png"),
    PAWN_BLACK("/Pieces/Black/pawn_black.png"),
    ROOK_WHITE("/Pieces/White/rook_white.png"),
    ROOK_BLACK("/Pieces/Black/rook_black.png"),
    BISHOP_WHITE("/Pieces/White/bishop_white.png"),
    BISHOP_BLACK("/Pieces/Black/bishop_black.png"),
    KNIGHT_WHITE("/Pieces/White/knight_white.png"),
    KNIGHT_BLACK("/Pieces/Black/knight_black.png"),
    QUEEN_WHITE("/Pieces/White/queen_white.png"),
    QUEEN_BLACK("/Pieces/Black/queen_black.png"),
    KING_WHITE("/Pieces/White/king_white.png"),
    KING_BLACK("/Pieces/Black/king_black.png"),
    EMPTY("/Pieces/empty.png");

    /** variable contains the path to the corresponding piece **/
    private final String path;

    /**
     * Constructor receives a String to initialize {@link #path}
     *
     * @param path path of the image of this chess piece
     */
    EChessPieces(final String path) {
        this.path = path;
    }

    /**
     * Getter for {@link #path}
     *
     * @return path path of the image of this chess piece
     */
    public String getPath() {
        return path;
    }
}
