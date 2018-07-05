import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class GameDriverBuilder {

	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		Deck deck = new Deck(7, true);

		Player me = new Player("Jeff");
		Dealer dealer = new Dealer("House");

		boolean continueGame = true;

		while (continueGame) {
			// need to re-do, don't give dealer and player class access to the deck...
			me.giveNewHand(deck.dealNextCard(), deck.dealNextCard());
			dealer.giveNewHand(deck.dealNextCard(), deck.dealNextCard());

			// TODO: why doesn't this work?
			// System.out.println(dealer.returnHandHidden());
			System.out.println("Dealer Hand: " + dealer.getHands().toString(true));

			playerPlayHands(deck, me);

			if (me.waitingForDealer()) {
				System.out.println("Dealer Initial Hand: ");
				System.out.println(dealer.getHands().toString(false));
				System.out.println(dealerPlayHand(deck, dealer));
				System.out.println("Dealer Final Hand: " + dealer.getHands().toString(false));
			} else {
				System.out.println("No playable hands for player! Sorry bout da bust!");
			}

			for (int i = 0; i < me.getNumberOfHands(); i++) {

				int handCounter = i + 1;

				Hand hand = me.getHands()[i];
				// System.out.println("Player Hand #" + handCounter + ": " +
				// deliverGameResult(hand.getHandSum(), dealer.getHands().getHandSum()));

				GameResult gResult = deliverGameResult(hand.getHandSum(), dealer.getHands().getHandSum());

				toCSVOutput(formatToCSVData(Integer.toString(handCounter), hand.toString(false),
						Integer.toString(hand.getHandSum()), dealer.getHands().toString(false),
						Integer.toString(dealer.getHandSum()), gResult.toString(), "\n"));

			}

			System.out.println("Continue game? 'Y' or 'N'");
			String response = scanner.next();
			if (response.compareToIgnoreCase("Y") == 0) {
				continueGame = true;
				clearLastGame(me, dealer);
			} else {
				continueGame = false;
			}
		}
		scanner.close();
	}

	private static String formatToCSVData(String... strings) {

		String output = "";

		for (String string : strings) {
			output = output + string + ",";
		}

		return output.substring(0, output.length() - 1);

	}

	private static void toCSVOutput(String row) {
		try (FileWriter writer = new FileWriter("../results.csv", true)) {

			writer.append(row);

		} catch (IOException ioe) {

			ioe.printStackTrace();

		}
	}

	/*
	 * Clears all players and dealer's hands for the next game, does not clear their
	 * win/lose counts
	 * 
	 */

	private static void clearLastGame(Player player, Dealer dealer) {

		for (int i = 0; i < player.getNumberOfHands(); i++) {
			if (player.getHands()[i] != null) {
				player.getHands()[i] = null;
			}
		}
		player.setNumberOfHands(0);

		if (dealer.getHands() != null) {
			dealer.voidHand();
			System.out.println("just voided dealer's hand for a new game");
		}

	}

	private static GameResult deliverGameResult(int playerScore, int dealerScore) {

		if (dealerScore > 21 && playerScore <= 21) {
			return GameResult.PLAYER_WINS;
		}

		if (dealerScore == playerScore) {
			return GameResult.PUSH;
		}

		if (dealerScore > playerScore) {
			return GameResult.DEALER_WINS;
		}

		if (playerScore > dealerScore && playerScore <= 21) {
			return GameResult.PLAYER_WINS;
		}

		return GameResult.DEALER_WINS;
	}

	private static int dealerPlayHand(Deck deck, Dealer dealer) {

		while (dealer.getHandSum() < 17) {

			dealer.addCard(deck.dealNextCard());

			if (dealer.getHandSum() > 21) {
				return -1;
			}
		}

		return dealer.getHandSum();
	}

	public static void playerPlayHands(Deck deck, Player player) {

		Hand[] playerHands = player.getHands();

		for (int i = 0; i < player.getNumberOfHands(); i++) {

			Hand hand = playerHands[i];

			boolean handDone = hand.isHandDone();

			while (!handDone) {

				if (hand.hasBlackJack()) {
					handDone = true;
					break;
				}

				System.out.print(player.getPlayerName() + " hand number " + player.getNumberOfHands() + ":   ");
				System.out.println(hand.toString(false));

				if (hand.canDoubleDown() || hand.canSplit()) {
					System.out.println("(S)tay, (H)it, (P)air Split, or (D)ouble Down? ('S', 'H', 'P', or 'D')");
				} else {
					System.out.println("Stay or Hit? ('S' or 'H')");
				}

				String response = scanner.next();

				if (response.compareToIgnoreCase("S") == 0) {

					hand.setHandFinishedStatus(true);

				} else if (response.compareToIgnoreCase("H") == 0) {

					hand.addCard(deck.dealNextCard());

					hand.updateHandStatus();

				} else if (response.compareToIgnoreCase("D") == 0 && hand.canDoubleDown()) {

					hand.addDoubleDownCard(deck.dealNextCard());
					// no need to call update, already know it will be via addDoubleDownCard()

				} else if (response.compareToIgnoreCase("P") == 0 && hand.canSplit()) {

					Card removedSplitCard = hand.split(); // remove the split card
					System.out.println(hand.toString(false));

					player.giveNewSplitHand(deck.dealNextCard(), removedSplitCard);

				}

				System.out.println(hand.toString(false));
				// evaluate this hand
				hand.updateHandStatus();
				handDone = hand.isHandDone();
			}
		}
		System.out.println("Player has finished all hands!");
	}
}
