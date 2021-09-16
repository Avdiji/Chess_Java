package Chess.Game.GUI.ChessboardGUI;

import Chess.Game.GUI.IChessFrame;
import Chess.Game.Logic.ChessFieldButton;
import Chess.Game.Logic.Pieces.EChessPieces;
import Chess.Game.Logic.Pieces.IChessPiece;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

/**
 * @author Fitor Avdiji
 * <p>
 * Class for the GUI of the Grave - Black
 */
public class Grave_Black extends JPanel implements IChessFrame {

    /**
     * The Grave containing the white pieces
     **/
    private JPanel grave_black;
    /**
     * Surrender button black player
     **/
    private JButton ff_black;

    /**
     * Text in ff_black
     **/
    private static final String FORFEIT_BLACK = "Forfeit Black";

    /**
     * Constructor
     */
    public Grave_Black() {
        initMainFrame();
        initComponents();
        addComponents();
    }

    /**
     * Getter for {@link #grave_black}
     * @return grave_black
     */
    public JPanel getPanel_GraveButtons(){
        return grave_black;
    }

    @Override
    public void initMainFrame() {
        this.setBackground(COLOR_BACKGROUND);
        this.setBorder(new EmptyBorder(
                GameWindow.MARGIN_BORDER_GRAVE[0],
                GameWindow.MARGIN_BORDER_GRAVE[1],
                GameWindow.MARGIN_BORDER_GRAVE[2],
                GameWindow.MARGIN_BORDER_GRAVE[3]));
        this.setLayout(new BorderLayout());
    }

    @Override
    public void initComponents() {
        grave_black = new JPanel();
        grave_black.setLayout(new GridLayout(8, 2));
        grave_black.setBackground(IChessFrame.COLOR_BACKGROUND);
        for (int i = 0; i < 8 * 2; ++i) {
            ChessFieldButton tmp_button = new ChessFieldButton(GameWindow.idlePosition, EChessPieces.EMPTY, IChessPiece.COLOR_FIELD_WHITE);
            tmp_button.initPiece();
            grave_black.add(tmp_button);
        }

        ff_black = new JButton(FORFEIT_BLACK);
        ff_black.setPreferredSize(new Dimension(2 * IChessPiece.SIZE_FIELD, IChessPiece.SIZE_FIELD / 2));
        ff_black.setFont(new Font(FONT, FONT_TYPE, SIZE_BUTTON_LABEL));
        ff_black.setBackground(COLOR_BUTTON_BACKGROUND);
        ff_black.setForeground(COLOR_LABEL);
    }

    @Override
    public void addComponents() {
        this.add(grave_black, BorderLayout.CENTER);
        this.add(ff_black, BorderLayout.SOUTH);
    }
}
