package gui.paint.nodes.bank;

import data.Globals;
import gui.paint.nodes.base_nodes.BoolNode;
import org.osbot.rs07.script.MethodProvider;

public class OpenBankNode extends BoolNode {

    private static long serialVersionUID = 1L;

    public OpenBankNode() {
        super("If", "OpenBank");
    }

    @Override
    public void onExecute() {
        MethodProvider mp = Globals.getBot().getMethods();
        try {
            setNextNodeToExecute(mp.getBank().open());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
