package handlers;

import java.awt.event.KeyEvent;

public class Keys {
	public static final int NUM_KEYS = 16;

	public static boolean keyState[] = new boolean[NUM_KEYS];
	public static boolean prevKeyState[] = new boolean[NUM_KEYS];

	public static int UP = 0;
	public static int LEFT = 1;
	public static int DOWN = 2;
	public static int RIGHT = 3;
	public static int W = 4;
	public static int A = 5;
	public static int S = 6;
	public static int D = 7;
	public static int ENTER = 8;
	public static int ESCAPE = 9;
	private static int i = 0;

	public static void keySet(int i, boolean b) {
		if (i == KeyEvent.VK_UP)
			keyState[UP] = b;
		else if (i == KeyEvent.VK_LEFT)
			keyState[LEFT] = b;
		else if (i == KeyEvent.VK_DOWN)
			keyState[DOWN] = b;
		else if (i == KeyEvent.VK_RIGHT)
			keyState[RIGHT] = b;
		else if (i == KeyEvent.VK_W)
			keyState[W] = b;
		else if (i == KeyEvent.VK_A)
			keyState[A] = b;
		else if (i == KeyEvent.VK_S)
			keyState[S] = b;
		else if (i == KeyEvent.VK_D)
			keyState[D] = b;
		else if (i == KeyEvent.VK_ENTER)
			keyState[ENTER] = b;
		else if (i == KeyEvent.VK_ESCAPE)
			keyState[ESCAPE] = b;
	}

	public static void update() {
		if (i == 10) {
			for (int i = 0; i < NUM_KEYS; i++) {
				prevKeyState[i] = keyState[i];
			}
		} else {
			i++;
		}
	}

	public static boolean isPressed(int i) {
		return keyState[i] && prevKeyState[i];
	}

	public static boolean isPressedShort(int i) {
		return keyState[i] && !prevKeyState[i];
	}

	public static boolean anyKeyPress() {
		for (int i = 0; i < NUM_KEYS; i++) {
			if (keyState[i])
				return true;
		}
		return false;
	}
}
