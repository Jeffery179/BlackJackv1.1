
public class Hand {

	private Card[] cards = new Card[10];
	private int numCardsInHand = 0;
	public boolean handDone = false;
	boolean canSplit = false;
	boolean canDoubleDown = false;

	public Hand(Card firstCard, Card secondCard, boolean done) {
		cards[numCardsInHand] = firstCard;
		numCardsInHand++;
		cards[numCardsInHand] = secondCard;
		numCardsInHand++;
		this.handDone = false;

		System.out.println("added " + firstCard.toString() + " and " + secondCard.toString());
	}

	public Card[] getCards() {
		return cards;
	}

	/*
	 * Returns true if the hand has black jack: Ace and 10
	 * 
	 * @return boolean
	 */
	public boolean hasBlackJack() {

		if (this.numCardsInHand == 2) {
			if ((cards[0].getNumber() == 1 && cards[1].getNumber() == 10)
					|| (cards[0].getNumber() == 10 && cards[1].getNumber() == 1)) {
				return true;
			}
		}

		return false;

	}

	public int getNumCardsInHand() {
		return numCardsInHand;
	}

	public void addCard(Card card) {
		cards[numCardsInHand] = card;
		numCardsInHand++;

		System.out.println("added additional card " + card.toString());
	}

	/**
	 * Adds a card to the hand and then finishes. Double Down scenario.
	 * 
	 * @param Card
	 * @return void
	 * 
	 */
	public void addDoubleDownCard(Card card) {
		cards[numCardsInHand] = card;
		numCardsInHand++;
		this.handDone = true;
	}

	/*
	 * splits the hand by returning half the hand
	 * 
	 */
	public Card split() {

		if (numCardsInHand != 2) {
			return null;
		}

		Card tempCard = cards[numCardsInHand];

		cards[numCardsInHand] = null;

		numCardsInHand--;

		return tempCard;
	}

	/**
	 * 
	 * @return true if the hand is eligible for a double down
	 */
	public boolean canDoubleDown() {
		return this.numCardsInHand == 2;
	}

	/*
	 * @return true if the hand is eligible for split play
	 * 
	 */
	public boolean canSplit() {

		if (numCardsInHand != 2) {
			return false;
		}

		if (cards[0].getNumber() == cards[1].getNumber()) {
			return true;
		}

		return false;
	}

	/*
	 * Returns the value of this hand.
	 * 
	 * @param none
	 * 
	 * @return int
	 * 
	 */
	public int getHandSum() {
		int handSum = 0;
		int softSum = 0;
		int hardSum = 0;
		int cardNum = 0;
		int numAces = 0;

		boolean hasAce = false;

		for (int i = 0; i < this.numCardsInHand; i++) {

			cardNum = this.cards[i].getNumber();

			// Ace
			if (cardNum == 1) {
				hasAce = true;
				numAces++;

				hardSum += 11;

			} else if (cardNum >= 10) { // face card value is 10

				hardSum += 10;

			} else {

				hardSum += cardNum;

			}

			if (hasAce) {
				softSum = hardSum;
				for (int c = 0; c < numAces; c++) {
					softSum = softSum - 10;
				}
			}

		}
		if (hardSum > 21 && hasAce) {
			return softSum;
		}
		return hardSum;
	}

	/**
	 * Print the cards in a hand.
	 * 
	 * @param dealerHand
	 *            determines whether the first card will be shown
	 */
	public String toString(boolean dealerHand) {

		String handString = "";

		for (int i = 0; i < this.numCardsInHand; i++) {
			if (i == 0 && dealerHand) {
				handString = handString + " [hidden card] ";
			} else {
				handString = handString + "[" + this.cards[i].toString() + "] ";
			}
		}
		if (dealerHand) {
			return handString;
		}

		return handString + " = " + getHandSum();

	}

	/**
	 * Prints the value of the hand.
	 */
	public void printHandSum() {
		System.out.println("Hand value = " + getHandSum());
	}

	public boolean isHandDone() {
		return this.handDone;
	}

	public void updateHandStatus() {
		if (getHandSum() >= 21) {
			this.handDone = true;
			this.canDoubleDown = false;
			this.canSplit = false;
		}
	}

	public void setHandFinishedStatus(boolean value) {
		this.handDone = true;
	}
}
