package sprint3_2.product;


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
    private int cellPadding = cellSize / 6;
    private int symbolStrokeWidth = gridWidth;
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
                        //maybe update here as well to say who won?
                        //or "press start to play again"
                        centerPanel.updateTurnDisplay();
                         if(gameLogic.getGameState() == GameLogic.GameState.BLUE_WON){
//                             JOptionPane.showMessageDialog(null,"blue won","Title",JOptionPane.PLAIN_MESSAGE);
//                             gui.GameStop();
                         }
                         else if(gameLogic.getGameState() == GameLogic.GameState.RED_WON){
//                             JOptionPane.showMessageDialog(null,"red won","Title",JOptionPane.PLAIN_MESSAGE);
                         }
                         else if(gameLogic.getGameState() == GameLogic.GameState.DRAW){
//                             JOptionPane.showMessageDialog(null,"draw","Title",JOptionPane.PLAIN_MESSAGE);
                         }
                         else{
                             repaint();
                         }
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
        cellPadding = cellSize / 6;
        //the adjusted boardSize is the maximum space that can be taken my the cells
        adjustedSize = cellSize * cellNumber;

        //This grid width scales down as the cell width and height increases
        if (cellNumber < 13) {
            gridWidth = 13 - cellNumber;
            symbolStrokeWidth = gridWidth;
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
//        if(gameLogic.getGameState() == GameLogic.GameState.PLAYING){
//            drawBoard(g);
//        }
        if(gameLogic.getGameState() != GameLogic.GameState.IDLE){
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
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(symbolStrokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        Font font = new Font("SansSerif",Font.BOLD, (int) (0.8 * cellSize));
        FontMetrics metrics = g.getFontMetrics(font);
        g.setFont(font);

//        int x1 = col * CELL_SIZE + CELL_PADDING;
//        int y1 = row * CELL_SIZE + CELL_PADDING;
//        if (game.getCell(row, col) == Cell.CROSS) {
//            g2d.setColor(Color.RED);
//            int x2 = (col + 1) * CELL_SIZE - CELL_PADDING;
//            int y2 = (row + 1) * CELL_SIZE - CELL_PADDING;
//            g2d.drawLine(x1, y1, x2, y2);
//            g2d.drawLine(x2, y1, x1, y2);
        //I can convert the index to something in sign or cosin to figure how far to go out

        //Searches the entire array and places a piece in each game board cell associated with an array cell
        for (int row = 0; row < gameLogic.getTotalRows(); ++row) {
            for (int col = 0; col < gameLogic.getTotalColumns(); ++col) {
                if (gameLogic.getCell(row, col) == GameLogic.Cell.O) {
                    int x = col * cellSize + (cellSize - metrics.stringWidth("O")) / 2;
                    int y = row * cellSize + ((cellSize - metrics.getHeight()) / 2) + metrics.getAscent();
                    g.drawString("O", x, y);
                    for(int i = 0; i < 8; i++) {

                        if (gameLogic.getCombinationDirection(row, col, i) != GameLogic.CombinationDirection.EMPTY) {
                            System.out.println("test");
                            int x1 = (col + (int) Math.round(-1 * Math.cos(Math.toRadians(45 * gameLogic.getCombinationDirection(row, col, i).ordinal())))) * cellSize + (cellSize / 2);
                            int y1 = (row + (int) Math.round(-1 * Math.sin(Math.toRadians(45 * gameLogic.getCombinationDirection(row, col, i).ordinal())))) * cellSize + (cellSize / 2);
                            int x2 = (col + (int) Math.round(Math.cos(Math.toRadians(45 * gameLogic.getCombinationDirection(row, col, i).ordinal())))) * cellSize + (cellSize / 2);
                            int y2 = (row + (int) Math.round(Math.sin(Math.toRadians(45 * gameLogic.getCombinationDirection(row, col, i).ordinal())))) * cellSize + (cellSize / 2);
//                            System.out.println(x1 + " " + y1 + " " + x2 + " " + y2);
                            if(gameLogic.getTurnRecorder(row, col) % 2 != 0) {
                                g2d.setColor(Color.BLUE);
                            }
                            else{
                                g2d.setColor(Color.RED);
                            }
                            g2d.drawLine(x1, y1, x2, y2);
                            g2d.setColor(Color.BLACK);
                            //the reason it overlaps is because the letter placement is intermixed with the piece placement
                        }
                    }
                }
                else if (gameLogic.getCell(row, col) == GameLogic.Cell.S) {
                    int x = col * cellSize + (cellSize - metrics.stringWidth("S")) / 2;
                    int y = row * cellSize + ((cellSize - metrics.getHeight()) / 2) + metrics.getAscent();
                    g.drawString("S", x, y);
                    for(int i = 0; i < 8; i ++) {
                        if (gameLogic.getCombinationDirection(row, col, i) != GameLogic.CombinationDirection.EMPTY) {
                            int x1 = (col * cellSize + (cellSize / 2));
                            int y1 = (row * cellSize + (cellSize / 2));
                            ;
                            int x2 = (col + 2 * (int) Math.round(Math.cos(Math.toRadians(45 * gameLogic.getCombinationDirection(row, col, i).ordinal())))) * cellSize + (cellSize / 2);
                            int y2 = (row + 2 * (int) Math.round(Math.sin(Math.toRadians(45 * gameLogic.getCombinationDirection(row, col, i).ordinal())))) * cellSize + (cellSize / 2);
                            System.out.println(x1 + " " + y1 + " " + x2 + " " + y2);
                            if(gameLogic.getTurnRecorder(row, col) % 2 != 0) {
                                g2d.setColor(Color.BLUE);
                            }
                            else{
                                g2d.setColor(Color.RED);
                            }                            g2d.drawLine(x1, y1, x2, y2);
                            g2d.setColor(Color.BLACK);
                        }
                    }
                }
            }
        }
    }
}