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

	public Missile(TileMap tm, int rotation, Player player) {
		super(tm);
		// TODO Auto-generated constructor stub
		try {
			missile = ImageIO.read(getClass().getResourceAsStream("/HUD/Missile.gif"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.rotation = rotation;
		this.player = player;
		init();
	}

	private void init() {
		x = player.getx() + 10;
		y = player.gety() + 10;
		moveSpeed = 5;
		width = 2;
		height = 25;

	}

	public void draw(Graphics2D g) {
		update();
		double rotationRequired = Math.toRadians(rotation);
		AffineTransform orig = g.getTransform();
		g.translate(x, y);
		g.rotate(rotationRequired);
		g.drawImage(missile, -10, -10, 2, 25, null);
		g.setTransform(orig);
	}

	public Rectangle getRectangle() {
		return new Rectangle((int) x, (int) y, getHeight(), getWidth());
	}

	private void update() {
		if (rotation >= 360) {
			rotation -= 360;
		}
		if (rotation < 0) {
			rotation += 360;
		}
		if (rotation == 0)
			y -= moveSpeed;
		else if (rotation < 90) {
			x += moveSpeed;
			y -= moveSpeed;
		} else if (rotation == 90)
			x += moveSpeed;
		else if (rotation > 90 && rotation < 180) {
			x += moveSpeed;
			y += moveSpeed;
		} else if (rotation == 180)
			y += moveSpeed;
		else if (rotation > 180 && rotation < 270) {
			x -= moveSpeed;
			y += moveSpeed;
		} else if (rotation == 270)
			x -= moveSpeed;
		else if (rotation > 270 && rotation < 360) {
			x -= moveSpeed;
			y -= moveSpeed;
		}
	}
}
