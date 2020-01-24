package data;

import gui.GUI;
import gui.paint.Connector;
import gui.paint.Node;
import gui.paint.nodes.base_nodes.StartNode;
import org.osbot.rs07.Bot;
import org.osbot.rs07.script.Script;

import java.awt.*;
import java.io.File;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public final class Globals {
    private static HashSet<Node> nodes = new HashSet<>();
    private static HashSet<Connector> connectors = new HashSet<>();
    private static Queue<Node> nodesToAdd = new LinkedList<>();
    private static Queue<Connector> connectorsToAdd = new LinkedList<>();
    private static Queue<Node> nodesToDestroy = new LinkedList<>();
    private static Node currentHoveredNode = null;
    private static Node selectedNode = null;
    private static Node currentExecutingNode = null;
    private static StartNode startNode;
    private static Connector selectedConnector = null;
    private static Connector currentHoveredConnector = null;
    private static Bot bot;
    private static boolean moveNodesLeft;
    private static boolean moveNodesRight;
    private static boolean moveNodesUp;
    private static boolean moveNodesDown;

    private Globals() {
    }

    public static String getFilePath() {
        Script script = bot.getScriptExecutor().getCurrent();
        return script.getDirectoryData() + File.separator + script.getName() + File.separator;
    }

    public static Point getMousePointRelativeToClient(Bot bot) {
        Point mousePoint = MouseInfo.getPointerInfo().getLocation();
        Point clientPoint = bot.getCanvas().getLocationOnScreen();

        return new Point(mousePoint.x - clientPoint.x, mousePoint.y - clientPoint.y);
    }

    public static Bot getBot() {
        return bot;
    }

    public static void setBot(Bot bot) {
        Globals.bot = bot;
    }

    public static boolean isMoveNodesDown() {
        return moveNodesDown;
    }

    public static void setMoveNodesDown(boolean moveNodesDown) {
        Globals.moveNodesDown = moveNodesDown;
    }

    public static boolean isMoveNodesLeft() {
        return moveNodesLeft;
    }

    public static void setMoveNodesLeft(boolean moveNodesLeft) {
        Globals.moveNodesLeft = moveNodesLeft;
    }

    public static boolean isMoveNodesRight() {
        return moveNodesRight;
    }

    public static void setMoveNodesRight(boolean moveNodesRight) {
        Globals.moveNodesRight = moveNodesRight;
    }

    public static boolean isMoveNodesUp() {
        return moveNodesUp;
    }

    public static void setMoveNodesUp(boolean moveNodesUp) {
        Globals.moveNodesUp = moveNodesUp;
    }

    public static StartNode getStartNode() {
        return startNode;
    }

    public static void setStartNode(StartNode startNode) {
        Globals.startNode = startNode;
    }

    public static Queue<Node> getNodesToDestroy() {
        return nodesToDestroy;
    }

    public static HashSet<Connector> getConnectors() {
        return connectors;
    }

    public static Queue<Connector> getConnectorsToAdd() {
        return connectorsToAdd;
    }

    public static Connector getSelectedConnector() {
        return selectedConnector;
    }

    public static void setSelectedConnector(Connector selectedConnector) {
        Globals.selectedConnector = selectedConnector;
    }

    public static Connector getCurrentHoveredConnector() {
        return currentHoveredConnector;
    }

    public static void setCurrentHoveredConnector(Connector currentHoveredConnector) {
        Globals.currentHoveredConnector = currentHoveredConnector;
    }

    public static Node getCurrentExecutingNode() {
        return currentExecutingNode;
    }

    public static void setCurrentExecutingNode(Node currentExecutingNode) {
        Globals.currentExecutingNode = currentExecutingNode;
    }

    public static Node getSelectedNode() {
        return selectedNode;
    }

    public static void setSelectedNode(Node selectedNode) {
        Globals.selectedNode = selectedNode;
    }

    public static Node getCurrentHoveredNode() {
        return currentHoveredNode;
    }

    public static void setCurrentHoveredNode(Node currentHoveredNode) {
        Globals.currentHoveredNode = currentHoveredNode;
    }

    public static Point getMousePointRelativeToGUI() {
        Point guiPoint = GUI.getInstance().getNodePanel().getLocationOnScreen();
        Point mousePoint = MouseInfo.getPointerInfo().getLocation();

        return new Point(mousePoint.x - guiPoint.x, mousePoint.y - guiPoint.y);
    }

    public static boolean isScriptExecuting() {
        return GUI.getInstance().isExecuteClicked();
    }

    public static Queue<Node> getNodesToAdd() {
        return nodesToAdd;
    }

    public static HashSet<Node> getNodes() {
        return nodes;
    }

    public static void setNodes(HashSet<Node> nodes) {
        Globals.nodes = nodes;
    }

}
