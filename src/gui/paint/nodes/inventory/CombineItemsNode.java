package gui.paint.nodes.inventory;

import data.Globals;
import gui.paint.nodes.base_nodes.BoolNode;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.script.MethodProvider;

import java.awt.*;

public class CombineItemsNode extends BoolNode {

    private static long serialVersionUID = 1L;

    String[] itemsToCombine;

    public CombineItemsNode(String... itemsToCombine) {
        super(new Dimension(100, itemsToCombine.length * 25), itemsToCombine);
        this.itemsToCombine = itemsToCombine;
    }

    @Override
    public void onExecute() {
        MethodProvider mp = Globals.getBot().getMethods();

        Item item1 = mp.getInventory().getItem(itemsToCombine[2]);
        Item item2 = mp.getInventory().getItem(itemsToCombine[3]);

        if (item1 != null && item2 != null)
            setNextNodeToExecute(item1.interact("Use") && item2.interact("Use"));
        else
            setNextNodeToExecute(false);
    }
}
