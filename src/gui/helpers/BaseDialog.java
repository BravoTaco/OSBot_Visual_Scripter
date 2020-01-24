package gui.helpers;

import gui.components.BottomPanel;
import gui.components.Button;
import gui.components.MainPanel;
import utils.Utilities;

import javax.swing.*;
import java.awt.*;

public abstract class BaseDialog extends JDialog {
    private MainPanel mainPanel;
    private BottomPanel bottomPanel;
    private JPanel centerPanel;
    private JPanel buttonsPanel;
    private JPanel westBottomPanel;
    private Button okButton;
    private Button cancelButton;

    private boolean okClicked;

    public BaseDialog(Dimension minSize) {
        Utilities.setDefaultColors(this);
        setMinimumSize(minSize);
        setModal(true);
        setModalityType(ModalityType.APPLICATION_MODAL);

        mainPanel = new MainPanel();
        add(mainPanel);

        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
        Utilities.setDefaultColors(centerPanel);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        bottomPanel = new BottomPanel(false, false, true);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        buttonsPanel = new JPanel();
        Utilities.setDefaultColors(buttonsPanel);

        okButton = new Button("Ok") {
            @Override
            public void onClick() {
                setVisible(false);
                okClicked = true;
                dispose();
                onOk();
            }
        };

        cancelButton = new Button("Cancel") {
            @Override
            public void onClick() {
                setVisible(false);
                dispose();
                onCancel();
            }
        };

        buttonsPanel.add(cancelButton);
        buttonsPanel.add(okButton);

        bottomPanel.add(buttonsPanel, BorderLayout.EAST);

        westBottomPanel = new JPanel();
        Utilities.setDefaultColors(westBottomPanel);
        bottomPanel.add(westBottomPanel, BorderLayout.WEST);

        JPanel spacer = new JPanel();
        spacer.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        Utilities.setDefaultColors(spacer);
        centerPanel.add(spacer);
    }

    public void start() {
        pack();
        setMinimumSize(getSize());
        setPreferredSize(getMinimumSize());
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public boolean wasClosed() {
        return !isVisible() && !okClicked;
    }

    public JPanel getCenterPanel() {
        return centerPanel;
    }

    public void addComponentToCenterPanel(Component... components) {
        for (Component component : components) {
            JPanel tempPanel = new JPanel();
            Utilities.setDefaultColors(tempPanel);
            tempPanel.add(component);
            centerPanel.add(tempPanel);
        }
    }

    public void addComponentsToBottomWestPanel(Component... components) {
        for (Component component : components)
            westBottomPanel.add(component);
    }

    public abstract void onOk();

    public abstract void onCancel();
}
