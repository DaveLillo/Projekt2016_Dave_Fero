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
		moveSpeed = 1;
		length = 25;
		fatness = 2;
		updateRotation();

		missilestartx = player.getx();
		missilestarty = player.gety();

		calculate();
	}

	private void calculate() {

		double unitx = Math.cos(Math.toRadians(rotation));
		double unity = Math.sin(Math.toRadians(rotation));

		double offsetx = unitx * length;
		double offsety = unity * length;

		missileendx = missilestartx + offsetx;
		missileendy = missilestarty + offsety;
	}

	public void draw(Graphics2D g) {
		update();
		double rotationRequired = Math.toRadians(rotation);
		AffineTransform orig = g.getTransform();
		g.translate(missilestartx, missilestarty);
		g.rotate(rotationRequired);
		g.drawImage(missile, 0, -1, 25, 2, null);
		g.setTransform(orig);
	}

	public Rectangle getRectangle() {
		return new Rectangle((int) x, (int) y, getHeight(), getWidth());
	}

	private void updateRotation() {
		rotation %= 360;
	}

	private void update() {
		missilestartx = missileendx;
		missilestarty = missileendy;

		calculate();
	}
}
