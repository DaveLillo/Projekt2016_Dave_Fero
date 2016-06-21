package gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import handlers.Keys;
import spiel.GamePanel;

public class ShipState extends GameState {

	private Font font;

	private BufferedImage ship;
	private BufferedImage ship2;

	private BufferedImage missile1;
	private BufferedImage missile2;
	private BufferedImage missile3;

	private int chosenShip;
	private int chosenMissile;
	private int currentOption;

	private String[] currentOptionchoice = { "Ship", "Missile", "Start" };

	private ArrayList<BufferedImage> ships;
	private ArrayList<BufferedImage> missiles;

	public ShipState(GameStateManager gsm) {
		super(gsm);
		ships = new ArrayList<>();
		missiles = new ArrayList<>();
		font = new Font("Century Gothic", Font.PLAIN, 14);

		try {
			ship = ImageIO.read(getClass().getResourceAsStream("/Sprites/Player/PlayerSprites.gif"));
			ship2 = ImageIO.read(getClass().getResourceAsStream("/Sprites/Player/PlayerSprites3.gif"));
			missile1 = ImageIO.read(getClass().getResourceAsStream("/HUD/missile.gif"));
			missile2 = ImageIO.read(getClass().getResourceAsStream("/HUD/missile2.gif"));
			missile3 = ImageIO.read(getClass().getResourceAsStream("/HUD/missile3.gif"));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void addAll() {
		ships.add(ship);
		ships.add(ship2);
		missiles.add(missile1);
		missiles.add(missile2);
		missiles.add(missile3);
	}

	public void select() {// hier weiter machen
		if (currentOption == 2) {
			FileOutputStream fos;
			if (chosenShip == 0) {
				try {
					fos = new FileOutputStream("Resources/Options/shipSettings.txt");
					fos.write("shipNR0".getBytes());
					fos.flush();
					fos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else if (chosenShip == 1) {
				try {
					fos = new FileOutputStream("Resources/Options/shipSettings.txt");
					fos.write("shipNR1".getBytes());
					fos.flush();
					fos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// missile writing out
			if (chosenMissile == 0) {
				try {
					fos = new FileOutputStream("Resources/Options/missileSetting.txt");
					fos.write("0".getBytes());
					fos.flush();
					fos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else if (chosenMissile == 1) {
				try {
					fos = new FileOutputStream("Resources/Options/missileSetting.txt");
					fos.write("1".getBytes());
					fos.flush();
					fos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (chosenMissile == 2) {
				try {
					fos = new FileOutputStream("Resources/Options/missileSetting.txt");
					fos.write("2".getBytes());
					fos.flush();
					fos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void init() {
	}

	@Override
	public void update() {
		handleInput();
	}

	@Override
	public void draw(Graphics2D g) {
		addAll();
		BufferedImage i = ships.get(chosenShip);
		BufferedImage o = missiles.get(chosenMissile);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		g.setColor(Color.WHITE);
		g.setFont(font);
		g.drawString("Select a Ship", 110, 50);
		g.drawString("<", 120, 100);
		g.drawImage(i, 140, 80, null);
		g.drawString(">", 180, 100);
		if (currentOption == 0)
			g.drawRect(136, 110, 35, 1);
		else if (currentOption == 1)
			g.drawRect(136, 183, 35, 1);
		else if (currentOption == 2)
			g.drawRect(140, 205, 35, 1);
		g.drawString("Choose Missile type", 95, 150);
		g.drawString("<", 120, 170);
		g.drawImage(o, 140, 150, null);
		g.drawString(">", 180, 170);
		g.drawString("start", 140, 200);
	}

	@Override
	public void handleInput() {
		if (Keys.isPressedShort(Keys.ESCAPE))
			gsm.setState(GameStateManager.MENUSTATE);
		if (Keys.isPressedShort(Keys.ENTER) && currentOption == 2) {
			gsm.setPaused(false);
			select();
			gsm.setState(GameStateManager.LEVEL1STATE);
		}
		if (Keys.isPressedShort(Keys.UP) && currentOption > 0) {
			currentOption--;
		} else if (Keys.isPressedShort(Keys.DOWN) && currentOption < currentOptionchoice.length)
			currentOption++;
		if (currentOption == 0) {
			if (Keys.isPressedShort(Keys.RIGHT) && chosenShip < ships.size()) {
				chosenShip++;
			}
			if (Keys.isPressedShort(Keys.LEFT) && chosenShip > 0) {
				chosenShip--;
			}
		} else if (currentOption == 1) {
			if (Keys.isPressedShort(Keys.RIGHT) && chosenMissile < missiles.size() - 1) {
				chosenMissile++;
			}
			if (Keys.isPressedShort(Keys.LEFT) && chosenMissile > 0) {
				chosenMissile--;
			}
		}

	}
}
