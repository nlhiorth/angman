import java.util.Scanner;

public class Hangman {

    Game g;

    public Hangman() {
        g = new Game();
    }

    public static void main(String[] args) {
        Hangman n = new Hangman();
        n.gameLoop();
    }

    /*
    The main loop for the game. Manages user I/O.
     */

    private void gameLoop() {
        g.newGame();
        Scanner in = new Scanner(System.in);
        String userInput;
        printInstructions();

        do {
            System.out.println(g.getVeiledString());
            g.game(in.nextLine());
        } while (!g.isWordGuessed());

        System.out.println(g.getVeiledString());
        printSuccess(g.misses);
        System.out.println();
        g.updateAndPrintScoreboard();
        tryAgain(in);
    }

    private void printInstructions() {
        System.out.println("***** Welcome to Hangman *****");
        System.out.println("USAGE: guess the word with a letter [a-z] or");
        System.out.println("Use 'TOP' to view the top scoreboard, 'RESTART' to start a new game, "
                + "'HELP' to cheat and 'EXIT' to quit the game.");
    }

    private void printSuccess(int misses) {
        System.out.println("Congrats, you succeeded with 'only' " + misses + " misses! Wow, great.");
    }

    private void tryAgain(Scanner s) {
        System.out.println("Do you want to try again?");
        System.out.println("'y' for YES, any key for NO");
        if (s.nextLine().compareToIgnoreCase("y") == 0) {
            System.out.println("Everything is reset! Also, new word.");
            gameLoop();
        } else {
            System.out.println("Alrighty, see you later!");
        }
    }
}
