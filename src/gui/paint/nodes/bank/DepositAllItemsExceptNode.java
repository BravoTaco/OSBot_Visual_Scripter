package gui.paint.nodes.bank;

import data.Globals;
import gui.paint.nodes.base_nodes.BoolNode;
import org.osbot.rs07.script.MethodProvider;

import java.awt.*;

public class DepositAllItemsExceptNode extends BoolNode {

    private static long serialVersionUID = 1L;

    String[] itemNames;

    public DepositAllItemsExceptNode(String... itemNames) {
        super(new Dimension(100, itemNames.length * 25), itemNames);

        this.itemNames = new String[itemNames.length - 3];
        for (int i = 3, j = 0; j < this.itemNames.length; i++, j++)
            this.itemNames[j] = itemNames[i];

    }

    @Override
    public void onExecute() {
        MethodProvider mp = Globals.getBot().getMethods();
        setNextNodeToExecute(mp.getBank().depositAllExcept(itemNames));
    }
}
