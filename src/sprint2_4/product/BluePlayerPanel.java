package sprint2_4.product;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BluePlayerPanel {
    public JPanel playerOptionPanel;
    public JPanel topPanel;
    public JPanel bottomPanel;
    private JRadioButton simpleGame;
    private JRadioButton generalGame;
    private JRadioButton humanOption;
    private JRadioButton computerOption;
    private JRadioButton sOption;
    private JRadioButton oOption;
    private JCheckBox recordOption;
    private GameLogic gameLogic;
    private GUI gui;
    BluePlayerPanel(GUI gui, GameLogic gameLogic) {
        this.gui = gui;
        this.gameLogic = gameLogic;

        setPlayerOptionPanel();
        setTopPanel();
        setBottomPanel();
    }

    private void setPlayerOptionPanel(){
        GridBagConstraints gbc = new GridBagConstraints();

        playerOptionPanel = new JPanel();

        humanOption = new JRadioButton("Human");
        computerOption = new JRadioButton("Computer");
        sOption = new JRadioButton("S");
        oOption = new JRadioButton("O");
        recordOption = new JCheckBox("Record Game");

        sOption.addActionListener(new SButtonListener());
        oOption.addActionListener(new OButtonListener());

        ButtonGroup playerGroup = new ButtonGroup();
        playerGroup.add(humanOption);
        playerGroup.add(computerOption);

        ButtonGroup moveGroup = new ButtonGroup();
        moveGroup.add(sOption);
        moveGroup.add(oOption);

        playerOptionPanel.setLayout(new GridBagLayout());

        gbc.insets = new Insets(10,10,10,10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        playerOptionPanel.add(new JLabel("Player1"), gbc);

        gbc.insets = new Insets(5,10,5,10);
        gbc.gridy = -1;
        playerOptionPanel.add(humanOption, gbc);

        gbc.insets = new Insets(5,20,5,20);
        gbc.gridy = -2;
        playerOptionPanel.add(sOption, gbc);

        gbc.gridy = -3;
        playerOptionPanel.add(oOption, gbc);

        gbc.insets = new Insets(5,10,5,10);
        gbc.gridy = -4;
        playerOptionPanel.add(computerOption, gbc);

        humanOption.doClick();
        sOption.doClick();
    }
    private void setTopPanel(){
        GridBagConstraints gbc = new GridBagConstraints();

        topPanel = new JPanel();
        topPanel.setLayout(new GridBagLayout());

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
        topPanel.add(gameModeLabel, gbc);

        gbc.gridy = -1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(2,3,0,3);
        topPanel.add(simpleGame, gbc);

        gbc.gridx = 1;
        topPanel.add(generalGame,gbc);

        simpleGame.doClick();
    }
    private void setBottomPanel(){
        bottomPanel = new JPanel();

        recordOption = new JCheckBox("Record Game");

        bottomPanel.setLayout(new BorderLayout());

        recordOption.setBorder(new EmptyBorder(10,10,10,10));

        bottomPanel.add(recordOption, BorderLayout.SOUTH);
    }
    public void BluePlayerGameStart(){
//        humanOption.setEnabled(false);
//        computerOption.setEnabled(false);
        simpleGame.setEnabled(false);
        generalGame.setEnabled(false);
        recordOption.setEnabled(false);
    }

    public void BluePlayerGameStop(){
//        humanOption.setEnabled(true);
//        computerOption.setEnabled(true);
        //maybe make s and o only visable if human option is selected
        simpleGame.setEnabled(true);
        generalGame.setEnabled(true);
        recordOption.setEnabled(true);
    }
    private class SButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            gameLogic.setBluePlayerMove(GameLogic.Cell.S);
        }
    }
    private class OButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            gameLogic.setBluePlayerMove(GameLogic.Cell.O);

        }
    }
    private class SimpleGameButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            gameLogic.setGameMode(GameLogic.GameMode.SIMPLE);
        }
    }
    private class GeneralGameButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            gameLogic.setGameMode(GameLogic.GameMode.GENERAL);
        }
    }
    private class HumanButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        }
    }
    private class ComputerButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        }
    }
}