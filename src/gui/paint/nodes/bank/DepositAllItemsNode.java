package gui.paint.nodes.bank;

import data.Globals;
import gui.paint.nodes.base_nodes.BoolNode;
import org.osbot.rs07.script.MethodProvider;

public class DepositAllItemsNode extends BoolNode {

    private static long serialVersionUID = 1L;

    public DepositAllItemsNode() {
        super("If", "DepositedAllItems");
    }

    @Override
    public void onExecute() {
        MethodProvider mp = Globals.getBot().getMethods();
        setNextNodeToExecute(mp.getBank().depositAll());
    }
}
