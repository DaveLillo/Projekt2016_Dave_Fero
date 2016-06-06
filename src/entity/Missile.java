package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import tileMap.TileMap;

public class Missile extends MapObject {

	public BufferedImage missile;
	Player player;
	public double missilestartx;
	public double missilestarty;
	public double missileendx;
	public double missileendy;

	public Missile(TileMap tm, int rotation, Player player) {
		super(tm);
		try {
			missile = ImageIO.read(getClass().getResourceAsStream("/HUD/Missile.gif"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.rotation = rotation;
		this.player = player;
		init();
	}

	private void init() {
		moveSpeed = 5;
		width = 2;
		height = 25;
		updateRotation();
		if (rotation == 0) {
			missilestartx = (int) player.getx() + 10;
			missilestarty = (int) player.gety() - 10;
		} else if (rotation < 90) {
			missilestartx = (int) player.getx() + 10;
			missilestarty = (int) player.gety();
		} else if (rotation == 90) {
			missilestartx = (int) player.getx() + 10;
			missilestarty = (int) player.gety() + 10;
		} else if (rotation < 180) {
			missilestartx = (int) player.getx();
			missilestarty = (int) player.gety() + 10;
		} else if (rotation == 180) {
			missilestartx = (int) player.getx() - 10;
			missilestarty = (int) player.gety() + 10;
		} else if (rotation < 270) {
			missilestartx = (int) player.getx() - 10;
			missilestarty = (int) player.gety();
		} else if (rotation == 270) {
			missilestartx = (int) player.getx() - 10;
			missilestarty = (int) player.gety() - 10;
		} else if (rotation < 360) {
			missilestartx = (int) player.getx();
			missilestarty = (int) player.gety() - 10;
		}
		calculate();
	}

	private void calculate() {
		if (rotation == 0) {
			missileendx = missilestartx;
			missileendy = missilestarty - width;
		} else if (rotation < 90) {
			double a = width * Math.cos(Math.toRadians(90 - rotation));
			double b = Math.sqrt(width * width - a * a);
			missileendx = missilestartx + a;
			missileendy = missilestarty - b;
		} else if (rotation == 90) {
			missileendx = missilestartx + width;
			missileendy = missilestarty;
		} else if (rotation > 90 && rotation < 180) {
			double a = width * Math.cos(Math.toRadians(90 - (rotation - 90)));
			double b = Math.sqrt(width * width - a * a);
			missileendx = missilestartx + b;
			missileendy = missilestarty + a;
		} else if (rotation == 180) {
			missileendx = missilestartx;
			missileendy = missilestarty + width;
		} else if (rotation > 180 && rotation < 270) {
			double a = width * Math.cos(Math.toRadians(270 - rotation));
			double b = Math.sqrt(width * width - a * a);
			missileendx = missilestartx - a;
			missileendy = missilestarty + b;
		} else if (rotation == 270) {
			missileendx = missilestartx - width;
			missileendy = missilestarty;
		} else if (rotation > 270 && rotation < 360) {
			double a = width * Math.cos(Math.toRadians(90 - rotation - 270));
			double b = Math.sqrt(width * width - a * a);
			missileendx = missilestartx - b;
			missileendy = missilestarty + a;
		}
	}

	public void draw(Graphics2D g) {
		update();
		double rotationRequired = Math.toRadians(rotation);
		AffineTransform orig = g.getTransform();
		g.translate(missilestartx, missilestarty);
		g.rotate(rotationRequired);
		g.drawImage(missile, -10, -10, 2, 25, null);
		g.setTransform(orig);
	}

	public Rectangle getRectangle() {
		return new Rectangle((int) x, (int) y, getHeight(), getWidth());
	}

	private void updateRotation() {
		if (rotation >= 360) {
			rotation -= 360;
		}
		if (rotation < 0) {
			rotation += 360;
		}
	}

	private void update() {
		updateRotation();
		missilestartx = missileendx;
		missilestarty = missileendy;
		calculate();
	}
}
