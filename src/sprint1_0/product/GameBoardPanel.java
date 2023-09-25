package sprint1_0.product;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameBoardPanel extends JPanel {
    //I need to fix all these variable declerations
    int GAME_BOARD_SIZE = 250;
    int adjustedSize = GAME_BOARD_SIZE;
    //If I want this to change dynamically I need to it look at if the height or width is smaller and then repaint
    int cellNumber = 3;
    //cell size is not matching with the click
    //maybe I need to do math to find a better way to ditribute pizels for the cell size
    //I can spread out pixels or increase for outside or...
    int cellSize = GAME_BOARD_SIZE / cellNumber;
    public static final int GRID_WIDTH = 1;//thickness
    //I want this number to scale with size
    public static final int GRID_WIDHT_HALF = GRID_WIDTH / 2;

    public int CELL_PADDING = cellSize / 6;
    public  int SYMBOL_SIZE = cellSize - CELL_PADDING * 2;
    public int SYMBOL_STROKE_WIDTH = 8;
    private GameLogic gameLogic;
    GameBoardPanel(GameLogic gameLogic){
        this.gameLogic = gameLogic;
        Border blackLine = BorderFactory.createLineBorder(Color.black, 1);

        setBorder(blackLine);
        setPreferredSize(new Dimension(GAME_BOARD_SIZE,GAME_BOARD_SIZE));
//        setLayout(new GridBagLayout());

        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
//                if (game.getGameState() == GameState.PLAYING) {
                    int rowSelected = e.getY() / cellSize;
                    int colSelected = e.getX() / cellSize;
//                    gameLogic.makeMove();
//                    game.makeMove(rowSelected, colSelected);
//                } else {
//                    game.resetGame();
                System.out.println(rowSelected + " " + colSelected);
//                }
                repaint();
            }
        });
    }

public void SizeChange(int cellNumber){
    this.cellNumber = cellNumber;
    cellSize = GAME_BOARD_SIZE/cellNumber;
    adjustedSize = cellSize * cellNumber;
    setSize(new Dimension(adjustedSize,adjustedSize));
//I need to call the function to reset the array

    //these make it change from center
//    setPreferredSize(new Dimension(adjustedSize,adjustedSize));
//    repaint();
//    revalidate();
}
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGridLines(g);
        drawBoard(g);
    }

    private void drawGridLines(Graphics g){
        g.setColor(Color.BLACK);
        for (int row = 1; row < cellNumber; row++) {
            g.fillRoundRect(0, cellSize * row - GRID_WIDHT_HALF,
                    adjustedSize-1, GRID_WIDTH, GRID_WIDTH, GRID_WIDTH);
        }
        for (int col = 1; col < cellNumber; col++) {
            g.fillRoundRect(cellSize * col - GRID_WIDHT_HALF, 0,
                    GRID_WIDTH, adjustedSize-1, GRID_WIDTH, GRID_WIDTH);
        }
    }

    //this class just repaints the game every move
    //that is why it checks everything
    private void drawBoard(Graphics g) {
        System.out.println("w");
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(SYMBOL_STROKE_WIDTH, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        System.out.println(gameLogic.getTotalColumns());
//        for (int row = 0; row < gameLogic.getTotalRows(); ++row) {
//            for (int col = 0; col < gameLogic.getTotalColumns(); ++col) {
//                System.out.println("c");
//                int x1 = col * cellSize + CELL_PADDING;
//                int y1 = row * cellSize + CELL_PADDING;
//                if (gameLogic.getCell(row, col) == Cell.EMPTY) {
//                    g2d.setColor(Color.RED);
//                    int x2 = (col + 1) * cellSize - CELL_PADDING;
//                    int y2 = (row + 1) * cellSize - CELL_PADDING;
//                    g2d.drawLine(x1, y1, x2, y2);
//                    g2d.drawLine(x2, y1, x1, y2);
//                }
////                else if (gameLogic.getCell(row, col) == gameLogic.NOUGHT) {
////                    g2d.setColor(Color.BLUE);
////                    g2d.drawOval(x1, y1, SYMBOL_SIZE, SYMBOL_SIZE);
////                }
//            }
//        }
    }
}
