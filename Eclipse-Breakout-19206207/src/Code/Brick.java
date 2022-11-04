package Code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

public class Brick {
	private int x, y;
	private int type;
	private static int column = 15;
	private static int row = 12;
	public static final int BRICK_WIDTH = 50;
	public static final int BRICK_HEIGHT = 16;
	private boolean alive;
	private boolean showBonus;
	private Color color;
	private Bonus bonus;

	public Brick(int x, int y, Color color, int type, boolean alive) {
		this.x = x;
		this.y = y;
		this.color = color;
		this.type = type;
		this.alive = alive;
		if (type <= 15) {
			bonus = new Bonus(x + BRICK_WIDTH / 2 - Ball.DIAMETER / 2, y + BRICK_HEIGHT);
		}
	}

	public static ArrayList<Brick> setBricks(int level){
		ArrayList<Brick> bricks = new ArrayList<>();
		Color tmpColor = Color.black;
		Random ran = new Random();
		switch(level) {
			case 5:{
				for(int i=4; i<row; i++) {
					for(int j=0; j<column; j++) {
						int random = ran.nextInt(100) + 1;
						switch(i) {
							case 4: bricks.add(new Brick(j*BRICK_WIDTH, i*BRICK_HEIGHT, Color.red, random, true));break;
							case 5: bricks.add(new Brick(j*BRICK_WIDTH, i*BRICK_HEIGHT, Color.orange, random, true));break;
							case 6: bricks.add(new Brick(j*BRICK_WIDTH, i*BRICK_HEIGHT, new Color((float)(0.8), 0, 0),random, true));break;
							case 7: bricks.add(new Brick(j*BRICK_WIDTH, i*BRICK_HEIGHT, Color.pink, random, true));break;
							case 8: bricks.add(new Brick(j*BRICK_WIDTH, i*BRICK_HEIGHT, Color.green, random, true));break;
							case 9: bricks.add(new Brick(j*BRICK_WIDTH, i*BRICK_HEIGHT, new Color(224, 204, 27), random, true));break;
							case 10: bricks.add(new Brick(j*BRICK_WIDTH, i*BRICK_HEIGHT, Color.cyan, random, true));break;
							case 11: bricks.add(new Brick(j*BRICK_WIDTH, i*BRICK_HEIGHT, new Color(15, 51, 154), random, true));break;
						}
					}
				}
			}break;

			case 3:{
				for(int i=4;i<row;i++){
					for(int j=0;j<column;j++){
						int random = (int) (Math.random()*100);
						switch (i){
							case 4: bricks.add(new Brick(j*BRICK_WIDTH, i*BRICK_HEIGHT, Color.red, random, true));break;
							case 6: switch (j%2){
									case 0: bricks.add(new Brick(j*BRICK_WIDTH, i*BRICK_HEIGHT, Color.magenta, random, true));break;
							}break;
							case 7: switch (j%2){
								case 1: bricks.add(new Brick(j*BRICK_WIDTH, i*BRICK_HEIGHT, new Color(255 ,128,255), random, true));break;
							}break;
							case 8: switch (j%2){
								case 1: bricks.add(new Brick(j*BRICK_WIDTH, i*BRICK_HEIGHT, new Color(0 ,255,64), random, true));break;
							}break;
							case 9: switch (j%2){
								case 0: bricks.add(new Brick(j*BRICK_WIDTH, i*BRICK_HEIGHT, new Color(194,247,136), random, true));break;
							}break;
							case 10: switch (j%2){
								case 1: bricks.add(new Brick(j*BRICK_WIDTH, i*BRICK_HEIGHT, new Color(129 ,254,248), random, true));break;
							}break;
							case 11: bricks.add(new Brick(j*BRICK_WIDTH, i*BRICK_HEIGHT, new Color(201 ,247,254), random, true));break;
						}
					}
				}
			}break;

			case 2:	{
				bricks.add(new Brick((column-1)*BRICK_WIDTH, 4*BRICK_HEIGHT, Color.red, (int) (Math.random()*100), true));
				for(int j=column-1; j>=column-2; j--)
					bricks.add(new Brick(j*BRICK_WIDTH, 5*BRICK_HEIGHT, Color.orange, (int) (Math.random()*100), true));
				for(int j=column-1; j>=column-3; j--)
					bricks.add(new Brick(j*BRICK_WIDTH, 6*BRICK_HEIGHT, Color.yellow, (int) (Math.random()*100), true));
				for(int j=column-1; j>=column-5; j--)
					bricks.add(new Brick(j*BRICK_WIDTH, 7*BRICK_HEIGHT, Color.green, (int) (Math.random()*100),true));
				for(int j=column-1; j>=column-7; j--)
					bricks.add(new Brick(j*BRICK_WIDTH, 8*BRICK_HEIGHT, new Color(180, 241, 14), (int) (Math.random()*100),true));
				for(int j=column-1; j>=column-9; j--)
					bricks.add(new Brick(j*BRICK_WIDTH, 9*BRICK_HEIGHT, Color.orange, (int) (Math.random()*100),true));
				for(int j=column-1; j>=column-11; j--)
					bricks.add(new Brick(j*BRICK_WIDTH, 10*BRICK_HEIGHT, Color.cyan, (int) (Math.random()*100),true));
				for(int j=column-1; j>=column-13; j--)
					bricks.add(new Brick(j*BRICK_WIDTH, 11*BRICK_HEIGHT, Color.blue, (int) (Math.random()*100),true));
			}break;

			case 1:{
				bricks.add(new Brick(column/2*BRICK_WIDTH, 6*BRICK_HEIGHT, Color.red, (int) (Math.random()*100), true));//?????index??7
				for(int j=6; j<9; j++)
					bricks.add(new Brick(j*BRICK_WIDTH, 7*BRICK_HEIGHT, new Color(254, 123, 118), (int) (Math.random()*100), true));
				for(int j=5; j<10; j++)
					bricks.add(new Brick(j*BRICK_WIDTH, 8*BRICK_HEIGHT, Color.orange, (int) (Math.random()*100), true));
				for(int j=4; j<11; j++)
					bricks.add(new Brick(j*BRICK_WIDTH, 9*BRICK_HEIGHT,  new Color(213, 254, 118, 255), (int)(Math.random()*100), true));
				for(int j=3; j<12; j++)
					bricks.add(new Brick(j*BRICK_WIDTH, 10*BRICK_HEIGHT, Color.yellow, (int) (Math.random()*100), true));
				for(int j=4; j<11; j++)
					bricks.add(new Brick(j*BRICK_WIDTH, 11*BRICK_HEIGHT, new Color(213, 254, 118, 255), (int) (Math.random()*100), true));
				for(int j=5; j<10; j++)
					bricks.add(new Brick(j*BRICK_WIDTH, 12*BRICK_HEIGHT,  Color.orange, (int) (Math.random()*100), true));
				for(int j=6; j<9; j++)
					bricks.add(new Brick(j*BRICK_WIDTH, 13*BRICK_HEIGHT, new Color(254, 123, 118), (int) (Math.random()*100), true));
				bricks.add(new Brick(column/2*BRICK_WIDTH, 14*BRICK_HEIGHT, Color.red, (int) (Math.random()*100), true));//?????index??7
			}break;

			case 4:{
				for(int i=4; i<row; i+=2){
					bricks.add(new Brick(column/2*BRICK_WIDTH, i*BRICK_HEIGHT, Color.YELLOW, (int) (Math.random()*100), true));
				}
				for(int i=0; i<column; i+=14){
					for(int j=8; j<=row+2; j+=2) {
						switch (j){
							case 8: tmpColor = Color.white;break;
							case 10: tmpColor = Color.yellow;break;
							case 12: tmpColor = Color.green;break;
							case 14: tmpColor = new Color(255,128,0);break;
						}
						bricks.add(new Brick(i * BRICK_WIDTH, j * BRICK_HEIGHT, tmpColor, (int) (Math.random() * 100), true));
					}
				}
				for(int i=1; i<=2; i++){
					for(int j=7; j<=row+2; j+=2) {
						switch (j){
							case 7: tmpColor = Color.white;break;
							case 9: tmpColor = Color.yellow;break;
							case 11: tmpColor = Color.green;break;
							case 13: tmpColor = new Color(255,128,0);break;
						}
						bricks.add(new Brick(i * BRICK_WIDTH, j * BRICK_HEIGHT, tmpColor, (int) (Math.random() * 100), true));
					}
				}
				for(int i=3; i<=4; i++){
					for(int j=6; j<row+2; j+=2) {
						switch (j){
							case 6: tmpColor = Color.white;break;
							case 8: tmpColor = Color.yellow;break;
							case 10: tmpColor = Color.green;break;
							case 12: tmpColor = new Color(255,128,0);break;
						}
						bricks.add(new Brick(i * BRICK_WIDTH, j * BRICK_HEIGHT, tmpColor, (int) (Math.random() * 100), true));
					}
				}
				for(int i=5; i==5||i==6||i==8||i==9; i++){
					for(int j=5; j<row; j+=2) {
						switch (j){
							case 5: tmpColor = Color.white;break;
							case 7: tmpColor = Color.yellow;break;
							case 9: tmpColor = Color.green;break;
							case 11: tmpColor = new Color(255,128,0);break;
						}
						bricks.add(new Brick(i * BRICK_WIDTH, j * BRICK_HEIGHT, tmpColor, (int) (Math.random() * 100), true));
					}
				}
				for(int i=8; i<=9; i++){
					for(int j=5; j<row; j+=2) {
						switch (j){
							case 5: tmpColor = Color.white;break;
							case 7: tmpColor = Color.yellow;break;
							case 9: tmpColor = Color.green;break;
							case 11: tmpColor = new Color(255,128,0);break;
						}
						bricks.add(new Brick(i * BRICK_WIDTH, j * BRICK_HEIGHT, tmpColor, (int) (Math.random() * 100), true));
					}
				}
				for(int i=10; i<=11; i++){
					for(int j=6; j<row+2; j+=2) {
						switch (j){
							case 6: tmpColor = Color.white;break;
							case 8: tmpColor = Color.yellow;break;
							case 10: tmpColor = Color.green;break;
							case 12: tmpColor = new Color(255,128,0);break;
						}
						bricks.add(new Brick(i * BRICK_WIDTH, j * BRICK_HEIGHT, tmpColor, (int) (Math.random() * 100), true));
					}
				}
				for(int i=12; i<=13; i++){
					for(int j=7; j<row+2; j+=2) {
						switch (j) {
							case 7: tmpColor = Color.white;break;
							case 9: tmpColor = Color.yellow;break;
							case 11: tmpColor = Color.green;break;
							case 13: tmpColor = new Color(255, 128, 0);break;
						}
						bricks.add(new Brick(i * BRICK_WIDTH, j * BRICK_HEIGHT, tmpColor, (int) (Math.random() * 100), true));
					}
				}
				for(int i=8; i<row; i+=2){
					switch (i) {
						case 8: tmpColor = Color.white;break;
						case 10: tmpColor = Color.yellow;break;
						case 12: tmpColor = Color.green;break;
						case 14: tmpColor = new Color(255, 128, 0);break;
					};
					bricks.add(new Brick(14 * BRICK_WIDTH, i * BRICK_HEIGHT, tmpColor, (int) (Math.random() * 100), true));
				}
			}break;
		}
		return bricks;
	}

	public void draw(Graphics g, Color c) {
		g.setColor(Color.black);
		g.draw3DRect(x,y,BRICK_WIDTH,BRICK_HEIGHT,true);
		g.setColor(c);
        g.fill3DRect(x,y,BRICK_WIDTH,BRICK_HEIGHT,true);
	}

	public Color getColor() {
		return color;
	}
	public Rectangle getBrick() {
		return new Rectangle(x,y,BRICK_WIDTH,BRICK_HEIGHT);
	}
	public Bonus getBonus(){
		return bonus;
	}
	public int getX() {
		return x;
	}
	public int getType() {
		return type;
	}
	public void setShowBonus(boolean showBonus) {
		this.showBonus = showBonus;
	}
	public void setAlive(boolean alive){
		this.alive = alive;
	}
	public boolean getAlive(){
		return alive;
	}
	public boolean getShowBonus() {
		return showBonus;
	}
}
