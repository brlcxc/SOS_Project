package sprint2_3.product;

import javax.swing.*;
import java.awt.*;

public class CenterPanel extends JPanel {
    public GameBoardPanel gameBoardPanel;
    private GameLogic gameLogic;
    private JLabel turnInfo;
    CenterPanel(GameLogic gameLogic) {

    GridBagConstraints gbc = new GridBagConstraints();
        gameBoardPanel = new GameBoardPanel(this, gameLogic);
        setLayout(new GridBagLayout());

        this.gameLogic = gameLogic;

        JLabel title = new JLabel("SOS");
        title.setFont(new Font("SansSerif", 1, 34));
        turnInfo = new JLabel("Press \"Start\" to begin");

        gbc.insets = new Insets(10,10,0,10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        add(title, gbc);
        gbc.insets = new Insets(10,10,10,10);
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
    public void updateTurnDisplay(){
        if(gameLogic.getTurn() == 0){
            turnInfo.setText("Press \"Start\" to begin");
//            turnInfo.setForeground(Color.BLACK);
        }
        else if(gameLogic.getTurn() % 2 == 1) {
//            turnInfo.setText("Player1 Turn: " + gameLogic.getTurn());
            turnInfo.setText("Player1 Turn");
//            turnInfo.setForeground(Color.BLUE);
        }
        else {
//            turnInfo.setText("Player2 Turn: " + gameLogic.getTurn());
            turnInfo.setText("Player2 Turn");
//            turnInfo.setForeground(Color.RED);
        }
        repaint();
        //turn 7: Player2's move
    }
    //set turn function
    //maybe disable buttons not in use
}