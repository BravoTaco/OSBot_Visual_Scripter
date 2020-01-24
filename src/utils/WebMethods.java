package utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public final class WebMethods {
    private WebMethods() {
    }

    public static Image getImageFromURL(String urlString) {
        Image image = null;
        try {
            URL url = new URL(urlString);
            BufferedImage bufferedImage = ImageIO.read(url);
            image = new ImageIcon(bufferedImage).getImage();
        } catch (IOException ie) {
            ie.printStackTrace();
        }
        return image;
    }

    public static Icon getIconFromURL(String urlString) {
        Icon icon = null;
        try {
            URL url = new URL(urlString);
            BufferedImage bufferedImage = ImageIO.read(url);
            icon = new ImageIcon(bufferedImage);
        } catch (IOException ie) {
            ie.printStackTrace();
        }
        return icon;
    }
}
