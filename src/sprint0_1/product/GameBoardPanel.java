package sprint0_1.product;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class GameBoardPanel extends JPanel {
    public static final int GAME_BOARD_SIZE = 250;
    public static final int CELL_SIZE = GAME_BOARD_SIZE / 3;
    public static final int GRID_WIDTH = 8;//thickness
    public static final int GRID_WIDHT_HALF = GRID_WIDTH / 2;
    GameBoardPanel(){
        Border blackLine = BorderFactory.createLineBorder(Color.black, 8);

        setBorder(blackLine);
        setPreferredSize(new Dimension(GAME_BOARD_SIZE,GAME_BOARD_SIZE));
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGridLines(g);
    }

    private void drawGridLines(Graphics g){
        g.setColor(Color.BLACK);
        for (int row = 1; row < 3; row++) {
            g.fillRoundRect(0, CELL_SIZE * row - GRID_WIDHT_HALF,
                    GAME_BOARD_SIZE-1, GRID_WIDTH, GRID_WIDTH, GRID_WIDTH);
        }
        for (int col = 1; col < 3; col++) {
            g.fillRoundRect(CELL_SIZE * col - GRID_WIDHT_HALF, 0,
                    GRID_WIDTH, GAME_BOARD_SIZE-1, GRID_WIDTH, GRID_WIDTH);
        }

    }
}
