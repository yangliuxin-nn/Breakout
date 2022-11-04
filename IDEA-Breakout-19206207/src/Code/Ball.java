package Code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import static java.lang.Math.sqrt;

public class Ball{
	private int x, y;
	private double dx, dy;
	private double speed;
	private boolean hitP;
	private boolean onceSpeed = true;
	private Game game;
	private ArrayList<Brick> bricks;
	public static final int DIAMETER = 14;

	public Ball(Game game, int x, int y, double dx, double dy) {
		this.x = x;
		this.y = y;
		this.game = game;
		this.dx = dx;
		this.dy = dy;
		speed = Math.sqrt(dx * dx + dy * dy);
	}

	public void hitBrick() {
		int hitNum;
		bricks = game.getBricks();
		hitNum = 0;
		for (Brick br : bricks) {
			if(br.getBrick().intersects(getBall()) && br.getAlive()) {
				game.addScore();
				hitNum++;
				br.setAlive(false);
				if(br.getBonus() != null)
					br.setShowBonus(true);
				if(hitNum < 2) {
					if((x + DIAMETER/2) < br.getX() || (x + DIAMETER/2) > (br.getX() + br.BRICK_WIDTH))
						dx = -dx;
					else
						dy = -dy;
				}
			}
		}
	}

	public void hitPaddle(Paddle paddle) {
		double rand, radian, alpha;
		if(onceSpeed) {
			if (getBall().intersects(paddle.getPaddleRec())) {
				y = paddle.getY() - DIAMETER;
				hitP = true;
				rand = Math.toRadians(Math.random() * 6 + 10);//[10,15]
				if(y + DIAMETER > paddle.getY()) {
					dx = - dx;
				}
				else if ((x + DIAMETER) >= paddle.getX() && (x + DIAMETER / 2) < (paddle.getX() + paddle.getWidth() * 1 / 3)) {
					if (Math.round(dx) == 0) {
						dx = -1;
						dy = - sqrt(speed * speed - dx * dx);
					} else {
						radian = Math.abs(Math.atan(dy / dx));
						if (dx > 0) {
							alpha = radian + rand;
							if(alpha > Math.toRadians(85)) {
								alpha = Math.toRadians(85);
							}
							dx = speed * Math.cos(alpha);
							dy = -(speed * Math.sin(alpha));
						} else if (dx < 0) {
							alpha = radian - rand;
							if(alpha < 0)
								alpha = radian;
							dx = - speed * Math.cos(alpha);
							dy = - speed * Math.sin(alpha);
						}
					}
				} else if ((x + (DIAMETER / 2)) >= (paddle.getX() + paddle.getWidth() * 1 / 3) && (x + (DIAMETER / 2)) <= (paddle.getX() + paddle.getWidth() * 2 / 3)) {
					dy = -dy;
				} else if ((x + (DIAMETER / 2)) > (paddle.getX() + paddle.getWidth() * 2 / 3) && x <= (paddle.getX() + paddle.getWidth())) {
					if (Math.round(dx) == 0) {
						dx = 1;
						dy = - sqrt(speed * speed - dx * dx);
					} else {
						radian = Math.abs(Math.atan(dy / dx));
						if (dx > 0) {
							alpha = radian - rand;
							if(alpha < 0)
								alpha = radian;
							dx = speed * Math.cos(Math.toRadians(alpha));
							dy = -speed * Math.sin(Math.toRadians(alpha));
						} else if (dx < 0) {
							alpha = radian + rand;
							if(alpha > Math.toRadians(85))
								alpha = Math.toRadians(85);
							dx = - (speed * Math.cos(alpha));
							dy = - (speed * Math.sin(alpha));
						}
					}
				}
			}
		}
		//accelerate the speed but set upper bound for it
		speed += 0.006;
		if(speed > 16)
			speed = 16;
	}

	public void hitEdge(){
		if(x <= 0 || x + DIAMETER >= 750) {
			dx = -dx;
			if(Math.round(dx) == 0)
				dx = -1;
		}
		if(y <= 35) {
			dy = -dy;
		}
	}

	public void move(double dx, double dy){
		if (Math.round(dy) == 0)//avoid moving horizontally
			dy = Math.abs(dy) / dy;
		x = (int) Math.round(x + dx);
		y = (int) Math.round(y + dy);
		if(y <= 35)
			y = 35;
		else if(x + DIAMETER >= 750)
			x = 750 - DIAMETER;
		else if(x <= 0)
			x = 0;
	}

	public void move(int dx1){
		x += dx1;
		if(x + DIAMETER >= 15 * Brick.BRICK_WIDTH)
			x = 15 * Brick.BRICK_WIDTH - DIAMETER;
	}

	public void draw(Graphics g, Color color) {
		g.setColor(color);
		g.fillOval(x, y, DIAMETER, DIAMETER);
	}

	public double getDx() {
		return dx;
	}
	public double getDy() {
		return dy;
	}
	public void setX(int x){
		this.x = x;
	}
	public int getX(){
		return x;
	}
	public int getY() {
		return y;
	}
	public void setHitP(boolean hitP) {
		this.hitP = hitP;
	}
	public boolean isHitP() {
		return hitP;
	}
	public Rectangle getBall() {
		return new Rectangle(x, y, DIAMETER, DIAMETER);
	}
	public void setOnceSpeed(boolean onceSpeed) {
		this.onceSpeed = onceSpeed;
	}
}