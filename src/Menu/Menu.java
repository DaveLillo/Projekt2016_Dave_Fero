
package Menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.Fenster;

public class Menu implements ActionListener {
	private JButton buttonStart;
	private JButton buttonExit;

	public void addButtons(Color c) {
		Fenster f = new Fenster();
		JFrame fenster = f.openWindow();

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.setSize(fenster.getX(), 200);
		buttonPanel.setBounds(0, 800, fenster.getX(), 200);
		;
		buttonPanel.setBackground(Color.BLACK);
		/*
		 * Actionlistener
		 */
		ActionListener start = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Fenster f = new Fenster(800, 600, Color.BLACK, true, false);
				f.addJLabel(new JLabel("Servas"));
				fenster.hide();
				f.openWindow();
			}
		};
		ActionListener exit = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
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
		buttonStart.setPreferredSize(new Dimension(400, 100));
		buttonStart.addActionListener(start);
		buttonPanel.add(buttonStart);
		/*
		 * Exit-Button
		 */
		buttonExit = new JButton();
		buttonExit.setBackground(Color.BLACK);
		buttonExit.setText("Exit");
		buttonExit.setForeground(Color.WHITE);
		buttonExit.setPreferredSize(new Dimension(400, 100));
		buttonExit.addActionListener(exit);
		buttonPanel.add(buttonExit);
		fenster.add(buttonPanel, BorderLayout.SOUTH);

		fenster.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}