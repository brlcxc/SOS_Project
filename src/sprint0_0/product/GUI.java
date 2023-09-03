package sprint0_0.product;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

    final int WINDOW_WIDTH = 900;
    final int WINDOW_HEIGHT = 500;


    public GUI(){
        setContentPane();
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setLocationRelativeTo((Component)null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setTitle("Tic Tac Toe");
        setVisible(true);
    }
    private void setContentPane(){
//        gameBoardCanvas = new GameBoardCanvas();
//        CANVAS_WIDTH = CELL_SIZE * 3;
//        CANVAS_HEIGHT = CELL_SIZE * 3;
//        gameBoardCanvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
//
//        Container contentPane = getContentPane();
//        contentPane.setLayout(new BorderLayout());
//        contentPane.add(gameBoardCanvas, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GUI();
            }
        });
    }
}