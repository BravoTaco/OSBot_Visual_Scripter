package gui.paint.nodes.bank;

import data.Globals;
import gui.paint.nodes.base_nodes.BoolNode;
import org.osbot.rs07.script.MethodProvider;

import java.awt.*;

public class WithdrawItemsNode extends BoolNode {

    private static long serialVersionUID = 1L;

    private String[] itemNames;

    public WithdrawItemsNode(String... itemNames) {
        super(new Dimension(100, itemNames.length * 25), itemNames);

        this.itemNames = new String[itemNames.length - 2];
        for (int i = 2, j = 0; j < this.itemNames.length; i++, j++)
            this.itemNames[j] = itemNames[i];

    }

    @Override
    public void onExecute() {
        MethodProvider mp = Globals.getBot().getMethods();
        for (String s : itemNames) {
            if (!mp.getBank().withdrawAll(s)) {
                setNextNodeToExecute(false);
                return;
            }
        }
        setNextNodeToExecute(true);
    }
}
