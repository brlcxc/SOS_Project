package sprint0_1.product;

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
    JRadioButton humanOption;
    JRadioButton computerOption;
    JRadioButton sOption;
    JRadioButton oOption;
    ButtonGroup playerGroup;
    ButtonGroup moveGroup;
    CenterPanel centerPanel;
    JSpinner spin;
    RedPlayerPanel(CenterPanel centerPanel) {
        this.centerPanel = centerPanel;
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
        spin = new JSpinner(new SpinnerNumberModel(3,3,16,1));
//        spin.addChangeListener();

        //code taken from stack overflow
        JComponent comp = spin.getEditor();
        JFormattedTextField field = (JFormattedTextField) comp.getComponent(0);
        DefaultFormatter formatter = (DefaultFormatter) field.getFormatter();
        formatter.setCommitsOnValidEdit(true);
        spin.addChangeListener(new SizeListener());

        gbc.anchor = GridBagConstraints.LINE_END;
        topPanel.add(spin, gbc);
        //call repaint once actiavted
    }
    private void setBottomPanel(){
        bottomPanel = new JPanel();

        bottomPanel.setLayout(new BorderLayout());
    }

    private class SizeListener implements ChangeListener {
        private SizeListener() {
            //I need to call a class from gameboard pannel which might be hard
        }
        @Override
        public void stateChanged(ChangeEvent e) {
            centerPanel.ChangeGameBoardSize((Integer) spin.getValue());
            //there has to be a better way than calling a function that calls a function
        }
    }
}
