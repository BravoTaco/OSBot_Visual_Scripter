package gui.paint.nodes.player;

import data.Globals;
import gui.paint.nodes.base_nodes.BoolNode;

public class IsFightingNode extends BoolNode {

    private static long serialVersionUID = 1L;

    public IsFightingNode() {
        super("If", "Player", "IsFighting");
    }

    @Override
    public void onExecute() {
        setNextNodeToExecute(Globals.getBot().getMethods().myPlayer().getInteracting() != null);
    }
}
