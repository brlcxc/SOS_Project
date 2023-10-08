package sprint2_4.product;

import javax.swing.*;
import java.awt.*;

import static java.lang.Math.min;
//import sprint2_1.unnamed.jpg;

public class GUI extends JFrame {
    public BluePlayerPanel bluePlayerPanel;
    public RedPlayerPanel redPlayerPanel;
    public CenterPanel centerPanel;
    private final int WINDOW_WIDTH = 700;
    private final int WINDOW_HEIGHT = 440;
    private GameLogic gameLogic;

    public GUI(){
        setContentPane();
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo((Component)null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("SOS");
        setVisible(true);
        System.out.println(redPlayerPanel.topPanel.getWidth());
        System.out.println(bluePlayerPanel.topPanel.getWidth());
        resizeBoard();
    }
    private void setContentPane(){
        GridBagConstraints gbc = new GridBagConstraints();

        gameLogic = new GameLogic();
        centerPanel = new CenterPanel(this, gameLogic);
        bluePlayerPanel = new BluePlayerPanel(this, gameLogic);
        redPlayerPanel = new RedPlayerPanel(this, gameLogic);

        gameLogic.initGame();

        Container contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.27;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1;
        contentPane.add(bluePlayerPanel.topPanel, gbc);

        gbc.gridy = -1;
        gbc.weighty = 0;
        contentPane.add(bluePlayerPanel.playerOptionPanel, gbc);

        gbc.gridy = -2;
        gbc.weighty = 1;
        contentPane.add(bluePlayerPanel.bottomPanel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 4;
        gbc.weightx = 0.46;
        contentPane.add(centerPanel, gbc);

        gbc.gridx = 2;
        gbc.gridheight = 1;
        gbc.weightx = 0.27;
        gbc.anchor = GridBagConstraints.LAST_LINE_END;
        contentPane.add(redPlayerPanel.topPanel, gbc);

        gbc.gridy = -1;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.LAST_LINE_END;
        contentPane.add(redPlayerPanel.playerOptionPanel, gbc);

        gbc.gridy = -2;
        gbc.weighty = 0;
        contentPane.add(redPlayerPanel.bottomPanel, gbc);

        redPlayerPanel.updateButtonSize();
    }

    //adjusts the size of both panels to make them an even width
    private void resizeBoard() {
        int minWidth = min(redPlayerPanel.topPanel.getWidth(), bluePlayerPanel.topPanel.getWidth());
        redPlayerPanel.topPanel.setSize(new Dimension(minWidth, redPlayerPanel.topPanel.getHeight()));
        bluePlayerPanel.topPanel.setSize(new Dimension(minWidth, redPlayerPanel.topPanel.getHeight()));
    }

    //start method public to all other gui classes
    //calls start method in other gui classes
    public void GameStart(){
        redPlayerPanel.RedPlayerGameStart();
        bluePlayerPanel.BluePlayerGameStart();
        gameLogic.startGame(redPlayerPanel.getBoardSizeInput());
        centerPanel.updateTurnDisplay();
    }
    //stop method public to all other gui classes
    //calls stop method in other gui classes
    public void GameStop(){
        redPlayerPanel.RedPlayerGameStop();
        bluePlayerPanel.BluePlayerGameStop();
        gameLogic.initGame();
        centerPanel.updateTurnDisplay();

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GUI();
            }
        });
    }
}