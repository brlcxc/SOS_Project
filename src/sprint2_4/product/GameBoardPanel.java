package sprint2_4.product;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameBoardPanel extends JPanel {
    int gameBoardSize = 250;
    int adjustedSize = gameBoardSize;
    int cellNumber = GameLogic.DEFAULT_DIMENSION;
    private int cellSize = gameBoardSize / cellNumber;
    private int gridWidth = 13 - cellNumber;
    private int gridWidhtHalf = gridWidth / 2;
    private GameLogic gameLogic;
    private boolean moveValidation;
    GameBoardPanel(GUI gui, CenterPanel centerPanel, GameLogic gameLogic){
        this.gameLogic = gameLogic;

        setPreferredSize(new Dimension(gameBoardSize, gameBoardSize));
        SizeChange(cellNumber);

        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (gameLogic.getGameState() == GameLogic.GameState.PLAYING) {
                    int rowSelected = e.getY() / cellSize;
                    int colSelected = e.getX() / cellSize;
                    moveValidation = gameLogic.makeMove(rowSelected, colSelected);
                    System.out.println(rowSelected + " " + colSelected);
//                }
                    //make making a move boolean so that I can varify
                    if(moveValidation){
                        centerPanel.updateTurnDisplay();
                        repaint();
                    }
                }
            }
        });
    }

public void SizeChange(int cellNumber){
    this.cellNumber = cellNumber;
    cellSize = gameBoardSize /cellNumber;
    adjustedSize = cellSize * cellNumber;

    if (cellNumber < 13) {
        gridWidth = 13 - cellNumber;
        gridWidhtHalf = gridWidth / 2;
    }
    setSize(new Dimension(adjustedSize,adjustedSize));
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
        g.fillRoundRect(0, 0,
                adjustedSize, gridWidth, gridWidth, gridWidth);
        g.fillRoundRect(0, 0,
                gridWidth, adjustedSize, gridWidth, gridWidth);
        g.fillRoundRect(adjustedSize - gridWidth, 0,
                gridWidth, adjustedSize, gridWidth, gridWidth);
        g.fillRoundRect(0, adjustedSize - gridWidth,
                adjustedSize, gridWidth, gridWidth, gridWidth);
        for (int row = 1; row < cellNumber; row++) {
            g.fillRoundRect(0, cellSize * row - gridWidhtHalf,
                    adjustedSize-1, gridWidth, gridWidth, gridWidth);
        }
        for (int col = 1; col < cellNumber; col++) {
            g.fillRoundRect(cellSize * col - gridWidhtHalf, 0,
                    gridWidth, adjustedSize-1, gridWidth, gridWidth);
        }
    }

    private void drawBoard(Graphics g) {
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
}