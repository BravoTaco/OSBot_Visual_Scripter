package gui.components;

import utils.Utilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class Button extends JButton {
    public Button(Icon icon, Icon hoveredIcon) {
        Utilities.setDefaultColors(this);
        setFocusable(false);
        setIcon(icon);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                onClick();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setIcon(hoveredIcon);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setIcon(icon);
            }
        });
    }

    public Button(String text) {
        Utilities.setDefaultColors(this);
        setFont(new Font("Sans Serif", Font.BOLD, 14));
        setText(text);
        setFocusable(false);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                onClick();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setForeground(Color.orange);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setForeground(Color.cyan);
            }
        });
    }

    public abstract void onClick();
}
