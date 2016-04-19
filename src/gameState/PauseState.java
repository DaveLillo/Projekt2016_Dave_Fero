package gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import handlers.Keys;
import spiel.GamePanel;

public class PauseState extends GameState {

	private Font font;

	public PauseState(GameStateManager gsm) {
		super(gsm);

		font = new Font("Century Gothic", Font.PLAIN, 14);

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
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		g.setColor(Color.WHITE);
		g.setFont(font);
		g.drawString("Game Paused", 105, 90);
		g.drawString("exit to menu", 115, 125);
		g.drawRect(125, 130, 63, 0);
	}

	@Override
	public void handleInput() {
		if (Keys.isPressed(Keys.ESCAPE))
			gsm.setPaused(false);
		if (Keys.isPressed(Keys.ENTER)) {
			gsm.setPaused(false);
			gsm.setState(GameStateManager.MENUSTATE);
		}
	}
}
