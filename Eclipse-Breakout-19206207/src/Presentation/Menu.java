package Presentation;

import Code.Game;

import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel {
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
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);

        drawString(g, "Welcome to Breakout", new Rectangle(Game.SCREEN_WIDTH / 3, Game.SCREEN_HEIGHT / 14, Game.SCREEN_WIDTH / 3, Game.SCREEN_HEIGHT / 3),
                36);
        drawString(g, "To play a game press N", new Rectangle(Game.SCREEN_WIDTH / 3, 2 * Game.SCREEN_HEIGHT / 8, Game.SCREEN_WIDTH / 3, Game.SCREEN_HEIGHT / 3),
                18);
        drawString(g, "To see the High scores press H",
                new Rectangle(Game.SCREEN_WIDTH / 3, 3 * Game.SCREEN_HEIGHT / 8, Game.SCREEN_WIDTH / 3, Game.SCREEN_HEIGHT / 3), 18);
        drawString(g, "For help press A", new Rectangle(Game.SCREEN_WIDTH / 3, 4 * Game.SCREEN_HEIGHT / 8 - 5
                , Game.SCREEN_WIDTH / 3, Game.SCREEN_HEIGHT / 3), 18);
        drawString(g, "To exit press X", new Rectangle(Game.SCREEN_WIDTH / 3, 5 * Game.SCREEN_HEIGHT / 8 - 10, Game.SCREEN_WIDTH / 3, Game.SCREEN_HEIGHT / 3), 18);

        g.setColor(Color.yellow);
        for(int i = 0; i < 4; i++)
            g.drawRect(200, 185 + 60 * i, 360, 60);
    }
}
