package sprint2_1.product;

import javax.swing.*;
import java.awt.*;

public class CenterPanel extends JPanel {
    private GameBoardPanel gameBoardPanel;
    CenterPanel(GameBoardPanel gameBoardPanel) {
        GridBagConstraints gbc = new GridBagConstraints();

        setLayout(new GridBagLayout());

        this.gameBoardPanel = gameBoardPanel;

        JLabel title = new JLabel("SOS");
        title.setFont(new Font("SansSerif", 1, 28));

        gbc.insets = new Insets(10,10,10,10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        add(title, gbc);

        gbc.gridy = -1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(gameBoardPanel, gbc);

        gbc.gridy = -2;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        add(new JLabel("Current turn: ..."), gbc);
    }
}