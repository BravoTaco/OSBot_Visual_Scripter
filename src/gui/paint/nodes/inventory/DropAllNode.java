package gui.paint.nodes.inventory;

import data.Globals;
import gui.paint.nodes.base_nodes.VoidNode;

public class DropAllNode extends VoidNode {

    private static long serialVersionUID = 1L;

    public DropAllNode() {
        super("Drop", "All");
    }

    @Override
    public void onExecute() {
        Globals.getBot().getMethods().getInventory().dropAll();
        setNextNodeToExecute();
    }
}
