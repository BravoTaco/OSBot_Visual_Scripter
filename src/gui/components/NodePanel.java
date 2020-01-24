package gui.components;

import data.Globals;
import data.gui.ConnectorTypes;
import gui.paint.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NodePanel extends JPanel {

    private final long minMouseHoldTime = 130L;
    private int lastX;
    private int lastY;
    private long amountOfTimeDragged;
    private long startTime;

    private MouseAdapter mouseAdapter = new MouseAdapter() {
        @Override
        public void mouseDragged(MouseEvent e) {
            amountOfTimeDragged = System.currentTimeMillis() - startTime;
            if (amountOfTimeDragged >= minMouseHoldTime) {
                int x = e.getX() - lastX;
                int y = e.getY() - lastY;
                if (x > 1 || x < -1)
                    x /= x;
                if (y > 1 || y < -1)
                    y /= y;
                for (Node node : Globals.getNodes())
                    node.translate(x, y);
                lastX = e.getX();
                lastY = e.getY();
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            amountOfTimeDragged = 0;
            startTime = 0;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            startTime = System.currentTimeMillis();
        }
    };

    public NodePanel() {
        setBackground(Color.DARK_GRAY.darker());
        setForeground(Color.cyan);
        setBorder(BorderFactory.createEtchedBorder());
        setMinimumSize(new Dimension(800, 600));

        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);

        Thread repaintThread = new Thread(this::onPaint);
        repaintThread.start();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (Globals.getCurrentHoveredNode() != null && Globals.getSelectedConnector() == null) {
                    if (Globals.getCurrentHoveredNode().getNodeRect().contains(e.getPoint())) {
                        if (Globals.getSelectedNode() == null) {
                            Globals.setSelectedNode(Globals.getCurrentHoveredNode());
                        } else {
                            Globals.setSelectedNode(null);
                        }
                    }
                }

                if (Globals.getCurrentHoveredConnector() != null && Globals.getSelectedNode() == null) {
                    if (Globals.getCurrentHoveredConnector().getConnector().getBounds().contains(e.getPoint())) {
                        if (Globals.getSelectedConnector() == null && !Globals.getCurrentHoveredConnector().getType().equals(ConnectorTypes.IN.getType())) {
                            Globals.setSelectedConnector(Globals.getCurrentHoveredConnector());
                        } else if (Globals.getCurrentHoveredConnector() == Globals.getSelectedConnector()) {
                            Globals.setSelectedConnector(null);
                        } else {
                            setNewLinkBetweenConnectors();
                        }
                    }
                }
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        if (Globals.isMoveNodesDown())
            Globals.getNodes().forEach(e -> e.moveVertically(-5));

        if (Globals.isMoveNodesUp())
            Globals.getNodes().forEach(e -> e.moveVertically(5));

        if (Globals.isMoveNodesRight())
            Globals.getNodes().forEach(e -> e.moveHorizontal(-5));

        if (Globals.isMoveNodesLeft())
            Globals.getNodes().forEach(e -> e.moveHorizontal(5));

        if (Globals.getNodesToAdd().size() > 0)
            Globals.getNodes().add(Globals.getNodesToAdd().poll());

        if (Globals.getConnectorsToAdd().size() > 0)
            Globals.getConnectors().add(Globals.getConnectorsToAdd().poll());

        if (Globals.getNodesToDestroy().size() > 0)
            Globals.getNodesToDestroy().poll().destroy();

        Point mousePoint = Globals.getMousePointRelativeToGUI();
        for (Node node : Globals.getNodes()) {
            if (node.getNodeRect().contains(mousePoint)) {
                g2.setColor(Color.white);
                Globals.setCurrentHoveredNode(node);
            }
            if (Globals.getSelectedNode() != null && node == Globals.getSelectedNode()) {
                g2.setColor(Color.orange);
                node.setPositionToMousePosition();
            }
            if (Globals.getCurrentExecutingNode() == node)
                g2.setColor(Color.GREEN);
            node.draw(g2);
            g2.setColor(Color.cyan);
        }
    }

    private void onPaint() {
        while (true) {
            repaint();
            try {
                Thread.sleep(17);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void setNewLinkBetweenConnectors() {
        if (Globals.getSelectedConnector() == null || Globals.getCurrentHoveredConnector() == null)
            return;

        if (Globals.getSelectedConnector().getAttachedNode() == Globals.getCurrentHoveredConnector().getAttachedNode())
            return;

        if (canLink()) {
            if (Globals.getSelectedConnector().getConnectedConnector() == null) {
                Globals.getSelectedConnector().setConnectedConnector(Globals.getCurrentHoveredConnector());
                Globals.setSelectedConnector(null);
            } else if (Globals.getSelectedConnector().getConnectedConnector() != null) {
                Globals.getSelectedConnector().getConnectedConnector().setConnectedConnector(null);
                Globals.getSelectedConnector().setConnectedConnector(Globals.getCurrentHoveredConnector());
                Globals.setSelectedConnector(null);
            }
        }
    }

    private boolean canLink() {
        String selectedConnectorType = Globals.getSelectedConnector().getType();
        String hoveredConnectorType = Globals.getCurrentHoveredConnector().getType();

        boolean hoveredIsInType = hoveredConnectorType.equals(ConnectorTypes.IN.getType());

        return selectedConnectorType.equals(ConnectorTypes.OUT.getType()) && hoveredIsInType
                || selectedConnectorType.equals(ConnectorTypes.OUT_TRUE.getType()) && hoveredIsInType
                || selectedConnectorType.equals(ConnectorTypes.OUT_FALSE.getType()) && hoveredIsInType;
    }
}
