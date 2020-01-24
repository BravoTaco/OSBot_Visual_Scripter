package core;

import data.Globals;
import gui.GUI;
import gui.paint.nodes.base_nodes.StartNode;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;
import paint.PaintButton;
import utils.Sleep;

import java.awt.*;

@ScriptManifest(author = "BravoTaco", name = "OSBot Visual Scripter", version = 1.0, info = "", logo = "")
public class VisualScripting extends Script {

    private PaintButton reconfigureButton;

    private boolean scriptInitialized = false;

    @Override
    public void onStart() throws InterruptedException {
        log("Initializing Startup Parameters!");
        Globals.setBot(this.bot);
        GUI.getInstance().exchangeContext(bot);
        GUI.getInstance().start();
        Globals.setStartNode(new StartNode());
        reconfigureButton = new PaintButton(this.getBot(), "Reconfigure", new Point(435, 310), new Dimension(75, 25)) {
            @Override
            public void onClick() {
                GUI.getInstance().pause();
            }
        };
        log("Initializing Startup Parameters Complete!");
        scriptInitialized = true;
    }

    @Override
    public int onLoop() throws InterruptedException {
        if (!GUI.getInstance().wasClosed() && GUI.getInstance().isExecuteClicked())
            if (Globals.getCurrentExecutingNode() == null)
                Globals.setCurrentExecutingNode(Globals.getStartNode());
            else
                Globals.getCurrentExecutingNode().onExecute();
        else if (!GUI.getInstance().isExecuteClicked() && !GUI.getInstance().wasClosed())
            Sleep.sleepUntil(() -> GUI.getInstance().isExecuteClicked() || GUI.getInstance().wasClosed(), 10000, 100);
        else if (GUI.getInstance().wasClosed())
            stop(false);

        return random(400, 800);
    }

    @Override
    public void onExit() throws InterruptedException {
        super.onExit();
        if (GUI.getInstance().getMainFrame().isVisible())
            GUI.getInstance().exit();
        bot.getMouseListeners().forEach((e) -> bot.removeMouseListener(e));
    }

    @Override
    public void onPaint(Graphics2D g) {
        if (scriptInitialized && !GUI.getInstance().wasClosed()) {
            super.onPaint(g);


            if (GUI.getInstance().isExecuteClicked())
                bot.getMouseListeners().forEach((e) -> {
                    if (e instanceof PaintButton)
                        ((PaintButton) e).draw(g);
                });
        }
    }
}
