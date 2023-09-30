package sprint2_2.product;

import javax.swing.*;
import java.awt.*;

public class CenterPanel extends JPanel {
    public GameBoardPanel gameBoardPanel;
    private GameLogic gameLogic;
    private JLabel turnInfo;
//    CenterPanel(GameBoardPanel gameBoardPanel, GameLogic gameLogic) {
    CenterPanel(GameLogic gameLogic) {

    GridBagConstraints gbc = new GridBagConstraints();
        gameBoardPanel = new GameBoardPanel(this, gameLogic);
        setLayout(new GridBagLayout());

//        this.gameBoardPanel = gameBoardPanel;
        this.gameLogic = gameLogic;

        JLabel title = new JLabel("SOS");
        title.setFont(new Font("SansSerif", 1, 28));
        turnInfo = new JLabel("Press \"Start\" to begin");

        gbc.insets = new Insets(10,10,10,10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        add(title, gbc);

        gbc.gridy = -1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        //this does work
//        JPanel outerPanel = new JPanel();
//        outerPanel.setPreferredSize(new Dimension(260,260));
//        outerPanel.add(gameBoardPanel);
//        add(outerPanel, gbc);
        add(gameBoardPanel, gbc);

        gbc.gridy = -2;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        add(turnInfo, gbc);
//        add(new JLabel("Current turn: " + gameLogic.getTurn()), gbc);
        //I need to call repaint here
    }
    public void updateTurn(){
        if(gameLogic.getTurn() % 2 == 1) {
            turnInfo.setText("Turn " + gameLogic.getTurn());
            turnInfo.setForeground(Color.BLUE);
        }
        else {
            turnInfo.setText("Turn " + gameLogic.getTurn());
            turnInfo.setForeground(Color.RED);
        }
        repaint();
    }
    //set turn function
    //maybe disable buttons not in use
    //maybe I need to call a class that gameboard has access to so that it can call center panel
}