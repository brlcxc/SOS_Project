package sprint3_1.product;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.DefaultFormatter;
import java.awt.*;

public class RightTopPanel extends JPanel {
    private GameLogic gameLogic;
    private JSpinner boardSizeInput;
    private GUI gui;
    RightTopPanel(GameLogic gameLogic, GUI gui){
        this.gui = gui;
        this.gameLogic = gameLogic;
        GridBagConstraints gbc = new GridBagConstraints();

        setLayout(new GridBagLayout());
        JLabel sizeLabel = new JLabel("Board size: ");
        boardSizeInput = new JSpinner(new SpinnerNumberModel(GameLogic.DEFAULT_DIMENSION,GameLogic.BOARD_MIN,GameLogic.BOARD_MAX,1));

        JComponent comp = boardSizeInput.getEditor();
        JFormattedTextField field = (JFormattedTextField) comp.getComponent(0);
        DefaultFormatter formatter = (DefaultFormatter) field.getFormatter();
        formatter.setCommitsOnValidEdit(true);
        boardSizeInput.addChangeListener(new SizeListener());

        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.gridy = 0;
        gbc.gridx = 0;
        add(sizeLabel, gbc);

        gbc.gridx = 1;
        add(boardSizeInput, gbc);
    }
    public int getBoardSizeInput(){
        return (Integer) boardSizeInput.getValue();
    }
    public void GameStart(){
        boardSizeInput.setEnabled(false);
    }
    private class SizeListener implements ChangeListener {
        private SizeListener() {
        }
        @Override
        public void stateChanged(ChangeEvent e) {
            gui.getCenterPanel().gameBoardPanel.SizeChange((Integer) boardSizeInput.getValue());
        }
    }

    public void GameStop(){
        boardSizeInput.setEnabled(true);
        boardSizeInput.setValue(gameLogic.getBoardDimension());
    }
}
