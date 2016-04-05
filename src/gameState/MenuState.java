package gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import spiel.GamePanel;

public class MenuState extends GameState implements KeyListener {

	private BufferedImage head;

	private int currentChoice = 0;
	private String[] options = { "Start", "Quit" };

	private Color titleColor;
	private Font titleFont;

	private Font font;
	private Font font2;

	private BufferedImage image;

	private KeyEvent keyEvent;

	public MenuState(GameStateManager gsm) {

		super(gsm);

		try {

			// load floating head
			// head =
			// ImageIO.read(getClass().getResourceAsStream("/HUD/Hud.gif")).getSubimage(0,
			// 12, 12, 11);

			// titles and fonts
			titleColor = Color.WHITE;
			titleFont = new Font("Times New Roman", Font.PLAIN, 28);
			font = new Font("Arial", Font.PLAIN, 14);
			font2 = new Font("Arial", Font.PLAIN, 10);

			// load sound fx
			/*
			 * JukeBox.load("/SFX/menuoption.mp3", "menuoption");
			 * JukeBox.load("/SFX/menuselect.mp3", "menuselect");
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void init() {
	}

	public void update() {

	}

	public void draw(Graphics2D g) {
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/Backgrounds/HyperDriveShip.gif")).getSubimage(0, 12,
					12, 11);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawImage(image, GamePanel.WIDTH * GamePanel.SCALE, GamePanel.HEIGHT * GamePanel.SCALE, null);
		// draw bg
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);

		// draw title
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("A S T E R O I D S", 55, 50);

		// draw menu options
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString("Start", 80, 200);
		g.drawString("Quit", 200, 200);

		// draw floating head
		if (currentChoice == 0)
			g.drawImage(head, 125, 154, null);
		else if (currentChoice == 1)
			g.drawImage(head, 125, 174, null);

		// other
		g.setFont(font2);
		g.drawString("2016 Dave + Fero", 10, 232);

	}

	private void select() {
		if (currentChoice == 0) {
			// JukeBox.play("menuselect");
			// PlayerSave.init();
			// gsm.setState(GameStateManager.LEVEL1ASTATE);
			System.out.println("Start");
		} else if (currentChoice == 1) {
			System.exit(0);
		}
	}

	public void handleInput() {
		if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER)
			select();
		if (keyEvent.getKeyCode() == KeyEvent.VK_UP) {
			if (currentChoice > 0) {
				// JukeBox.play("menuoption", 0);
				currentChoice--;
			}
		}
		if (keyEvent.getKeyCode() == KeyEvent.VK_DOWN) {
			if (currentChoice < options.length - 1) {
				// JukeBox.play("menuoption", 0);
				currentChoice++;
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keyEvent = e;
		handleInput();
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}
