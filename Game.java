import java.util.Scanner;

public class Game {

	String secret;
	char[] guesses;
	boolean cheater;
	int misses;
	Scoreboard sc;

	public Game () {
		sc = new Scoreboard();
	}

	private boolean isGuessAZ(String guess) {
		return guess.matches("[a-z]");
	}

	public boolean isGuessKeyword(String input) {
		switch (input.toLowerCase()) {
			case "restart" : newGame();
				System.out.println("New word, new possibilities."); return true;
			case "top" : sc.printScoreboard(); return true;
			case "exit" : System.exit(0); return true;
			case "help" : revealChar(); return true;
			default : return false;
		}
	}

	private boolean isGuessCorrect(char guess) {
		for (int i = 0; i < secret.length(); i++) {
			if (secret.charAt(i) == guess) {
				return true;
			}
		} return false;
	}

	/*
	Adds a new char to the array of guessed letters.
	 */

	private void addNewChar(char c) {
		for (int i = 0; i < guesses.length - 1; i++) {
			if (guesses[i] == '\0') {
				guesses[i] = c;
				return;
			}
		}
	}

	public void newGame() {
		guesses  = new char[24];
		cheater = false;
		secret = RandomWordGenerator.get();
		misses = 0;
	}

	private void revealChar() {
		for (int i = 0; i < secret.length(); i++) {
			if (!isCharContainedInSecretWord(i)) {
				addNewChar(secret.charAt(i));
				System.out.println("You are now flagged as a cheater.");
				cheater = true;
				return;
			}
		}
	}

	public String getVeiledString() {
		String temp = "";
		for (int i = 0; i < secret.length(); i++) {
			if (isCharContainedInSecretWord(i)) {
				temp = temp + secret.charAt(i) + " ";
			} else {
				temp = temp + "_ ";
			}
		}
		return temp;
	}

	private boolean isCharContainedInSecretWord(int indexInString) {
		for (int j = 0; guesses[j] != 0; j++) {
			if (secret.charAt(indexInString) == guesses[j]) {
				return true;
			}
		} return false;
	}

	private boolean alreadyGuessed(String s) {
		for (int j = 0; guesses[j] != 0; j++) {
			if (s.charAt(0) == guesses[j]) {
				return true;
			}
		} return false;
	}

	/*
	Checks if the entire word can be built by the guessed chars.
	If so, the player has successfully guessed the word.
	 */

	public boolean isWordGuessed() {
		for (int i = 0; i < secret.length(); i++) {
			if (!isCharContainedInSecretWord(i)) return false;
		} return true;
	}

	/*
	The game()-method evaluates input from the user whilst the game is running.
	 */

	public void game(String userInput) {

		// Check if a character between A-Z is inputted.
		if (isGuessAZ(userInput.toLowerCase())) {
			char guess = userInput.toLowerCase().charAt(0);
			// Check if already guessed.
			if (alreadyGuessed(userInput)) {
				System.out.println("You already tried " + guess + "! Silly you!");
			// If not already guessed and correct, notify player and add char to 'guessed chars'.
			} else if (isGuessCorrect(guess)) {
				addNewChar(guess);
				System.out.println("You found the letter " + guess + "! Congrats!");
			// The only remaining option is a wrong guess, notify player and add char to 'guessed chars'.
			} else {
				System.out.println("Oh boy, you guessed " + guess + ". Hangman incoming.");
				addNewChar(guess);
				misses++;
			}
		/*
		If the guess did not satisfy the A-Z condition, check if it is NOT a keyword.
		In that case, it must be an invalid string inputted by the user.
		 */

		} else if (!isGuessKeyword(userInput)) {
			System.out.println("'" + userInput + "' is not valid. El no valido.");
		}
	}

	/*
	Compound method for inserting a new "Scores" object, and printing the sorted Arraylist of "Scores"
	 */

	public void updateAndPrintScoreboard() {
		Scanner s = new Scanner(System.in);
		if (!cheater) {
			System.out.println("Enter your name: ");
			String name = s.nextLine();
			if (name.contains(String.valueOf(','))) {
				System.out.println("Sorry, names cannot contain commas. Try again!");
				updateAndPrintScoreboard();
			}
			sc.updateScoreboard(name, misses);
			sc.printScoreboard();
		} else {
			System.out.println("You cheated, no scoreboard-entry for you.");
		}
	}
}
