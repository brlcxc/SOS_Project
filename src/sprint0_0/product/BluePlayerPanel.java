package sprint0_0.product;

import javax.swing.*;
import java.awt.*;

public class BluePlayerPanel extends JPanel {
    //maybe two seperate classes that change some text and colors as well as the anchor
    JPanel playerOptionPanel;
    JPanel topPanel;
    JPanel bottomPanel;
    JRadioButton humanOption;
    JRadioButton computerOption;
    JRadioButton sOption;
    JRadioButton oOption;
    JCheckBox recordOption;
    ButtonGroup playerGroup;
    ButtonGroup moveGroup;
    BluePlayerPanel() {
        GridBagConstraints gbc = new GridBagConstraints();


        setPlayerOptionPanel();
        setTopPanel();
        setBottomPanel();

        setLayout(new GridBagLayout());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0.1;
        add(topPanel, gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1;
        gbc.weighty = 0.8;
        gbc.gridy = -1;
        add(playerOptionPanel, gbc);
        gbc.gridy = -2;
        gbc.weighty = 0.1;
        add(bottomPanel, gbc);
//        setBackground(Color.blue);
//        GridBagConstraints gbc = new GridBagConstraints();
//        setLayout(new GridBagLayout());
//
//        playerGroup = new ButtonGroup();
//        moveGroup = new ButtonGroup();
//
//        //I need to resize after it has been added
//        humanOption = new JRadioButton("Human");
//        computerOption = new JRadioButton("Computer");
//        sOption = new JRadioButton("S");
//        oOption = new JRadioButton("O");
//        recordOption = new JCheckBox("Record Game");
//
//        playerGroup.add(humanOption);
//        playerGroup.add(computerOption);
//
//        moveGroup.add(sOption);
//        moveGroup.add(oOption);
//
//        humanOption.setBackground(Color.green);
//
//        gbc.insets = new Insets(10,10,10,0);
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.weightx = 1;
//        gbc.anchor = GridBagConstraints.WEST;
//        gbc.anchor = GridBagConstraints.LAST_LINE_START;
//        gbc.weighty = 1;
//
//        add(new JLabel("Player1"), gbc);
//        gbc.insets = new Insets(5,10,5,10);
//        gbc.weighty = 0;
//        gbc.gridy = -1;
//        add(humanOption, gbc);
//        gbc.insets = new Insets(5,20,5,20);
//        gbc.gridy = -2;
//        add(sOption, gbc);
//        gbc.gridy = -3;
//        add(oOption, gbc);
//        gbc.insets = new Insets(5,10,5,10);
//        gbc.gridy = -4;
//        add(computerOption, gbc);
//        gbc.gridy = -5;
////        gbc.fill = GridBagConstraints.REMAINDER;
//        gbc.weighty = 1;
//        gbc.anchor = GridBagConstraints.LAST_LINE_START;
//        add(recordOption, gbc);
//
//        humanOption.doClick();
//        sOption.doClick();
//        //I proably need to switch this to gridbag layout
//
////        layout.putConstraint(SpringLayout.SOUTH, test2, 5, SpringLayout.NORTH, test1);
//
//        //I can just make a constructure to pass certain details

    }

    private void setPlayerOptionPanel(){
        //        setBackground(Color.blue);
        playerOptionPanel = new JPanel();

        GridBagConstraints gbc = new GridBagConstraints();
        playerOptionPanel.setLayout(new GridBagLayout());

        playerGroup = new ButtonGroup();
        moveGroup = new ButtonGroup();

        //I need to resize after it has been added
        humanOption = new JRadioButton("Human");
        computerOption = new JRadioButton("Computer");
        sOption = new JRadioButton("S");
        oOption = new JRadioButton("O");
        recordOption = new JCheckBox("Record Game");

        playerGroup.add(humanOption);
        playerGroup.add(computerOption);

        moveGroup.add(sOption);
        moveGroup.add(oOption);

        humanOption.setBackground(Color.green);

        gbc.insets = new Insets(10,10,10,0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        gbc.weighty = 1;

        playerOptionPanel.add(new JLabel("Player1"), gbc);
        gbc.insets = new Insets(5,10,5,10);
        gbc.weighty = 0;
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
        gbc.gridy = -5;
//        gbc.fill = GridBagConstraints.REMAINDER;
//        gbc.weighty = 1;
//        gbc.anchor = GridBagConstraints.LAST_LINE_START;
//        playerOptionPanel.add(recordOption, gbc);

        humanOption.doClick();
        sOption.doClick();
    }
    private void setTopPanel(){
        topPanel = new JPanel();
    }
    private void setBottomPanel(){
        GridBagConstraints gbc = new GridBagConstraints();

        bottomPanel = new JPanel();
//        bottomPanel.setBackground(Color.blue);

        bottomPanel.setLayout(new GridBagLayout());
        recordOption = new JCheckBox("Record Game");
        bottomPanel.setBackground(Color.blue);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.SOUTH;
        bottomPanel.add(recordOption, gbc);
    }

}

//long term I what buttons rather than checkboxes for the S and