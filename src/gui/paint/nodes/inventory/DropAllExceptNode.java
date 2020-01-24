package gui.paint.nodes.inventory;

import data.Globals;
import gui.paint.nodes.base_nodes.VoidNode;
import org.osbot.rs07.script.MethodProvider;

import java.awt.*;

public class DropAllExceptNode extends VoidNode {

    private static long serialVersionUID = 1L;

    private String[] itemsToKeep;

    public DropAllExceptNode(String... itemsToKeep) {
        super(new Dimension(100, itemsToKeep.length * 25), itemsToKeep);

        this.itemsToKeep = new String[itemsToKeep.length];

        for (int i = 1; i < itemsToKeep.length; i++) {
            this.itemsToKeep[i] = itemsToKeep[i];
        }
    }

    @Override
    public void onExecute() {
        MethodProvider methodProvider = Globals.getBot().getMethods();
        methodProvider.getInventory().dropAllExcept(itemsToKeep);
        setNextNodeToExecute();
    }
}
