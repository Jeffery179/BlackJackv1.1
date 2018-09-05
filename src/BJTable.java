import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class BJTable extends JFrame {

	private static JFrame frame = null;

	public static int handsPlayed = 0;

	/* All the components below belong to the main frame */
	private static JLabel dealerLabel;
	private static JPanel panel;
	private static JLabel playerLabel;
	private static JPanel panel_1;
	private static JButton hitButton;
	private static JButton stayButton;
	private static JButton splitButton;
	private static JButton doubleDownButton;
	private static JLabel winsLabel;
	private static JLabel lossesLabel;
	private static JLabel winCountLabel;
	private static JLabel lossesCount;
	private static JLabel drawsLabel;
	private static JLabel drawsCountlabel;
	private static JButton newGameBtn;
	private static JButton dealCardsBtn;
	private static JLabel gameStatusLbl;
	private static JButton helpButton;
	private static JLabel hintLabel;

	/* Components of the black jack game */
	private static Deck deck = null;
	private static Player player1 = null;
	private static Dealer dealer = null;

	// keeps track of wins, losses, draws for GUI
	private static int totalWins = 0;
	private static int totalLosses = 0;
	private static int totalDraws = 0;

	// Panels to hold the visuals of dealer and player cards:
	private static CardsPanel dealerCardPanel;
	private static CardsPanel playerCardPanel;
	private JPanel panel_2;
	private JPanel panel_3;

	private static Card dealerHiddenCard = null;

	public static boolean usedHint = false;

	public static String fileLocation = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		frame = new BJTable();

		frame.setVisible(true);

	}

	/**
	 * Create the frame.
	 */
	public BJTable() {
		getContentPane().setBackground(new Color(143, 188, 143));
		getContentPane().setForeground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 995, 619);
		getContentPane().setLayout(null);

		dealerLabel = new JLabel("Dealer");
		dealerLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		dealerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dealerLabel.setBounds(0, 39, 159, 43);
		getContentPane().add(dealerLabel);

		playerLabel = new JLabel("Player");
		playerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		playerLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		playerLabel.setBounds(0, 273, 159, 43);
		getContentPane().add(playerLabel);

		hitButton = new JButton("Hit");
		hitButton.setBounds(843, 152, 115, 29);
		getContentPane().add(hitButton);

		stayButton = new JButton("Stand");
		stayButton.setBounds(843, 211, 115, 29);
		getContentPane().add(stayButton);

		winsLabel = new JLabel("Win");
		winsLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		winsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		winsLabel.setBounds(522, 16, 69, 20);
		getContentPane().add(winsLabel);

		lossesLabel = new JLabel("Loss");
		lossesLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lossesLabel.setBounds(606, 17, 69, 20);
		getContentPane().add(lossesLabel);

		newGameBtn = new JButton("New Game");
		newGameBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startNewGame();
			}
		});
		newGameBtn.setBounds(843, 14, 115, 29);
		getContentPane().add(newGameBtn);

		dealCardsBtn = new JButton("Deal Cards");
		dealCardsBtn.setBounds(843, 66, 115, 29);
		getContentPane().add(dealCardsBtn);

		gameStatusLbl = new JLabel("");
		gameStatusLbl.setFont(new Font("Lucida Console", Font.BOLD, 21));
		gameStatusLbl.setBounds(37, 13, 391, 25);
		getContentPane().add(gameStatusLbl);

		winCountLabel = new JLabel("0");
		winCountLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		winCountLabel.setBounds(544, 43, 47, 25);
		getContentPane().add(winCountLabel);

		lossesCount = new JLabel("0");
		lossesCount.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lossesCount.setBounds(616, 43, 59, 25);
		getContentPane().add(lossesCount);

		drawsLabel = new JLabel("Push");
		drawsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		drawsLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		drawsLabel.setBounds(681, 17, 69, 20);
		getContentPane().add(drawsLabel);

		drawsCountlabel = new JLabel("0");
		drawsCountlabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		drawsCountlabel.setBounds(690, 45, 67, 25);
		getContentPane().add(drawsCountlabel);

		hintLabel = new JLabel("click for help");
		hintLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
		hintLabel.setHorizontalAlignment(SwingConstants.CENTER);
		hintLabel.setBackground(new Color(220, 20, 60));
		hintLabel.setBounds(843, 301, 115, 62);
		getContentPane().add(hintLabel);

	}

	public static void startNewGame() {

		newGameBtn.setEnabled(false);

		deck = new Deck(7, true);

		player1 = new Player("Player");

		dealer = new Dealer("House");

		dealCards();

		dealCardsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dealCards(); // When clicked, deal a new game
			}
		});

		frame.getContentPane().add(dealCardsBtn);

		dealCardsBtn.requestFocus();

		frame.repaint();

	}

	public static void dealCards() {

		handsPlayed++;

		usedHint = false;

		player1.emptyHand();

		dealer.emptyHand();

		player1.giveNewHand(deck.dealNextCard(), deck.dealNextCard());

		dealerHiddenCard = deck.dealNextCard();

		dealer.giveNewHand(deck.dealNextCard(), new FaceDownCard());

		dealCardsBtn.setEnabled(false);

		updateCardVisuals();

		gameStatusLbl.setText("Player Turn"); // Next instruction

		hintLabel.setText("Click for help");

		if (hitButton.getActionListeners().length < 1) {
			// hitButton = new JButton("Hit"); // Hit button
			hitButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					hit(); // When pressed, hit
				}
			});
		}

		frame.getContentPane().add(hitButton);

		hitButton.requestFocus();

		hitButton.setEnabled(true);

		if (stayButton.getActionListeners().length < 1) {
			stayButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					stand(); // When pressed, stand
				}
			});
		}

		frame.getContentPane().add(stayButton);

		stayButton.requestFocus();

		stayButton.setEnabled(true);

		helpButton = new JButton("Help");

		if (helpButton.getActionListeners().length < 1) {
			helpButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					usedHint = true;
					updateHelp();
				}
			});
		}
		helpButton.setBounds(843, 371, 115, 29);

		frame.getContentPane().add(helpButton);

		checkPlayerStatus();
	}

	public static void updateHelp() {

		BJStrategy strategyGuide = new BJStrategy();

		String action = strategyGuide.getAction(player1.getHand().hasAce(), player1.getHand().getHandSum(),
				dealer.getHandSum());

		hintLabel.setText(action);

		frame.repaint();

	}

	public static void hit() {

		player1.addCard(deck.dealNextCard());

		updateCardVisuals();

		checkPlayerStatus();

	}

	public static void stand() {

		// switch out the face down card with the hidden card in this class:
		dealer.getHands().setCardInHand(dealerHiddenCard);

		gameStatusLbl.setText("Dealer reveals face down card!");

		updateCardVisuals();

		frame.repaint();

		int playerScore = player1.getHand().getHandSum();

		int dealerScore = dealer.getHandSum();
		
		GameResult result = null;
		
		// hit on soft 16
		while (dealerScore < 17) {

			dealer.addCard(deck.dealNextCard());

			dealerScore = dealer.getHandSum();

			updateCardVisuals();
			
		}

		if (dealerScore > 21 && playerScore <= 21) {
			gameStatusLbl.setText("Dealer busts!");

			totalWins++;

			winCountLabel.setText(String.valueOf(totalWins));

			result = GameResult.PLAYER_WINS;

		} else if (dealerScore == playerScore) {

			gameStatusLbl.setText("Push!");

			totalDraws++;

			drawsCountlabel.setText(String.valueOf(totalDraws));

			result = GameResult.PUSH;

		} else if (dealerScore > playerScore) {

			totalLosses++;

			gameStatusLbl.setText("Dealer beats player!");

			lossesCount.setText(String.valueOf(totalLosses));

			result = GameResult.DEALER_WINS;

		} else if (playerScore > dealerScore && playerScore <= 21) {
			totalWins++;

			winCountLabel.setText(String.valueOf(totalWins));

			gameStatusLbl.setText("Player beats dealer!");

			result = GameResult.PLAYER_WINS;

		} else {
			totalLosses++;

			gameStatusLbl.setText("Dealer beats player");

			lossesCount.setText(String.valueOf(totalLosses));

			result = GameResult.DEALER_WINS;
		}

		hitButton.setEnabled(false);

		stayButton.setEnabled(false);

		dealCardsBtn.setEnabled(true);

		frame.repaint();
	}

	/*
	 * runs whenever a player is dealt card(s), to check for player/dealer blackjack
	 * ands busts; stops game on either of those scenarios
	 */
	public static void checkPlayerStatus() {

		int playerScore = player1.getHand().getHandSum();

		int dealerScore = dealer.getHandSum();

		if (dealerScore == 21 && playerScore == 21) {

			gameStatusLbl.setText("Push!");

			totalDraws++;

			drawsCountlabel.setText(String.valueOf(totalDraws));

		}

		if (dealerScore == 21) {

			gameStatusLbl.setText("Dealer Hits Blackjack!");

			totalDraws++;

			drawsCountlabel.setText(String.valueOf(totalDraws));

		}

		if (playerScore == 21) {
			// player hit blackjack:

			gameStatusLbl.setText("Player hits blackjack!");

			totalWins++;

			winCountLabel.setText(String.valueOf(totalWins));


		} else if (playerScore > 21) {
			// player busted

			gameStatusLbl.setText("Player busts!");

			totalLosses++;

			lossesCount.setText(String.valueOf(totalLosses));

		}

		if (playerScore >= 21) {
			hitButton.setEnabled(false);

			stayButton.setEnabled(false);

			dealCardsBtn.setEnabled(true);
		}

		frame.repaint();

	}

	public static void updateCardVisuals() {

		if (dealerCardPanel != null) { // If they're already added, remove them
			frame.getContentPane().remove(dealerCardPanel);

			frame.getContentPane().remove(playerCardPanel);
		}

		// Create and display two panels
		dealerCardPanel = new CardsPanel(dealer.getHands(), (dealer.getHands().getNumCardsInHand()), 75, 100, 165, 5);

		frame.getContentPane().add(dealerCardPanel);

		playerCardPanel = new CardsPanel(player1.getHand(), (player1.getHand().getNumCardsInHand()), 310, 100, 165, 5);

		frame.getContentPane().add(playerCardPanel);

		frame.repaint();

	}
}
