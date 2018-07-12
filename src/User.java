/**
 * @author Jeffery Lee
 *
 *         This super class represents a role in the game of black jack, the
 *         user can be a dealer or player. The implemented methods in this class
 *         are used by both a player and dealer. Abstract methods in this class
 *         will need to be implemented independently per dealer and player.
 *
 */

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

	/*
	 * @param null
	 * 
	 * @return null
	 * 
	 * Increases this player's hand by 1.
	 * 
	 */
	public void increaseHandCount() {
		numberOfHands++;
	}

	// abstract methods to be implemented individually by derived child classes:

	abstract public void giveNewHand(Card firstCard, Card secondCard);

	abstract public void emptyHand();

	abstract public void voidHand();

	abstract public void addCard(Card nextCard);
}