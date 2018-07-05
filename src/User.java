abstract public class User {

	private String name;

	private int numberOfHands = 0;

	public String getPlayerName() {
		return name;
	}

	public void setPlayerName(String name) {
		this.name = name;
	}

	public int getNumberOfHands() {
		return numberOfHands;
	}

	public void setNumberOfHands(int numHands) {
		this.numberOfHands = numHands;
	}

	public void increaseHandCount() {
		numberOfHands++;
	}
	// abstract methods to be implemented individually by derived classes:

	abstract public void giveNewHand(Card firstCard, Card secondCard);

	abstract public void emptyHand();

	abstract public void voidHand();

	abstract public void addCard(Card nextCard);
}