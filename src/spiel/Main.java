package spiel;
import java.awt.Color;

import Menu.Menu;
import ui.Fenster;

public class Main {

	public static void main(String args[]) {
		Fenster f = new Fenster();
		f.openWindow();
		Menu m = new Menu();
		m.addButtons(Color.BLACK);

	}
}