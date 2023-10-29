package sprint3_2.product;

import sprint2_1.product.GameBoardPanel;

import javax.swing.*;
import java.awt.*;

import static java.lang.Math.min;
//import sprint2_1.unnamed.jpg;

public class GUI extends JFrame {
//    private BluePlayerPanel bluePlayerPanel;
    public static Font font;
    private LeftTopPanel leftTopPanel;
    private LeftPlayerPanel leftPlayerPanel;
    private LeftBottomPanel leftBottomPanel;
    private RightTopPanel rightTopPanel;
    private RightPlayerPanel rightPlayerPanel;
    private RightBottomPanel rightBottomPanel;
//    private RedPlayerPanel redPlayerPanel;
    private CenterPanel centerPanel;
    private final int WINDOW_WIDTH = 700;
    private final int WINDOW_HEIGHT = 440;
    private GameLogic gameLogic;

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
        gameLogic = new GameLogic();
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

//        bottomRightPanel.updateButtonSize();
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
//        redPlayerPanel.RedPlayerGameStart();
//        bluePlayerPanel.BluePlayerGameStart();
        //maybe all the new class instances can be passed here?
        //makes starting a new gaem after one is over hard though
        //maybe not too hard since nothing is actually concrete in game logic until the game starts
        //currently I have the same game logic for as long as the program runs - I will have to reset the instance when using inheritance
        //maybe use player moves as paramters for new instance
        if(gameLogic.getGameMode() == GameLogic.GameMode.SIMPLE){
////            gameLogic = null;
//            GameLogic newGameLogic = new SimpleGameLogic(gameLogic);
//            gameLogic = null;
            gameLogic = new SimpleGameLogic(gameLogic.getRedPlayerMove(), gameLogic.getBluePlayerMove());
            //what is the term for having a sub class of type parent class
        }
        else{
            gameLogic = new GeneralGameLogic(gameLogic.getRedPlayerMove(), gameLogic.getBluePlayerMove());
        }
        gameLogic.initGame();
        leftTopPanel.GameStart(gameLogic);
        leftPlayerPanel.GameStart(gameLogic);
        leftBottomPanel.GameStart(gameLogic);
        rightTopPanel.GameStart(gameLogic);
        rightPlayerPanel.GameStart(gameLogic);
        rightBottomPanel.GameStart(gameLogic);
        centerPanel.GameStart(gameLogic);
        //game logic can recall all the set functions once the new instance is passed
        //I need to ensure all necesary data is transfered to each new instance
        gameLogic.startGame(rightTopPanel.getBoardSizeInput());
        centerPanel.updateTurnDisplay();
    }
    //stop method public to all other gui classes
    //calls stop method in other gui classes
    public void GameStop(){
//        redPlayerPanel.RedPlayerGameStop();
//        bluePlayerPanel.BluePlayerGameStop();
        leftTopPanel.GameStop();
        leftPlayerPanel.GameStop();
        leftBottomPanel.GameStop();
        rightTopPanel.GameStop();
        rightPlayerPanel.GameStop();
        rightBottomPanel.GameStop();
        gameLogic.initGame();
        centerPanel.updateTurnDisplay();

    }
    public void GameEnd(){
//        redPlayerPanel.RedPlayerGameStop();
//        bluePlayerPanel.BluePlayerGameStop();
        //I want board marks to be visible
        //new game to be visible
        //who won to be visible
        //press "new game" to play again to be visible
        //so o moves to be locked? - might not be necesary
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
}

//mmaybe make top cent and top bottom pannels to further space everything out