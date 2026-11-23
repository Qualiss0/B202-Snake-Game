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
