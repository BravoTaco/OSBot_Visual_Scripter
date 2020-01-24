package gui.paint.nodes.base_nodes;

import data.Globals;
import data.gui.ConnectorTypes;
import gui.paint.Connector;
import gui.paint.Node;

import java.awt.*;

public abstract class VoidNode extends Node {
    private static long serialVersionUID = 1L;
    private Connector inConnector;
    private Connector outConnector;

    public VoidNode(String... text) {
        super(text);
        Point inConnectorPoint = new Point(
                getNodeRect().x + 10,
                getNodeRect().y - getNodeRect().height / 2);
        inConnector = new Connector(this, inConnectorPoint, ConnectorTypes.IN.getType());
        Point outConnectorPoint = new Point(
                getNodeRect().x - getNodeRect().width - 10,
                getNodeRect().y - getNodeRect().height / 2);
        outConnector = new Connector(this, outConnectorPoint, ConnectorTypes.OUT.getType());
    }

    public VoidNode(Dimension size, String... text) {
        super(size, text);
        Point inConnectorPoint = new Point(
                getNodeRect().x + 10,
                getNodeRect().y - getNodeRect().height / 2);
        inConnector = new Connector(this, inConnectorPoint, ConnectorTypes.IN.getType());
        Point outConnectorPoint = new Point(
                getNodeRect().x - getNodeRect().width - 10,
                getNodeRect().y - getNodeRect().height / 2);
        outConnector = new Connector(this, outConnectorPoint, ConnectorTypes.OUT.getType());
    }

    public abstract void onExecute();

    @Override
    public void destroy() {
        if (inConnector.getConnectedConnector() != null)
            inConnector.getConnectedConnector().setConnectedConnector(null);
        inConnector = null;
        if (outConnector.getConnectedConnector() != null)
            outConnector.getConnectedConnector().setConnectedConnector(null);
        outConnector = null;
        Globals.getNodes().remove(this);
        Globals.setSelectedNode(null);
    }

    @Override
    public void onDraw(Graphics2D g) {
        Point inConnectorPoint = new Point(getNodeRect().x - 10, getNodeRect().y + getNodeRect().height / 2 - 5);
        inConnector.onDraw(g, inConnectorPoint);
        Point outConnectorPoint = new Point(getNodeRect().x + getNodeRect().width, getNodeRect().y + getNodeRect().height / 2 - 5);
        outConnector.onDraw(g, outConnectorPoint);
    }

    public void setNextNodeToExecute() {
        if (outConnector.getConnectedConnector() != null)
            Globals.setCurrentExecutingNode(outConnector.getConnectedConnector().getAttachedNode());
        else
            Globals.setCurrentExecutingNode(Globals.getStartNode());

    }

    public Connector getOutConnector() {
        return outConnector;
    }

}
