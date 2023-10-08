package sprint2_4.product;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameBoardPanel extends JPanel {
    final static int gameBoardSize = 250;
    private int adjustedSize = gameBoardSize;
    private int cellNumber = GameLogic.DEFAULT_DIMENSION;
    private int cellSize = gameBoardSize / cellNumber;
    private int gridWidth = 13 - cellNumber;
    private int gridWidthHalf = gridWidth / 2;
    private GameLogic gameLogic;
    private boolean moveValidation;
    GameBoardPanel(GUI gui, CenterPanel centerPanel, GameLogic gameLogic){
        this.gameLogic = gameLogic;

        setPreferredSize(new Dimension(gameBoardSize, gameBoardSize));
        SizeChange(cellNumber);

        //activates when the user clicks on the game board
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (gameLogic.getGameState() == GameLogic.GameState.PLAYING) {
                    int rowSelected = e.getY() / cellSize;
                    int colSelected = e.getX() / cellSize;

                    //If legal the move is made within makeMove()
                    //makeRow() returns false if the move has not been made
                    //Turn and player information is not updated unless the move is legal
                    moveValidation = gameLogic.makeMove(rowSelected, colSelected);
                    if(moveValidation){
                        centerPanel.updateTurnDisplay();
                        repaint();
                    }
                }
            }
        });
    }

    //Board size change method
    //Only used prior to game start
    public void SizeChange(int cellNumber){
    this.cellNumber = cellNumber;

    //cell size is the highest possible integer that can be evenly fit within gameBoardSize
    cellSize = gameBoardSize /cellNumber;
    //the adjusted boardSize is the maximum space that can be taken my the cells
    adjustedSize = cellSize * cellNumber;

    //This grid width scales down as the cell width and height increases
    if (cellNumber < 13) {
        gridWidth = 13 - cellNumber;
        gridWidthHalf = gridWidth / 2;
    }
    setSize(new Dimension(adjustedSize,adjustedSize));
    //The lines are redrawn
    repaint();
}
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGridLines(g);
        if(gameLogic.getGameState() == GameLogic.GameState.PLAYING){
            drawBoard(g);
        }
    }

    private void drawGridLines(Graphics g){
        g.setColor(Color.BLACK);

        //draws the grid border
        g.fillRoundRect(0, 0,
                adjustedSize, gridWidth, gridWidth, gridWidth);
        g.fillRoundRect(0, 0,
                gridWidth, adjustedSize, gridWidth, gridWidth);
        g.fillRoundRect(adjustedSize - gridWidth, 0,
                gridWidth, adjustedSize, gridWidth, gridWidth);
        g.fillRoundRect(0, adjustedSize - gridWidth,
                adjustedSize, gridWidth, gridWidth, gridWidth);

        //draws the grid rows and columns
        for (int row = 1; row < cellNumber; row++) {
            g.fillRoundRect(0, cellSize * row - gridWidthHalf,
                    adjustedSize-1, gridWidth, gridWidth, gridWidth);
        }
        for (int col = 1; col < cellNumber; col++) {
            g.fillRoundRect(cellSize * col - gridWidthHalf, 0,
                    gridWidth, adjustedSize-1, gridWidth, gridWidth);
        }
    }

    private void drawBoard(Graphics g) {
        Font font = new Font("SansSerif",Font.BOLD, (int) (0.8 * cellSize));
        FontMetrics metrics = g.getFontMetrics(font);
        g.setFont(font);

        //Searches the entire array and places a piece in each game board cell associated with an array cell
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
}