public class FaceDownCard extends Card {

	private final String fileName = "cardImages/facedown.png";

	public FaceDownCard(Suit suit, int value) {
		super(suit, value);
	}

	public FaceDownCard() {

	}

	/*
	 * Returns the file name representing an image of this card.
	 */
	public String getFileName() {
		return fileName;
	}

}