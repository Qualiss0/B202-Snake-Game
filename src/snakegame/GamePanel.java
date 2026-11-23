package snakegame;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

public class GamePanel extends JPanel {
    public static final int CELL_SIZE = 25;
    public static final int COLUMNS = 24;
    public static final int ROWS = 24;
    private static final int BAR_HEIGHT = 40; // top bar

    private int score = 0;

    public GamePanel() {
        setPreferredSize(new Dimension(COLUMNS * CELL_SIZE, ROWS * CELL_SIZE + BAR_HEIGHT));
        setBackground(new Color(18, 24, 38));
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawScoreBar(g);

        g.translate(0, BAR_HEIGHT); // below the bar
        drawGrid(g);
        g.translate(0, -BAR_HEIGHT);
    }

    private void drawGrid(Graphics g) {
        g.setColor(new Color(26, 33, 50));
        for (int i = 0; i <= COLUMNS; i++) {
            g.drawLine(i * CELL_SIZE, 0, i * CELL_SIZE, ROWS * CELL_SIZE);
            g.drawLine(0, i * CELL_SIZE, COLUMNS * CELL_SIZE, i * CELL_SIZE);
        }
    }

    private void drawScoreBar(Graphics g) {
        int width = COLUMNS * CELL_SIZE;
        g.setColor(new Color(30, 38, 58));
        g.fillRect(0, 0, width, BAR_HEIGHT);

        g.setFont(new Font("Arial", Font.BOLD, 15));
        g.setColor(Color.WHITE);
        g.drawString("Score: " + score, 12, 26);
    }
}
