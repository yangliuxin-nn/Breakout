package Presentation;

import Code.Game;
import Code.ScoreKeeper;

import javax.swing.*;
import java.awt.*;

public class ScoreScreen extends JPanel {
    private ScoreKeeper scoreKeeper;
    private boolean menu = false;
    private PlayerListener listener;

    public ScoreScreen(ScoreKeeper sc, PlayerListener l) {
        this.scoreKeeper = sc;
        this.listener = l;
    }

    private void drawString(Graphics g, String text, Rectangle rect, int size) {
        Graphics2D g2d = (Graphics2D) g.create();

        Font font = new Font("Arial", Font.BOLD, size);
        g2d.setFont(font);
        FontMetrics metrics = g2d.getFontMetrics();
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();

        g2d.setColor(Color.GREEN);
        g2d.drawString(text, x, y);
    }

    public void paintComponent(Graphics g) {
        g.fillRect(0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);
        drawString(g, "Breakout Hall of Fame", new Rectangle(0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT / 8),
                36);
        g.setColor(Color.WHITE);
        ScoreKeeper.Score[] scores = scoreKeeper.getScores();
        g.setFont(new Font("Arial", Font.BOLD, 24));
        for (int i = 0; i < scores.length; i++) {
            ScoreKeeper.Score score = scores[i];
            g.drawString("" + (i+1), Game.SCREEN_WIDTH / 9, 96 + i * 32);
            g.drawString(score.getName(), 3 * Game.SCREEN_WIDTH / 10, 96 + i * 32);
            g.drawString("" + score.getScore(), 7 * Game.SCREEN_WIDTH / 10, 96 + i * 32);
        }
        drawString(g, "Press 'M' to return to the Main Menu", new Rectangle(0, 416, Game.SCREEN_WIDTH, 96), 24);
    }

    public void run() {
        while (!menu) {
            if (listener.isMenu()) {
                menu = true;
            }
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
            }
        }
        listener.reset();
        menu = false;
    }
}
