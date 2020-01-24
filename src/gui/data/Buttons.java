package gui.data;

import data.Globals;
import gui.GUI;
import gui.components.Button;
import gui.helpers.CustomPositionDialog;
import gui.helpers.InformationDialog;
import gui.helpers.OptionDialog;
import gui.helpers.StringDialog;
import gui.paint.nodes.bank.*;
import gui.paint.nodes.interaction.InteractionDialog;
import gui.paint.nodes.interaction.InteractionNode;
import gui.paint.nodes.inventory.*;
import gui.paint.nodes.player.IsAnimatingNode;
import gui.paint.nodes.player.IsFightingNode;
import gui.paint.nodes.sleeps.SleepNode;
import gui.paint.nodes.utilities.LogMessageNode;
import gui.paint.nodes.utilities.StopScriptNode;
import gui.paint.nodes.walker.WalkerNode;
import gui.paint.nodes.widgets.InteractWithWidgetNode;
import utils.*;

import javax.swing.*;
import java.awt.*;

public final class Buttons {
    private static Buttons instance;

    private Button executeButton;
    private Button exitButton;
    private Button saveButton;
    private Button loadButton;
    private Button interactionNodeButton;
    private Button playerNodeButton;
    private Button inventoryNodeButton;
    private Button sleepNodeButton;
    private Button utilsNodeButton;
    private Button walkerNodeButton;
    private Button bankNodeButton;
    private Button informationButton;
    private Button widgetsButton;

