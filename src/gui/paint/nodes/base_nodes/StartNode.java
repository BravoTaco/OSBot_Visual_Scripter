package gui.paint.nodes.base_nodes;

import data.Globals;
import data.gui.ConnectorTypes;
import gui.paint.Connector;
import gui.paint.Node;

import java.awt.*;

public class StartNode extends Node {
    private static long serialVersionUID = 1L;
    private Connector outConnector;

    public StartNode() {
        super("Start", "Node");

        Point outConnectorPoint = new Point(getNodeRect().x + 10, getNodeRect().y - getNodeRect().height / 2);
        outConnector = new Connector(this, outConnectorPoint, ConnectorTypes.OUT.getType());
    }

    @Override
    public void onExecute() {
        if (outConnector.getConnectedConnector() != null)
            Globals.setCurrentExecutingNode(outConnector.getConnectedConnector().getAttachedNode());
    }

    @Override
    public void onDraw(Graphics2D g) {
        Point outConnectorPoint = new Point(getNodeRect().x + getNodeRect().width, getNodeRect().y + getNodeRect().height / 2 - 5);
        outConnector.onDraw(g, outConnectorPoint);
    }

    @Override
    public void destroy() {
        System.out.println("Cant Destroy Start Node!");
    }
}
