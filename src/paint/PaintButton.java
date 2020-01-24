package paint;

import data.Globals;
import org.osbot.rs07.Bot;
import org.osbot.rs07.input.mouse.BotMouseListener;
import utils.Utilities;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class PaintButton extends BotMouseListener {
    private Rectangle buttonRect;
    private String label;
    private Bot bot;

    public PaintButton(Bot bot, String label, Point point, Dimension dimension) {
        buttonRect = new Rectangle(point, dimension);
        this.bot = bot;
        this.label = label;
        bot.addMouseListener(this);
    }

    public abstract void onClick();

    @Override
    public void checkMouseEvent(MouseEvent mouseEvent) {
        if (buttonRect.contains(Globals.getMousePointRelativeToClient(bot)) && mouseEvent.getID() == MouseEvent.MOUSE_PRESSED) {
            mouseEvent.consume();
            onClick();
        }
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.ORANGE);
        g.draw(buttonRect);
        Color buttonColor = (buttonRect.contains(Globals.getMousePointRelativeToClient(bot))) ? Color.white : Color.cyan;
        g.setColor(buttonColor);
        Utilities.drawCenteredText(g, 0, label, buttonRect, new Font("Sans Serif", Font.BOLD, 12));
    }
}
