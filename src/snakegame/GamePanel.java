package snakegame;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    public static final int CELL_SIZE = 25;
    public static final int COLUMNS = 24;
    public static final int ROWS = 24;
    private static final int BAR_HEIGHT = 40; // top bar

    private static final int NORMAL_DELAY = 120;

    private Snake snake;
    private Timer timer;

    private int score = 0;
    private boolean gameOver = false;

    public GamePanel() {
        setPreferredSize(new Dimension(COLUMNS * CELL_SIZE, ROWS * CELL_SIZE + BAR_HEIGHT));
        setBackground(new Color(18, 24, 38));
        setFocusable(true);
        addKeyListener(this);

        snake = new Snake(COLUMNS / 2, ROWS / 2);
        timer = new Timer(NORMAL_DELAY, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateGame();
        repaint();
    }

    public void updateGame() {
        if (gameOver) {
            return;
        }
        snake.move();

        if (snake.isOutside(COLUMNS, ROWS) || snake.hitsItself()) {
            gameOver = true;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (!gameOver) {
            if (key == KeyEvent.VK_UP) {
                snake.changeDirection(0, -1);
            } else if (key == KeyEvent.VK_DOWN) {
                snake.changeDirection(0, 1);
            } else if (key == KeyEvent.VK_LEFT) {
                snake.changeDirection(-1, 0);
            } else if (key == KeyEvent.VK_RIGHT) {
                snake.changeDirection(1, 0);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawScoreBar(g);

        g.translate(0, BAR_HEIGHT); // below the bar
        g.clipRect(0, 0, COLUMNS * CELL_SIZE, ROWS * CELL_SIZE);
        drawGrid(g);

        snake.draw(g);

        if (gameOver) {
            drawMessage(g, "GAME OVER", 180, "Score: " + score, 265);
        }

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

    private void drawMessage(Graphics g, String title, int titleX, String text, int textX) {
        g.setColor(new Color(0, 0, 0, 150)); // transparent black
        g.fillRect(0, 230, 600, 110);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.drawString(title, titleX, 280);

        g.setFont(new Font("Arial", Font.PLAIN, 16));
        g.drawString(text, textX, 315);
    }
}
