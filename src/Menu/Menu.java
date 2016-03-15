
package Menu;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import ui.Fenster;

public class Menu implements ActionListener {

	private int height;
	private int width;
	private Color backgroundColor;
	private boolean centered, fullScreen;
	private JFrame fenster;
	private JButton buttonStart;
	private JButton buttonExit;

	public Menu() {
		height = getScreenHeight();
		width = getScreenWidth();
		backgroundColor = Color.BLACK;
		centered = true;
	}

	public Menu(int width, int height, Color backgroundColor, boolean centered, boolean fullScreen) {
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

	public void addButtons(Color c) {
		fenster = new JFrame("Asteroids");
		fenster.getContentPane().setBackground(c);
		fenster.setCursor(new Cursor(1));
		if (width == 0) {
			width = getScreenWidth() * 10 / 9;
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

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.setSize(fenster.getY(), 200);
		buttonPanel.setLocation(fenster.getX(), 800);
		buttonPanel.setBackground(Color.BLACK);
		/*
		 * Actionlistener
		 */
		ActionListener start = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Fenster f = new Fenster(0, 0, Color.WHITE, true, true);
				f.addJLabel(new JLabel("Servas"));
				f.openWindow();
			}
		};
		ActionListener a = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		};
		/*
		 * Start-Button
		 */
		buttonStart = new JButton();
		buttonStart.setBackground(Color.BLACK);
		buttonStart.setText("Start");
		buttonStart.setForeground(Color.WHITE);
		buttonStart.setSize(500, 200);
		buttonStart.addActionListener(start);
		buttonPanel.add(buttonStart);
		/*
		 * Exit-Button
		 */
		buttonExit = new JButton();
		buttonExit.setBackground(Color.BLACK);
		buttonExit.setText("Exit");
		buttonExit.setForeground(Color.WHITE);
		buttonExit.setSize(200, 50);
		buttonExit.addActionListener(a);
		buttonPanel.add(buttonExit);

		fenster.add(buttonPanel);
		fenster.setVisible(true);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}