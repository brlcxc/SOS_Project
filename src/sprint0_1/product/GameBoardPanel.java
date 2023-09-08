package sprint0_1.product;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class GameBoardPanel extends JPanel {
    JPanel test;
    JLabel title;
    GameBoardPanel() {
        GridBagConstraints gbc = new GridBagConstraints();
//        setBackground(Color.black);
        Border blackline = BorderFactory.createLineBorder(Color.black);
        setLayout(new GridBagLayout());
        test = new JPanel();
        test.setBorder(blackline);

        title = new JLabel("SOS");
        title.setFont(new Font("SansSerif", 1, 28));
//        test.setSize(getWidth() - 20, getWidth() - 20);

//        gbc.ipady = 20;
        gbc.insets = new Insets(10,10,10,10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
//        gbc.anchor = GridBagConstraints.NORTH;
//        gbc.ipadx = 20;
        add(title, gbc);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1;
//        gbc.ipady = 50;
        gbc.gridy = -1;
//        gbc.anchor = GridBagConstraints.SOUTH;
        add(test, gbc);
        gbc.gridy = -2;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.CENTER;
//        gbc.fill = GridBagConstraints.NONE;
        add(new JLabel("Current turn: ..."), gbc);
        System.out.println(getWidth());

    }
}
//maybe I need three different sections to divide out the space and I wont have to worry about allignmen t

//maybe I dont need to resize the panel but rather the object within based off panel size