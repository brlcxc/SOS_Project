package sprint2_3.product;

import javax.swing.*;
import java.awt.*;

import static java.lang.Math.min;
//import sprint2_1.unnamed.jpg;

public class GUI extends JFrame {

    final int WINDOW_WIDTH = 700;
    final int WINDOW_HEIGHT = 440;
    private BluePlayerPanel bluePlayerPanel;
    private RedPlayerPanel redPlayerPanel;
    private CenterPanel centerPanel;
    private GameLogic gameLogic;
//    private GameBoardPanel gameBoardPanel;


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
//        gameBoardPanel = new GameBoardPanel(gameLogic);
        //maybe I need to make gameboard panel public within central and just pass central to everyone rather than gameboard
        //this would allow everyone to access it
//        centerPanel = new CenterPanel(gameBoardPanel, gameLogic);
        centerPanel = new CenterPanel(gameLogic);
        bluePlayerPanel = new BluePlayerPanel(centerPanel, gameLogic);
        redPlayerPanel = new RedPlayerPanel(centerPanel, gameLogic);

        gameLogic.initGame(GameLogic.DEFAULT_DIMENSION);

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

    private void resizeBoard() {
        int minWidth = min(redPlayerPanel.topPanel.getWidth(), bluePlayerPanel.topPanel.getWidth());
        redPlayerPanel.topPanel.setSize(new Dimension(minWidth, redPlayerPanel.topPanel.getHeight()));
        bluePlayerPanel.topPanel.setSize(new Dimension(minWidth, redPlayerPanel.topPanel.getHeight()));
        //I need to use a component listener for resizing
        System.out.println(redPlayerPanel.topPanel.getWidth());
        System.out.println(bluePlayerPanel.topPanel.getWidth());
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GUI();
            }
        });
    }
}