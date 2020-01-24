package gui.paint.nodes.utilities;

import data.Globals;
import gui.paint.nodes.base_nodes.VoidNode;

public class LogMessageNode extends VoidNode {

    private static long serialVersionUID = 1L;

    private String message;

    public LogMessageNode(String message) {
        super("Log", "Message");
        this.message = message;
    }

    @Override
    public void onExecute() {
        Globals.getBot().getScriptExecutor().getCurrent().log(message);
        setNextNodeToExecute();
    }
}
