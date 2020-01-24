package gui.helpers;

import gui.components.Button;
import utils.Utilities;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class InformationDialog extends JDialog {
    private JPanel mainPanel;
    private JPanel bottomPanel;
    private JPanel eastBottomPanel;
    private JTextPane textPane;

    private Button okButton;

    public InformationDialog(String... text) {
        Utilities.setDefaultColors(this);
        setTitle("Information");
        setModal(true);

        mainPanel = new JPanel();
        Utilities.setDefaultColors(mainPanel);
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel);

        textPane = new JTextPane();
        Utilities.setDefaultColors(textPane);
        textPane.setFont(new Font("Sans Serif", Font.BOLD, 16));
        textPane.setEditable(false);
        mainPanel.add(textPane, BorderLayout.CENTER);

        bottomPanel = new JPanel();
        Utilities.setDefaultColors(bottomPanel);
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createEtchedBorder());
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        eastBottomPanel = new JPanel();
        Utilities.setDefaultColors(eastBottomPanel);
        bottomPanel.add(eastBottomPanel, BorderLayout.EAST);

        okButton = new Button("Ok") {
            @Override
            public void onClick() {
                onOk();
            }
        };

        eastBottomPanel.add(okButton);

        addText(text);

    }

    public void start() {
        pack();
        setMinimumSize(new Dimension(getWidth() + 30, getHeight() + 50));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void onOk() {
        setVisible(false);
        dispose();
    }

    private void addText(String... text) {
        StyledDocument doc = textPane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%s%s%s", "\n", "\n", "\n"));

        for (String s : text) {
            if (s.length() < 80)
                sb.append(String.format("• %s%s", s, '\n'));
            else {
                sb.append("• ");
                String[] splitString = new String[]{s.substring(0, s.indexOf(' ', 80)), s.substring(s.indexOf(' ', 80))};
                for (int i = 0; i < 2; i++) {
                    if (i == 1)
                        sb.append('\n');
                    sb.append(splitString[i]);
                }
                sb.append('\n');
            }

            sb.append('\n');

        }
        sb.append('\n');
        textPane.setText(sb.toString());
    }
}
