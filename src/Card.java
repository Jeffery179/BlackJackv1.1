/**
 * @author Jeffery Lee
 * 
 *         This class holds our our Card object. Each Card represents a card
 *         with a suit and value. In the game of blackjack, the suits are
 *         unimportant, the value is the focus of the game.
 * 
 *
 */
public class Card {
	
	private Suit cardSuit;

	private int cardValue;
	
	public Card() {

		this.cardSuit = null;

		this.cardValue = 0;

	}

	public Card(Suit suit, int value) {
		
		this.cardSuit = suit;

		this.cardValue = value;
		
	}
	
	/*
	 * @param null
	 * 
	 * @return int
	 * 
	 * Returns the numeric value of this Card. The cards 2-10 will be represented as
	 * 2 - 10. All face cards will be represented as 10. Aces will be represented as
	 * 1.
	 * 
	 */
	public int getNumber() {

		return this.cardValue;
	}
	
	/*
	 * @param null
	 * 
	 * @return String
	 * 
	 * Returns the lexical representation of a card for printing purposes.
	 * 
	 */
	public String toString() {
		
		String value = "";
		
		switch(this.cardValue) {
		
		case 2:
			value = "Two";
			break;
		case 3:
			value = "Three";
			break;
		case 4:
			value = "Four";
			break;
		case 5:
			value = "Five";
			break;
		case 6: 
			value = "Six";
			break;
		case 7: 
			value = "Seven";
			break;
		case 8: 
			value = "Eight";
			break;
		case 9:
			value = "Nine";
			break;
		case 10: 
			value = "Ten";
			break;
		case 11:
			value = "Jack";
			break;
		case 12:
			value = "Queen";
			break;
		case 13:
			value = "King";
			break;
		case 1:
			value = "Ace";
			break;
		}
		
		return value + " of " + this.cardSuit;
	}

	/*
	 * Returns the file name representing an image of this card.
	 */
	public String getFileName() {
		return String.format("/cardImages/%s%s.png", cardValue, cardSuit);
	}
}
