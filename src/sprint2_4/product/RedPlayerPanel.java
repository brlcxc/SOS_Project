package sprint2_4.product;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.DefaultFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RedPlayerPanel extends JPanel {
    private GameLogic gameLogic;
    public JPanel playerOptionPanel;
    public JPanel topPanel;
    public JPanel bottomPanel;
    private JRadioButton humanOption;
    private JRadioButton computerOption;
    private JRadioButton sOption;
    private JRadioButton oOption;
    private ButtonGroup playerGroup;
    private ButtonGroup moveGroup;
    private JSpinner boardSizeInput;
    private JButton initiateGameButton;
    private JButton replayButton;
    private GUI gui;
    RedPlayerPanel(GUI gui, GameLogic gameLogic) {
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
        gbc.anchor = GridBagConstraints.EAST;
        playerOptionPanel.add(new JLabel("Player2"), gbc);

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
        topPanel.add(sizeLabel, gbc);

        gbc.gridx = 1;
        topPanel.add(boardSizeInput, gbc);
    }
    private void setBottomPanel(){
        GridBagConstraints gbc = new GridBagConstraints();

        bottomPanel = new JPanel();
        initiateGameButton = new JButton("Start");
        replayButton = new JButton("Replay");

        initiateGameButton.addActionListener(new InitiateGameButtonListener());

        bottomPanel.setLayout(new GridBagLayout());
        bottomPanel.setBorder(new EmptyBorder(0,0,10,10));

        gbc.gridx = 0;
        gbc.weightx = 0.8;
        bottomPanel.add(new JLabel(), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weighty = 1;
        gbc.weightx = 1.6;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.LAST_LINE_END;

        bottomPanel.add(initiateGameButton, gbc);
        gbc.gridy = -1;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.LAST_LINE_END;
        gbc.insets = new Insets(8,0,0,0);

        bottomPanel.add(replayButton, gbc);

        initiateGameButton.setPreferredSize(new Dimension((int) (bottomPanel.getWidth() * 1.6), 26));
        replayButton.setPreferredSize(new Dimension((int) (bottomPanel.getWidth() * 1.6), 26));
        System.out.println(replayButton.getWidth() + " " + replayButton.getHeight());
    }

    public void updateButtonSize(){}
    public int getBoardSizeInput(){
        return (Integer) boardSizeInput.getValue();
    }
    public void RedPlayerGameStart(){
        boardSizeInput.setEnabled(false);
        initiateGameButton.setText("New Game");
    }

    public void RedPlayerGameStop(){
        boardSizeInput.setEnabled(true);
        boardSizeInput.setValue(gameLogic.getBoardDimension());
        initiateGameButton.setText("Start");
    }

    private class SizeListener implements ChangeListener {
        private SizeListener() {
        }
        @Override
        public void stateChanged(ChangeEvent e) {
            gui.centerPanel.gameBoardPanel.SizeChange((Integer) boardSizeInput.getValue());
            System.out.println("test5");
        }
    }
    private class SButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            gameLogic.updateRedPlayerMove(GameLogic.Cell.S);
        }
    }
    private class OButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            gameLogic.updateRedPlayerMove(GameLogic.Cell.O);
        }
    }
    private class InitiateGameButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(gameLogic.getGameState() == GameLogic.GameState.IDLE) {
                gui.GameStart();
            } else if (gameLogic.getGameState() == GameLogic.GameState.PLAYING) {
                    gui.GameStop();
            }
        }
    }
}
