package sprint3_0.product;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LeftBottomPanel extends JPanel {
    private JCheckBox recordOption;
    private GameLogic gameLogic;
    LeftBottomPanel(GameLogic gameLogic){
        this.gameLogic = gameLogic;
        recordOption = new JCheckBox("Record Game");

        setLayout(new BorderLayout());

        recordOption.setBorder(new EmptyBorder(10,10,10,10));

        add(recordOption, BorderLayout.SOUTH);
    }
    public void GameStart(){
        recordOption.setEnabled(false);
    }

    public void GameStop(){
        recordOption.setEnabled(true);
    }
}
