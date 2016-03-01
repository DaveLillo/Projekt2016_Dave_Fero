import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class Main {

	public static void main(String[] args) {
		Fenster f = new Fenster(0, 0, Color.RED, true, true);
		f.openWindow();
		JLabel j = new JLabel("Seavas");
		j.setFont(new Font("Arial", 0, 20));
		j.setBounds(100, 100, 100, 100);
		f.addJLabel(j);
	}
}