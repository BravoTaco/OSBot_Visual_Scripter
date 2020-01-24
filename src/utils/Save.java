package utils;

import data.Globals;
import gui.helpers.InformationDialog;
import gui.paint.Node;
import org.osbot.rs07.script.Script;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Save {
    private String filePath;
    private Script script;
    private String fileName;

    public Save(String fileName) {
        script = Globals.getBot().getScriptExecutor().getCurrent();
        filePath = script.getDirectoryData() + File.separator + script.getName() + File.separator;
        this.fileName = fileName + ".save";
    }

    public void saveFile() {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.mkdirs();
            }

            FileOutputStream fileOutputStream = new FileOutputStream(filePath + fileName);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);

            StringBuilder sb = new StringBuilder();
            for (Node node : Globals.getNodes())
                sb.append(String.format("%s\n", node));

            System.out.println(sb.toString());

            outputStream.writeObject(Globals.getNodes());

            outputStream.flush();
            outputStream.close();

            new InformationDialog("Successfully Saved File!").start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
