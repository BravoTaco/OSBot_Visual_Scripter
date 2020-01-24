package gui.paint.nodes.interaction;

import gui.helpers.BaseDialog;
import utils.Utilities;

import javax.swing.*;
import java.awt.*;

public class InteractionDialog extends BaseDialog {
    private final String[] typeOfObjects = new String[]{"NPC", "Player", "GroundItem", "Object"};
    private JComboBox<String> typeOfObject;
    private JLabel nameOfObjectLabel;
    private JTextField nameOfObjectTextField;
    private JLabel interactionStringLabel;
    private JTextField interactionStringTextField;
    private String selectedObjectType;
    private String objectName;
    private String interactionString;

    public InteractionDialog() {
        super(new Dimension(400, 300));

        typeOfObject = new JComboBox<>();
        Utilities.setDefaultColors(typeOfObject);
        for (String s : typeOfObjects)
            typeOfObject.addItem(s);

        addComponentToCenterPanel(typeOfObject);

        nameOfObjectTextField = new JTextField();
        nameOfObjectTextField.setColumns(30);
        Utilities.setDefaultColors(nameOfObjectTextField);

        nameOfObjectLabel = new JLabel("Name: ");
        Utilities.setDefaultColors(nameOfObjectLabel);

        addComponentToCenterPanel(nameOfObjectLabel, nameOfObjectTextField);

        interactionStringTextField = new JTextField();
        interactionStringTextField.setColumns(30);
        Utilities.setDefaultColors(interactionStringTextField);

        interactionStringLabel = new JLabel("Interaction: ");
        Utilities.setDefaultColors(interactionStringLabel);

        addComponentToCenterPanel(interactionStringLabel, interactionStringTextField);

    }

    public String getObjectName() {
        return objectName;
    }

    public String getSelectedObjectType() {
        return selectedObjectType;
    }

    public String getInteractionString() {
        return interactionString;
    }

    @Override
    public void onOk() {
        selectedObjectType = (String) typeOfObject.getSelectedItem();
        objectName = nameOfObjectTextField.getText();
        interactionString = interactionStringTextField.getText();
    }

    @Override
    public void onCancel() {
        System.out.println("Canceled!");
    }
}
