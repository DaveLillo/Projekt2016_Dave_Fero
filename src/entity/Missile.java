package entity;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import tileMap.TileMap;

public class Missile extends MapObject {

	public BufferedImage missile;

	public Missile(TileMap tm, int rotation) {
		super(tm);
		// TODO Auto-generated constructor stub
		try {
			missile = ImageIO.read(getClass().getResourceAsStream("/HUD/Missile.gif"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.rotation = rotation;
		init();
	}

	private void init() {
		x = 100;
		y = 100;

	}

	public void draw(Graphics2D g) {
		update();
		System.out.println("x: " + x + ", y: " + y + ", rotation: " + rotation);
		double rotationRequired = Math.toRadians(rotation);
		AffineTransform orig = g.getTransform();
		g.translate(x, y);
		g.rotate(rotationRequired);
		g.drawImage(missile, -10, -10, 2, 25, null);
		g.setTransform(orig);
	}

	private void update() {
		x++;
		y++;
	}
}
