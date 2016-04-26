package gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import handlers.Keys;
import spiel.GamePanel;

public class OptionState extends GameState {

	private int currentOptionChoice = 0;
	private String[] options2 = { "Sounds", "Keys", "return" };

	private Color titleColor;
	private Font titleFont;

	BufferedReader in;
	BufferedReader keysIn;
	private boolean soundOptions;
	private boolean keysOptions;

	private Font font;

	public OptionState(GameStateManager gsm) {
		super(gsm);
		try {
			// titles and fonts
			titleColor = Color.WHITE;
			titleFont = new Font("Times New Roman", Font.PLAIN, 28);
			font = new Font("Arial", Font.PLAIN, 14);

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

	public void reader() {
		try {
			in = new BufferedReader(
					new FileReader("C:/Users/User/git/Projekt2016_Dave_Fero/Resources/Options/musicSettings.txt"));
			keysIn = new BufferedReader(
					new FileReader("C:/Users/User/git/Projekt2016_Dave_Fero/Resources/Options/keySettings.txt"));
			if (in.readLine().equals("N")) {
				soundOptions = false;
			} else {
				soundOptions = true;
			}
			if (keysIn.readLine().equals("0")) {
				keysOptions = false;
			} else {
				keysOptions = true;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void draw(Graphics2D g) {
		// draw bg
		reader();
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
		if (!soundOptions) {
			g.drawString("OFF", 170, 45);
		} else {
			g.drawString("ON", 170, 45);
		}
		g.drawString(options2[1] + ": ", 110, 80);
		if (!keysOptions)
			g.drawString("Arrow Keys", 150, 80);
		else
			g.drawString("WASD", 150, 80);
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
			FileOutputStream fos;
			if (!soundOptions) {
				try {
					fos = new FileOutputStream(
							"C:/Users/User/git/Projekt2016_Dave_Fero/Resources/Options/musicSettings.txt");
					fos.write("Y".getBytes());
					fos.flush();
					fos.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
				try {
					fos = new FileOutputStream(
							"C:/Users/User/git/Projekt2016_Dave_Fero/Resources/Options/musicSettings.txt");
					fos.write("N".getBytes());
					fos.flush();
					fos.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if (currentOptionChoice == 1) {
			FileOutputStream fos1;
			if (!keysOptions) {
				try {
					fos1 = new FileOutputStream(
							"C:/Users/User/git/Projekt2016_Dave_Fero/Resources/Options/keySettings.txt");
					fos1.write("1".getBytes());
					fos1.flush();
					fos1.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
				try {
					fos1 = new FileOutputStream(
							"C:/Users/User/git/Projekt2016_Dave_Fero/Resources/Options/keySettings.txt");
					fos1.write("0".getBytes());
					fos1.flush();
					fos1.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
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
		if (Keys.isPressed(Keys.ESCAPE)) {
			currentOptionChoice = 2;
			select();
		}
	}
}
