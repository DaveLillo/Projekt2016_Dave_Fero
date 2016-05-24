package gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import handlers.Keys;
import spiel.GamePanel;

public class GameOverState extends GameState {

	private int score;
	private int currentChoice;

	public GameOverState(GameStateManager gsm) {
		super(gsm);
		init();
	}

	public void init() {
		score = 4;
	}

	public void update() {
		handleInput();
	}

	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);

		g.setColor(Color.WHITE);
		g.setFont(new Font("Times New Roman", Font.PLAIN, 28));
		g.drawString("GAME OVER", 80, 50);

		g.setFont(new Font("Arial", Font.PLAIN, 12));
		g.drawString("Your Score: " + score, 120, 120);
		g.drawString("2016 Dave + Fero", 10, 232);
		g.drawString("Retry", 80, 180);
		g.drawString("Exit", 200, 180);
		if (currentChoice == 0) {
			g.drawRect(80, 185, 27, 0);
		} else {
			g.drawRect(200, 185, 23, 0);
		}
	}

	@Override
	public void handleInput() {
		if (Keys.isPressedShort(Keys.ENTER)) {
			select();
		}
		if (Keys.isPressedShort(Keys.LEFT)) {
			if (currentChoice > 0) {
				currentChoice--;
			}
		}
		if (Keys.isPressedShort(Keys.RIGHT)) {
			if (currentChoice == 0) {
				currentChoice++;
			}
		}
	}

	private void select() {
		if (currentChoice == 0) {
			gsm.setState(GameStateManager.LEVEL1STATE);
		} else {
			gsm.setState(GameStateManager.MENUSTATE);
		}
	}

}
