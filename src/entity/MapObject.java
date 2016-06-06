package entity;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import handlers.Keys;
import tileMap.TileMap;

public abstract class MapObject {

	// tile stuff
	protected TileMap tileMap;
	protected int tileSize;
	protected double xmap;
	protected double ymap;

	// position and vector
	protected double x;
	protected double y;
	protected double dx;
	protected double dy;

	// dimensions
	protected int width;
	protected int height;

	// collision box
	protected int cwidth;
	protected int cheight;

	// collision
	protected int currRow;
	protected int currCol;
	protected double xdest;
	protected double ydest;
	protected double xtemp;
	protected double ytemp;
	protected boolean topLeft;
	protected boolean topRight;
	protected boolean bottomLeft;
	protected boolean bottomRight;

	protected Animation animation;
	protected int currentAction;
	protected int previousAction;
	protected boolean facingRight;

	protected boolean left;
	protected boolean right;
	protected boolean up;
	protected boolean down;

	protected double moveSpeed;
	protected double maxSpeed;
	protected double stopSpeed;
	protected double fallSpeed;
	protected double maxFallSpeed;
	protected double jumpStart;
	protected double stopJumpSpeed;

	// velosity
	protected int rotation;
	private int count;
	private boolean[] last300moves;
	private int last;
	private double velocityConst = (Math.pow(1.018, count));
	private double factor = 2.0;

	protected boolean isPlayer;

	public MapObject(TileMap tm) {
		tileMap = tm;
		tileSize = tm.getTileSize();
		animation = new Animation();
		facingRight = true;
		rotation = 0;
		last300moves = new boolean[300];
		last = 0;
	}

	public void setVelocityToZero() {
		last300moves = new boolean[300];
		count = 0;
	}

	public int getx() {
		return (int) x;
	}

	public int gety() {
		return (int) y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getCWidth() {
		return cwidth;
	}

	public int getCHeight() {
		return cheight;
	}

	public boolean isFacingRight() {
		return facingRight;
	}

	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void setVector(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}

	public void setMapPosition() {
		if (Keys.isPressed(Keys.LEFT)) {
			rotation -= 5;
		}
		if (Keys.isPressed(Keys.RIGHT)) {
			rotation += 5;
		}
		if (Keys.isPressed(Keys.UP)) {
			addToArray(true);
			calculateVelosity(true);
			correctPosition();
		}
		if (Keys.isPressed(Keys.DOWN)) {
			addToArray(false);
			calculateVelosity(false);
			correctPosition();
		}
		if (!Keys.isPressed(Keys.UP) && !Keys.isPressed(Keys.DOWN)) {
			last = 0;
			last300moves[last] = false;
			if (factor == 70) {
				calculateDirection(false, false);
			} else {
				factor += 2;
				calculateDirection(true, true);
			}
		}
	}

	private void correctPosition() {
		if (x < 0)
			x = 1;
		if (x > 320)
			x = 319;
		if (y < 0)
			y = 1;
		if (y > 240)
			y = 239;
		if (rotation < 0)
			rotation = 360 + rotation;
		if (rotation > 360)
			rotation -= 360;
	}

	private void calculateVelosity(boolean b) { // b = true --> up
		if (count != 60) {
			count++;
		}
		if (b) {
			checkMoves(b);
			if (rotation == 0) {
				y -= count / 10 * velocityConst;
			} else {
				calculateDirection(b, false);
			}
		} else {
			checkMoves(b);
			if (rotation == 0) {
				y += count / 10 * velocityConst;
			} else {
				calculateDirection(b, false);
			}
		}
	}

	private void calculateDirection(boolean up, boolean slowDown) {
		double a;
		double b;
		if (up) { // vorwärts
			if (rotation == 180) {
				a = (count / 10 * velocityConst) * Math.cos(Math.toRadians(rotation - 90));
				b = Math.sqrt((Math.pow((count / 10 * velocityConst), 2) - Math.pow(a, 2)));
				if (!slowDown) {
					x += a / 4;
					y += b / 4;
				} else {
					x += a / factor;
					y += b / factor;
				}
			} else if (rotation > 90 && rotation < 180) { // rechts unten
				a = (count / 10 * velocityConst) * Math.cos(Math.toRadians(rotation - 90));
				b = Math.sqrt((Math.pow((count / 10 * velocityConst), 2) - Math.pow(a, 2)));
				if (!slowDown) {
					x += a / 2;
					y += b / 2;
				} else {
					x += a / factor;
					y += b / factor;
				}
			} else if (rotation > 180 && rotation < 270) { // links unten
				a = (count / 10 * velocityConst) * Math.cos(Math.toRadians(90 - (rotation - 180)));
				b = Math.sqrt((Math.pow((count / 10 * velocityConst), 2) - Math.pow(a, 2)));
				if (!slowDown) {
					x -= a / 2;
					y += b / 2;
				} else {
					x -= a / factor;
					y += b / factor;
				}
			} else if (rotation > 270 && rotation < 360) { // links oben
				a = (count / 10 * velocityConst) * Math.cos(Math.toRadians(90 - (360 - rotation)));
				b = Math.sqrt((Math.pow((count / 10 * velocityConst), 2) - Math.pow(a, 2)));
				if (!slowDown) {
					x -= a / 2;
					y -= b / 2;
				} else {
					x -= a / factor;
					y -= b / factor;
				}
			} else { // rechts oben
				a = (count / 10 * velocityConst) * Math.cos(Math.toRadians(90 - rotation));
				b = Math.sqrt((Math.pow((count / 10 * velocityConst), 2) - Math.pow(a, 2)));
				if (!slowDown) {
					x += a / 2;
					y -= b / 2;
				} else {
					x += a / factor;
					y -= b / factor;
				}
			}
		} else { // rückwärts
			// nï¿½chstes berechnen und kleiner machen
		}
	}

	private boolean checkMoves(boolean b) { // b = true --> up
		for (int i = 0; i < count; i++) {
			if (last300moves[i] != b) {
				count = 1;
				return false;
			}
		}
		return true;
	}

	private void addToArray(boolean b) { // b = true --> up
		if (checkMoves(b)) {
			last300moves[last] = b;
		} else {
			last = 0;
			last300moves[last] = b;
		}
		if (last == 299) {
			// last bleibt 300
		} else {
			last++;
		}
	}

	public void setLeft(boolean b) {
		left = b;
	}

	public void setRight(boolean b) {
		right = b;
	}

	public void setUp(boolean b) {
		up = b;
	}

	public void setDown(boolean b) {
		down = b;
	}

	public void draw(Graphics2D g) {
		if (isPlayer) {
			setMapPosition();
			double rotationRequired = Math.toRadians(rotation);

			AffineTransform orig = g.getTransform();

			g.translate(Math.round(x), Math.round(y));
			g.rotate(rotationRequired);

			g.drawImage(animation.getImage(), -10, -10, 20, 20, null);

			// draw collision box

			/*
			 * Rectangle r = getRectangle(); r.x = -20; r.y = -20; g.draw(r);
			 */

			g.setTransform(orig);
		} else {
			g.drawImage(animation.getImage(), (int) x - 10, (int) y - 10, width, height, null);
			// g.drawRect((int) x, (int) y, width, height);
		}
	}
}
