package gui.helpers;

import utils.Utilities;

import javax.swing.*;
import java.awt.*;

public class StringDialog extends BaseDialog {
    private String answer;

    private JLabel enterTextLabel;
    private JTextField answerTextField;

    public StringDialog(Dimension minSize, String label) {
        super(minSize);
        enterTextLabel = new JLabel(label + ": ");
        Utilities.setDefaultColors(enterTextLabel);

        answerTextField = new JTextField();
        Utilities.setDefaultColors(answerTextField);
        answerTextField.setColumns(30);

        addComponentToCenterPanel(enterTextLabel, answerTextField);

    }

    public String getAnswer() {
        return answer;
    }

    @Override
    public void onOk() {
        answer = answerTextField.getText();
    }

    @Override
    public void onCancel() {
        System.out.println("Canceled");
    }
}
