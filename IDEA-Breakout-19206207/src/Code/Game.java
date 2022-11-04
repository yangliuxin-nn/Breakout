package Code;

import Presentation.GameScreen;
import Presentation.PlayerListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Game extends JComponent implements Runnable{
	public static final int SCREEN_WIDTH = 763;
	public static final int SCREEN_HEIGHT = 512;
	private final int TARGET_FPS = 40;
	private final double TARGET_TIME_BETWEEN_RENDERS = 1000000000 / TARGET_FPS;
	public static final Rectangle SCREEN_BOUNDS = new Rectangle(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
	private int score;
	private int life = 5;
	private int level = 1;
	private boolean pause = true;
	private boolean isWiden;
	private Paddle paddle;
	private Ball ball;
	private Bonus bonus;
	private PlayerListener playerListener;
	private GameScreen gameScreen;
	private ArrayList<Brick> bricks;
	private ArrayList<Ball> balls = new ArrayList<Ball>();

	public Game(PlayerListener playerListener, GameScreen g){
		score = 0;
		this.gameScreen = g;
		g.addGame(this);
		this.playerListener = playerListener;
		paddle = new Paddle(this, (SCREEN_WIDTH - Paddle.WIDTH) / 2, SCREEN_HEIGHT - Paddle.HEIGHT, Paddle.WIDTH);
		bonus = new Bonus(this);
		bricks = Brick.setBricks(level);
		ball = new Ball(this,(SCREEN_WIDTH-Ball.DIAMETER)/2,SCREEN_HEIGHT-Paddle.HEIGHT-Ball.DIAMETER,6, -6);
		balls.add(ball);
	}

	public void run() {
		double now;
		double lastRenderTime;
		now = System.nanoTime();
		while (life > 0 && level <= 5) {
			lastRenderTime = now;
			if (!pause) {
				updateGame();
				while (now - lastRenderTime < TARGET_TIME_BETWEEN_RENDERS) {
					try {
						Thread.sleep(5);
					} catch (Exception e) {
					}
					now = System.nanoTime();
				}
				if (playerListener.isPlayPause()) {
					pause = true;
				}
				gameScreen.paintImmediately(SCREEN_BOUNDS);
			}
			else {
				readyGame();
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
				}
			}
		}
	}

	public void updateGame(){
		updateBonus();
		paddle.movePaddle(playerListener.getDirection());
		updateBall();
		paddle.checkTime();
		if(getBrickNum() == 0){
			gameScreen.paintImmediately(SCREEN_BOUNDS);
			if(level < 5) {
				nextRound();
			}
			else {
				JOptionPane.showMessageDialog(null, "Congratulations! You have finished all rounds!");
				level = 6;
			}
		}
	}

	public void updateBonus(){
		if(bonus.isMulti()) {
			bonus.multiBall();
		}
		if(bonus.notSticky()){
			for(Ball b: balls){
				b.move(b.getDx(), b.getDy());
				b.setOnceSpeed(true);
			}
		}
		else{
			if(bonus.getTempBall().getX() > getPaddle().getX() + getPaddle().getWidth())
				bonus.getTempBall().setX(getPaddle().getX() + getPaddle().getWidth() - Ball.DIAMETER);
			if(playerListener.getDirection() == 1 && paddle.getX() >= 20) {
				if(bonus.getTempBall().getX() - 20 > 0)
					bonus.getTempBall().move(-20);
			}
			else if(playerListener.getDirection() == 0 && (paddle.getX() + paddle.getWidth() <= 750))
				bonus.getTempBall().move(20);
			bonus.getTempBall().setOnceSpeed(false);
			//when the ball is stickied, the hitPaddle function is not implemented
		}
	}

	public void updateBall(){
		ArrayList<Ball> discardBall = new ArrayList<>();
		for(Ball b: balls){
			b.hitBrick();
			b.hitEdge();
			b.hitPaddle(paddle);
			if(b.getY() + ball.DIAMETER >= paddle.getY() + Paddle.HEIGHT)
				discardBall.add(b);
		}
		balls.removeAll(discardBall);
		if(balls.size() == 0) {
			life--;
			if(life > 0) {
				ball = new Ball(this, paddle.getX()+paddle.getWidth()/2-Ball.DIAMETER/2, SCREEN_HEIGHT-Paddle.HEIGHT-Ball.DIAMETER, 6, -6);
				readyGame();
				balls.add(ball);
			}
		}
	}

	public void nextRound(){
		playerListener.reset();
		bonus.clearLaser();
		balls.clear();
		bonus.clearBonus();
		JOptionPane.showMessageDialog(null,"Congratulations! Are you ready for the next round?");
		bricks = Brick.setBricks(++level);
		paddle.setX((SCREEN_WIDTH - paddle.WIDTH) / 2);
		paddle.setWidth(paddle.WIDTH);
		ball = new Ball(this,(SCREEN_WIDTH-Ball.DIAMETER)/2,SCREEN_HEIGHT-Paddle.HEIGHT-Ball.DIAMETER,6, -6);
		balls.add(ball);
		readyGame();
	}

	public void readyGame(){
		pause = true;
		if (playerListener.isPlayPause()) {
			pause = false;
		}
	}

	public void addScore(){
		score += 10;
	}
	public void setWiden(boolean widen) {
		isWiden = widen;
	}
	public boolean getWiden() {
		return isWiden;
	}
	public boolean isPaused() {
		return pause;
	}
	public int getLives(){
		return life;
	}
	public int getScore(){
		return score;
	}
	public int getLevel() {
		return level;
	}
	public int getBrickNum(){
		int num = 0;
		for(Brick br: getBricks()){
			if(br.getAlive())
				num++;
		}
		return num;
	}
	public PlayerListener getPlayerListener(){
		return playerListener;
	}
	public Bonus getBonus() {
		return bonus;
	}
	public Paddle getPaddle() {
		return paddle;
	}
	public ArrayList<Brick> getBricks() {
		return bricks;
	}
	public ArrayList<Ball> getBalls(){
		return balls;
	}
}
