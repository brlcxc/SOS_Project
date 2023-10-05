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
    JPanel playerOptionPanel;
    JPanel topPanel;
    JPanel bottomPanel;
    private JRadioButton humanOption;
    private JRadioButton computerOption;
    private JRadioButton sOption;
    private JRadioButton oOption;
    private ButtonGroup playerGroup;
    private ButtonGroup moveGroup;
//    CenterPanel centerPanel;
//    private GameBoardPanel gameBoardPanel;
//    private CenterPanel centerPanel;
    private GameLogic gameLogic;
    private JSpinner boardSizeInput;
    private JButton initiateGameButton;
    private JButton replayButton;
    private GUI gui;
//    private GameLogic.Cell moveType;
    RedPlayerPanel(GUI gui, GameLogic gameLogic) {
        this.gui = gui;
//        this.centerPanel = centerPanel;
//        gameBoardPanel = centerPanel.gameBoardPanel;
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
//        spin.addChangeListener();

        //code taken from stack overflow
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
        //call repaint once actiavted
    }
    private void setBottomPanel(){
        GridBagConstraints gbc = new GridBagConstraints();

        bottomPanel = new JPanel();
//        bottomPanel.setBackground(Color.BLUE);
        initiateGameButton = new JButton("Start");
        replayButton = new JButton("Replay");

//        initiateGameButton.setPreferredSize(new Dimension((int) (bottomPanel.getWidth() * 1.5), 24));
//        replayButton.setPreferredSize(new Dimension((int) (bottomPanel.getWidth() * 1.5), 24));

        initiateGameButton.addActionListener(new InitiateGameButtonListener());

        //maybe have one button that changes text depening on if the game is ongoing or not
        //quit or pause button shows up near turn when replay is selected
        //maybe an increment button while paused too?

        //change new game to start game

        bottomPanel.setLayout(new GridBagLayout());
        bottomPanel.setBorder(new EmptyBorder(0,0,10,10));

        gbc.gridx = 0;
        gbc.weightx = 0.8;
        bottomPanel.add(new JLabel(), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weighty = 1;
//        gbc.gridx = 1;
//        gbc.weightx = 0.2;
        gbc.weightx = 1.6;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.LAST_LINE_END;
//        gbc.insets = new Insets(0,0,0,10);

        bottomPanel.add(initiateGameButton, gbc);
        gbc.gridy = -1;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.LAST_LINE_END;
        gbc.insets = new Insets(8,0,0,0);

        bottomPanel.add(replayButton, gbc);
//        bottomPanel.setBackground(Color.BLUE);
        //change y weight to lower down
        //idk why these lines are even needed
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
//                spin.setValue(gameLogic.getBoardSize());
        initiateGameButton.setText("New Game");
//        gameLogic.startGame((Integer) boardSizeInput.getValue(), (Integer) boardSizeInput.getValue());
//        boardSizeInput.setValue(gameLogic.getBoardDimension());
//        centerPanel.updateTurnDisplay();
    }

    public void RedPlayerGameStop(){
        boardSizeInput.setEnabled(true);
        boardSizeInput.setValue(gameLogic.getBoardDimension());
        initiateGameButton.setText("Start");
//                gameLogic.initGame(gameLogic.getBoardDimension(), gameLogic.getBoardDimension());
//        gameLogic.initGame(gameLogic.getBoardDimension());
//                gameBoardPanel.SizeChange(gameLogic.getBoardDimension());
//        centerPanel.updateTurnDisplay();
    }

    private class SizeListener implements ChangeListener {
        private SizeListener() {
            //I need to call a class from gameboard pannel which might be hard
        }
        @Override
        public void stateChanged(ChangeEvent e) {
//            centerPanel.ChangeGameBoardSize((Integer) spin.getValue());
            //I should instead have size change in logic and then have this call logic - game board should then calllogic
            //I might need to function calls though if I want everything full seperate
//            initiateGameButton.setPreferredSize(new Dimension((int) (bottomPanel.getWidth() * 0.5), 26));
//        replayButton.setPreferredSize(new Dimension((int) (bottomPanel.getWidth() * 0.5), 26));
            gui.centerPanel.gameBoardPanel.SizeChange((Integer) boardSizeInput.getValue());
            System.out.println("test5");
//            System.out.println(replayButton.getWidth() + " " + replayButton.getHeight());

//            gameLogic.initGame((Integer) spin.getValue(), (Integer) spin.getValue());
            //there has to be a better way than calling a function that calls a function
        }
    }
    private class SButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
//            if(gameLogic.getTurn() % 2 == 0) {
//                gameBoardPanel.updateMoveType(GameLogic.Cell.S);
//            }            //maybe I need a set move type too
            gameLogic.updateRedPlayerMove(GameLogic.Cell.S);
        }
    }
    private class OButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
//            if(gameLogic.getTurn() % 2 == 0) {
//                gameBoardPanel.updateMoveType(GameLogic.Cell.O);
//            }
            gameLogic.updateRedPlayerMove(GameLogic.Cell.O);

        }
    }
    private class InitiateGameButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //set button to inative when a player wins
            //maybe start game can be a popup in the center and have new game always to the side
            //are you sure you want to start a new game confirmation
            if(gameLogic.getGameState() == GameLogic.GameState.IDLE) {
                gui.GameStart();
//                boardSizeInput.setEnabled(false);
////                spin.setValue(gameLogic.getBoardSize());
//                initiateGameButton.setText("New Game");
//                gameLogic.startGame((Integer) boardSizeInput.getValue(), (Integer) boardSizeInput.getValue());
//                boardSizeInput.setValue(gameLogic.getBoardDimension());
//                centerPanel.updateTurnDisplay();
                //I need these functions moved within GUI
                //I can also change button color from green to red to reflect a game start
            } else if (gameLogic.getGameState() == GameLogic.GameState.PLAYING) {
                    gui.GameStop();
//                boardSizeInput.setEnabled(true);
//                boardSizeInput.setValue(gameLogic.getBoardDimension());
//                initiateGameButton.setText("Start");
////                gameLogic.initGame(gameLogic.getBoardDimension(), gameLogic.getBoardDimension());
//                gameLogic.initGame(gameLogic.getBoardDimension());
////                gameBoardPanel.SizeChange(gameLogic.getBoardDimension());
//                centerPanel.updateTurnDisplay();
                //press start to being game
                //make button size a ration of red panel
            }
        }
    }
//    public GameLogic.Cell getMoveType(){
//        return moveType;
//    }
}
