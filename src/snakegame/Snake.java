package snakegame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class Snake implements IDrawable {
    private ArrayList<Point> body = new ArrayList<>();
    private int dx;
    private int dy;
    private boolean growNextMove = false;

    public Snake(int startX, int startY, int length) {
        if (length < 1) {
            throw new IllegalArgumentException("The snake needs at least 1 part.");
        }
        for (int i = 0; i < length; i++) {
            body.add(new Point(startX - i, startY));
        }
        dx = 1;
        dy = 0;
    }

    public Snake(int startX, int startY) {
        this(startX, startY, 3);
    }

    public void move() {
        Point head = body.get(0);
        Point newHead = new Point(head.x + dx, head.y + dy);
        body.add(0, newHead);

        if (growNextMove) {
            growNextMove = false; // keep the tail
        } else {
            body.remove(body.size() - 1);
        }
    }

    public void grow() {
        growNextMove = true;
    }

    // No turning back
    public void changeDirection(int newDx, int newDy) {
        if (newDx == -dx && newDy == -dy) {
            return;
        }
        dx = newDx;
        dy = newDy;
    }

    public boolean hitsItself() {
        Point head = body.get(0);
        for (int i = 1; i < body.size(); i++) {
            if (head.equals(body.get(i))) {
                return true;
            }
        }
        return false;
    }

    public boolean isOutside(int columns, int rows) {
        Point head = body.get(0);
        return head.x < 0 || head.x >= columns || head.y < 0 || head.y >= rows;
    }

    public boolean isOn(int x, int y) {
        for (Point part : body) {
            if (part.x == x && part.y == y) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void draw(Graphics g) {
        int size = GamePanel.CELL_SIZE;
        for (int i = 0; i < body.size(); i++) {
            Point part = body.get(i);
            if (i == 0) {
                g.setColor(new Color(60, 220, 110)); // head
            } else {
                g.setColor(new Color(30, 150, 75));  // body
            }
            g.fillRoundRect(part.x * size + 1, part.y * size + 1, size - 2, size - 2, 8, 8);
        }
    }

    public Point getHead() {
        return body.get(0);
    }
}
