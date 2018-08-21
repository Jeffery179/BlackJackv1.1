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
		getContentPane().setBackground(new Color(95, 158, 160));
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
		hitButton.setBounds(825, 200, 115, 29);
		getContentPane().add(hitButton);

		stayButton = new JButton("Stand");
		stayButton.setBounds(825, 262, 115, 29);
		getContentPane().add(stayButton);
		
		splitButton = new JButton("Split");
		splitButton.setBounds(802, 428, 115, 29);
		getContentPane().add(splitButton);

		doubleDownButton = new JButton("Double");
		doubleDownButton.setBounds(825, 486, 115, 29);
		getContentPane().add(doubleDownButton);

		winsLabel = new JLabel("Wins");
		winsLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		winsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		winsLabel.setBounds(522, 16, 69, 20);
		getContentPane().add(winsLabel);

		lossesLabel = new JLabel("Losses");
		lossesLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lossesLabel.setBounds(606, 17, 69, 20);
		getContentPane().add(lossesLabel);

		newGameBtn = new JButton("New Game");
		newGameBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startNewGame();
			}
		});
		newGameBtn.setBounds(825, 39, 115, 29);
		getContentPane().add(newGameBtn);

		dealCardsBtn = new JButton("Deal Cards");
		dealCardsBtn.setBounds(825, 124, 115, 29);
		getContentPane().add(dealCardsBtn);

		gameStatusLbl = new JLabel(".......");
		gameStatusLbl.setBounds(37, 13, 253, 25);
		getContentPane().add(gameStatusLbl);

		winCountLabel = new JLabel("0");
		winCountLabel.setBounds(544, 43, 47, 25);
		getContentPane().add(winCountLabel);

		lossesCount = new JLabel("0");
		lossesCount.setBounds(616, 43, 59, 25);
		getContentPane().add(lossesCount);

		drawsLabel = new JLabel("Pushes");
		drawsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		drawsLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		drawsLabel.setBounds(681, 17, 69, 20);
		getContentPane().add(drawsLabel);

		winCountLabel = new JLabel("0");
		winCountLabel.setBounds(690, 45, 67, 25);
		getContentPane().add(winCountLabel);

	}

	public static void startNewGame() {

		newGameBtn.setEnabled(false);

		deck = new Deck(7, true);

		player1 = new Player("Player");
		dealer = new Dealer("House");

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

		player1.giveNewHand(deck.dealNextCard(), deck.dealNextCard());

		dealer.giveNewHand(deck.dealNextCard(), deck.dealNextCard());

		dealCardsBtn.setEnabled(false);

		updateCardVisuals();

		checkPlayerStatus();

		gameStatusLbl.setText("Player Turn"); // Next instruction

		// hitButton = new JButton("Hit"); // Hit button
		hitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hit(); // When pressed, hit
			}
		});

		frame.getContentPane().add(hitButton);
		hitButton.requestFocus();
		hitButton.setEnabled(true);

		stayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stand(); // When pressed, stand
			}
		});
		frame.getContentPane().add(stayButton);
		stayButton.requestFocus();
		stayButton.setEnabled(true);
	}

	public static void hit() {

		player1.addCard(deck.dealNextCard());

		updateCardVisuals();

		checkPlayerStatus();

	}

	public static void stand() {

		int playerScore = player1.getHand().getHandSum();

		int dealerScore = dealer.getHandSum();

		System.out.println(playerScore + " player score");

		System.out.println(dealerScore + " dealer score");

		// hit on soft 16
		while (dealerScore < 17) {
			dealer.addCard(deck.dealNextCard());
			dealerScore = dealer.getHandSum();
			updateCardVisuals();
		}

		if (dealerScore > 21 && playerScore <= 21) {

			gameStatusLbl.setText("Dealer busts, player wins!");
			totalWins++;
			winCountLabel.setText(String.valueOf(totalWins));
			// return GameResult.PLAYER_WINS;
		} else if (dealerScore == playerScore) {

			gameStatusLbl.setText("Push!");
			totalDraws++;
			drawsCountlabel.setText(String.valueOf(totalDraws));
			// return GameResult.PUSH;
		} else if (dealerScore > playerScore) {
			totalLosses++;
			gameStatusLbl.setText("Dealer beats player, dealer wins!");
			lossesCount.setText(String.valueOf(totalLosses));
			// return GameResult.DEALER_WINS;
		} else if (playerScore > dealerScore && playerScore <= 21) {
			totalWins++;
			winCountLabel.setText(String.valueOf(totalWins));
			gameStatusLbl.setText("Player beats dealer, player wins!");
			// return GameResult.PLAYER_WINS;
		} else {
			// return GameResult.DEALER_WINS;
			totalLosses++;
			gameStatusLbl.setText("Dealer beats player");
			lossesCount.setText(String.valueOf(totalLosses));
		}

		hitButton.setEnabled(false);

		stayButton.setEnabled(false);

		dealCardsBtn.setEnabled(true);

		frame.repaint();
	}

	/*
	 * runs whenever a player is dealt card(s), to check for blackjack ands busts;
	 * stops game on either of those scenarios
	 */
	public static void checkPlayerStatus() {

		int playerScore = player1.getHand().getHandSum();

		int dealerScore = dealer.getHandSum();

		if (playerScore == 21) {
			// player hit blackjack:

			if (dealerScore == 21) {

				gameStatusLbl.setText("Push!");

				totalDraws++;

				drawsCountlabel.setText(String.valueOf(totalDraws));

			} else {

				gameStatusLbl.setText("Player hits blackjack! Winner!");

				totalWins++;

				winCountLabel.setText(String.valueOf(totalWins));

			}
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
