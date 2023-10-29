package sprint3_2.product;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RightBottomPanel extends JPanel {
    private GameLogic gameLogic;
    private GUI gui;
    private JButton initiateGameButton;
    private JButton replayButton;
    RightBottomPanel(GameLogic gameLogic, GUI gui){
        this.gameLogic = gameLogic;
        this.gui = gui;
        GridBagConstraints gbc = new GridBagConstraints();

        initiateGameButton = new JButton("Start");
        replayButton = new JButton("Replay");

        initiateGameButton.addActionListener(new InitiateGameButtonListener());

        setLayout(new GridBagLayout());
        setBorder(new EmptyBorder(0,0,10,10));

        gbc.gridx = 0;
        gbc.weightx = 0.8;
        add(new JLabel(), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weighty = 1;
        gbc.weightx = 1.6;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.LAST_LINE_END;

        add(initiateGameButton, gbc);
        gbc.gridy = -1;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.LAST_LINE_END;
        gbc.insets = new Insets(8,0,0,0);

        add(replayButton, gbc);

        initiateGameButton.setPreferredSize(new Dimension((int) (getWidth() * 1.6), 26));
        replayButton.setPreferredSize(new Dimension((int) (getWidth() * 1.6), 26));
    }
    private class InitiateGameButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(gameLogic.getGameState() == GameLogic.GameState.IDLE) {
                gui.GameStart();
            }
            else {
                gui.GameStop();
            }
//            else if (gameLogic.getGameState() == GameLogic.GameState.PLAYING) {
//                gui.GameStop();
//            }
        }
    }
    public void GameStart(GameLogic gameLogic){
        this.gameLogic = gameLogic;
        initiateGameButton.setText("New Game");
    }

    public void GameStop(){
        initiateGameButton.setText("Start");
    }
}
