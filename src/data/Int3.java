package data;

import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.script.MethodProvider;

import java.io.Serializable;

public class Int3 implements Serializable {

    private static long serialVersionUID = 1L;

    private int x, y, z;

    public Int3(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public Position getPosition() {
        return new Position(x, y, z);
    }

    public boolean containsPlayer() {
        MethodProvider mp = Globals.getBot().getMethods();
        return getPosition().getArea(10).contains(mp.myPlayer());
    }
}
