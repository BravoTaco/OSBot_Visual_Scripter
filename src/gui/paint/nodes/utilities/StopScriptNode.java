package gui.paint.nodes.utilities;

import data.Globals;
import data.gui.ConnectorTypes;
import gui.GUI;
import gui.paint.Connector;
import gui.paint.Node;

import java.awt.*;

public class StopScriptNode extends Node {

    private static long serialVersionUID = 1L;

    private Connector inConnector;

    public StopScriptNode() {
        super("Stop", "Script");
        Point inConnectorPoint = new Point(
                getNodeRect().x + 10,
                getNodeRect().y - getNodeRect().height / 2);
        inConnector = new Connector(this, inConnectorPoint, ConnectorTypes.IN.getType());
    }

    @Override
    public void onExecute() {
        GUI.getInstance().exit();
    }

    @Override
    public void onDraw(Graphics2D g) {
        Point inConnectorPoint = new Point(getNodeRect().x - 10, getNodeRect().y + getNodeRect().height / 2 - 5);
        inConnector.onDraw(g, inConnectorPoint);
    }

    @Override
    public void destroy() {
        if (inConnector.getConnectedConnector() != null)
            inConnector.getConnectedConnector().setConnectedConnector(null);
        inConnector = null;
        Globals.getNodes().remove(this);
        Globals.setSelectedNode(null);
    }
}
