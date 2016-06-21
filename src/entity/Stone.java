package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import handlers.Content;

public class Stone extends Enemy {

	private BufferedImage[] idleSprites;

	// 0 = links oben, 1 = links unten, 2 = rechts unten, 3 = rechts oben
	private int dir;

	public Stone() {
		health = 3;
		length = 30;
		fatness = 30;
		cwidth = 30;
		cheight = 30;

		isPlayer = false;

		damage = 1;
		moveSpeed = 0.2;

		idleSprites = Content.Stone[0];

		animation.setFrames(idleSprites);
		animation.setDelay(0);
		x = 30;
		y = 30;
		dir = (int) Math.random() * 4;
	}

	public void update() {
		/**
		 * 0 = links oben 1 = links unten 2 = rechts unten 3 = rechts oben
		 */
		if (x < 0) {
			if (dir == 0) {
				dir = 3;
			} else {
				dir = 2;
			}
		}

		if (x > 280) {
			if (dir == 3) {
				dir = 0;
			} else {
				dir = 1;
			}
		}

		if (y < 0) {
			if (dir == 0) {
				dir = 1;
			} else {
				dir = 2;
			}
		}

		if (y > 200) {
			if (dir == 2) {
				dir = 3;
			} else {
				dir = 0;
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
		update();
		super.draw(g);
	}

	public double getRadius() {
		return length / 2;
	}

}
