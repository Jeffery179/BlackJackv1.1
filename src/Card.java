/**
 * 
 * @author jeffery
 * 
 *
 */
public class Card {
	
	private Suit cardSuit;
	private int cardValue;
	
	public Card(Suit suit, int value) {
		
		this.cardSuit = suit;
		this.cardValue = value;
		
	}
	
	public int getNumber() {
		return this.cardValue;
	}
	
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
}
