package utils;

import java.io.File;

public final class GetFilesFromFolder {
    private GetFilesFromFolder() {
    }

    public static File[] getFilesFromFolder(String filePath) {
        File folder = new File(filePath);
        return folder.listFiles();
    }

    public static String[] getFileNamesFromFolder(String filePath) {
        File[] files = getFilesFromFolder(filePath);

        if (files != null) {
            String[] names = new String[files.length];

            for (int i = 0; i < files.length; i++) {
                names[i] = files[i].getName();
            }
            return names;
        }
        return null;
    }
}
