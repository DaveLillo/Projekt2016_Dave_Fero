package gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;

import handlers.Keys;
import spiel.GamePanel;

public class OptionState extends GameState {

	private int currentOptionChoice = 0;
	private String[] options2 = { "Sounds", "Keys", "Shoot", "return" };

	private Color titleColor;
	private Font titleFont;

	BufferedReader in;
	BufferedReader keysIn;
	public static boolean soundOptions;
	private boolean keysOptions;
	// true = space
	private boolean spaceOption;

	private Font font;

	public OptionState(GameStateManager gsm) {
		super(gsm);
		try {
			// titles and fonts
			titleColor = Color.WHITE;
			titleFont = new Font("Times New Roman", Font.PLAIN, 28);
			font = new Font("Arial", Font.PLAIN, 14);
		} catch (Exception e) {
			e.printStackTrace();
		}
		init();
	}

	public void init() {
		reader();
	}

	public void update() {
		handleInput();
	}

	public void reader() {
		try {
			in = new BufferedReader(new FileReader("Resources/Options/musicSettings.txt"));
			keysIn = new BufferedReader(new FileReader("Resources/Options/keySettings.txt"));
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void draw(Graphics2D g) {
		reader();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);

		g.setColor(titleColor);
		g.setFont(titleFont);
		g.setFont(font);

		g.setColor(titleColor);
		g.drawString("Sound: ", 110, 55);
		if (!soundOptions) {
			g.drawString("OFF", 160, 55);
		} else {
			g.drawString("ON", 160, 55);
		}
		g.drawString(options2[1] + ": ", 110, 80);
		if (spaceOption) {
			g.drawString(options2[2] + ": Spacebar", 110, 105);
		} else {
			g.drawString(options2[2] + ": Mouse", 110, 105);
		}
		if (!keysOptions)
			g.drawString("Arrow Keys", 150, 80);
		else
			g.drawString("WASD", 150, 80);
		g.drawString(options2[3], 130, 170);

		if (currentOptionChoice == 0) {
			g.drawRect(110, 60, 80, 0);
		} else if (currentOptionChoice == 1) {
			g.drawRect(110, 85, 115, 0);
		} else if (currentOptionChoice == 2) {
			g.drawRect(110, 110, 110, 0);
		} else if (currentOptionChoice == 3) {
			g.drawRect(130, 175, 40, 0);
		}

	}

	private void select() {
		if (currentOptionChoice == 0) {
			FileOutputStream fos;
			if (!soundOptions) {
				try {
					fos = new FileOutputStream("Resources/Options/musicSettings.txt");
					fos.write("Y".getBytes());
					fos.flush();
					fos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {
				try {
					fos = new FileOutputStream("Resources/Options/musicSettings.txt");
					fos.write("N".getBytes());
					fos.flush();
					fos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		if (currentOptionChoice == 1) {
			FileOutputStream fos1;
			if (!keysOptions) {
				try {
					fos1 = new FileOutputStream("Resources/Options/keySettings.txt");
					fos1.write("1".getBytes());
					fos1.flush();
					fos1.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {
				try {
					fos1 = new FileOutputStream("Resources/Options/keySettings.txt");
					fos1.write("0".getBytes());
					fos1.flush();
					fos1.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		if (currentOptionChoice == 2) {
			if (spaceOption) {
				spaceOption = false;
			} else {
				spaceOption = true;
			}
		}

		if (currentOptionChoice == 3) {
			gsm.setState(GameStateManager.MENUSTATE);
		}
	}

	public void handleInput() {
		if (Keys.isPressedShort(Keys.ENTER))
			select();

		if (Keys.isPressedShort(Keys.DOWN)) {
			if (currentOptionChoice < options2.length - 1) {
				currentOptionChoice++;
			}
		}
		if (Keys.isPressedShort(Keys.UP)) {
			if (currentOptionChoice > 0) {
				currentOptionChoice--;
			}
		}
		if (Keys.isPressedShort(Keys.ESCAPE)) {
			currentOptionChoice = 3;
			select();
		}
	}
}
