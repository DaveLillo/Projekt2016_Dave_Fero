package ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public class Fenster {

	private int height;
	private int width;
	private Color backgroundColor;
	private boolean centered, fullScreen;
	private JFrame fenster;

	public Fenster() {
		height = 600;
		width = 800;
		backgroundColor = Color.BLACK;
		centered = true;
		fenster = new JFrame("Asteroids");
		fenster = new JFrame();
		fenster.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setFullScreen(true);
	}

	public Fenster(int width, int height, Color backgroundColor, boolean centered, boolean fullScreen) {
		setHeight(height);
		setWidth(width);
		setBackgroundColor(backgroundColor);
		setCentered(centered);
		setFullScreen(fullScreen);
		fenster = new JFrame();
		fenster.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public boolean getFullScreen() {
		return fullScreen;
	}

	public void setFullScreen(boolean fullScreen) {
		this.fullScreen = fullScreen;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public boolean isCentered() {
		return centered;
	}

	public void setCentered(boolean centered) {
		this.centered = centered;
	}

	public JFrame openWindow() {
		fenster = new JFrame("JFrame");
		fenster.getContentPane().setBackground(backgroundColor);
		fenster.setCursor(new Cursor(1));
		if (width == 0) {
			width = getScreenWidth() * 2;
		}
		if (height == 0) {
			height = getScreenHeight() * 10 / 9;
		}
		fenster.setSize(width, height);
		if (centered) {
			fenster.setLocationRelativeTo(null);
		}
		if (fullScreen) {
			fenster.setExtendedState(JFrame.MAXIMIZED_BOTH);
		}
		fenster.setVisible(true);
		return fenster;
	}

	private int getScreenWidth() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		return (int) screenSize.getWidth();
	}

	private int getScreenHeight() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		return (int) screenSize.getHeight();
	}

	public void addJLabel(JLabel jlabel) {
		fenster.add(jlabel);
	}
}