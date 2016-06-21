package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Missile extends MapObject {

	public BufferedImage missile;
	Player player;
	public double missilestartx;
	public double missilestarty;
	public double missileendx;
	public double missileendy;

	@SuppressWarnings("resource")
	public Missile(int rotation, Player player) {
		try {
			BufferedReader b = new BufferedReader(new FileReader("Resources/Options/missileSetting.txt"));
			String s = b.readLine();
			if (s.equals("0")) {
				missile = ImageIO.read(getClass().getResourceAsStream("/HUD/Missile.gif"));
			} else if (s.equals("1")) {
				missile = ImageIO.read(getClass().getResourceAsStream("/HUD/missile2.gif"));
			} else if (s.equals("2")) {
				missile = ImageIO.read(getClass().getResourceAsStream("/HUD/missile3.gif"));
			} else {
				missile = ImageIO.read(getClass().getResourceAsStream("/HUD/Missile.gif"));
			}
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
		missilestartx = ((missileendx - missilestartx) / 2) + missilestartx;
		missilestarty = ((missileendy - missilestarty) / 2) + missilestarty;

		calculate();
	}
}
