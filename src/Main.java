import java.awt.Color;

import javax.swing.JLabel;

import Menu.Menu;

public class Main {

	public static void main(String args[]) {
		Menu m = new Menu();
		m.openWindow(Color.BLACK);
		Fenster f = new Fenster(0, 0, Color.WHITE, true, true);
		f.addJLabel(new JLabel("Servas"));
		f.openWindow();
	}
}