
package Menu;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.Fenster;

public class Menu implements ActionListener {

	private JFrame fenster;
	private JButton buttonStart;
	private JButton buttonExit;

	public void addButtons(Color c) {
		Fenster f = new Fenster();
		JFrame fenster = f.openWindow();

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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}