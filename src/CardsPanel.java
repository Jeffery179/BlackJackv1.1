import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

class CardsPanel extends JPanel {

	// This class extends JPanel, and will create a panel which displays the images
	// of a number of cards,
	// stored in an instance of CardGroup, next to each other. Additionally the card
	// total is displayed using standard Ace subtraction rule.

	CardsPanel(Hand hand, int left, int top, int width, int height, int gap) {

		int numCards = hand.getNumCardsInHand();

		setBounds(left, top, 35 + numCards * (width + gap), height);
		setLayout(null);
		setOpaque(false); // for transparent background

		int total = hand.getHandSum();

		System.out.println(total);

		JLabel handScoreLbl = new JLabel(String.valueOf(total));
		handScoreLbl.setForeground(Color.WHITE);
		handScoreLbl.setFont(new Font("Lucida Grande", Font.BOLD, 35));
		handScoreLbl.setVerticalAlignment(SwingConstants.CENTER);
		handScoreLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		handScoreLbl.setBounds(0, 0, 45, height);
		add(handScoreLbl);

		for (int i = 0; i < numCards; i++) {

			System.out.println(hand.getCards()[i].getFileName());

			ImagePanel cardImagesPanel = new ImagePanel(hand.getCards()[i].getFileName());

			cardImagesPanel.setBounds(50 + i * (width + gap), 0, width, height);

			add(cardImagesPanel);

		}
	}
}