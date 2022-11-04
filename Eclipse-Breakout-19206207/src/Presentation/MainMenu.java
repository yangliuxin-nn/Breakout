package Presentation;

import Code.Game;
import Code.ScoreKeeper;

import javax.swing.*;
import java.awt.*;

public class MainMenu {
    private boolean exit;
    private JFrame window;
    private PlayerListener listener;
    private Game game;
    private GameScreen gameScreen;
    private Menu menu;
    private JPanel main;
    private ScoreScreen scoreScreen;
    private CardLayout cardLayout;
    private ScoreKeeper scoreKeeper;
    private AboutScreen aboutScreen;

    public MainMenu(JFrame win, PlayerListener lis) {
        this.window = win;
        this.listener = lis;
        cardLayout = new CardLayout();
        main = new JPanel(cardLayout);
        menu = new Menu();
        gameScreen = new GameScreen();
        aboutScreen = new AboutScreen(listener);
        scoreKeeper = new PersistentScoreKeeper();
        scoreScreen = new ScoreScreen(scoreKeeper, listener);
        main.add(menu, "Main Menu");
        main.add(gameScreen, "Game Screen");
        main.add(scoreScreen, "High Scores");
        main.add(aboutScreen, "About");
        window.getContentPane().add(main);
    }

    public void run() {
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(Long.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        while (!exit) {
            if (listener.isNew()) {
                game = new Game(listener, gameScreen);
                cardLayout.show(main, "Game Screen");
                game.run();
                int s = game.getScore();
                if (s > scoreKeeper.getLowestScore()) {
                    String name = JOptionPane.showInputDialog("New High Score, Please enter your name:");
                    scoreKeeper.addScore(name, s);
                    scoreKeeper.saveScores();
                    cardLayout.show(main, "High Scores");
                    scoreScreen.run();
                }
                else if(game.getLives() == 0){
                    JOptionPane.showMessageDialog(null,"Game Over");
                }
                cardLayout.show(main, "Main Menu");
                listener.reset();
            } else if (listener.isHigh()) {
                cardLayout.show(main, "High Scores");
                scoreScreen.run();
                cardLayout.show(main, "Main Menu");
            } else if (listener.isExit()) {
                exit = true;
                scoreKeeper.saveScores();
                System.exit(0);
            } else if (listener.isAbout()) {
                cardLayout.show(main, "About");
                aboutScreen.run();
                cardLayout.show(main, "Main Menu");
            }
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        exit = false;
    }
}
