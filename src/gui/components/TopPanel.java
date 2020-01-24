package gui.components;

import utils.Utilities;

import javax.swing.*;
import java.awt.*;

public class TopPanel extends JPanel {
    private JPanel westPanel;
    private JPanel centerPanel;
    private JPanel eastPanel;

    public TopPanel() {
        Utilities.setDefaultColors(this);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEtchedBorder());

        westPanel = new JPanel();
        Utilities.setDefaultColors(westPanel);
        add(westPanel, BorderLayout.WEST);

        centerPanel = new JPanel();
        Utilities.setDefaultColors(centerPanel);
        add(centerPanel, BorderLayout.CENTER);

        eastPanel = new JPanel();
        Utilities.setDefaultColors(eastPanel);
        add(eastPanel, BorderLayout.EAST);
    }

    public void addComponentsToWestPanel(Component... components) {
        for (Component component : components) {
            westPanel.add(component);
        }
    }

    public void addComponentsToCenterPanel(Component... components) {
        for (Component component : components) {
            centerPanel.add(component);
        }
    }

    public void addComponentsToEastPanel(Component... components) {
        for (Component component : components) {
            eastPanel.add(component);
        }
    }


}
