package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import tileMap.TileMap;

public class Player extends MapObject {

	private ArrayList<Enemy> enemies;

	private int lives;
	private int health;
	private int maxHealth;
	private int damage;
	private boolean flinching;
	private long flinchCount;
	private int score;
	private ArrayList<EnergyParticle> energyParticles;
	private long time;

	private boolean attacking;

	private Rectangle ar;
	private Rectangle aur;
	private Rectangle cr;

	public Player(TileMap tm) {
		super(tm);

		ar = new Rectangle(0, 0, 0, 0);
		ar.width = 30;
		ar.height = 20;
		aur = new Rectangle((int) x - 15, (int) y - 45, 30, 30);
		cr = new Rectangle(0, 0, 0, 0);
		cr.width = 50;
		cr.height = 40;

		width = 30;
		height = 30;
		cwidth = 15;
		cheight = 38;

		moveSpeed = 1.6;
		maxSpeed = 15;
		stopSpeed = 1.6;

		damage = 1;

		lives = 3;
		health = maxHealth = 1;

		energyParticles = new ArrayList<EnergyParticle>();

		// load sfx
	}

	public void init(ArrayList<Enemy> enemies, ArrayList<EnergyParticle> energyParticles) {
		this.enemies = enemies;
		this.energyParticles = energyParticles;
	}

	public int getHealth() {
		return health;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setAttacking() {
		attacking = true;
	}

	public void setDead() {
		health = 0;
		stop();
	}

	public String getTimeToString() {
		int minutes = (int) (time / 3600);
		int seconds = (int) ((time % 3600) / 60);
		return seconds < 10 ? minutes + ":0" + seconds : minutes + ":" + seconds;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long t) {
		time = t;
	}

	public void setHealth(int i) {
		health = i;
	}

	public void setLives(int i) {
		lives = i;
	}

	public void gainLife() {
		lives++;
	}

	public void loseLife() {
		lives--;
	}

	public int getLives() {
		return lives;
	}

	public void increaseScore(int score) {
		this.score += score;
	}

	public int getScore() {
		return score;
	}

	public void hit(int damage) {
		if (flinching)
			return;
		// JukeBox.play("playerhit");
		stop();
		health -= damage;
		if (health < 0)
			health = 0;
		flinching = true;
		flinchCount = 0;
		dy = -3;
		falling = true;
		jumping = false;
	}

	public void reset() {
		health = maxHealth;
		stop();
	}

	public void stop() {
		left = right = up = down = flinching = attacking = false;
	}

	private void getNextPosition() {
		if (left) {
			dx -= moveSpeed;
			if (dx < -maxSpeed) {
				dx = -maxSpeed;
			}
		} else if (right) {
			dx += moveSpeed;
			if (dx > maxSpeed) {
				dx = maxSpeed;
			}
		} else {
			if (dx > 0) {
				dx -= stopSpeed;
				if (dx < 0) {
					dx = 0;
				}
			} else if (dx < 0) {
				dx += stopSpeed;
				if (dx > 0) {
					dx = 0;
				}
			}
		}

		if (attacking) {
			dx = 0;
		}
	}

	public void update() {

		time++;

		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);

		if (dx == 0) {
			x = (int) x;
		}

		if (flinching) {
			flinchCount++;
			if (flinchCount > 120) {
				flinching = false;
			}
		}

		for (int i = 0; i < energyParticles.size(); i++) {
			energyParticles.get(i).update();
			if (energyParticles.get(i).shouldRemove()) {
				energyParticles.remove(i);
				i--;
			}
		}

		// check if attacking, set bool attacking

		// check enemy interaction, collision, isDead...

	}

	public void draw(Graphics2D g) {
		for (int i = 0; i < energyParticles.size(); i++) {
			energyParticles.get(i).draw(g);
		}

		if (flinching) {
			if (flinchCount % 10 < 5)
				return;
		}

		super.draw(g);
	}
}
