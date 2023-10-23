package sprint3_2.product;

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
        leftTopPanel.GameStart();
        leftPlayerPanel.GameStart();
        leftBottomPanel.GameStart();
        rightTopPanel.GameStart();
        rightPlayerPanel.GameStart();
        rightBottomPanel.GameStart();
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GUI();
            }
        });
    }
}

//mmaybe make top cent and top bottom pannels to further space everything out