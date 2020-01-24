package gui.paint.nodes.inventory;

import data.Globals;
import gui.paint.nodes.base_nodes.BoolNode;
import org.osbot.rs07.script.MethodProvider;

import java.awt.*;

public class InventoryContainsItemNode extends BoolNode {

    private static long serialVersionUID = 1L;

    String[] itemNames;

    public InventoryContainsItemNode(String... itemNames) {
        super(new Dimension(145, itemNames.length * 25), itemNames);
        this.itemNames = itemNames;
    }

    @Override
    public void onExecute() {
        MethodProvider mp = Globals.getBot().getMethods();
        for (int i = 2; i < itemNames.length; i++) {
            if (!mp.getInventory().contains(itemNames[i])) {
                setNextNodeToExecute(false);
                return;
            }
        }
        setNextNodeToExecute(true);
    }
}
