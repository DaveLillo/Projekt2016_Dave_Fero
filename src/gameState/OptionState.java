package gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import handlers.Keys;
import spiel.GamePanel;

public class OptionState extends GameState {

	private int currentOptionChoice = 0;
	private String[] options2 = { "Sounds", "Keys", "return" };

	private Color titleColor;
	private Font titleFont;

	private String soundsON = "OFF";
	private Font font;
	private Font font2;
	private boolean enterKey;

	public OptionState(GameStateManager gsm) {

		super(gsm);

		try {
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

		// draw menu options
		g.setFont(font);

		// draw floating head

		// options

		g.setColor(titleColor);
		g.drawString("Sound: ", 110, 45);
		g.drawString(soundsON, 170, 45);
		g.drawString(options2[1], 110, 80);
		g.drawString(options2[2], 140, 170);

		if (currentOptionChoice == 0) {
			g.drawRect(110, 50, 100, 0);
		} else if (currentOptionChoice == 1) {
			g.drawRect(110, 85, 60, 0);
		} else if (currentOptionChoice == 2) {
			g.drawRect(140, 175, 55, 0);
		}

	}

	private void select() {
		if (currentOptionChoice == 0) {
			if (soundsON.equals("OFF"))
				soundsON = "ON";
			else
				soundsON = "OFF";
		}

		if (currentOptionChoice == 2) {
			gsm.setState(GameStateManager.MENUSTATE);
		}
	}

	public void handleInput() {
		if (Keys.isPressed(Keys.ENTER))
			select();

		if (Keys.isPressed(Keys.DOWN)) {
			if (currentOptionChoice < options2.length - 1) {
				currentOptionChoice++;
			}
		}
		if (Keys.isPressed(Keys.UP)) {
			if (currentOptionChoice > 0) {
				currentOptionChoice--;
			}
		}
		if (Keys.isPressed(Keys.ESCAPE) || currentOptionChoice == 5) {
			enterKey = false;
		}
	}
}
