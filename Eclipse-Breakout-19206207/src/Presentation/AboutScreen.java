package Presentation;

import Code.Game;

import javax.swing.*;
import java.awt.*;

public class AboutScreen extends JPanel {
    private boolean menu = false;
    private PlayerListener listener;

    public AboutScreen(PlayerListener pl) {
        listener = pl;
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);
        g.setColor(Color.green);
        drawString(g, "Breakout Controls", new Rectangle(0, 10, Game.SCREEN_WIDTH, 64), 36);
        Font font = new Font("Arial", Font.BOLD, 20);
        g.setFont(font);
        g.setColor(Color.white);
        g.drawString("Move Left", 1 * Game.SCREEN_WIDTH / 6, 96 + 1 * 32);
        g.drawString("left arrow", 4 * Game.SCREEN_WIDTH / 6, 96 + 1 * 32);
        g.drawString("Move Right", 1 * Game.SCREEN_WIDTH / 6, 96 + 2 * 32);
        g.drawString("right arrow", 4 * Game.SCREEN_WIDTH / 6, 96 + 2 * 32);
        g.drawString("Trigger Laser", 1 * Game.SCREEN_WIDTH / 6, 96 + 3 * 32);
        g.drawString("press S", 4 * Game.SCREEN_WIDTH / 6, 96 + 3 * 32);
        g.drawString("Off Sticky", 1 * Game.SCREEN_WIDTH / 6, 96 + 4 * 32);
        g.drawString("press D", 4 * Game.SCREEN_WIDTH / 6, 96 + 4 * 32);
        g.drawString("Play/Pause", 1 * Game.SCREEN_WIDTH / 6, 96 + 5 * 32);
        g.drawString("press P", 4 * Game.SCREEN_WIDTH / 6, 96 + 5 * 32);
        drawString(g, "Press 'M' to return to the Main Menu", new Rectangle(0, 416, Game.SCREEN_WIDTH, 96), 24);
        g.drawString("Break all bricks in the current round to enter the next round.",  Game.SCREEN_WIDTH / 9, 96 + 8 * 32);
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
}
