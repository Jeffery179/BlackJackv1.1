public class DealerHand extends Hand {

	public DealerHand(Card firstCard, Card secondCard, boolean done) {
		super(firstCard, secondCard, done);
	}

	public int getHandSum() {
		int handSum = 0;
		int softSum = 0;
		int hardSum = 0;
		int cardNum = 0;
		int numAces = 0;

		boolean hasAce = false;

		for (int i = 0; i < this.getNumCardsInHand(); i++) {

			cardNum = this.getCards()[i].getNumber();

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

	public boolean hasSoftHand() {

		Card[] myCards = this.getCards();

		for (Card card : myCards) {
			if (card.getNumber() == 1) {
				return true;
			}
		}
		return false;
	}
}