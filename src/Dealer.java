public class Dealer extends User {

	private DealerHand hand = null;

	public Dealer(String name) {
		setPlayerName(name);
	}

	@Override
	public void giveNewHand(Card firstCard, Card secondCard) {

		this.hand = new DealerHand(firstCard, secondCard, false);
		setNumberOfHands(1);

	}

	@Override
	public void emptyHand() {

		this.hand = null;

		setNumberOfHands(0);

	}

	@Override
	public void voidHand() {

		this.hand = null;

	}

	@Override
	public void addCard(Card nextCard) {
		this.hand.addCard(nextCard);
	}

	public Hand getHands() {
		return hand;
	}

	public int getHandSum() {
		return this.hand.getHandSum();
	}

}