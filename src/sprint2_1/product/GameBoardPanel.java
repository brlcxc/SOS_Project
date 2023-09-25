package sprint2_1.product;


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
    private int cellSize = GAME_BOARD_SIZE / cellNumber;
    private int GRID_WIDTH = 10;//thickness
    //I want this number to scale with size
    private int GRID_WIDHT_HALF = GRID_WIDTH / 2;

    public int CELL_PADDING = cellSize / 6;
//    private int SYMBOL_SIZE = cellSize - CELL_PADDING * 2;
    private int SYMBOL_STROKE_WIDTH = 8;
    private GameLogic gameLogic;
    private GameLogic.Cell moveType = GameLogic.Cell.S;
    GameBoardPanel(GameLogic gameLogic){
        this.gameLogic = gameLogic;
        Border blackLine = BorderFactory.createLineBorder(Color.black, 13 - cellNumber);

//        setBorder(blackLine);
        setPreferredSize(new Dimension(GAME_BOARD_SIZE,GAME_BOARD_SIZE));
//        setLayout(new GridBagLayout());

        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
//                if (game.getGameState() == GameState.PLAYING) {
                    int rowSelected = e.getY() / cellSize;
                    int colSelected = e.getX() / cellSize;
                    gameLogic.makeMove(rowSelected, colSelected, moveType);
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

    //I need math here to change the size accordingly and let it scale
    //I can use cell number to scale?
//    GRID_WIDTH = 13 - cellNumber;
    if (cellNumber < 13) {
        GRID_WIDTH = 13 - cellNumber;
        GRID_WIDHT_HALF = GRID_WIDTH / 2;
    }
//    setBorder(BorderFactory.createLineBorder(Color.black, 13 - cellNumber));
//    if (10 - cellNumber > 0) {
//        SYMBOL_STROKE_WIDTH = 10 - cellNumber;
//        CELL_PADDING = cellSize / (3 + cellNumber);
////        SYMBOL_SIZE = cellSize - CELL_PADDING * 2;
//        //I want padding to decrease
//        //the padding surrounding the figure stays the same ratio
//    }
//    SYMBOL_SIZE = cellSize - CELL_PADDING * 2;

//    CELL_PADDING = cellSize / 4;
    setSize(new Dimension(adjustedSize,adjustedSize));
//    setBorder(BorderFactory.createLineBorder(Color.black, 13 - cellNumber));

//    GRID_WIDTH = 13 - cellNumber;
//    GRID_WIDHT_HALF = GRID_WIDTH / 2;
//    setBorder(BorderFactory.createLineBorder(Color.black, 13 - cellNumber));
//    SYMBOL_STROKE_WIDTH = 13 - cellNumber;

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
        g.fillRoundRect(0, 0,
                adjustedSize, GRID_WIDTH, GRID_WIDTH, GRID_WIDTH);
        g.fillRoundRect(0, 0,
                GRID_WIDTH, adjustedSize, GRID_WIDTH, GRID_WIDTH);
        g.fillRoundRect(adjustedSize - GRID_WIDTH, 0,
                GRID_WIDTH, adjustedSize, GRID_WIDTH, GRID_WIDTH);
        g.fillRoundRect(0, adjustedSize - GRID_WIDTH,
                adjustedSize, GRID_WIDTH, GRID_WIDTH, GRID_WIDTH);
        for (int row = 1; row < cellNumber; row++) {
            g.fillRoundRect(0, cellSize * row - GRID_WIDHT_HALF,
                    adjustedSize-1, GRID_WIDTH, GRID_WIDTH, GRID_WIDTH);
        }
        for (int col = 1; col < cellNumber; col++) {
            g.fillRoundRect(cellSize * col - GRID_WIDHT_HALF, 0,
                    GRID_WIDTH, adjustedSize-1, GRID_WIDTH, GRID_WIDTH);
        }
//        setBorder(BorderFactory.createLineBorder(Color.black, 13 - cellNumber));
    }

    //this class just repaints the game every move
    //that is why it checks everything
    private void drawBoard(Graphics g) {
//        Graphics2D g2d = (Graphics2D) g;
//        g2d.setStroke(new BasicStroke(SYMBOL_STROKE_WIDTH, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        Font font = new Font("SansSerif",Font.BOLD, (int) (0.8 * cellSize));
        FontMetrics metrics = g.getFontMetrics(font);
        g.setFont(font);
        for (int row = 0; row < gameLogic.getTotalRows(); ++row) {
            for (int col = 0; col < gameLogic.getTotalColumns(); ++col) {
                if (gameLogic.getCell(row, col) == GameLogic.Cell.O) {
                    int x = col * cellSize + (cellSize - metrics.stringWidth("O")) / 2;
                    int y = row * cellSize + ((cellSize - metrics.getHeight()) / 2) + metrics.getAscent();
                    g.drawString("O", x, y);
                }
                else if (gameLogic.getCell(row, col) == GameLogic.Cell.S) {
                    int x = col * cellSize + (cellSize - metrics.stringWidth("S")) / 2;
                    int y = row * cellSize + ((cellSize - metrics.getHeight()) / 2) + metrics.getAscent();
                    g.drawString("S", x, y);
                }
            }
        }
    }
    public void updateMoveType(GameLogic.Cell moveType){
        this.moveType = moveType;
    }
}