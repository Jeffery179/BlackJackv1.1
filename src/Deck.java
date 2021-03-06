/**
 * @author Jeffery Lee
 * 
 * This class represents the physical deck that contains all the Card objects as an array.
 * 
 *
 */

import java.util.Arrays;
import java.util.Collections;

public class Deck {
	
	private Card[] cards;
	private int numCards; //currently in deck
	private final int standardNumCards = 52; //standard deck
	
	//call constructor for normal deck
	public Deck() {
		this(1, true);
	}
	
	//constructor for custom deck
	public Deck(int numberOfDecks, boolean shuffle) {
		
		this.numCards = standardNumCards * numberOfDecks;
		this.cards = new Card[this.numCards];
		
		int position = 0;
		while(position < this.cards.length - 1) {
			for (int cardValue = 1; cardValue < 14; cardValue++) {
				for(int fourCards = 0; fourCards < 4; fourCards++) {
					
					this.cards[position++] = new Card(Suit.values()[fourCards], cardValue);

				}
			}
		}
		
		if(shuffle) {
			shuffle();
		}
	}

	private void shuffle() {
		Collections.shuffle(Arrays.asList(this.cards));
	}
	
	/*
	 * @param null
	 * 
	 * @return Card
	 * 
	 * Returns the next card from the deck. Will subtract the card from the deck and
	 * lower the total number of cards by 1.
	 * 
	 */
	public Card dealNextCard() {
		
		Card topCard = this.cards[0];
		for(int i = 1; i < this.numCards; i++) {
			this.cards[i-1] = this.cards[i];
		}
		this.cards[this.numCards - 1] = null;
		this.numCards--;
		
		return topCard;
	}
	
	// TODO: This method will probably be better served in the future returning a
	// String instead of printing to console.
	/*
	 * @param null
	 * 
	 * @return null
	 * 
	 * Prints the entire deck in its' current state.
	 * 
	 */
	public void printDeck() {

		for (int i = 0; i < this.cards.length; i++) {
			System.out.println("position " + i + ": " + this.cards[i]);
		}

	}

	// TODO: This method will probably be better served in the future returning a
	// String instead of printing to console.
	/*
	 * @param int
	 * 
	 * @return null
	 * 
	 * Prints the desired number of cards from the deck in its' current state.
	 * 
	 */
	public void printDeck(int numToPrint) {
		
		for(int i = 0; i < numToPrint; i++) {
			System.out.printf("% 3d/%d %s\n", i+1, this.numCards, this.cards[i].toString());
		}
		
		System.out.println(this.numCards + " are left in the deck");
	}
}
