package gui.components;

import utils.Utilities;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    public MainPanel() {
        Utilities.setDefaultColors(this);
        setLayout(new BorderLayout());
    }
}
