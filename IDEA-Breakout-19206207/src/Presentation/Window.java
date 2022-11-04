package Presentation;
import javax.swing.*;

import Code.Game;

public class Window{
	public static void main(String[] args) {
		JFrame window = new JFrame();
		window.setSize(Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT + 35);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setTitle("Breakout");
		window.setLocationRelativeTo(null);
        PlayerListener p = new PlayerListener();
        window.addKeyListener(p);
        MainMenu menu = new MainMenu(window, p);
        window.setVisible(true);
        window.requestFocusInWindow();
        menu.run();
	}
}