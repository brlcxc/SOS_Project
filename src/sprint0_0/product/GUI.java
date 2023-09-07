package sprint0_0.product;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

    final int WINDOW_WIDTH = 900;
    final int WINDOW_HEIGHT = 500;
//    private GameBoardCanvas gameBoardCanvas;
    private BluePlayerPanel bluePlayerPanel;
    private RedPlayerPanel redPlayerPanel;
    private GameBoardPanel gameBoardPanel;


    public GUI(){
        setContentPane();
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo((Component)null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("SOS");
        setVisible(true);
        System.out.println(gameBoardPanel.getWidth());
        gameBoardPanel.setSize(gameBoardPanel.getWidth(), gameBoardPanel.getWidth());
        //maybe make it a number divisible by something?

    }
    private void setContentPane(){
        GridBagConstraints gbc = new GridBagConstraints();
        bluePlayerPanel = new BluePlayerPanel();
        redPlayerPanel = new RedPlayerPanel();
        gameBoardPanel = new GameBoardPanel();
//        gameBoardCanvas = new GameBoardCanvas();
//        CANVAS_WIDTH = CELL_SIZE * 3;
//        CANVAS_HEIGHT = CELL_SIZE * 3;
//        gameBoardCanvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
//
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.27;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
//        gbc.fill = GridBagConstraints.VERTICAL;
//        gbc.weighty = 1;
        contentPane.add(bluePlayerPanel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.46;
        contentPane.add(gameBoardPanel, gbc);

        //for game panel I need to utlize the getsize command

        gbc.gridx = 2;
        gbc.weightx = 0.27;
        contentPane.add(redPlayerPanel, gbc);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GUI();
            }
        });
    }
}