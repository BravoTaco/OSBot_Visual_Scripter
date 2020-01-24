package gui.paint.nodes.inventory;

import data.Globals;
import gui.paint.nodes.base_nodes.BoolNode;

public class InventoryIsFullNode extends BoolNode {

    private static long serialVersionUID = 1L;

    public InventoryIsFullNode() {
        super("If", "Inventory", "Is Full");
    }

    @Override
    public void onExecute() {
        setNextNodeToExecute(Globals.getBot().getMethods().getInventory().isFull());
    }
}
