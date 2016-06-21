package gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import entity.Enemy;
import entity.Missile;
import entity.Player;
import entity.Stone;
import handlers.Background;
import handlers.Keys;
import music.JukeBox;

public class Level1State extends GameState {

	private Background stars;

	private Player player;
	private ArrayList<Enemy> enemies;
	private ArrayList<Missile> missiles;

	private boolean blockInput = false;
	private BufferedReader keyOpt;

	private int currStage;
	private int maxStage = 5;

	public static int SCORE;

	private boolean alreadyplayed;

	public Level1State(GameStateManager gsm) {
		super(gsm);
		init();
	}

	public void init() {
		stars = new Background("/Backgrounds/stars.gif", 0);

		enemies = new ArrayList<Enemy>();
		// eprojectiles = new ArrayList<EnemyProjectile>();
		populateEnemies();

		missiles = new ArrayList<Missile>();

		if (!alreadyplayed) {
			player = new Player();
			player.setPosition(160, 120);
			player.setHealth(3);
			JukeBox.stop();
			JukeBox.play("Resources/music/level1boss.mp3");
		}

		player.setFlinching(true);
	}

	private void populateEnemies() {
		Stone s;
		Random r = new Random();

		for (int i = 0; i < currStage + 3; i++) {
			s = new Stone();
			s.setPosition(r.nextInt(280), r.nextInt(200));
			enemies.add(s);
		}
	}

	public void update() {
		handleInput();

		player.update();

		checkCollisions();

		if (enemies.size() == 0) {
			if (currStage == maxStage) {
				currStage = 0;
			} else {
				currStage++;
				alreadyplayed = true;
				init();
			}
		}

		for (int i = 0; i < missiles.size(); i++) {
			if (missiles.get(i).getx() < -100.0 || missiles.get(i).getx() > 500 || missiles.get(i).gety() > 500
					|| missiles.get(i).gety() < -100) {
				missiles.remove(i);
			}
		}
	}

	public void checkCollisions() {
		Rectangle rplayer = new Rectangle(player.getx() - 10, player.gety(), player.getWidth() - 10,
				player.getHeight() - 10);
		for (int i = 0; i < enemies.size(); i++) {
			Enemy e = enemies.get(i);
			Rectangle rstone = new Rectangle(e.getx(), e.gety(), e.getWidth(), e.getHeight());
			if (rstone.intersects(rplayer)) {
				if (player.isFlinching()) {
					break;
				}
				if (player.getHealth() == 1) {
					gsm.setState(GameStateManager.GAMEOVERSTATE);
				} else {
					player.setHealth(player.getHealth() - 1);
					player.setPosition(160, 120);
					player.setRotation(0);
					player.setVelocityToZero();
					player.setFlinching(true);
				}
			}
		}

		for (int i = 0; i < missiles.size(); i++) {
			Missile m = missiles.get(i);
			for (int j = 0; j < enemies.size(); j++) {
				Stone s = (Stone) enemies.get(j);
				double d = Line2D.ptSegDistSq(m.missilestartx, m.missilestarty, m.missileendx, m.missileendy, s.getx(),
						s.gety());
				if (Math.abs(d) < s.getRadius() * 5) {
					enemies.remove(j);
					missiles.remove(i);
					SCORE++;
					break;
				}
			}
		}
	}

	public void draw(Graphics2D g) {
		stars.draw(g);

		/*
		 * Rectangle rplayer = new Rectangle(player.getx() - 10, player.gety() -
		 * 10, player.getWidth() - 10, player.getHeight() - 10);
		 * g.rotate(player.getRotation()); System.out.println(player.getWidth()
		 * + " " + player.getHeight()); g.draw(rplayer);
		 * g.rotate(-player.getRotation()); for (int i = 0; i < enemies.size();
		 * i++) { Enemy e = enemies.get(i); Rectangle rstone = new
		 * Rectangle(e.getx(), e.gety(), e.getWidth(), e.getHeight());
		 * g.draw(rstone); if (rstone.intersects(rplayer)) {
		 * System.out.println("collision"); enemies.remove(i); } }
		 */

		g.setColor(Color.WHITE);

		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).draw(g);
		}

		for (int i = 0; i < missiles.size(); i++) {
			missiles.get(i).draw(g);
		}

		player.draw(g);

		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.PLAIN, 10));
		g.drawString("Lives: " + player.getHealth(), 1, 10);
		g.drawString("Stage " + (currStage + 1), 135, 10);
	}

	public void handleInput() {
		String s = "";
		if (Keys.isPressedShort(Keys.ESCAPE))
			gsm.setPaused(true);
		if (blockInput || player.getHealth() == 0)
			return;
		try {
			keyOpt = new BufferedReader(new FileReader("Resources/Options/keySettings.txt"));
			s = keyOpt.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (s.equals("0")) {
			player.setUp(Keys.keyState[Keys.UP]);
			player.setLeft(Keys.keyState[Keys.LEFT]);
			player.setDown(Keys.keyState[Keys.DOWN]);
			player.setRight(Keys.keyState[Keys.RIGHT]);
		} else if (s.equals("1")) {
			player.setUp(Keys.keyState[Keys.W]);
			player.setLeft(Keys.keyState[Keys.A]);
			player.setDown(Keys.keyState[Keys.S]);
			player.setRight(Keys.keyState[Keys.D]);
		}
		if (Keys.isPressedShort(Keys.SPACE)) {
			missiles.add(new Missile(player.getRotation(), player));
		}

	}
}
