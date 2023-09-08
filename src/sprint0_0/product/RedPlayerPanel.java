package sprint0_0.product;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class RedPlayerPanel extends JPanel {
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
    RedPlayerPanel() {
        GridBagConstraints gbc = new GridBagConstraints();


        setPlayerOptionPanel();
        setTopPanel();
        setBottomPanel();

        setLayout(new GridBagLayout());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0.1;
        add(topPanel, gbc);
        gbc.anchor = GridBagConstraints.EAST;
        gbc.weightx = 1;
        gbc.weighty = 0.8;
        gbc.gridy = -1;
        add(playerOptionPanel, gbc);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = -2;
        gbc.weighty = 0.1;
        add(bottomPanel, gbc);
    }

    private void setPlayerOptionPanel(){
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

//        humanOption.setBackground(Color.green);
        gbc.weightx = 1.0; // fill all available space horizontally
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10,10,10,10);
        gbc.gridx = 0;
        gbc.gridy = 0;
//        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.anchor = GridBagConstraints.LAST_LINE_END;
//        gbc.weighty = 1;

        playerOptionPanel.add(new JLabel("Player2"), gbc);
        gbc.insets = new Insets(5,10,5,10);
//        gbc.weighty = 0;
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
//        topPanel.setBackground(Color.blue);
    }
    private void setBottomPanel(){
        GridBagConstraints gbc = new GridBagConstraints();

        bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.blue);

        bottomPanel.setLayout(new BorderLayout());
//        bottomPanel.setLayout(new GridBagLayout());

        recordOption = new JCheckBox("Record Game");
//        bottomPanel.setBackground(Color.blue);

//        gbc.fill = GridBagConstraints.BOTH;
//        gbc.anchor = GridBagConstraints.LAST_LINE_START;
//        gbc.gridy = -1;
//        gbc.gridx = 0;
        bottomPanel.add(new JLabel("check"), BorderLayout.SOUTH);

//        recordOption.setBorder(new EmptyBorder(10,10,10,10));
//        bottomPanel.add(recordOption, BorderLayout.SOUTH);
//        bottomPanel.add(recordOption, gbc);
//        bottomPanel.add(recordOption);

    }

}


//import javax.swing.*;
//import java.awt.*;
//
//public class RedPlayerPanel extends JPanel {
//    //maybe two seperate classes that change some text and colors as well as the anchor
//    JPanel playerOptionPanel;
//    JRadioButton humanOption;
//    JRadioButton computerOption;
//    JRadioButton sOption;
//    JRadioButton oOption;
//    JCheckBox recordOption;
//    ButtonGroup playerGroup;
//    ButtonGroup moveGroup;
//    RedPlayerPanel() {
////        setBackground(Color.blue);
////        GridBagConstraints gbc = new GridBagConstraints();
////        setLayout(new GridBagLayout());
////
////        playerGroup = new ButtonGroup();
////        moveGroup = new ButtonGroup();
////
////        //I need to resize after it has been added
////        humanOption = new JRadioButton("Human");
////        computerOption = new JRadioButton("Computer");
////        sOption = new JRadioButton("S");
////        oOption = new JRadioButton("O");
////        recordOption = new JCheckBox("Record");
////
////        playerGroup.add(humanOption);
////        playerGroup.add(computerOption);
////
////        moveGroup.add(sOption);
////        moveGroup.add(oOption);
////
//////        humanOption.setBackground(Color.green);
////
////        gbc.insets = new Insets(10,10,10,10);
////        gbc.gridx = 0;
////        gbc.gridy = 0;
////        gbc.weightx = 1;
////        gbc.anchor = GridBagConstraints.EAST;
////        gbc.anchor = GridBagConstraints.LAST_LINE_END;
////        gbc.weighty = 1;
////
////        add(new JLabel("Player2"), gbc);
////        gbc.insets = new Insets(5,10,5,10);
////        gbc.weighty = 0;
////        gbc.gridy = -1;
////        add(humanOption, gbc);
////        gbc.insets = new Insets(5,20,5,20);
////        gbc.gridy = -2;
////        add(sOption, gbc);
////        gbc.gridy = -3;
////        add(oOption, gbc);
////        gbc.insets = new Insets(5,10,5,10);
////        gbc.gridy = -4;
////        add(computerOption, gbc);
////        gbc.gridy = -5;
//////        gbc.fill = GridBagConstraints.REMAINDER;
////        gbc.weighty = 1;
////        gbc.anchor = GridBagConstraints.LAST_LINE_START;
////        add(recordOption, gbc);
////
////        humanOption.doClick();
////        sOption.doClick();
//        //I proably need to switch this to gridbag layout
//
////        layout.putConstraint(SpringLayout.SOUTH, test2, 5, SpringLayout.NORTH, test1);
//
//        //I can just make a constructure to pass certain details
//
//    }
//
//}
