package gui.paint;

import data.Globals;
import gui.GUI;
import gui.paint.nodes.base_nodes.BoolNode;
import gui.paint.nodes.base_nodes.StartNode;
import gui.paint.nodes.base_nodes.VoidNode;
import utils.Utilities;

import java.awt.*;
import java.io.Serializable;

public abstract class Node implements Serializable {
    private static long serialVersionUID = 1L;

    private Rectangle nodeRect;
    private String[] text;

    public Node(String... text) {
        nodeRect = new Rectangle(new Point(0, 0), new Dimension(75, 75));
        this.text = text;
        Globals.getNodesToAdd().add(this);
        if (!(this instanceof StartNode))
            Globals.setSelectedNode(this);
        else
            this.nodeRect.setLocation(new Point(
                    Utilities.getCenterPointOfComponent(GUI.getInstance().getNodePanel()).x - nodeRect.width / 2,
                    Utilities.getCenterPointOfComponent(GUI.getInstance().getNodePanel()).y - nodeRect.height / 2));
    }

    public Node(Dimension size, String... text) {
        nodeRect = new Rectangle(new Point(0, 0), size);
        this.text = text;
        Globals.getNodesToAdd().add(this);
        if (!(this instanceof StartNode))
            Globals.setSelectedNode(this);
        else
            this.nodeRect.setLocation(new Point(
                    Utilities.getCenterPointOfComponent(GUI.getInstance().getNodePanel()).x - nodeRect.width / 2,
                    Utilities.getCenterPointOfComponent(GUI.getInstance().getNodePanel()).y - nodeRect.height / 2));
    }

    public abstract void onExecute();

    public abstract void onDraw(Graphics2D g);

    public void addToDestroyQueue() {
        Globals.getNodesToDestroy().add(this);
    }

    public abstract void destroy();

    public void draw(Graphics2D g) {
        g.draw(nodeRect);
        Font font = new Font("Sans Serif", Font.BOLD, 12);
        Utilities.drawCenteredText(g, text, nodeRect, font);
        onDraw(g);
    }

    public Rectangle getNodeRect() {
        return nodeRect;
    }

    public void moveVertically(int amountToMove) {
        nodeRect.setLocation(new Point(nodeRect.x, nodeRect.y + amountToMove));
        Globals.setMoveNodesDown(false);
        Globals.setMoveNodesUp(false);
    }

    public void moveHorizontal(int amountToMove) {
        nodeRect.setLocation(new Point(nodeRect.x + amountToMove, nodeRect.y));
        Globals.setMoveNodesRight(false);
        Globals.setMoveNodesLeft(false);
    }

    public void translate(int xDir, int yDir) {
        nodeRect.translate(xDir, yDir);
    }

    public void setPositionToMousePosition() {
        Point mousePoint = Globals.getMousePointRelativeToGUI();
        nodeRect.setLocation(new Point(mousePoint.x - nodeRect.width / 2, mousePoint.y - nodeRect.height / 2));
    }

    @Override
    public String toString() {
        if (this instanceof BoolNode)
            return "Bool Node: " + nodeRect.getLocation().x + ", " + nodeRect.getLocation().y;
        else if (this instanceof VoidNode)
            return "Void Node: " + nodeRect.getLocation().x + ", " + nodeRect.getLocation().y;
        else if (this instanceof StartNode)
            return "Start Node: " + nodeRect.getLocation().x + ", " + nodeRect.getLocation().y;
        else
            return "Unknown: " + nodeRect.getLocation().x + ", " + nodeRect.getLocation().y;
    }
}
