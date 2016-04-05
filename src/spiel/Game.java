package spiel;

import javax.swing.JFrame;

public class Game {

	public static void main(String[] args) {
		JFrame window = new JFrame("Asteroids");
		window.add(new GamePanel());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}

}
