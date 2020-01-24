package gui.paint.nodes.player;

import data.Globals;
import gui.paint.nodes.base_nodes.BoolNode;

public class IsAnimatingNode extends BoolNode {

    private static long serialVersionUID = 1L;

    public IsAnimatingNode() {
        super("If", "Player", "IsAnimating");
    }

    @Override
    public void onExecute() {
        setNextNodeToExecute(Globals.getBot().getMethods().myPlayer().isAnimating());
    }
}
