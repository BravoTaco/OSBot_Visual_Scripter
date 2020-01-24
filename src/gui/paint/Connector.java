package gui.paint;

import data.Globals;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.io.Serializable;

public class Connector implements Serializable {
    private static long serialVersionUID = 1L;
    private RoundRectangle2D connector;
    private Connector connectedConnector;
    private Node attachedNode;
    private String type;

    public Connector(Node attachedNode, Point point, String type) {
        this.attachedNode = attachedNode;
        connector = new RoundRectangle2D.Float(point.x, point.y, 10, 10, 10, 10);
        Globals.getConnectorsToAdd().add(this);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public Node getAttachedNode() {
        return attachedNode;
    }

    public Connector getConnectedConnector() {
        return connectedConnector;
    }

    public void setConnectedConnector(Connector connectedConnector) {
        this.connectedConnector = connectedConnector;
    }

    public RoundRectangle2D getConnector() {
        return connector;
    }

    public void onDraw(Graphics2D g, Point point) {
        g.setColor(getColor());
        connector = new RoundRectangle2D.Float(point.x, point.y, 10, 10, 10, 10);
        if (connector.getBounds().contains(Globals.getMousePointRelativeToGUI())) {
            g.setColor(Color.white);
            Globals.setCurrentHoveredConnector(this);
        }
        if (Globals.getSelectedConnector() == this) {
            g.setColor(Color.orange);
            Point mousePoint = Globals.getMousePointRelativeToGUI();
            Point connectorPoint = connector.getBounds().getLocation();
            g.drawLine(connectorPoint.x + 5, connectorPoint.y + 5, mousePoint.x, mousePoint.y);
        }
        g.draw(connector);
        if (connectedConnector != null) {
            g.setColor(getColor());
            Point connectorLocation = connector.getBounds().getLocation();
            Rectangle connectorBounds = connector.getBounds();
            if (!Globals.getNodes().contains(connectedConnector.attachedNode)) {
                connectedConnector = null;
                return;
            }
            Point connectedConnectorLocation = connectedConnector.getConnector().getBounds().getLocation();
            Rectangle connectedConnectorBounds = connectedConnector.getConnector().getBounds();
            g.drawLine(
                    connectorLocation.x + connectorBounds.width / 2,
                    connectorLocation.y + connectorBounds.height / 2,
                    connectedConnectorLocation.x + connectedConnectorBounds.width / 2,
                    connectedConnectorLocation.y + connectedConnectorBounds.height / 2);
        }
    }

    private Color getColor() {
        switch (getType()) {
            case "IN":
                return Color.MAGENTA;
            case "OUT_FALSE":
                return Color.RED;
            default:
                return Color.GREEN;
        }
    }
}
