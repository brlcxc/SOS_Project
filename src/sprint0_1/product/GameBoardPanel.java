package sprint0_1.product;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameBoardPanel extends JPanel {
    public static final int GAME_BOARD_SIZE = 250;
    int cellNum = 16;
    //cell size is not matching with the click
    //maybe I need to do math to find a better way to ditribute pizels for the cell size
    //I can spread out pixels or increase for outside or...
    int CELL_SIZE = GAME_BOARD_SIZE / cellNum;
    public static final int GRID_WIDTH = 1;//thickness
    //I want this number to scale with size
    public static final int GRID_WIDHT_HALF = GRID_WIDTH / 2;
    GameBoardPanel(){
        Border blackLine = BorderFactory.createLineBorder(Color.black, 1);

        setBorder(blackLine);
        setPreferredSize(new Dimension(GAME_BOARD_SIZE,GAME_BOARD_SIZE));

        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
//                if (game.getGameState() == GameState.PLAYING) {
                    int rowSelected = e.getY() / CELL_SIZE;
                    int colSelected = e.getX() / CELL_SIZE;
//                    game.makeMove(rowSelected, colSelected);
//                } else {
//                    game.resetGame();
                System.out.println(rowSelected + " " + colSelected);
//                }
                repaint();
            }
        });
    }
    public void SizeChange(int newCellNum){
        cellNum = newCellNum;
        CELL_SIZE = GAME_BOARD_SIZE/newCellNum;
        repaint();
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGridLines(g);
    }

    private void drawGridLines(Graphics g){
        g.setColor(Color.BLACK);
        for (int row = 1; row < cellNum; row++) {
            g.fillRoundRect(0, CELL_SIZE * row - GRID_WIDHT_HALF,
                    GAME_BOARD_SIZE-1, GRID_WIDTH, GRID_WIDTH, GRID_WIDTH);
        }
        for (int col = 1; col < cellNum; col++) {
            g.fillRoundRect(CELL_SIZE * col - GRID_WIDHT_HALF, 0,
                    GRID_WIDTH, GAME_BOARD_SIZE-1, GRID_WIDTH, GRID_WIDTH);
        }
//        System.out.println(GAME_BOARD_SIZE - cellNum * CELL_SIZE);
        setSize(new Dimension(CELL_SIZE * cellNum,CELL_SIZE * cellNum));
//        repaint();
    }
}
