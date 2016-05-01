package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import handlers.Content;
import tileMap.TileMap;

public class Stone extends Enemy {

	private BufferedImage[] idleSprites;

	// 0 = links oben, 1 = links unten, 2 = rechts unten, 3 = rechts oben
	private int dir;

	public Stone(TileMap tm) {
		super(tm);
		health = 3;
		width = 30;
		height = 30;
		cwidth = 30;
		cheight = 30;

		isPlayer = false;

		damage = 1;
		moveSpeed = 0.3;

		idleSprites = Content.Stone[0];

		animation.setFrames(idleSprites);
		animation.setDelay(0);
		x = 30;
		y = 30;
		dir = (int) Math.random() * 4;
	}

	public void update() {
		if (flinching) {
			flinchCount++;
			if (flinchCount == 6) {
				flinching = false;
			}
		}

		if (dir == 0) {
			x -= moveSpeed;
			y -= moveSpeed;
		} else if (dir == 1) {
			x -= moveSpeed;
			y += moveSpeed;
		} else if (dir == 2) {
			x += moveSpeed;
			y += moveSpeed;
		} else {
			x += moveSpeed;
			y -= moveSpeed;
		}
		animation.update();
	}

	public void draw(Graphics2D g) {
		super.draw(g);
	}

}
