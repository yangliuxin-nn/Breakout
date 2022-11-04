package Code;

import java.awt.*;
import java.util.ArrayList;

public class Bonus{
	private int x, y;
	private int count = 0;
	private int laserWidth = 14;
	private int laserHeight = 70;
	private int initialX;//the position where the laser is triggered
	private int initialX_Laser;
	private int ini_widthL;
	private boolean isLaser;
	private boolean isSticky;
	private boolean isMulti;
	private boolean triggerLaser;
	private boolean canSet = true;//to make initialX constant during one trigger
	public static final int DIAMETER = 14;
	private Paddle paddle;
	private Ball tempBall;
	private Game game;

	public Bonus(int x, int y){
		this.x = x;
		this.y = y;
	}
	public Bonus(Game game) {
		this.game = game;
		this.paddle = game.getPaddle();
	}

	public void draw(Graphics g, Color color) {
		g.setColor(color);
		g.fillOval(x, y, DIAMETER, DIAMETER);
	}

	public void dropBonus(Brick br){
		if (br.getBonus().getBonusRec().intersects(paddle.getPaddleRec())) {
			br.setShowBonus(false);
			game.addScore();
			if(br.getType() <= 3) {
				isSticky = true;
			}
			else if(br.getType() <= 5 && br.getType() > 3){
				game.setWiden(true);
				paddle.setTime(paddle.getTime() + 150);
			}
			else if(br.getType() <= 7 && br.getType() > 5) {
				isLaser = true;
			}
			else if(br.getType() <= 9 && br.getType() > 7) {
				isMulti = true;
			}
		}
		br.getBonus().y += 3;
		if((y + DIAMETER) >= Game.SCREEN_HEIGHT)
			br.setShowBonus(false);
	}

	public void laser(Graphics g){
		if(isLaser && game.getPlayerListener().isReadyLaser())
			triggerLaser = true;
		else
			game.getPlayerListener().setReadyLaser(false);
		if(isLaser && canSet) {
			ini_widthL = paddle.getWidth();
			if(triggerLaser) {
				initialX = paddle.getX();
				initialX_Laser = initialX + paddle.getWidth() / 2 - laserWidth / 2;
			}
		}
		if(isLaser && triggerLaser) {
			canSet = false;
			drawLaser(g,initialX);
			Rectangle laserRec = new Rectangle(initialX_Laser, paddle.getY() - laserHeight - count, laserWidth, count);
			for (Brick br : game.getBricks()) {
				if (br.getBrick().intersects(laserRec)) {
					br.setAlive(false);
					if (br.getBonus() != null)
						br.setShowBonus(true);
					game.addScore();
				}
			}
			count += 8;
			if (paddle.getY() - laserHeight - count <= 35) {
				clearLaser();
			}
		}
		if(isLaser && !game.getPlayerListener().isReadyLaser()) {
			drawLaser(g, paddle.getX());
		}
	}

	public void drawLaser(Graphics g, int x){
		g.setColor(Color.magenta);
		Polygon p = new Polygon();
		p.addPoint(x + ini_widthL / 2 - laserWidth / 2, paddle.getY() - laserHeight - count);
		p.addPoint(x + ini_widthL / 2 + laserWidth / 2, paddle.getY() - laserHeight - count);
		p.addPoint(x + ini_widthL / 2, paddle.getY() - laserHeight - count - 14);
		g.fillPolygon(p);
		g.fill3DRect(x + ini_widthL/2-laserWidth/2, paddle.getY()-laserHeight-count,laserWidth,laserHeight,true);
	}

	public void multiBall(){
		ArrayList<Ball> tempBalls = new ArrayList<Ball>(game.getBalls());
		for(Ball b: tempBalls){
			if(tempBalls.size() <= 12) {
				game.getBalls().add(new Ball(game, b.getX(), b.getY(), b.getDx() + 1, b.getDy()));
				game.getBalls().add(new Ball(game, b.getX(), b.getY(), b.getDx() - 1, b.getDy()));
			}
		}
	}

	public boolean notSticky(){
		for(Ball b: game.getBalls()) {
			if (b.isHitP()) {
				if(isSticky) {
					tempBall = b;
					//tempBall is the ball that will move with the paddle
					if (game.getPlayerListener().isOffSticky()) {
						isSticky = false;
						tempBall.setHitP(false);
						return true;
					}
					return false;
				}
			}
			b.setHitP(false);
		}
		return true;
	}

	public void clearLaser(){
		isLaser = false;
		game.getPlayerListener().setReadyLaser(false);
		triggerLaser = false;
		canSet = true;
		count = 0;
	}

	public void clearBonus(){
		isMulti = false;
		game.setWiden(false);
		isLaser = false;
		isSticky = false;
	}

	public boolean isMulti(){
		if(isMulti){
			isMulti = false;
			return true;
		}
		return false;
	}

	public Ball getTempBall() {
		return tempBall;
	}
	public Rectangle getBonusRec(){
		return new Rectangle(x,y,DIAMETER,DIAMETER);
	}
	public boolean isLaser(){
		return isLaser;
	}
	public boolean isSticky(){
		return isSticky;
	}
}
