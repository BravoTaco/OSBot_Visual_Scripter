package gui.paint.nodes.widgets;

import data.Globals;
import gui.paint.nodes.base_nodes.BoolNode;
import org.osbot.rs07.api.ui.RS2Widget;
import org.osbot.rs07.script.MethodProvider;

public class InteractWithWidgetNode extends BoolNode {

    private static long serialVersionUID = 1L;

    private String action;

    public InteractWithWidgetNode(String action) {
        super("Interact", "Widget", action);
        this.action = action;
    }

    @Override
    public void onExecute() {
        MethodProvider mp = Globals.getBot().getMethods();

        RS2Widget tempWidget = null;

        for (RS2Widget widget : mp.getWidgets().getAll()) {
            if (widget != null && widget.getInteractActions() != null) {
                for (String s : widget.getInteractActions()) {
                    if (s != null && s.equalsIgnoreCase(action)) {
                        tempWidget = widget;
                        break;
                    }
                }
            }
            if (tempWidget != null)
                break;
        }

        setNextNodeToExecute(tempWidget != null && tempWidget.interact(action));
    }
}
