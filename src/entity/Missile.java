package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import tileMap.TileMap;

public class Missile extends MapObject {

	public BufferedImage missile;

	public Missile(TileMap tm) {
		super(tm);
		// TODO Auto-generated constructor stub
		try {
			missile = ImageIO.read(getClass().getResourceAsStream("/HUD/Missile.gif"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void draw(Graphics2D g) {
		g.drawImage(missile, 50, 50, null);
	}
}
