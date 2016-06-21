package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import tileMap.TileMap;

public class Player extends MapObject {

	private int lives;
	private int health;
	private int maxHealth;
	private boolean flinching;
	private long flinchCount;
	private int score;
	private long time;

	private boolean attacking;

	private ArrayList<BufferedImage[]> sprites;
	private final int[] NUMFRAMES = { 1 };
	private final int[] FRAMEWIDTHS = { 30 };
	private final int[] FRAMEHEIGHTS = { 30 };
	private final int[] SPRITEDELAYS = { -1 };

	private Rectangle ar;
	private Rectangle cr;

	private static final int IDLE = 0;

	public Player(TileMap tm) {
		super(tm);

		isPlayer = true;

		ar = new Rectangle(0, 0, 0, 0);
		ar.width = 30;
		ar.height = 20;
		// aur = new Rectangle((int) x - 15, (int) y - 45, 30, 30);
		cr = new Rectangle(0, 0, 0, 0);
		cr.width = 50;
		cr.height = 40;

		length = 30;
		fatness = 30;
		cwidth = 40;
		cheight = 40;

		moveSpeed = 1.6;
		maxSpeed = 15;
		stopSpeed = 1.6;

		facingRight = true;

		lives = 3;
		health = maxHealth = 1;

		try {
			@SuppressWarnings("resource")
			BufferedReader b = new BufferedReader(new FileReader("Resources/Options/shipSettings.txt"));
			BufferedImage spritesheet = null;
			if (b.readLine().equals("shipNR0")) {
				spritesheet = ImageIO.read(getClass().getResourceAsStream("/Sprites/Player/PlayerSprites.gif"));
			} else {
				spritesheet = ImageIO.read(getClass().getResourceAsStream("/Sprites/Player/PlayerSprites3.gif"));
			}
			// choosing missiles
			b = new BufferedReader(new FileReader("Resources/Options/missileSetting.txt"));
			if (b.readLine().equals("0")) {
				spritesheet = ImageIO.read(getClass().getResourceAsStream("/HUD/Missile.gif"));
			} else if (b.readLine().equals("1")) {
				spritesheet = ImageIO.read(getClass().getResourceAsStream("/HUD/missile2.gif"));
			} else if (b.readLine().equals("2")) {
				spritesheet = ImageIO.read(getClass().getResourceAsStream("/HUD/missile3.gif"));
			}

			int count = 0;
			sprites = new ArrayList<BufferedImage[]>();
			for (int i = 0; i < NUMFRAMES.length; i++) {
				BufferedImage[] bi = new BufferedImage[NUMFRAMES[i]];
				for (int j = 0; j < NUMFRAMES[i]; j++) {
					bi[j] = spritesheet.getSubimage(j * FRAMEWIDTHS[i], count, FRAMEWIDTHS[i], FRAMEHEIGHTS[i]);
				}
				sprites.add(bi);
				count += FRAMEHEIGHTS[i];
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		setAnimation(IDLE);
		// set energy particles
		// load sfx
	}

	public int getHealth() {
		return health;
	}

	public void setRotation(int rotation) {
		this.rotation = rotation;
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

	private void setAnimation(int i) {
		currentAction = i;
		animation.setFrames(sprites.get(currentAction));
		animation.setDelay(SPRITEDELAYS[currentAction]);
		length = FRAMEWIDTHS[currentAction];
		fatness = FRAMEHEIGHTS[currentAction];
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
	}

	public void reset() {
		health = maxHealth;
		currentAction = -1;
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

		if (dx == 0) {
			x = (int) x;
		}

		if (flinching) {
			flinchCount++;
			if (flinchCount > 180) {
				flinching = false;
				flinchCount = 0;
			}
		}

		setAnimation(IDLE);

		animation.update();

		facingRight = true;

		// check if attacking, set bool attacking

		// check enemy interaction, collision, isDead...

	}

	public int getRotation() {
		return rotation;
	}

	public void draw(Graphics2D g) {

		if (flinching) {
			if (flinchCount % 10 < 5) {
				return;
			}
		}

		super.draw(g);
	}

	public boolean isFlinching() {
		return flinching;
	}

	public void setFlinching(boolean b) {
		flinching = b;
	}
}
