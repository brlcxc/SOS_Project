package sprint0_1.product;

import javax.swing.*;
import java.awt.*;

public class CenterPanel extends JPanel {
//    public static final int GAME_BOARD_SIZE = 250;
//    public static final int CELL_SIZE = GAME_BOARD_SIZE / 3;
//    public static final int GRID_WIDTH = 8;
//    public static final int GRID_WIDHT_HALF = GRID_WIDTH / 2;
    GameBoardPanel gameBoardPanel;
    JLabel title;
    CenterPanel() {
        GridBagConstraints gbc = new GridBagConstraints();

        setLayout(new GridBagLayout());

        gameBoardPanel = new GameBoardPanel();

        title = new JLabel("SOS");
        title.setFont(new Font("SansSerif", 1, 28));

        gbc.insets = new Insets(10,10,10,10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        add(title, gbc);

        gbc.gridy = -1;
        gbc.weighty = 1;
        add(gameBoardPanel, gbc);

        gbc.gridy = -2;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        add(new JLabel("Current turn: ..."), gbc);
    }

}