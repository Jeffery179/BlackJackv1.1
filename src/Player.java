public class Player extends User {

	private PlayerHand[] hands = null;

	private Hand hand;

	@Override
	public void giveNewHand(Card firstCard, Card secondCard) {

		this.hand = new PlayerHand(firstCard, secondCard, false);

		increaseHandCount();

		increaseHandCount();

	}

	@Override
	public void emptyHand() {

		for (int i = 0; i < this.hands.length; i++) {
			this.hands[i] = null;
		}

		setNumberOfHands(0);
	}

	@Override
	public void voidHand() {
		// TODO Auto-generated method stub

	}

	public Player(String name) {

		this.hands = new PlayerHand[10];

		setPlayerName(name);

		// setting players initial hand to be empty
		this.emptyHand();

	}

	public Hand[] getHands() {
		return this.hands;
	}

	public Hand getHand() {
		return this.hand;
	}

	/*
	 * Return true if a player has a hand that is waiting for the dealer (non-busted
	 * hand)
	 * 
	 * @return boolean
	 */
	public boolean waitingForDealer() {

		// for (int i = 0; i < getNumberOfHands(); i++) {
		// if (hands[i].getHandSum() < 21) {
		// return true;
		// }
		// }

		if (hand.getHandSum() < 21) {
			return true;
		}

		return false;
	}

	public void giveNewSplitHand(Card newCard, Card splitCard) {

		this.hands[getNumberOfHands()] = new PlayerHand(splitCard, newCard, false);

		increaseHandCount();
	}

	@Override
	public void addCard(Card nextCard) {

		this.hand.addCard(nextCard);

		increaseHandCount();

	}

}