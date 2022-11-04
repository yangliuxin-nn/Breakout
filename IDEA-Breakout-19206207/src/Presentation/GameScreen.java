package Presentation;

import Code.Ball;
import Code.Brick;
import Code.Game;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JComponent {
    private Game game;
    protected void paintComponent(Graphics g) {
        if (game != null) {
            g.setColor(Color.black);
            g.fillRect(0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);
            g.setColor(Color.green);
            Font font = new Font("Arial", Font.BOLD, 20);
            g.setFont(font);
            int level = game.getLevel() == 6 ? 5 : game.getLevel();
            g.drawString("Level: " + level, 60, 20);
            g.drawString("Lives: " + game.getLives(), 230, 20);
            g.drawString("Score: " + game.getScore(), 400, 20);
            g.drawString("Bricks: " + game.getBrickNum(), 570, 20);

            Font font1 = new Font("Arial", Font.BOLD, 16);
            g.setFont(font1);
            g.setColor(Color.white);
            if(game.isPaused() && game.getLives() > 0){
                g.drawString("Press 'P' to begin", 500, 300);
            }
            if(game.getBonus().isLaser() && game.getLives()>0){
                g.drawString("Press 'S' to trigger laser", 250, 300);
                g.drawString("Laser can move with paddle", 250, 350);
            }
            if(game.getBonus().getTempBall()!=null) {
                if (game.getBonus().isSticky() && game.getLives() > 0 && game.getBonus().getTempBall().isHitP()) {
                    g.drawString("Press 'D' to off sticky", 250, 400);
                    g.drawString("The sticky ball can move with paddle", 250, 450);
                }
            }
            for(Ball b: game.getBalls()) {
                b.draw(g, Color.green);
            }
            g.setColor(Color.GRAY);
            g.fill3DRect(0,30, Game.SCREEN_WIDTH,5,true);
            game.getBonus().laser(g);
            for(Brick br: game.getBricks()){
                if(br.getAlive()) {
                    br.draw(g, br.getColor());
                }
                if(br.getShowBonus()){
                    game.getBonus().dropBonus(br);
                    if(br.getType() <= 9)
                        br.getBonus().draw(g, Color.cyan);
                    else
                        br.getBonus().draw(g, Color.magenta);
                }
            }
            game.getPaddle().draw(g,Color.gray);
        }
    }

    public void addGame(Game g) {
        this.game = g;
    }
}
