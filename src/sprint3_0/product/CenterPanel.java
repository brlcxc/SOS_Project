package sprint3_0.product;

import javax.swing.*;
import java.awt.*;

public class CenterPanel extends JPanel {
    public GameBoardPanel gameBoardPanel;
    private GameLogic gameLogic;
    private JLabel turnInfo;
    private GUI gui;
    CenterPanel(GUI gui, GameLogic gameLogic) {
        this.gui = gui;

        GridBagConstraints gbc = new GridBagConstraints();
        gameBoardPanel = new GameBoardPanel(gui,this, gameLogic);
        setLayout(new GridBagLayout());

        this.gameLogic = gameLogic;

        JLabel title = new JLabel("SOS");
        turnInfo = new JLabel("Press \"Start\" to begin");

        title.setFont(new Font("SansSerif", 1, 34));

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

    //displays text at bottom indicating the player trun
    public void updateTurnDisplay(){
        if(gameLogic.getTurn() == 0){
            turnInfo.setText("Press \"Start\" to begin");
        }
        else if(gameLogic.getBluePlayerTurn()) {
            turnInfo.setText("Player1 Turn");
        }
        else if(gameLogic.getRedPlayerTurn()){
            turnInfo.setText("Player2 Turn");
        }
        repaint();
    }
}