package gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import spiel.GamePanel;

public class PauseState extends GameState implements KeyListener {

	private Font font;
	private KeyEvent keyEvent;

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
		g.drawString("Game Paused", 90, 90);
	}

	@Override
	public void handleInput() {
		if (keyEvent.getKeyCode() == KeyEvent.VK_ESCAPE) {
			gsm.setPaused(false);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		handleInput();
		keyEvent = e;
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}
