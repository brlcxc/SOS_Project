package sprint3_1.product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LeftTopPanel extends JPanel {
    private JRadioButton simpleGame;
    private JRadioButton generalGame;
    private GameLogic gameLogic;
    LeftTopPanel(GameLogic gameLogic){
        this.gameLogic = gameLogic;
        GridBagConstraints gbc = new GridBagConstraints();

        setLayout(new GridBagLayout());

        JLabel gameModeLabel = new JLabel("Game Mode");
        simpleGame = new JRadioButton("Simple");
        generalGame = new JRadioButton("General");

        simpleGame.addActionListener(new SimpleGameButtonListener());
        generalGame.addActionListener(new GeneralGameButtonListener());

        ButtonGroup gameModeGroup = new ButtonGroup();
        gameModeGroup.add(simpleGame);
        gameModeGroup.add(generalGame);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(gameModeLabel, gbc);

        gbc.gridy = -1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(2,3,0,3);
        add(simpleGame, gbc);

        gbc.gridx = 1;
        add(generalGame,gbc);

        simpleGame.doClick();
    }
    //Calls the logic method to set game mode to SIMPLE
    private class SimpleGameButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            gameLogic.setGameMode(GameLogic.GameMode.SIMPLE);
        }
    }
    //Calls the logic method to set game mode to GENERAL
    private class GeneralGameButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            gameLogic.setGameMode(GameLogic.GameMode.GENERAL);
        }
    }
    public void GameStart(){
        simpleGame.setEnabled(false);
        generalGame.setEnabled(false);
    }

    public void GameStop(){
        simpleGame.setEnabled(true);
        generalGame.setEnabled(true);
    }
}
