import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Scoreboard {

    ArrayList<Scores> sc;

    public Scoreboard() {
        sc = new ArrayList<Scores>();
        parseFile();
    }

    /*
    Parses a csv-formatted file and generates Scores-objects, adds them to an ArrayList.
     */

    private void parseFile() {
        try {
            File f = new File("scoreboard.txt");
            Scanner fileInput = new Scanner(f);

            while (fileInput.hasNextLine()) {
                String line = fileInput.nextLine();
                String[] score = line.split(",");
                sc.add(new Scores(score[0], Integer.parseInt(score[1])));
            }
        } catch (FileNotFoundException f) {
            System.err.println("The file containing the scoreboard could not be found.");
        }
    }

    public void printScoreboard() {
        System.out.println("---- ***** SCOREBOARD ***** ----");
        for (int i = 0; i < sc.size(); i++) {
            System.out.println((i + 1) +  ". NAME: " + sc.get(i).getName() + " MISSES: " + sc.get(i).getMisses());
        }
        System.out.println("*********************************");
    }

    /*
    When the player has successfully guessed a word and they did not cheat,
     this method handles new scoreboard input. It then saves the input.
     */

    public void updateScoreboard(String name, int misses) {
        sc.add(new Scores(name, misses));
        Collections.sort(sc);
        writeAndClose();
    }

    private void writeAndClose() {
        try {
            File f = new File("scoreboard.txt");
            FileWriter fw = new FileWriter(f);
            for (Scores s : sc) {
                fw.write(s.getName() + "," + s.getMisses() + "\n");
            }
            fw.close();
        } catch (IOException ie) {
            System.out.println("The scoreboard could not be located, unable to save.");
        }

    }
}
