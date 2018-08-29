
/*
 * @author Jeffery Lee
 * @purpose this class holds the strategy for the player if they choose to use it
 * 
 * the black jack charts will be stored as 
 * */
public class BJStrategy {

	boolean[][] hardHandChart = null;

	boolean[][] softHandChart = null;

	private final String stand = "Stand";
	private final String hit = "Hit";

	public BJStrategy() {

		// true = hit, false = stand

		// hold chart for hands that are "hard", no ace
		hardHandChart = new boolean[][] { {}, // 0
				{}, // 1
				{}, // 2
				{}, // 3
				{ true, true, true, true, true, true, true, true, true, true, true }, // 4
				{ true, true, true, true, true, true, true, true, true, true, true }, // 5
				{ true, true, true, true, true, true, true, true, true, true, true }, // 6
				{ true, true, true, true, true, true, true, true, true, true, true }, // 7
				{ true, true, true, true, true, true, true, true, true, true, true }, // 8
				{ true, true, true, true, true, true, true, true, true, true, true }, // 9
				{ true, true, true, true, true, true, true, true, true, true, true }, // 10
				{ true, true, true, true, true, true, true, true, true, true, true }, // 11
				{ true, true, true, true, false, false, false, true, true, true, true }, // 12
				{ true, true, false, false, false, false, false, true, true, true, true }, // 13
				{ true, true, false, false, false, false, false, true, true, true, true }, // 14
				{ true, true, false, false, false, false, false, true, true, true, true }, // 15
				{ true, true, false, false, false, false, false, true, true, true, true }, // 16
				{ false, false, false, false, false, false, false, false, false, false, false }, // 17
				{ false, false, false, false, false, false, false, false, false, false, false }, // 18
				{ false, false, false, false, false, false, false, false, false, false, false }, // 19
				{ false, false, false, false, false, false, false, false, false, false, false }, // 20
				{ false, false, false, false, false, false, false, false, false, false, false } // 21
		};

		// hold chart for soft hands, has ace
		// hold chart for hands that are "hard", no ace
		softHandChart = new boolean[][] { {}, // 0
				{}, // 1
				{}, // 2
				{}, // 3
				{ true, true, true, true, true, true, true, true, true, true, true }, // 4
				{ true, true, true, true, true, true, true, true, true, true, true }, // 5
				{ true, true, true, true, true, true, true, true, true, true, true }, // 6
				{ true, true, true, true, true, true, true, true, true, true, true }, // 7
				{ true, true, true, true, true, true, true, true, true, true, true }, // 8
				{ true, true, true, true, true, true, true, true, true, true, true }, // 9
				{ true, true, true, true, true, true, true, true, true, true, true }, // 10
				{ true, true, true, true, true, true, true, true, true, true, true }, // 11
				{ true, true, true, true, false, false, false, true, true, true, true }, // 12
				{ true, true, true, true, true, true, true, true, true, true, true }, // 13
				{ true, true, true, true, true, true, true, true, true, true, true }, // 14
				{ true, true, true, true, true, true, true, true, true, true, true }, // 15
				{ true, true, true, true, true, true, true, true, true, true, true }, // 16
				{ true, true, true, true, true, true, true, true, true, true, true }, // 17
				{ false, true, false, false, false, false, false, false, false, true, true }, // 18
				{ false, false, false, false, false, false, false, false, false, false, false }, // 19
				{ false, false, false, false, false, false, false, false, false, false, false }, // 20
				{ false, false, false, false, false, false, false, false, false, false, false } // 21
		};

	}

	// return the message "Stand" or "Hit" as help for the player
	public String getAction(boolean softHand, int playerTotal, int dealerValue) {

		// values in 2D Array: true = hit, false = stand
		boolean status = false;

		if (softHand) {

			status = softHandChart[playerTotal][dealerValue];

		} else {

			status = hardHandChart[playerTotal][dealerValue];

		}

		if (status) {
			return hit;
		}

		return stand;

	}
}