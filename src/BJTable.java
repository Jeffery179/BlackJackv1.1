import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class BJTable extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BJTable frame = new BJTable();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BJTable() {
		getContentPane().setBackground(new Color(95, 158, 160));
		getContentPane().setForeground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 924, 528);
		getContentPane().setLayout(null);
		
		JLabel dealerLabel = new JLabel("Dealer");
		dealerLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		dealerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dealerLabel.setBounds(27, 30, 159, 43);
		getContentPane().add(dealerLabel);

		JPanel panel = new JPanel();
		panel.setBackground(Color.GREEN);
		panel.setBounds(27, 76, 293, 70);
		getContentPane().add(panel);

		JLabel playerLabel = new JLabel("Player");
		playerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		playerLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		playerLabel.setBounds(27, 178, 159, 43);
		getContentPane().add(playerLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.GREEN);
		panel_1.setBounds(27, 237, 293, 168);
		getContentPane().add(panel_1);

		JButton hitButton = new JButton("Hit");
		hitButton.setBounds(356, 286, 115, 29);
		getContentPane().add(hitButton);

		JButton stayButton = new JButton("Stay");
		stayButton.setBounds(356, 237, 115, 29);
		getContentPane().add(stayButton);
		
		JButton splitButton = new JButton("New button");
		splitButton.setBounds(356, 331, 115, 29);
		getContentPane().add(splitButton);

		JButton doubleDownButton = new JButton("Double");
		doubleDownButton.setBounds(356, 376, 115, 29);
		getContentPane().add(doubleDownButton);

		JLabel winsLabel = new JLabel("Wins");
		winsLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		winsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		winsLabel.setBounds(564, 16, 69, 20);
		getContentPane().add(winsLabel);

		JLabel lossesLabel = new JLabel("Losses");
		lossesLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lossesLabel.setBounds(659, 17, 69, 20);
		getContentPane().add(lossesLabel);

		JPanel winCountPanel = new JPanel();
		winCountPanel.setBounds(575, 39, 61, 34);
		getContentPane().add(winCountPanel);

		JPanel lossCountLabel = new JPanel();
		lossCountLabel.setBounds(656, 39, 61, 34);
		getContentPane().add(lossCountLabel);

	}
}
