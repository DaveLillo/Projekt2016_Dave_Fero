package gameState;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import entity.Enemy;
import entity.EnergyParticle;
import entity.Explosion;
import entity.Missile;
import entity.Player;
import entity.Stone;
import handlers.Background;
import handlers.Keys;
import spiel.GamePanel;
import tileMap.TileMap;

public class Level1State extends GameState {

	private Background stars;

	private Player player;
	private TileMap tileMap;
	private ArrayList<Enemy> enemies;
	// private ArrayList<EnemyProjectile> eprojectiles;
	private ArrayList<EnergyParticle> energyParticles;
	private ArrayList<Explosion> explosions;
	private ArrayList<Missile> missiles;
	private Missile missile;

	private boolean blockInput = false;
	private int eventCount = 0;
	private boolean eventStart;
	private ArrayList<Rectangle> tb;
	private boolean eventFinish;
	private boolean eventDead;
	private BufferedReader keyOpt;

	public Level1State(GameStateManager gsm) {
		super(gsm);
		init();
	}

	public void init() {
		stars = new Background("/Backgrounds/stars.gif", 0);

		tileMap = new TileMap(30);
		tileMap.loadTiles("/Tilesets/empty.gif");
		// tileMap.loadMap("/Maps/empty.map");
		tileMap.setPosition(140, 0);
		tileMap.setBounds(tileMap.getWidth() - 1 * tileMap.getTileSize(),
				tileMap.getHeight() - 2 * tileMap.getTileSize(), 0, 0);
		tileMap.setTween(1);

		player = new Player(tileMap);
		// TODO: das variabel machen
		player.setPosition(140, 100);
		player.setHealth(1);
		player.setLives(3);
		player.setTime(0);

		enemies = new ArrayList<Enemy>();
		// eprojectiles = new ArrayList<EnemyProjectile>();
		populateEnemies();

		missiles = new ArrayList<Missile>();
		addMissile(missile);

		energyParticles = new ArrayList<EnergyParticle>();

		player.init(enemies, energyParticles);
		explosions = new ArrayList<Explosion>();

		eventStart = true;
		tb = new ArrayList<Rectangle>();
		eventStart();

		// sfx + music
	}

	private void populateEnemies() {
		// Objekte erzeugen und Position setzen.
		Stone s;

		s = new Stone(tileMap);
		s.setPosition(100, 100);
		enemies.add(s);
	}

	private void addMissile(Missile m) {
		missiles.add(m);
	}

	public void update() {
		handleInput();

		if (player.getHealth() == 1 || player.gety() > tileMap.getHeight()) {
			// eventDead = blockInput = true;
		}

		if (eventStart)
			eventStart();
		if (eventDead)
			eventDead();
		if (eventFinish)
			eventFinish();

		player.update();

		tileMap.setPosition(GamePanel.WIDTH / 2 - player.getx(), GamePanel.HEIGHT / 2 - player.gety());
		tileMap.update();
		tileMap.fixBounds();

		for (int i = 0; i < enemies.size(); i++) {
			Enemy e = enemies.get(i);
			e.update();
			if (e.isDead()) {
				enemies.remove(i);
				i--;
				explosions.add(new Explosion(tileMap, e.getx(), e.gety()));
			}
		}

		/*
		 * for (int i = 0; i < eprojectiles.size(); i++) { EnemyProjectile ep =
		 * eprojectiles.get(i); ep.update(); if (ep.shouldRemove()) {
		 * eprojectiles.remove(i); i--; } }
		 */
	}

	public void draw(Graphics2D g) {
		stars.draw(g);

		tileMap.draw(g);

		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).draw(g);
		}

		for (int i = 0; i < missiles.size(); i++) {
			missiles.get(i).draw(g);
		}

		/*
		 * for (int i = 0; i < eprojectiles.size(); i++) {
		 * eprojectiles.get(i).draw(g); }
		 */

		for (int i = 0; i < explosions.size(); i++) {
			explosions.get(i).draw(g);
		}

		player.draw(g);

		g.setColor(Color.BLACK);
		for (int i = 0; i < tb.size(); i++) {
			g.fill(tb.get(i));
		}
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
			// TODO Auto-generated catch block
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

	}

	private void reset() {
		player.reset();
		player.setPosition(GamePanel.WIDTH * GamePanel.SCALE, GamePanel.HEIGHT * GamePanel.SCALE);
		populateEnemies();
		blockInput = true;
		eventCount = 0;
		tileMap.setShaking(false, 0);
		eventStart = true;
		eventStart();
	}

	private void eventStart() {
		eventCount++;
		if (eventCount == 1) {
			tb.clear();
			tb.add(new Rectangle(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT / 2));
			tb.add(new Rectangle(0, 0, GamePanel.WIDTH / 2, GamePanel.HEIGHT));
			tb.add(new Rectangle(0, GamePanel.HEIGHT / 2, GamePanel.WIDTH, GamePanel.HEIGHT / 2));
			tb.add(new Rectangle(GamePanel.WIDTH / 2, 0, GamePanel.WIDTH / 2, GamePanel.HEIGHT));
		}

		if (eventCount > 1 && eventCount < 60) {
			tb.get(0).height -= 4;
			tb.get(1).width -= 6;
			tb.get(2).y += 4;
			tb.get(3).x += 6;
		}

		if (eventCount == 60) {
			eventStart = blockInput = false;
			eventCount = 0;
			tb.clear();
		}
	}

	private void eventDead() {
		eventCount++;
		if (eventCount == 1) {
			player.setDead();
			player.stop();
		}

		if (eventCount == 60) {
			tb.clear();
			tb.add(new Rectangle(GamePanel.WIDTH / 2, GamePanel.HEIGHT / 2, 0, 0));
		} else if (eventCount > 60) {
			tb.get(0).x -= 6;
			tb.get(0).y -= 4;
			tb.get(0).width += 12;
			tb.get(0).height += 8;
		}

		if (eventCount >= 120) {
			if (player.getLives() == 0) {
				gsm.setState(GameStateManager.MENUSTATE);
			} else {
				eventDead = blockInput = false;
				eventCount = 0;
				player.loseLife();
				reset();
			}
		}
	}

	private void eventFinish() {
		eventCount++;
		if (eventCount == 1) {
			player.stop();
		} else if (eventCount == 120) {
			tb.clear();
			tb.add(new Rectangle(GamePanel.WIDTH / 2, GamePanel.HEIGHT / 2, 0, 0));
		} else if (eventCount > 120) {
			tb.get(0).x -= 6;
			tb.get(0).y -= 4;
			tb.get(0).width += 12;
			tb.get(0).height += 8;
		}

		if (eventCount == 180) {
			// save stats for next level
		}
	}
}
