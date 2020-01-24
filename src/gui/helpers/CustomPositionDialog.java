package gui.helpers;

import data.Globals;
import data.Int3;
import gui.components.Button;
import org.osbot.rs07.api.map.Position;
import utils.Utilities;

import javax.swing.*;
import java.awt.*;

public class CustomPositionDialog extends BaseDialog {

    private JLabel message;
    private JPanel xPanel;
    private JLabel xSpinnerLabel;
    private JSpinner xSpinner;
    private JPanel yPanel;
    private JLabel ySpinnerLabel;
    private JSpinner ySpinner;
    private JPanel zPanel;
    private JLabel zSpinnerLabel;
    private JSpinner zSpinner;

    private Button getCurrentPositionButton;

    private Int3 customPosition;

    public CustomPositionDialog(Dimension minSize) {
        super(minSize);

        SpinnerModel xSpinnerModel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 5);
        SpinnerModel ySpinnerModel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 5);
        SpinnerModel zSpinnerModel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 5);

        message = new JLabel("Input the X, Y, Z of the custom position.");
        Utilities.setDefaultColors(message);

        //xSetup
        xPanel = new JPanel();
        Utilities.setDefaultColors(xPanel);
        xSpinnerLabel = new JLabel("X: ");
        Utilities.setDefaultColors(xSpinnerLabel);
        xPanel.add(xSpinnerLabel);
        xSpinner = new JSpinner();
        Utilities.setDefaultColors(xSpinner);
        xSpinner.setModel(xSpinnerModel);
        xPanel.add(xSpinner);

        //ySetup
        yPanel = new JPanel();
        Utilities.setDefaultColors(yPanel);
        ySpinnerLabel = new JLabel("Y: ");
        Utilities.setDefaultColors(ySpinnerLabel);
        yPanel.add(ySpinnerLabel);
        ySpinner = new JSpinner();
        Utilities.setDefaultColors(ySpinner);
        ySpinner.setModel(ySpinnerModel);
        yPanel.add(ySpinner);

        //zSetup
        zPanel = new JPanel();
        Utilities.setDefaultColors(zPanel);
        zSpinnerLabel = new JLabel("Z: ");
        Utilities.setDefaultColors(zSpinnerLabel);
        zPanel.add(zSpinnerLabel);
        zSpinner = new JSpinner();
        Utilities.setDefaultColors(zSpinner);
        zSpinner.setModel(zSpinnerModel);
        zPanel.add(zSpinner);

        addComponentToCenterPanel(message, xPanel, yPanel, zPanel);

        getCurrentPositionButton = new Button("Load Current Position") {
            @Override
            public void onClick() {
                Position position = Globals.getBot().getMethods().myPlayer().getPosition();
                xSpinner.setValue(position.getX());
                ySpinner.setValue(position.getY());
                zSpinner.setValue(position.getZ());
            }
        };

        addComponentsToBottomWestPanel(getCurrentPositionButton);

    }

    public Int3 getCustomPosition() {
        return customPosition;
    }

    private int getIntFromObject(Object value) {
        return (int) value;
    }

    @Override
    public void onOk() {
        customPosition = new Int3(
                getIntFromObject(xSpinner.getValue()),
                getIntFromObject(ySpinner.getValue()),
                getIntFromObject(zSpinner.getValue()));
    }

    @Override
    public void onCancel() {

    }
}