    private Buttons() {
        widgetsButton = new Button("Widgets") {
            @Override
            public void onClick() {
                StringDialog stringDialog = new StringDialog(new Dimension(400, 300), "Enter the interaction of the widget you are wanting to interact with: ");
                stringDialog.start();
                if (!stringDialog.wasClosed())
                    new InteractWithWidgetNode(stringDialog.getAnswer());
            }
        };

        executeButton = new Button("Execute") {
            @Override
            public void onClick() {
                if (getText().equals("Execute")) {
                    GUI.getInstance().execute();
                } else {
                    GUI.getInstance().pause();
                }
            }
        };

        exitButton = new Button("Exit") {
            @Override
            public void onClick() {
                GUI.getInstance().exit();
            }
        };

        saveButton = new Button("Save") {
            @Override
            public void onClick() {
                StringDialog stringDialog = new StringDialog(new Dimension(300, 300), "Name Of Save File: ");
                stringDialog.start();
                if (!stringDialog.wasClosed())
                    new Save(stringDialog.getAnswer()).saveFile();
            }
        };

        loadButton = new Button("Load") {
            @Override
            public void onClick() {
                OptionDialog optionDialog = new OptionDialog("Select File To Load: ", GetFilesFromFolder.getFileNamesFromFolder(Globals.getFilePath()));
                optionDialog.start();
                if (!optionDialog.wasClosed())
                    new Load(optionDialog.getSelectedOption()).loadFile();
            }
        };

        interactionNodeButton = new Button("Interaction") {
            @Override
            public void onClick() {
                if (Globals.getSelectedNode() == null && Globals.getSelectedConnector() == null) {
                    InteractionDialog interactionDialog = new InteractionDialog();
                    interactionDialog.start();
                    if (!interactionDialog.wasClosed()) {
                        new InteractionNode(interactionDialog.getSelectedObjectType(), interactionDialog.getObjectName(), interactionDialog.getInteractionString());
                    }
                }
            }
        };

        playerNodeButton = new Button("Player") {
            @Override
            public void onClick() {
                if (Globals.getSelectedNode() == null && Globals.getSelectedConnector() == null) {
                    OptionDialog optionDialog = new OptionDialog("If Player: ", "IsAnimating", "IsFighting");
                    optionDialog.start();
                    if (!optionDialog.wasClosed()) {
                        switch (optionDialog.getSelectedOption()) {
                            case "IsAnimating":
                                new IsAnimatingNode();
                                break;
                            case "IsFighting":
                                new IsFightingNode();
                                break;
                        }
                    }
                }
            }
        };

        inventoryNodeButton = new Button("Inventory") {
            @Override
            public void onClick() {
                if (Globals.getSelectedNode() == null && Globals.getSelectedConnector() == null) {
                    OptionDialog optionDialog = new OptionDialog("If: ", "Inventory Is Full", "Inventory Contains Item", "Drop All", "Drop All Except", "Combine Items");
                    optionDialog.start();
                    if (!optionDialog.wasClosed()) {
                        String[] text;
                        switch (optionDialog.getSelectedOption()) {
                            case "Inventory Contains Item":
                                text = Utilities.populateNodeWithItemNames("Input the item names that you want to check for. (Separate each name with a , )", "If", "InventoryContainsItems");
                                if (text != null)
                                    new InventoryContainsItemNode(text);
                                break;
                            case "Inventory Is Full":
                                new InventoryIsFullNode();
                                break;
                            case "Drop All":
                                new DropAllNode();
                                break;
                            case "Drop All Except":
                                text = Utilities.populateNodeWithItemNames("Input the item names that you don't want to drop. (Separate each name with a , )", "If", "DropAllItemsExcept");
                                if (text != null)
                                    new DropAllExceptNode(text);
                                break;
                            case "Combine Items":
                                text = Utilities.populateNodeWithItemNames("Input the two item names that you wish to combine together. (Separate each name with a , )", "If", "CombinedItems");
                                if (text != null)
                                    new CombineItemsNode(text);
                                break;
                        }
                    }
                }
            }
        };

        sleepNodeButton = new Button("Sleep") {
            @Override
            public void onClick() {
                OptionDialog optionDialog = new OptionDialog("Sleep Until: ",
                        "IsAnimating", "IsNotAnimating", "IsUnderAttack",
                        "IsNotUnderAttack", "BankIsOpen", "BankIsClosed", "PickedUpItem");
                optionDialog.start();
                if (!optionDialog.wasClosed())
                    new SleepNode(optionDialog.getSelectedOption());
            }
        };

        walkerNodeButton = new Button("Walker") {
            @Override
            public void onClick() {
                OptionDialog optionDialog = new OptionDialog("Walk To: ", "Lumbridge Upper Bank", "Varrock West Bank",
                        "Varrock East Bank", "GrandExchange", "Falador West Bank", "Falador East Bank", "Draynor Bank", "Edgeville Bank", "Custom Position");
                optionDialog.start();
                if (!optionDialog.wasClosed() && !optionDialog.getSelectedOption().equals("Custom Position")) {
                    new WalkerNode(optionDialog.getSelectedOption());
                } else if (!optionDialog.wasClosed()) {
                    CustomPositionDialog customPositionDialog = new CustomPositionDialog(new Dimension(300, 400));
                    customPositionDialog.start();
                    if (!customPositionDialog.wasClosed())
                        new WalkerNode("Custom Position", customPositionDialog.getCustomPosition());
                }
            }
        };

        bankNodeButton = new Button("Bank") {
            @Override
            public void onClick() {
                OptionDialog optionDialog = new OptionDialog("If: ", "Bank Is Open", "Open Bank", "Close Bank",
                        "Deposit All Items", "Deposit All Items Except", "Withdraw Items");
                optionDialog.start();
                if (!optionDialog.wasClosed()) {
                    String[] itemNames;
                    switch (optionDialog.getSelectedOption()) {
                        case "Deposit All Items Except":
                            itemNames = Utilities.populateNodeWithItemNames("Input items names that you wish to keep (Separate each item name with a , )", "If", "DepositAllItems", "Except");
                            new DepositAllItemsExceptNode(itemNames);
                            break;
                        case "Withdraw Items":
                            itemNames = Utilities.populateNodeWithItemNames("Input items names that you wish to withdraw (Separate each item name with a , )", "If", "Withdrawed");
                            new WithdrawItemsNode(itemNames);
                            break;
                        case "Bank Is Open":
                            new BankIsOpenNode();
                            break;
                        case "Open Bank":
                            new OpenBankNode();
                            break;
                        case "Close Bank":
                            new CloseBankNode();
                            break;
                        case "Deposit All Items":
                            new DepositAllItemsNode();
                            break;
                    }
                }
            }
        };

        utilsNodeButton = new Button("Utilities") {
            @Override
            public void onClick() {
                OptionDialog optionDialog = new OptionDialog("Select Utility: ", "Stop Script", "Log Message");
                optionDialog.start();
                if (!optionDialog.wasClosed()) {
                    switch (optionDialog.getSelectedOption()) {
                        case "Stop Script":
                            new StopScriptNode();
                            break;
                        case "Log Message":
                            StringDialog stringDialog = new StringDialog(new Dimension(400, 300), "Input the message you would like to print out: ");
                            stringDialog.start();
                            if (!stringDialog.wasClosed())
                                new LogMessageNode(stringDialog.getAnswer());
                    }
                }
            }
        };


        Icon icon = WebMethods.getIconFromURL("https://i.imgur.com/bgeRsFu.png");
        Icon hoveredIcon = WebMethods.getIconFromURL("https://i.imgur.com/qKGVqs1.png");
        informationButton = new Button(icon, hoveredIcon) {
            @Override
            public void onClick() {
                InformationDialog informationDialog = new InformationDialog(
                        "Use the arrow keys or click and drag, to move around the canvas.",
                        "To move a node click it, than move it to the desired location and click again.",
                        "To delete a node select it, than press the Delete key on the keyboard.",
                        "To delete an existing connection between two nodes select the connector, than press the Delete key on the keyboard.",
                        "The purple connectors on the left side of the node are where you insert the outcomes from other nodes.",
                        "The green connectors are one of the out connectors of the node that is right-top of the node. They represent if the node returned true.",
                        "The red connectors is the other out connector that is right-bottom of the node. They represent if the node returned false"
                );
                informationDialog.start();
            }
        };
    }

    public static Buttons getInstance() {
        if (instance == null)
            instance = new Buttons();

        return instance;
    }

    public Button getWidgetsButton() {
        return widgetsButton;
    }

    public Button getInformationButton() {
        return informationButton;
    }

    public Button getWalkerNodeButton() {
        return walkerNodeButton;
    }

    public Button getBankNodeButton() {
        return bankNodeButton;
    }

    public Button getExecuteButton() {
        return executeButton;
    }

    public Button getExitButton() {
        return exitButton;
    }

    public Button getSaveButton() {
        return saveButton;
    }

    public Button getLoadButton() {
        return loadButton;
    }

    public Button getInteractionNodeButton() {
        return interactionNodeButton;
    }

    public Button getPlayerNodeButton() {
        return playerNodeButton;
    }

    public Button getInventoryNodeButton() {
        return inventoryNodeButton;
    }

    public Button getSleepNodeButton() {
        return sleepNodeButton;
    }

    public Button getUtilsNodeButton() {
        return utilsNodeButton;
    }
}
