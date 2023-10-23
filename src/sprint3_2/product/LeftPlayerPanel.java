package sprint3_2.product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LeftPlayerPanel extends JPanel {
    private GameLogic gameLogic;
    private JRadioButton humanOption;
    private JRadioButton computerOption;
    private JRadioButton sOption;
    private JRadioButton oOption;
    LeftPlayerPanel(GameLogic gameLogic){
        this.gameLogic = gameLogic;
        GridBagConstraints gbc = new GridBagConstraints();

        humanOption = new JRadioButton("Human");
        computerOption = new JRadioButton("Computer");
        sOption = new JRadioButton("S");
        oOption = new JRadioButton("O");

        sOption.addActionListener(new SButtonListener());
        oOption.addActionListener(new OButtonListener());

        ButtonGroup playerGroup = new ButtonGroup();
        playerGroup.add(humanOption);
        playerGroup.add(computerOption);

        ButtonGroup moveGroup = new ButtonGroup();
        moveGroup.add(sOption);
        moveGroup.add(oOption);

        setLayout(new GridBagLayout());

        gbc.insets = new Insets(10,10,10,10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        add(new JLabel("Player1"), gbc);

        gbc.insets = new Insets(5,10,5,10);
        gbc.gridy = -1;
        add(humanOption, gbc);

        gbc.insets = new Insets(5,20,5,20);
        gbc.gridy = -2;
        add(sOption, gbc);

        gbc.gridy = -3;
        add(oOption, gbc);

        gbc.insets = new Insets(5,10,5,10);
        gbc.gridy = -4;
        add(computerOption, gbc);

        humanOption.doClick();
        sOption.doClick();
    }
    //Sets the current blue player move to S
    private class SButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            gameLogic.setBluePlayerMove(GameLogic.Cell.S);
        }
    }
    //Sets the current blue player move to S
    private class OButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            gameLogic.setBluePlayerMove(GameLogic.Cell.O);

        }
    }
    //Not fully in use yet
    private class HumanButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        }
    }
    //Not fully in use yet
    private class ComputerButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        }
    }
    public void GameStart(){
    }

    public void GameStop(){
    }
}

//maybe just have the button listners change the color of the other players button