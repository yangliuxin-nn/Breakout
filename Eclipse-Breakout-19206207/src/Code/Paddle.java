package Code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Paddle {
	private int x, y;
	private int width;
	private int time;
	private Game game;
	public static final int WIDTH = 80;
	public static final int HEIGHT = 20;

	public Paddle(Game game, int x, int y, int width) {
		this.game = game;
		this.x = x;
		this.y = y;
		this.width = width;
	}

	public void draw(Graphics g, Color c) {
		g.setColor(c);
		g.fill3DRect(x, y, width, HEIGHT,true);
	}

	public void movePaddle(int direction){
		//1: left; 0: right
		if(direction == 1) {
			x -= 20;
			if(x <= 0)
				x = 0;
		}
		else if(direction == 0) {
			x += 20;
			if(x + this.width >= 750)
				x = 750 - this.width;
		}
	}

	public void checkTime(){
		if(time > 0) {
			time--;
		}
		else{
			game.setWiden(false);
			if(width > WIDTH) {
				width -= 5;
			}
		}
		if(game.getWiden() && width < WIDTH * 2){
			width += 5;
		}
	}

	public void setX(int x){
		this.x = x;
	}
	public int getX() {
		return x;
	}
	public int getY(){
		return y;
	}
	public void setWidth(int i){
		width = i;
	}
	public int getWidth(){
		return width;
	}
	public void setTime(int t) {
		time = t;
	}
	public int getTime() {
		return time;
	}
	public Rectangle getPaddleRec(){
		return new Rectangle(x,y+3,width,HEIGHT);
	}
}
