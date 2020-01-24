package gui.components;

import utils.Utilities;

import javax.swing.*;
import java.awt.*;

public class BottomPanel extends JPanel {
    private JPanel westPanel;
    private JPanel centerPanel;
    private JPanel eastPanel;

    public BottomPanel(boolean addWestPanel, boolean addCenterPanel, boolean addEastPanel) {
        Utilities.setDefaultColors(this);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEtchedBorder());

        if (addWestPanel) {
            westPanel = new JPanel();
            Utilities.setDefaultColors(westPanel);
            westPanel.setBorder(BorderFactory.createEtchedBorder());
            add(westPanel, BorderLayout.WEST);
        }

        if (addCenterPanel) {
            centerPanel = new JPanel();
            Utilities.setDefaultColors(centerPanel);
            centerPanel.setBorder(BorderFactory.createEtchedBorder());
            add(centerPanel, BorderLayout.CENTER);
        }

        if (addEastPanel) {
            eastPanel = new JPanel();
            Utilities.setDefaultColors(eastPanel);
            eastPanel.setBorder(BorderFactory.createEtchedBorder());
            add(eastPanel, BorderLayout.EAST);
        }
    }

    public void addComponentsToWestPanel(Component... components) {
        for (Component component : components)
            westPanel.add(component);
    }

    public void addComponentsToCenterPanel(Component... components) {
        for (Component component : components)
            centerPanel.add(component);
    }

    public void addComponentsToEastPanel(Component... components) {
        for (Component component : components)
            eastPanel.add(component);
    }
}
