import java.awt.Color;

import javax.swing.JLabel;

public class Main {

	public static void main(String args[]) {
		Fenster f = new Fenster(0, 0, Color.WHITE, true, true);
		f.addJLabel(new JLabel("Servas"));
		f.openWindow();
	}
}