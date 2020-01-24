package gui.paint.nodes.interaction;

import data.Globals;
import gui.paint.nodes.base_nodes.BoolNode;
import org.osbot.rs07.api.model.GroundItem;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.api.model.Player;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.script.MethodProvider;

public class InteractionNode extends BoolNode {

    private static long serialVersionUID = 1L;

    private String typeOfObject;
    private String nameOfObject;
    private String action;

    public InteractionNode(String... text) {
        super(text);
        typeOfObject = text[0];
        nameOfObject = text[1];
        action = text[2];
    }

    @Override
    public void onExecute() {
        MethodProvider mp = Globals.getBot().getMethods();
        switch (typeOfObject) {
            case "NPC":
                NPC npc = mp.getNpcs().closest(npc1 -> npc1.getName().equals(nameOfObject)
                        && npc1.hasAction(action) && mp.getMap().canReach(npc1));
                setNextNodeToExecute(npc != null && npc.interact(action));
                break;
            case "Object":
                RS2Object object = mp.getObjects().closest(rs2Object -> rs2Object.getName().equals(nameOfObject)
                        && rs2Object.hasAction(action) && mp.getMap().canReach(rs2Object));
                setNextNodeToExecute(object != null && object.interact(action));
                break;
            case "Player":
                Player player = mp.getPlayers().closest(player1 -> player1.getName().equals(nameOfObject) && mp.getMap().canReach(player1));
                setNextNodeToExecute(player != null && player.interact(action));
                break;
            case "GroundItem":
                GroundItem groundItem = mp.getGroundItems().closest(groundItem1 -> groundItem1.getName().equals(nameOfObject)
                        && groundItem1.hasAction(action) && mp.getMap().canReach(groundItem1));
                setNextNodeToExecute(groundItem != null && groundItem.interact(action));
                break;
        }
    }
}
