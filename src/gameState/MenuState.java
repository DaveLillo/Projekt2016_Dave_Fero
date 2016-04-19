package gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import handlers.Keys;
import spiel.GamePanel;

public class MenuState extends GameState {

	private BufferedImage head;

	private int currentChoice = 0;
	private int currentOptionChoice = 0;
	private boolean optionON = false;
	private String[] options = { "Start", "Settings", "Quit" };
	private String[] options2 = { "Sounds", "Keys", "return" };

	private Color titleColor;
	private Font titleFont;

	private String soundsON = "OFF";
	private Font font;
	private Font font2;

	private boolean enterKey;

	private BufferedImage image;

	public MenuState(GameStateManager gsm) {

		super(gsm);

		try {

			// load floating head
			head = ImageIO.read(getClass().getResourceAsStream("/HUD/Hud.gif"));

			image = ImageIO.read(getClass().getResourceAsStream("/Backgrounds/HyperDriveShip.gif"));
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
		handleInput();
	}

	public void draw(Graphics2D g) {
		// draw bg
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);

		// draw title
		g.setColor(titleColor);
		g.setFont(titleFont);

		// gif background
		g.drawImage(image, 8, 40, null);

		g.drawString("A S T E R O I D S", 55, 50);

		// draw menu options
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString("Start", 50, 200);
		g.drawString("Settings", 135, 200);
		g.drawString("Quit", 230, 200);

		// draw floating head
		if (currentChoice == 0) {
			g.drawImage(head, 51, 165, null);
			enterKey = false;
		} else if (currentChoice == 1) {
			g.drawImage(head, 144, 165, null);
			if (enterKey) {
				optionON = true;
			}
		} else if (currentChoice == 2)

		{
			g.drawImage(head, 233, 165, null);
			enterKey = false;
		}
		// other
		g.setFont(font2);
		g.drawString("2016 Dave + Fero", 10, 232);

	}

	private void select() {

		if (currentChoice == 0) {

			gsm.setState(GameStateManager.LEVEL1STATE);

		} else if (currentChoice == 1 && !enterKey) {
			if (Keys.isPressed(Keys.ENTER)) {
				gsm.setState(GameStateManager.OPTIONSTATE);
			}

		} else if (currentChoice == 2)
			System.exit(0);
	}

	public void handleInput() {
		if (Keys.isPressed(Keys.ENTER))
			select();
		if (Keys.isPressed(Keys.LEFT) && !optionON) {
			if (currentChoice > 0) {
				// JukeBox.play("menuoption", 0);
				currentChoice--;
			}
		}
		if (Keys.isPressed(Keys.RIGHT) && !optionON) {
			if (currentChoice < options.length - 1) {
				// JukeBox.play("menuoption", 0);
				currentChoice++;
			}

		}
	}
}
