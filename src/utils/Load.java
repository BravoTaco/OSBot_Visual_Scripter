package utils;

import data.Globals;
import gui.helpers.InformationDialog;
import gui.paint.Node;
import gui.paint.nodes.base_nodes.StartNode;
import org.osbot.rs07.script.Script;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashSet;

public class Load {
    private String filePath;
    private Script script;
    private String fileName;

    public Load(String fileName) {
        script = Globals.getBot().getScriptExecutor().getCurrent();
        filePath = script.getDirectoryData() + File.separator + script.getName() + File.separator;
        this.fileName = fileName;
    }

    public void loadFile() {
        try {
            if (fileName == null)
                return;

            FileInputStream fileInputStream = new FileInputStream(filePath + fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            HashSet<Node> nodes = (HashSet<Node>) objectInputStream.readObject();
            Globals.setNodes(nodes);

            for (Node node : Globals.getNodes()) {
                if (node instanceof StartNode)
                    Globals.setStartNode((StartNode) node);
            }

            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            InformationDialog informationDialog = new InformationDialog("Could not load save file! Please look in the logger to see the reason.");
            Globals.getBot().getScriptExecutor().getCurrent().log("ERROR: " + "[" + e + "]");
            informationDialog.start();
        }
    }
}
