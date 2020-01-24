package gui.paint.nodes.base_nodes;

import data.Globals;
import data.gui.ConnectorTypes;
import gui.paint.Connector;
import gui.paint.Node;

import java.awt.*;

public abstract class BoolNode extends Node {
    private static long serialVersionUID = 1L;
    private Connector inConnector;
    private Connector outTrueConnector;
    private Connector outFalseConnector;

    public BoolNode(String... text) {
        super(text);
        Point inConnectorPoint = new Point(getNodeRect().x - 10, getNodeRect().y + getNodeRect().height / 2 - 5);
        inConnector = new Connector(this, inConnectorPoint, ConnectorTypes.IN.getType());
        Point outTrueConnectorPoint = new Point(getNodeRect().x + getNodeRect().width, getNodeRect().y + (int) (getNodeRect().height / 1.5) - 5);
        outTrueConnector = new Connector(this, outTrueConnectorPoint, ConnectorTypes.OUT_TRUE.getType());
        Point outFalseConnectorPoint = new Point(getNodeRect().x + getNodeRect().width, getNodeRect().y + (getNodeRect().height / 3) - 5);
        outFalseConnector = new Connector(this, outFalseConnectorPoint, ConnectorTypes.OUT_FALSE.getType());
    }

    public BoolNode(Dimension size, String... text) {
        super(size, text);
        Point inConnectorPoint = new Point(getNodeRect().x - 10, getNodeRect().y + getNodeRect().height / 2 - 5);
        inConnector = new Connector(this, inConnectorPoint, ConnectorTypes.IN.getType());
        Point outTrueConnectorPoint = new Point(getNodeRect().x + getNodeRect().width, getNodeRect().y + (int) (getNodeRect().height / 1.5) - 5);
        outTrueConnector = new Connector(this, outTrueConnectorPoint, ConnectorTypes.OUT_TRUE.getType());
        Point outFalseConnectorPoint = new Point(getNodeRect().x + getNodeRect().width, getNodeRect().y + (getNodeRect().height / 3) - 5);
        outFalseConnector = new Connector(this, outFalseConnectorPoint, ConnectorTypes.OUT_FALSE.getType());
    }

    public abstract void onExecute();

    @Override
    public void destroy() {
        if (inConnector.getConnectedConnector() != null)
            inConnector.getConnectedConnector().setConnectedConnector(null);
        inConnector = null;
        if (outTrueConnector.getConnectedConnector() != null)
            outTrueConnector.getConnectedConnector().setConnectedConnector(null);
        outTrueConnector = null;
        if (outFalseConnector.getConnectedConnector() != null)
            outFalseConnector.getConnectedConnector().setConnectedConnector(null);
        outFalseConnector = null;
        Globals.getNodes().remove(this);
        Globals.setSelectedNode(null);
    }

    @Override
    public void onDraw(Graphics2D g) {
        Point inConnectorPoint = new Point(getNodeRect().x - 10, getNodeRect().y + getNodeRect().height / 2 - 5);
        inConnector.onDraw(g, inConnectorPoint);
        Point outTrueConnectorPoint = new Point(getNodeRect().x + getNodeRect().width, getNodeRect().y + (getNodeRect().height / 3) - 5);
        outTrueConnector.onDraw(g, outTrueConnectorPoint);
        Point outFalseConnectorPoint = new Point(getNodeRect().x + getNodeRect().width, getNodeRect().y + (int) (getNodeRect().height / 1.5) - 5);
        outFalseConnector.onDraw(g, outFalseConnectorPoint);
    }

    public void setNextNodeToExecute(boolean outBool) {
        if (outBool && getOutTrueConnector().getConnectedConnector() != null)
            Globals.setCurrentExecutingNode(getOutTrueConnector().getConnectedConnector().getAttachedNode());
        else if (!outBool && getOutFalseConnector().getConnectedConnector() != null)
            Globals.setCurrentExecutingNode(getOutFalseConnector().getConnectedConnector().getAttachedNode());
        else
            Globals.setCurrentExecutingNode(Globals.getStartNode());
    }

    public Connector getOutFalseConnector() {
        return outFalseConnector;
    }

    public Connector getOutTrueConnector() {
        return outTrueConnector;
    }

}
