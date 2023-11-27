package sprint5_0.product;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.DefaultFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;
//I need to fix acidnetal recording and deleting a file if record finsihes too soon
import static java.lang.Math.min;

public class GUI extends JFrame {
    public static Font font;
    private LeftTopPanel leftTopPanel;
    private LeftPlayerPanel leftPlayerPanel;
    private LeftBottomPanel leftBottomPanel;
    private RightTopPanel rightTopPanel;
    private RightPlayerPanel rightPlayerPanel;
    private RightBottomPanel rightBottomPanel;
    private CenterPanel centerPanel;
    private final int WINDOW_WIDTH = 700;
    private final int WINDOW_HEIGHT = 440;
    private GameLogic gameLogic;
    private Boolean replayedGame = false;

    public GUI(){
        setContentPane();
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo((Component)null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("SOS");
        font = new Font("SansSerif", 1, 18);
        setVisible(true);
        resizeBoard();
    }
    private void setContentPane(){
        GridBagConstraints gbc = new GridBagConstraints();

        //maybe start everyone on simple and then switch so that I can have an abstract class
        gameLogic = new SimpleComputerGameLogic();
        centerPanel = new CenterPanel(this, gameLogic);
        leftTopPanel = new LeftTopPanel(gameLogic);
        leftPlayerPanel = new LeftPlayerPanel(gameLogic);
        leftBottomPanel = new LeftBottomPanel(gameLogic);
        rightTopPanel = new RightTopPanel(gameLogic, this);
        rightPlayerPanel = new RightPlayerPanel(gameLogic);
        rightBottomPanel = new RightBottomPanel(gameLogic, this);
        gameLogic.initGame();

        Container contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.27;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1;
        contentPane.add(leftTopPanel, gbc);

        gbc.gridy = -1;
        gbc.weighty = 0;
        contentPane.add(leftPlayerPanel, gbc);

        gbc.gridy = -2;
        gbc.weighty = 1;
        contentPane.add(leftBottomPanel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 4;
        gbc.weightx = 0.46;
        contentPane.add(centerPanel, gbc);

        gbc.gridx = 2;
        gbc.gridheight = 1;
        gbc.weightx = 0.27;
        gbc.anchor = GridBagConstraints.LAST_LINE_END;
        contentPane.add(rightTopPanel, gbc);

        gbc.gridy = -1;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.LAST_LINE_END;
        contentPane.add(rightPlayerPanel, gbc);

        gbc.gridy = -2;
        gbc.weighty = 0;
        contentPane.add(rightBottomPanel, gbc);
    }

    //adjusts the size of both panels to make them an even width
    private void resizeBoard() {
        int minWidth = min(rightTopPanel.getWidth(), leftTopPanel.getWidth());
        rightTopPanel.setSize(new Dimension(minWidth, rightTopPanel.getHeight()));
        leftTopPanel.setSize(new Dimension(minWidth, rightTopPanel.getHeight()));
    }

    //start method public to all other gui classes
    //calls start method in other gui classes
    public void GameStart(){
        //maybe have a call here to delete the file of a prior game?
        //if game = recording and status = playing

        if(gameLogic.getGameMode() == GameLogic.GameMode.SIMPLE){
            gameLogic = new SimpleComputerGameLogic(gameLogic.getRedPlayerMove(), gameLogic.getBluePlayerMove(), gameLogic.getRedPlayerMode(), gameLogic.getBluePlayerMode(), gameLogic.getRecording());
        }
        else{
            gameLogic = new GeneralComputerGameLogic(gameLogic.getRedPlayerMove(), gameLogic.getBluePlayerMove(), gameLogic.getRedPlayerMode(), gameLogic.getBluePlayerMode(), gameLogic.getRecording());
        }
        gameLogic.initGame();
        leftTopPanel.GameStart(gameLogic);
        leftPlayerPanel.GameStart(gameLogic);
        leftBottomPanel.GameStart(gameLogic);
        rightTopPanel.GameStart(gameLogic);
        rightPlayerPanel.GameStart(gameLogic);
        rightBottomPanel.GameStart(gameLogic);
        centerPanel.GameStart(gameLogic);
        gameLogic.startGame(rightTopPanel.getBoardSizeInput());
        centerPanel.updateTurnDisplay();
        if(!replayedGame) {
            ComputerMoveMade();
        }
        centerPanel.Repaint();
        centerPanel.updateTurnDisplay();
    }
    public void ReplayMode(String filename){
        replayedGame = true;
        try {
            Scanner sc = new Scanner(new File(filename));
            gameLogic.setGameMode(GameLogic.GameMode.valueOf(sc.next()));
            gameLogic.setBluePlayerMode(GameLogic.PlayerMode.valueOf(sc.next()));
            gameLogic.setRedPlayerMode(GameLogic.PlayerMode.valueOf(sc.next()));
            rightTopPanel.setBoardSizeInput(Integer.parseInt(sc.next()));
            leftPlayerPanel.updatePlayerDisplay();
            rightPlayerPanel.updatePlayerDisplay();
            leftTopPanel.updateGameModeDisplay();
            leftBottomPanel.TurnOffRecording();
            GameStart();
            ReplayMoveMade(sc);
/*            gameLogic.setBluePlayerMove();
            gameLogic.setRedPlayerMove();*/
 /*                       while (sc.hasNextLine()) {
                            System.out.println(sc.nextLine());
                            System.out.println("test");
                        }*/
/*            int row, column;
            while (sc.hasNext()) {
                if(gameLogic.getBluePlayerTurn()){
                    gameLogic.setBluePlayerMove(GameLogic.Cell.valueOf(sc.next()));
                }
                else{
                    gameLogic.setRedPlayerMove(GameLogic.Cell.valueOf(sc.next()));
                }
                row = Integer.parseInt(sc.next());
                column = Integer.parseInt(sc.next());
                gameLogic.makeMove(row, column);
            }*/
        }
        catch(IOException ignored){
        }

        //replay mode needs to be called directly before game start and call game start within
        //I need to update game mode panel and size
/*        replayedGame = true;
        GameStart();*/
/*        leftPlayerPanel.updatePlayerDisplay();
        rightPlayerPanel.updatePlayerDisplay();
        leftTopPanel.updateGameModeDisplay();*/
//        centerPanel.Resize();
        replayedGame = false;
    }
    public void ReplayMoveMade(Scanner sc){
        if(gameLogic.getGameState() == GameLogic.GameState.PLAYING) {
            int row, column;
            if(gameLogic.getBluePlayerTurn()){
                gameLogic.setBluePlayerMove(GameLogic.Cell.valueOf(sc.next()));
            }
            else{
                gameLogic.setRedPlayerMove(GameLogic.Cell.valueOf(sc.next()));
            }
            row = Integer.parseInt(sc.next());
            column = Integer.parseInt(sc.next());
            gameLogic.makeMove(row, column);
            leftPlayerPanel.updateMoveDisplay();
            rightPlayerPanel.updateMoveDisplay();
            paintReplayMove(sc);
        }
        leftPlayerPanel.updateMoveDisplay();
        rightPlayerPanel.updateMoveDisplay();
    }
    public void paintReplayMove(Scanner sc){
        (new Thread(() -> {
            try {
                Thread.sleep(250);
                SwingUtilities.invokeAndWait(() ->
                {
                    centerPanel.updateTurnDisplay();
                    centerPanel.Repaint();
                    ReplayMoveMade(sc);
                });
            }catch(InterruptedException | InvocationTargetException e){
                e.printStackTrace(System.out);
            }
        })).start();
    }

    public void ComputerMoveMade(){
        if(gameLogic.getGameState() == GameLogic.GameState.PLAYING) {
            if ((gameLogic.getBluePlayerTurn() && (gameLogic.getBluePlayerMode() == GameLogic.PlayerMode.COMPUTER))
            || (gameLogic.getRedPlayerTurn() && (gameLogic.getRedPlayerMode() == GameLogic.PlayerMode.COMPUTER))) {
                gameLogic.makeComputerMove();
                leftPlayerPanel.updateMoveDisplay();
                rightPlayerPanel.updateMoveDisplay();
                paintComputerMove();
            }
        }
        leftPlayerPanel.updateMoveDisplay();
        rightPlayerPanel.updateMoveDisplay();
    }
    public void paintComputerMove(){
        (new Thread(() -> {
            try {
                Thread.sleep(250);
                SwingUtilities.invokeAndWait(() ->
                {
                    centerPanel.updateTurnDisplay();
                    centerPanel.Repaint();
                    ComputerMoveMade();
                });
            }catch(InterruptedException | InvocationTargetException e){
                e.printStackTrace(System.out);
            }
        })).start();
    }
    //stop method public to all other classes in gui
    //calls stop method in other gui classes
    public void GameStop(){
        if(gameLogic.getGameState() == GameLogic.GameState.PLAYING && gameLogic.getRecording()){
            gameLogic.DeleteFile();
            System.out.println("tets");
        }
        leftTopPanel.GameStop();
        leftPlayerPanel.GameStop();
        leftBottomPanel.GameStop();
        rightTopPanel.GameStop();
        rightPlayerPanel.GameStop();
        rightBottomPanel.GameStop();
        gameLogic.initGame();
        centerPanel.updateTurnDisplay();

    }
    public CenterPanel getCenterPanel(){
        return centerPanel;
    }
    public GameLogic getGameLogic(){return gameLogic;}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GUI();
            }
        });
    }
    class LeftPlayerPanel extends JPanel {
        private GameLogic gameLogic;
        private JRadioButton humanOption;
        private JRadioButton computerOption;
        private JRadioButton sOption;
        private JRadioButton oOption;

        LeftPlayerPanel(GameLogic gameLogic) {
            this.gameLogic = gameLogic;
            GridBagConstraints gbc = new GridBagConstraints();

            humanOption = new JRadioButton("Human");
            computerOption = new JRadioButton("Computer");
            sOption = new JRadioButton("S");
            oOption = new JRadioButton("O");

            humanOption.addActionListener(new HumanButtonListener());
            computerOption.addActionListener(new ComputerButtonListener());

            sOption.addActionListener(new SButtonListener());
            oOption.addActionListener(new OButtonListener());

            ButtonGroup playerGroup = new ButtonGroup();
            playerGroup.add(humanOption);
            playerGroup.add(computerOption);

            ButtonGroup moveGroup = new ButtonGroup();
            moveGroup.add(sOption);
            moveGroup.add(oOption);

            setLayout(new GridBagLayout());

            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.anchor = GridBagConstraints.LAST_LINE_START;
            add(new JLabel("Blue Player"), gbc);

            gbc.insets = new Insets(5, 10, 5, 10);
            gbc.gridy = -1;
            add(humanOption, gbc);

            gbc.insets = new Insets(5, 20, 5, 20);
            gbc.gridy = -2;
            add(sOption, gbc);

            gbc.gridy = -3;
            add(oOption, gbc);

            gbc.insets = new Insets(5, 10, 5, 10);
            gbc.gridy = -4;
            add(computerOption, gbc);

            humanOption.doClick();
            sOption.doClick();
        }

        //Sets the current blue player move to S
        private class SButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                gameLogic.setBluePlayerMove(GameLogic.Cell.S);
            }
        }

        //Sets the current blue player move to O
        private class OButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                gameLogic.setBluePlayerMove(GameLogic.Cell.O);

            }
        }

        private class HumanButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                gameLogic.setBluePlayerMode(GameLogic.PlayerMode.HUMAN);
            }
        }

        private class ComputerButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                gameLogic.setBluePlayerMode(GameLogic.PlayerMode.COMPUTER);
            }
        }

        public void GameStart(GameLogic gameLogic) {
            this.gameLogic = gameLogic;
            humanOption.setEnabled(false);
            computerOption.setEnabled(false);
        }

        public void GameStop() {
            humanOption.setEnabled(true);
            computerOption.setEnabled(true);
        }

        public void updateMoveDisplay() {
            if (gameLogic.getBluePlayerMove() == GameLogic.Cell.S && !sOption.isSelected()) {
                sOption.doClick();
            } else if(gameLogic.getBluePlayerMove() == GameLogic.Cell.O && !oOption.isSelected()){
                oOption.doClick();
            }
        }
        public void updatePlayerDisplay() {
/*            if (gameLogic.getBluePlayerMove() == GameLogic.Cell.S && !sOption.isSelected()) {
                sOption.doClick();
            } else if(gameLogic.getBluePlayerMove() == GameLogic.Cell.O && !oOption.isSelected()){
                oOption.doClick();
            }*/
            if (gameLogic.getBluePlayerMode() == GameLogic.PlayerMode.HUMAN && !humanOption.isSelected()) {
                humanOption.doClick();
            } else if (gameLogic.getBluePlayerMode() == GameLogic.PlayerMode.COMPUTER && !computerOption.isSelected()){
                computerOption.doClick();
            }
        }
    }
    class RightPlayerPanel extends JPanel{
        private GameLogic gameLogic;
        private JRadioButton humanOption;
        private JRadioButton computerOption;
        private JRadioButton sOption;
        private JRadioButton oOption;
        private JLabel playerLabel;
        RightPlayerPanel(GameLogic gameLogic){
            this.gameLogic = gameLogic;
            GridBagConstraints gbc = new GridBagConstraints();

            humanOption = new JRadioButton("Human");
            computerOption = new JRadioButton("Computer");
            sOption = new JRadioButton("S");
            oOption = new JRadioButton("O");

            humanOption.addActionListener(new HumanButtonListener());
            computerOption.addActionListener(new ComputerButtonListener());

            sOption.addActionListener(new SButtonListener());
            oOption.addActionListener(new OButtonListener());

            ButtonGroup playerGroup = new ButtonGroup();
            playerGroup.add(humanOption);
            playerGroup.add(computerOption);

            ButtonGroup moveGroup = new ButtonGroup();
            moveGroup.add(sOption);
            moveGroup.add(oOption);

            playerLabel = new JLabel("Red Player");

            setLayout(new GridBagLayout());

            gbc.insets = new Insets(10,10,10,10);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.anchor = GridBagConstraints.EAST;
            add(playerLabel, gbc);

            gbc.insets = new Insets(5,10,5,10);
            gbc.gridy = -1;
            add(humanOption, gbc);

            gbc.insets = new Insets(5,20,5,20);
            gbc.gridy = -2;
            add(sOption, gbc);

            gbc.gridy = -3;
            add(oOption, gbc);

            gbc.insets = new Insets(5,10,5,10);
            gbc.gridy = -4;
            add(computerOption, gbc);

            humanOption.doClick();
            sOption.doClick();
        }
        private class SButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                gameLogic.setRedPlayerMove(GameLogic.Cell.S);
            }
        }
        private class OButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                gameLogic.setRedPlayerMove(GameLogic.Cell.O);
            }
        }
        private class HumanButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                gameLogic.setRedPlayerMode(GameLogic.PlayerMode.HUMAN);
            }
        }
        private class ComputerButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                gameLogic.setRedPlayerMode(GameLogic.PlayerMode.COMPUTER);
            }
        }
        public void GameStart(GameLogic gameLogic){
            this.gameLogic = gameLogic;
            humanOption.setEnabled(false);
            computerOption.setEnabled(false);
        }

        public void GameStop(){
            humanOption.setEnabled(true);
            computerOption.setEnabled(true);
        }
        public void updateMoveDisplay() {
/*            if (gameLogic.getRedPlayerMove() == GameLogic.Cell.S) {
                sOption.doClick();
            } else {
                oOption.doClick();
            }*/
            if (gameLogic.getRedPlayerMove() == GameLogic.Cell.S && !sOption.isSelected()) {
                sOption.doClick();
            } else if(gameLogic.getRedPlayerMove() == GameLogic.Cell.O && !oOption.isSelected()){
                oOption.doClick();
            }
        }
        public void updatePlayerDisplay() {
            if (gameLogic.getRedPlayerMode() == GameLogic.PlayerMode.HUMAN && !humanOption.isSelected()) {
                humanOption.doClick();
            } else if (gameLogic.getRedPlayerMode() == GameLogic.PlayerMode.COMPUTER && !computerOption.isSelected()){
                computerOption.doClick();
            }
/*            if (gameLogic.getBluePlayerMode() == GameLogic.PlayerMode.HUMAN && !humanOption.isSelected()) {
                humanOption.doClick();
            } else if (gameLogic.getBluePlayerMode() == GameLogic.PlayerMode.COMPUTER && !computerOption.isSelected()){
                computerOption.doClick();
            }*/
        }
    }

    class CenterPanel extends JPanel {
        public GameBoardPanel gameBoardPanel;
        private GameLogic gameLogic;
        private JLabel turnInfo;
        private GUI gui;

        CenterPanel(GUI gui, GameLogic gameLogic) {
            this.gui = gui;

            GridBagConstraints gbc = new GridBagConstraints();
            gameBoardPanel = new GameBoardPanel(gui, this, gameLogic);
            setLayout(new GridBagLayout());

            this.gameLogic = gameLogic;

            JLabel title = new JLabel("SOS");
            turnInfo = new JLabel("Press \"Start\" to begin");

            title.setFont(new Font("SansSerif", 1, 34));

            gbc.insets = new Insets(10, 10, 0, 10);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 1;
            add(title, gbc);
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.gridy = -1;
            gbc.weighty = 1;
            gbc.anchor = GridBagConstraints.CENTER;
            add(gameBoardPanel, gbc);

            gbc.gridy = -2;
            gbc.weighty = 0;
            gbc.fill = GridBagConstraints.NONE;
            gbc.anchor = GridBagConstraints.CENTER;
            add(turnInfo, gbc);
        }
        public void Repaint(){
            gui.repaint();
        }
        public void Resize(int newSize){
            gameBoardPanel.SizeChange(newSize);
        }

        //displays text at bottom indicating the player turn
        public void updateTurnDisplay() {
            if (gameLogic.getTurn() == 0) {
                turnInfo.setText("Press \"Start\" to begin");
            } else if (gameLogic.getGameState() == GameLogic.GameState.BLUE_WON) {
                turnInfo.setText("Blue won - Press \"New Game\" to play again");
            } else if (gameLogic.getGameState() == GameLogic.GameState.RED_WON) {
                turnInfo.setText("Red won - Press \"New Game\" to play again");
            } else if (gameLogic.getGameState() == GameLogic.GameState.DRAW) {
                turnInfo.setText("Draw - Press \"New Game\" to play again");
            } else if (gameLogic.getBluePlayerTurn()) {
                turnInfo.setText("Blue Player Turn");
            } else if (gameLogic.getRedPlayerTurn()) {
                turnInfo.setText("Red Player Turn");
            }
            repaint();
        }

        public void GameStart(GameLogic gameLogic) {
            this.gameLogic = gameLogic;
            gameBoardPanel.GameStart(gameLogic);
        }
    }
    class GameBoardPanel extends JPanel {
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
        private CenterPanel centerPanel;
        private GameBoardPanel.Mouse mouse;
        private GUI gui;
        GameBoardPanel(GUI gui, CenterPanel centerPanel, GameLogic gameLogic){
            this.gui = gui;
            this.centerPanel = centerPanel;
            this.gameLogic = gameLogic;

            setPreferredSize(new Dimension(gameBoardSize, gameBoardSize));
            SizeChange(cellNumber);

            mouse = new GameBoardPanel.Mouse(this);
            addMouseListener(mouse);
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

            //Searches the entire array and places a piece in each game board cell associated with an array cell
            for (int row = 0; row < gameLogic.getTotalRows(); ++row) {
                for (int col = 0; col < gameLogic.getTotalColumns(); ++col) {
                    if (gameLogic.getCell(row, col) == GameLogic.Cell.O) {
                        drawPiece(g, col, row, "O", metrics);
                    }
                    else if (gameLogic.getCell(row, col) == GameLogic.Cell.S) {
                        drawPiece(g, col, row, "S", metrics);
                    }
                }
            }
            for (int row = 0; row < gameLogic.getTotalRows(); ++row) {
                for (int col = 0; col < gameLogic.getTotalColumns(); ++col) {
                    if (gameLogic.getCell(row, col) == GameLogic.Cell.O) {
                        drawOCombinations(g2d, col, row);
                    }
                    else if (gameLogic.getCell(row, col) == GameLogic.Cell.S) {
                        drawSCombinations(g2d, col, row);
                    }
                }
            }
        }
        private void drawPiece(Graphics g, int col, int row, String piece, FontMetrics metrics) {
            int x = col * cellSize + (cellSize - metrics.stringWidth("O")) / 2;
            int y = row * cellSize + ((cellSize - metrics.getHeight()) / 2) + metrics.getAscent();
            g.drawString(piece, x, y);
        }
        private void drawOCombinations(Graphics g2d, int col, int row) {
            for(int i = 0; i < 8; i++) {
                if (gameLogic.getCombinationDirection(row, col, i) != GameLogic.CombinationDirection.EMPTY) {
                    int x1 = (col + (int) Math.round(-1 * Math.cos(Math.toRadians(45 * gameLogic.getCombinationDirection(row, col, i).ordinal())))) * cellSize + (cellSize / 2);
                    int y1 = (row + (int) Math.round(-1 * Math.sin(Math.toRadians(45 * gameLogic.getCombinationDirection(row, col, i).ordinal())))) * cellSize + (cellSize / 2);
                    int x2 = (col + (int) Math.round(Math.cos(Math.toRadians(45 * gameLogic.getCombinationDirection(row, col, i).ordinal())))) * cellSize + (cellSize / 2);
                    int y2 = (row + (int) Math.round(Math.sin(Math.toRadians(45 * gameLogic.getCombinationDirection(row, col, i).ordinal())))) * cellSize + (cellSize / 2);
                    if(gameLogic.getTurnRecorder(row, col) % 2 != 0) {
                        g2d.setColor(Color.BLUE);
                    }
                    else{
                        g2d.setColor(Color.RED);
                    }
                    g2d.drawLine(x1, y1, x2, y2);
                    g2d.setColor(Color.BLACK);
                }
            }
        }
        private void drawSCombinations(Graphics g2d, int col, int row) {
            for(int i = 0; i < 8; i ++) {
                if (gameLogic.getCombinationDirection(row, col, i) != GameLogic.CombinationDirection.EMPTY) {
                    int x1 = (col * cellSize + (cellSize / 2));
                    int y1 = (row * cellSize + (cellSize / 2));
                    int x2 = (col + 2 * (int) Math.round(Math.cos(Math.toRadians(45 * gameLogic.getCombinationDirection(row, col, i).ordinal())))) * cellSize + (cellSize / 2);
                    int y2 = (row + 2 * (int) Math.round(Math.sin(Math.toRadians(45 * gameLogic.getCombinationDirection(row, col, i).ordinal())))) * cellSize + (cellSize / 2);
                    if(gameLogic.getTurnRecorder(row, col) % 2 != 0) {
                        g2d.setColor(Color.BLUE);

                    }
                    else{
                        g2d.setColor(Color.RED);
                    }
                    g2d.drawLine(x1, y1, x2, y2);
                    g2d.setColor(Color.BLACK);
                }
            }
        }
            public void GameStart(GameLogic gameLogic){
            this.gameLogic = gameLogic;
        }
        private class Mouse extends MouseAdapter {
            private GameBoardPanel gameBoardPanel;
            Mouse(GameBoardPanel gameBoardPanel){
                this.gameBoardPanel = gameBoardPanel;
            }
            public void mouseClicked(MouseEvent e) {
                gameBoardPanel.test(e.getX(), e.getY());
            }
        }

        public void test(int x, int y) {
            if ((gameLogic.getGameState() == GameLogic.GameState.PLAYING)
                    && !(gameLogic.getBluePlayerTurn() && gameLogic.getBluePlayerMode() == GameLogic.PlayerMode.COMPUTER)
                    && !(gameLogic.getRedPlayerTurn() && gameLogic.getRedPlayerMode() == GameLogic.PlayerMode.COMPUTER)) {
                int rowSelected = y / cellSize;
                int colSelected = x / cellSize;

                moveValidation = gameLogic.makeMove(rowSelected, colSelected);
                if (moveValidation) {
                    centerPanel.updateTurnDisplay();
                    gui.ComputerMoveMade();
                }
            }
        }

    }
    class LeftTopPanel extends JPanel {
        private JRadioButton simpleGame;
        private JRadioButton generalGame;
        private GameLogic gameLogic;
        LeftTopPanel(GameLogic gameLogic){
            this.gameLogic = gameLogic;
            GridBagConstraints gbc = new GridBagConstraints();

            setLayout(new GridBagLayout());

            JLabel gameModeLabel = new JLabel("Game Mode");
            simpleGame = new JRadioButton("Simple");
            generalGame = new JRadioButton("General");

            simpleGame.addActionListener(new SimpleGameButtonListener());
            generalGame.addActionListener(new GeneralGameButtonListener());

            ButtonGroup gameModeGroup = new ButtonGroup();
            gameModeGroup.add(simpleGame);
            gameModeGroup.add(generalGame);

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            add(gameModeLabel, gbc);

            gbc.gridy = -1;
            gbc.gridwidth = 1;
            gbc.insets = new Insets(2,3,0,3);
            add(simpleGame, gbc);

            gbc.gridx = 1;
            add(generalGame,gbc);

            simpleGame.doClick();
        }
        //Calls the logic method to set game mode to SIMPLE
        private class SimpleGameButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                gameLogic.setGameMode(GameLogic.GameMode.SIMPLE);
            }
        }
        //Calls the logic method to set game mode to GENERAL
        private class GeneralGameButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                gameLogic.setGameMode(GameLogic.GameMode.GENERAL);
            }
        }
        public void updateGameModeDisplay() {
            if (gameLogic.getGameMode() == GameLogic.GameMode.SIMPLE) {
                simpleGame.doClick();
            } else {
                generalGame.doClick();
            }
        }
        public void GameStart(GameLogic gameLogic){
            this.gameLogic = gameLogic;
            simpleGame.setEnabled(false);
            generalGame.setEnabled(false);
        }

        public void GameStop(){
            simpleGame.setEnabled(true);
            generalGame.setEnabled(true);
        }
    }
    class LeftBottomPanel extends JPanel {
        private JCheckBox recordOption;
        private GameLogic gameLogic;
        LeftBottomPanel(GameLogic gameLogic){
            this.gameLogic = gameLogic;
            recordOption = new JCheckBox("Record Game");
            recordOption.setFont(GUI.font);

            setLayout(new BorderLayout());

            recordOption.setBorder(new EmptyBorder(10,10,10,10));
            recordOption.addActionListener(new RecordBoxListener());

            add(recordOption, BorderLayout.SOUTH);
        }
        public void TurnOffRecording(){
            boolean selected = recordOption.getModel().isSelected();
            if(selected){
                recordOption.doClick();
            }
        }
        private class RecordBoxListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                boolean selected = abstractButton.getModel().isSelected();
                gameLogic.setRecording(selected);
            }
        }
        public void GameStart(GameLogic gameLogic){
            this.gameLogic = gameLogic;
            recordOption.setEnabled(false);
        }

        public void GameStop(){
            recordOption.setEnabled(true);
        }
    }
    class RightTopPanel extends JPanel {
        private GameLogic gameLogic;
        private JSpinner boardSizeInput;
        private GUI gui;
        RightTopPanel(GameLogic gameLogic, GUI gui){
            this.gui = gui;
            this.gameLogic = gameLogic;
            GridBagConstraints gbc = new GridBagConstraints();

            setLayout(new GridBagLayout());
            JLabel sizeLabel = new JLabel("Board size: ");
            boardSizeInput = new JSpinner(new SpinnerNumberModel(GameLogic.DEFAULT_DIMENSION,GameLogic.BOARD_MIN,GameLogic.BOARD_MAX,1));

            JComponent comp = boardSizeInput.getEditor();
            JFormattedTextField field = (JFormattedTextField) comp.getComponent(0);
            DefaultFormatter formatter = (DefaultFormatter) field.getFormatter();
            formatter.setCommitsOnValidEdit(true);
            boardSizeInput.addChangeListener(new RightTopPanel.SizeListener());

            gbc.anchor = GridBagConstraints.LINE_END;
            gbc.gridy = 0;
            gbc.gridx = 0;
            add(sizeLabel, gbc);

            gbc.gridx = 1;
            add(boardSizeInput, gbc);
        }
        public int getBoardSizeInput(){
            return (Integer) boardSizeInput.getValue();
        }
        public void setBoardSizeInput(int size){
            boardSizeInput.setValue(size);
            gui.getCenterPanel().gameBoardPanel.SizeChange((Integer) boardSizeInput.getValue());
        }
        public void GameStart(GameLogic gameLogic){
            this.gameLogic = gameLogic;
            boardSizeInput.setEnabled(false);
        }
        private class SizeListener implements ChangeListener {
            private SizeListener() {
            }
            @Override
            public void stateChanged(ChangeEvent e) {
                gui.getCenterPanel().gameBoardPanel.SizeChange((Integer) boardSizeInput.getValue());
            }
        }

        public void GameStop(){
            boardSizeInput.setEnabled(true);
            boardSizeInput.setValue(gameLogic.getTotalColumns());
        }
    }
    class RightBottomPanel extends JPanel {
        private GameLogic gameLogic;
        private GUI gui;
        private JButton initiateGameButton;
        private JButton replayButton;
        RightBottomPanel(GameLogic gameLogic, GUI gui){
            this.gameLogic = gameLogic;
            this.gui = gui;
            GridBagConstraints gbc = new GridBagConstraints();

            initiateGameButton = new JButton("Start");
            replayButton = new JButton("Replay");
            replayButton.addActionListener(new ReplayButtonListener());

            initiateGameButton.addActionListener(new InitiateGameButtonListener());

            setLayout(new GridBagLayout());
            setBorder(new EmptyBorder(0,0,10,10));

            gbc.gridx = 0;
            gbc.weightx = 0.8;
            add(new JLabel(), gbc);

            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.weighty = 1;
            gbc.weightx = 1.6;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.anchor = GridBagConstraints.LAST_LINE_END;

            add(initiateGameButton, gbc);
            gbc.gridy = -1;
            gbc.weighty = 0;
            gbc.anchor = GridBagConstraints.LAST_LINE_END;
            gbc.insets = new Insets(8,0,0,0);

            add(replayButton, gbc);

            initiateGameButton.setPreferredSize(new Dimension((int) (getWidth() * 1.6), 26));
            replayButton.setPreferredSize(new Dimension((int) (getWidth() * 1.6), 26));
        }
        private class InitiateGameButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                if(gameLogic.getGameState() == GameLogic.GameState.IDLE) {
                    //I might need to change this since it doesn't make sense to start a replay from here
                        gui.GameStart();

                }
                else {
                    gui.GameStop();
                }
            }
        }
        private class ReplayButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                //game should start immediatly after sleecting file like with replay mode
                JFileChooser fileChooser = new JFileChooser(new File("GameFiles")); // open file chooser dialog
                int status = fileChooser.showOpenDialog(null); // returns the dialog box status
                if (status == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String filename = selectedFile.getPath();
                    gui.ReplayMode(filename);
                   /* try {
                        Scanner sc = new Scanner(new File(filename));
 *//*                       while (sc.hasNextLine()) {
                            System.out.println(sc.nextLine());
                            System.out.println("test");
                        }*//*
                        while (sc.hasNext()) {
                            System.out.println(sc.next());
                            System.out.println("test");
                        }
                    }
                    catch(IOException ignored){
                    }*/
//                    System.out.println(new File(filename));

//                    ReplayMode();
                }
            }
        }
        public void GameStart(GameLogic gameLogic){
            this.gameLogic = gameLogic;
            initiateGameButton.setText("New Game");
            replayButton.setEnabled(false);
        }

        public void GameStop(){
            initiateGameButton.setText("Start");
            replayButton.setEnabled(true);
        }
    }
}