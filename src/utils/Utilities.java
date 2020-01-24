package utils;

import gui.helpers.StringDialog;

import java.awt.*;

public final class Utilities {
    private Utilities() {
    }

    public static Point mousePoint(Component component) {
        Point mousePoint = MouseInfo.getPointerInfo().getLocation();
        return new Point(
                mousePoint.x - component.getSize().width / 2,
                mousePoint.y - component.getSize().height / 2);
    }

    public static void setDefaultColors(Component component) {
        component.setBackground(Color.darkGray);
        component.setForeground(Color.cyan);
    }

    public static Point getCenterPointOfComponent(Component component) {
        if (component == null)
            return null;

        return new Point(component.getWidth() / 2, component.getHeight() / 2);
    }

    public static void drawCenteredText(Graphics2D g, int yOffset, String text, Shape shape, Font font) {
        FontMetrics fontMetrics = g.getFontMetrics(font);
        int x = shape.getBounds().x + (shape.getBounds().width - fontMetrics.stringWidth(text)) / 2;
        int y = shape.getBounds().y + yOffset + ((shape.getBounds().height - fontMetrics.getHeight()) / 2) + fontMetrics.getAscent();
        g.setFont(font);
        g.drawString(text, x, y);
    }

    public static void drawCenteredText(Graphics2D g, String[] text, Shape shape, Font font) {
        FontMetrics fontMetrics = g.getFontMetrics(font);
        int yOffset = shape.getBounds().height / text.length;
        for (String s : text) {
            int x = shape.getBounds().x + (shape.getBounds().width - fontMetrics.stringWidth(s)) / 2;
            int y = shape.getBounds().y + yOffset;
            g.setFont(font);
            g.drawString(s, x, y);
            yOffset += fontMetrics.getHeight();
        }
    }

    public static String[] populateNodeWithItemNames(String message, String... extraStrings) {
        StringDialog stringDialog = new StringDialog(new Dimension(400, 200), message);
        stringDialog.start();
        String[] itemsToKeep = null;
        if (!stringDialog.wasClosed()) {
            String[] temp = stringDialog.getAnswer().split(",");
            itemsToKeep = new String[temp.length + extraStrings.length];
            for (int i = 0; i < extraStrings.length; i++) {
                itemsToKeep[i] = extraStrings[i].trim();
            }
            for (int i = extraStrings.length, j = 0; j < temp.length; i++, j++) {
                itemsToKeep[i] = temp[j].trim();
            }
        }
        return itemsToKeep;
    }
}
