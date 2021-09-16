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
 * Class for the GUI of the Grave - White
 */
public class Grave_White extends JPanel implements IChessFrame {

    /**
     * The Grave containing the white pieces
     **/
    private JPanel grave_white;
    /**
     * Surrender button white player
     **/
    private JButton ff_white;

    /**
     * Text in ff_white
     **/
    private static final String FORFEIT_WHITE = "Forfeit White";

    /**
     * Constructor
     */
    public Grave_White() {
        initMainFrame();
        initComponents();
        addComponents();
    }

    /**
     * Getter for {@link #grave_white}
     * @return grave_white
     */
    public JPanel getPanel_GraveButtons(){
        return grave_white;
    }

    @Override
    public void initMainFrame() {
        this.setBackground(COLOR_BACKGROUND);
        this.setBorder(new EmptyBorder(
                GameWindow.MARGIN_BORDER_GRAVE[0],
                GameWindow.MARGIN_BORDER_GRAVE[3],
                GameWindow.MARGIN_BORDER_GRAVE[2],
                GameWindow.MARGIN_BORDER_GRAVE[1]));
        this.setLayout(new BorderLayout());
    }

    @Override
    public void initComponents() {
        grave_white = new JPanel();
        grave_white.setLayout(new GridLayout(8, 2));
        grave_white.setBackground(IChessFrame.COLOR_BACKGROUND);
        for (int i = 0; i < 8 * 2; ++i) {
            ChessFieldButton tmp_button = new ChessFieldButton(GameWindow.idlePosition, EChessPieces.EMPTY, IChessPiece.COLOR_FIELD_BLACK);
            tmp_button.initPiece();
            grave_white.add(tmp_button);
        }

        ff_white = new JButton(FORFEIT_WHITE);
        ff_white.setPreferredSize(new Dimension(2 * IChessPiece.SIZE_FIELD, IChessPiece.SIZE_FIELD / 2));
        ff_white.setFont(new Font(FONT, FONT_TYPE, SIZE_BUTTON_LABEL));
        ff_white.setBackground(COLOR_BUTTON_BACKGROUND);
        ff_white.setForeground(COLOR_LABEL);
    }

    @Override
    public void addComponents() {
        this.add(grave_white, BorderLayout.CENTER);
        this.add(ff_white, BorderLayout.SOUTH);
    }
}
