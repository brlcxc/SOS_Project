package sprint1_0.product;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

    final int WINDOW_WIDTH = 700;
    final int WINDOW_HEIGHT = 440;
    private BluePlayerPanel bluePlayerPanel;
    private RedPlayerPanel redPlayerPanel;
    private CenterPanel centerPanel;
    private GameLogic gameLogic;
    private GameBoardPanel gameBoardPanel;


    public GUI(){
        setContentPane();
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo((Component)null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("SOS");
        setVisible(true);
    }
    private void setContentPane(){
        GridBagConstraints gbc = new GridBagConstraints();

        gameLogic = new GameLogic();
        gameBoardPanel = new GameBoardPanel(gameLogic);
        centerPanel = new CenterPanel(gameBoardPanel);
        bluePlayerPanel = new BluePlayerPanel();
        redPlayerPanel = new RedPlayerPanel(gameBoardPanel, gameLogic);

        gameLogic.initGame(3,3);

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
        gbc.weighty = 1;
        contentPane.add(redPlayerPanel.bottomPanel, gbc);
    }

    private void resizeBoard() {
        //I need to use a component listener for resizing
    }

        public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GUI();
            }
        });
    }
}