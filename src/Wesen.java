
public abstract class Wesen {
	
	private int hp;
	private int dmg;
	private double speed;
	public static final int PROJECTILE_SPEED = 10;
	
	public Wesen (int hp, int dmg, double speed) {
		setHp(hp);
		setDmg(dmg);
		setSpeed(speed);
	}

	public int getHp() {
		return hp;
	}

	private void setHp(int hp) {
		this.hp = hp;
	}

	public int getDmg() {
		return dmg;
	}

	private void setDmg(int dmg) {
		this.dmg = dmg;
	}

	public double getSpeed() {
		return speed;
	}

	private void setSpeed(double speed) {
		this.speed = speed;
	}
	
	/**
	 * 
	 * @param amount
	 * @return true wenn Wesen stirbt
	 */
	public boolean getDmg(int amount) {
		if(hp - amount <= 0) {
			setHp(0);
			return true;
		}
		setHp(this.hp - amount);
		return false;
	}
}
