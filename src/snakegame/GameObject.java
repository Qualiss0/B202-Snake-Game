package snakegame;

import java.awt.Point;

public abstract class GameObject implements IDrawable {
    private int x; // column
    private int y; // row

    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isAt(int x, int y) {
        return this.x == x && this.y == y;
    }

    public boolean isAt(Point point) {
        return isAt(point.x, point.y);
    }

    protected int getPixelX() {
        return x * GamePanel.CELL_SIZE;
    }

    protected int getPixelY() {
        return y * GamePanel.CELL_SIZE;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
