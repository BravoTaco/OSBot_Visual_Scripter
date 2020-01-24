package gui.paint.nodes.walker;

import data.Globals;
import data.Int3;
import gui.paint.nodes.base_nodes.BoolNode;
import org.osbot.rs07.api.map.constants.Banks;
import org.osbot.rs07.script.MethodProvider;

import java.awt.*;

public class WalkerNode extends BoolNode {
    private static long serialVersionUID = 1L;
    private String location;
    private boolean isCustomPosition;
    private Int3 customPosition;

    public WalkerNode(String location) {
        super(new Dimension(175, 75), "Walk To", location);
        this.location = location;
    }

    public WalkerNode(String location, Int3 customPosition) {
        super(new Dimension(175, 75), "Walk To", customPosition.getPosition().toString());
        this.customPosition = customPosition;
        isCustomPosition = true;
    }

    @Override
    public void onExecute() {
        MethodProvider mp = Globals.getBot().getMethods();
        if (!isCustomPosition) {
            switch (location) {
                case "Lumbridge Upper Bank":
                    mp.getWalking().webWalk(Banks.LUMBRIDGE_UPPER);
                    setNextNodeToExecute(Banks.LUMBRIDGE_UPPER.contains(mp.myPlayer()));
                    break;
                case "Varrock West Bank":
                    mp.getWalking().webWalk(Banks.VARROCK_WEST);
                    setNextNodeToExecute(Banks.VARROCK_WEST.contains(mp.myPlayer()));
                    break;
                case "Varrock East Bank":
                    mp.getWalking().webWalk(Banks.VARROCK_EAST);
                    setNextNodeToExecute(Banks.VARROCK_EAST.contains(mp.myPlayer()));
                    break;
                case "GrandExchange":
                    mp.getWalking().webWalk(Banks.GRAND_EXCHANGE);
                    setNextNodeToExecute(Banks.GRAND_EXCHANGE.contains(mp.myPlayer()));
                    break;
                case "Falador West Bank":
                    mp.getWalking().webWalk(Banks.FALADOR_WEST);
                    setNextNodeToExecute(Banks.FALADOR_WEST.contains(mp.myPlayer()));
                    break;
                case "Falador East Bank":
                    mp.getWalking().webWalk(Banks.FALADOR_EAST);
                    setNextNodeToExecute(Banks.FALADOR_EAST.contains(mp.myPlayer()));
                    break;
                case "Draynor Bank":
                    mp.getWalking().webWalk(Banks.DRAYNOR);
                    setNextNodeToExecute(Banks.DRAYNOR.contains(mp.myPlayer()));
                    break;
                case "Edgeville Bank":
                    mp.getWalking().webWalk(Banks.EDGEVILLE);
                    setNextNodeToExecute(Banks.EDGEVILLE.contains(mp.myPlayer()));
                    break;
            }
        } else {
            mp.getWalking().webWalk(customPosition.getPosition());
            setNextNodeToExecute(customPosition.containsPlayer());
        }
    }
}
