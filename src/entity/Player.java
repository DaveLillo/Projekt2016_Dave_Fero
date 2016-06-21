package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Player extends MapObject {

	private int health;
	private boolean flinching;
	private long flinchCount;
	private int score;
	private ArrayList<BufferedImage[]> sprites;
	private final int[] NUMFRAMES = { 1 };
	private final int[] FRAMEWIDTHS = { 30 };
	private final int[] FRAMEHEIGHTS = { 30 };
	private final int[] SPRITEDELAYS = { -1 };

	private static final int IDLE = 0;

	public Player() {
		isPlayer = true;

		length = 30;
		fatness = 30;
		cwidth = 40;
		cheight = 40;

		moveSpeed = 1.6;
		maxSpeed = 15;
		stopSpeed = 1.6;

		facingRight = true;

		health = 1;

		try {
			BufferedImage spritesheet = ImageIO
					.read(getClass().getResourceAsStream("/Sprites/Player/PlayerSprites.gif"));

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
	}

	public int getHealth() {
		return health;
	}

	public void setRotation(int rotation) {
		this.rotation = rotation;
	}

	public void setHealth(int i) {
		health = i;
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

	public void update() {

		if (flinching) {
			flinchCount++;
			if (flinchCount > 180) {
				flinching = false;
				flinchCount = 0;
			}
		}

		setAnimation(IDLE);

		animation.update();
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
