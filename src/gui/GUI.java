package gui;

import data.Globals;
import gui.components.BottomPanel;
import gui.components.MainPanel;
import gui.components.NodePanel;
import gui.components.TopPanel;
import gui.data.Buttons;
import org.osbot.rs07.script.MethodProvider;
import utils.Utilities;
import utils.WebMethods;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GUI extends MethodProvider {
    private static GUI instance;
    private boolean executeClicked = false;
    private JFrame mainFrame;
    private MainPanel mainPanel;
    private BottomPanel bottomPanel;
    private TopPanel topPanel;
    private NodePanel nodePanel;

    private GUI() {
        mainFrame = new JFrame();
        Utilities.setDefaultColors(mainFrame);
        mainFrame.setMinimumSize(new Dimension(1280, 720));
        mainFrame.setIconImage(WebMethods.getImageFromURL("https://i.imgur.com/6TGi40D.png"));
        mainFrame.setTitle("OsRs Visual Scripter");
        mainFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        mainPanel = new MainPanel();
        mainFrame.setContentPane(mainPanel);

        bottomPanel = new BottomPanel(true, true, true);
        bottomPanel.addComponentsToWestPanel(
                Buttons.getInstance().getLoadButton(),
                Buttons.getInstance().getSaveButton());
        bottomPanel.addComponentsToEastPanel(
                Buttons.getInstance().getExitButton(),
                Buttons.getInstance().getExecuteButton());
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        topPanel = new TopPanel();
        topPanel.addComponentsToWestPanel(
                Buttons.getInstance().getInteractionNodeButton(),
                Buttons.getInstance().getInventoryNodeButton(),
                Buttons.getInstance().getPlayerNodeButton(),
                Buttons.getInstance().getSleepNodeButton(),
                Buttons.getInstance().getWalkerNodeButton(),
                Buttons.getInstance().getBankNodeButton(),
                Buttons.getInstance().getWidgetsButton(),
                Buttons.getInstance().getUtilsNodeButton());
        topPanel.addComponentsToEastPanel(
                Buttons.getInstance().getInformationButton());
        mainPanel.add(topPanel, BorderLayout.NORTH);

        nodePanel = new NodePanel();
        mainPanel.add(nodePanel, BorderLayout.CENTER);

        mainFrame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (!executeClicked && e.getKeyCode() == 127) {
                    if (Globals.getSelectedNode() != null)
                        Globals.getSelectedNode().addToDestroyQueue();
                    if (Globals.getSelectedConnector() != null && Globals.getSelectedConnector().getConnectedConnector() != null) {
                        Globals.getSelectedConnector().setConnectedConnector(null);
                        Globals.setSelectedConnector(null);
                    }
                } else if (executeClicked && e.getKeyCode() == 127) {
                    Globals.getBot().getScriptExecutor().getCurrent().log("Can not use the delete feature while the script is running! Please pause it first.");
                }
                switch (e.getKeyCode()) {
                    case 37:
                        Globals.setMoveNodesLeft(true);
                        break;
                    case 38:
                        Globals.setMoveNodesUp(true);
                        break;
                    case 39:
                        Globals.setMoveNodesRight(true);
                        break;
                    case 40:
                        Globals.setMoveNodesDown(true);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    public static GUI getInstance() {
        if (instance == null)
            instance = new GUI();

        return instance;
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }

    public NodePanel getNodePanel() {
        return nodePanel;
    }

    public void start() {
        executeClicked = false;
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
        mainFrame.requestFocus();
    }

    public void exit() {
        System.out.println("Closing the GUI");
        executeClicked = false;
        mainFrame.dispose();
        bot.getScriptExecutor().getCurrent().stop(false);
        System.out.println("GUI Closed!");
    }

    public void pause() {
        executeClicked = false;
        Buttons.getInstance().getExecuteButton().setText("Execute");
        mainFrame.setState(Frame.NORMAL);
    }

    public void execute() {
        executeClicked = true;
        Buttons.getInstance().getExecuteButton().setText("Pause");
        mainFrame.setState(Frame.ICONIFIED);
    }

    public boolean isExecuteClicked() {
        return executeClicked;
    }

    public boolean wasClosed() {
        return !mainFrame.isVisible() && !executeClicked;
    }
}
