package gui.paint.nodes.sleeps;

import data.Globals;
import gui.paint.nodes.base_nodes.BoolNode;
import org.osbot.rs07.script.MethodProvider;
import utils.Sleep;

import java.awt.*;

public class SleepNode extends BoolNode {

    private static long serialVersionUID = 1L;

    private String sleepCondition;

    public SleepNode(String sleepCondition) {
        super(new Dimension(100, 75), "Sleep Until", sleepCondition);
        this.sleepCondition = sleepCondition;
    }

    @Override
    public void onExecute() {
        MethodProvider methodProvider = Globals.getBot().getMethods();
        switch (sleepCondition) {
            case "IsAnimating":
                setNextNodeToExecute(Sleep.sleepUntil(() -> methodProvider.myPlayer().isAnimating(), 10000, 100));
                break;
            case "IsNotAnimating":
                setNextNodeToExecute(Sleep.sleepUntil(() -> !methodProvider.myPlayer().isAnimating(), 10000, 100));
                break;
            case "IsUnderAttack":
                setNextNodeToExecute(Sleep.sleepUntil(() -> methodProvider.myPlayer().isUnderAttack(), 10000, 100));
                break;
            case "IsNotUnderAttack":
                setNextNodeToExecute(Sleep.sleepUntil(() -> !methodProvider.myPlayer().isUnderAttack(), 10000, 100));
                break;
            case "BankIsOpen":
                setNextNodeToExecute(Sleep.sleepUntil(() -> methodProvider.getBank().isOpen(), 10000, 100));
                break;
            case "BankIsClosed":
                setNextNodeToExecute(Sleep.sleepUntil(() -> !methodProvider.getBank().isOpen(), 10000, 100));
                break;
            case "PickedUpItem":
                setNextNodeToExecute(Sleep.sleepUntil(() -> pickedUpItem(methodProvider.getInventory().getEmptySlots()), 10000, 100));
                break;
        }
    }

    private boolean pickedUpItem(int oldEmptySlots) {
        MethodProvider methodProvider = Globals.getBot().getMethods();
        return methodProvider.getInventory().getEmptySlots() < oldEmptySlots;
    }
}
