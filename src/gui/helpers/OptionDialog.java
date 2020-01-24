package gui.helpers;

import utils.Utilities;

import javax.swing.*;
import java.awt.*;

public class OptionDialog extends BaseDialog {
    private JLabel label;
    private JComboBox<String> optionsCB;
    private String selectedOption;

    public OptionDialog(String label, String... options) {
        super(new Dimension(150, 150));

        if (options != null) {
            optionsCB = new JComboBox<>();
            for (String s : options)
                optionsCB.addItem(s);
        } else {
            optionsCB = new JComboBox<>();
            optionsCB.addItem("");
        }

        this.label = new JLabel(label);
        Utilities.setDefaultColors(this.label);

        JPanel tempPanel = new JPanel();
        Utilities.setDefaultColors(tempPanel);
        tempPanel.add(this.label);
        tempPanel.add(optionsCB);
        getCenterPanel().add(tempPanel);
    }

    public OptionDialog(Dimension minSize, String... options) {
        super(minSize);
        if (options != null) {
            optionsCB = new JComboBox<>();
            for (String s : options)
                optionsCB.addItem(s);
        } else {
            optionsCB.addItem("");
        }
        JPanel tempPanel = new JPanel();
        Utilities.setDefaultColors(tempPanel);
        tempPanel.add(optionsCB);
        getCenterPanel().add(tempPanel);
    }

    public String getSelectedOption() {
        return selectedOption;
    }

    @Override
    public void onOk() {
        selectedOption = (String) optionsCB.getSelectedItem();
    }

    @Override
    public void onCancel() {
        System.out.println("Canceled!");
    }
}
