package sprint2_2.product;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BluePlayerPanel {
    JPanel playerOptionPanel;
    JPanel topPanel;
    JPanel bottomPanel;
    JRadioButton humanOption;
    JRadioButton computerOption;
    JRadioButton sOption;
    JRadioButton oOption;
    JRadioButton simpleGame;
    JRadioButton generalGame;
    private ButtonGroup gameModeGroup;
    JCheckBox recordOption;
    ButtonGroup playerGroup;
    ButtonGroup moveGroup;
    GameLogic gameLogic;
    GameBoardPanel gameBoardPanel;
    BluePlayerPanel(CenterPanel centerPanel, GameLogic gameLogic) {
        this.gameLogic = gameLogic;
        gameBoardPanel = centerPanel.gameBoardPanel;

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

        playerGroup = new ButtonGroup();
        playerGroup.add(humanOption);
        playerGroup.add(computerOption);

        moveGroup = new ButtonGroup();
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
//        System.out.println(topPanel.getWidth());
    }
    private void setTopPanel(){
        GridBagConstraints gbc = new GridBagConstraints();
        topPanel = new JPanel();
        topPanel.setLayout(new GridBagLayout());

        simpleGame = new JRadioButton("Simple");
        generalGame = new JRadioButton("General");
        JLabel gameModeLabel = new JLabel("Game Mode");

        moveGroup = new ButtonGroup();
        moveGroup.add(simpleGame);
        moveGroup.add(generalGame);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        topPanel.add(gameModeLabel, gbc);

        gbc.gridy = -1;
        gbc.gridwidth = 1;
//        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(2,3,0,3);

//        gbc.fill = GridBagConstraints.HORIZONTAL;
//        gbc.weightx = 1;
//        gbc.anchor = GridBagConstraints.PAGE_START;
        topPanel.add(simpleGame, gbc);
        gbc.gridx = 1;
        topPanel.add(generalGame,gbc);

        simpleGame.doClick();
//        topPanel.setBackground(Color.BLUE);
//        topPanel.add(simpleGame);
//        topPanel.add(generalGame);
    }
    private void setBottomPanel(){
        bottomPanel = new JPanel();

        recordOption = new JCheckBox("Record Game");

        bottomPanel.setLayout(new BorderLayout());

        recordOption.setBorder(new EmptyBorder(10,10,10,10));

        bottomPanel.add(recordOption, BorderLayout.SOUTH);
    }
    private class SButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
//            if(gameLogic.getTurn() % 2 != 0) {
//                gameBoardPanel.updateMoveType(GameLogic.Cell.S);
//            }            //maybe I need a set move type too
            gameLogic.updateBluePlayerMove(GameLogic.Cell.S);
        }
    }
    private class OButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
//            if(gameLogic.getTurn() % 2 != 0) {
//                gameBoardPanel.updateMoveType(GameLogic.Cell.O);
//            }
            gameLogic.updateBluePlayerMove(GameLogic.Cell.O);

        }
    }
    //I am running into an issue since it is an action listener - even though it is the proper turn the data wont be sent sent the button isnt necesarily pressed
    //maybe I ned to do the %2 in centeral and then call the proper panel to send move data from there
}