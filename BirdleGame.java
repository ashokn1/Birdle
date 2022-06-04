import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.PrintWriter;

public class BirdleGame {

  // SIZE

  //ATTRIBUTES
  private String word;
  private final String CHEAT = "divya"; // CHEAT CODE
  private ArrayList<String> birds;
  private ArrayList<String> guessList;
  private Scanner input;
  private boolean correct;
  private static int GUESSES;
  private static int LETTERS;

  //CONSTRUCTORS
  public BirdleGame(String mode) {
    correct = false;
    input = new Scanner(System.in);
    birds = new ArrayList<String>();
    guessList = new ArrayList<String>();
    String pathname = "FiveLetterBirds.txt";
    String guessPathname = "FiveLetterGuesses.txt";
    if (mode.equals("hard")) {
      pathname = "CommBirdsandPhrases.txt";
      guessPathname = pathname;
    } 
    System.out.println(pathname);
    readBirds(pathname, birds);
    readBirds(guessPathname, guessList);
    word = chooseBird();
    System.out.println(word);
    System.out.println(word.length());
    GUESSES = word.length() + 1;
    LETTERS = word.length();
  }

  public void readBirds(String pathname, ArrayList<String> destination)
  {
    File file = new File(pathname);
    try {
      input = new Scanner(file);
    } catch (FileNotFoundException ex) {
      System.out.println("*** Cannot open " + pathname + " ***");
      System.exit(1);  // quit the program
    } 
    destination.add("0");
    destination.add("1");
    while(input.hasNextLine()) {
      String line = input.nextLine();
      boolean added = false;
      for (int i = 1; i < destination.size(); i ++) {
        if (line.length() < destination.get(i).length()) {
          destination.add(i - 1, line);
          added = true;
          break;
        }
      }
      if (!added) {
        destination.add(line);
      }
    }
    destination.remove("0");
    destination.remove("1");
  }

  private String chooseBird() {
    int i = (int)(birds.size() * Math.random());
    return birds.get(i);
  }

  public String getWord(){
    return this.word;
  }

  private void guessBird() {
    String guess = "";
    input = new Scanner(System.in);
    do {
      System.out.println("\nenter guess:");
      guess = input.next();
      //cheat code
      if (guess.equals(CHEAT)) {
        System.out.println("Cheat code activated! Word: " + word);
      }
    } while (guess.length() != word.length() || !validGuess(guess));
  }

  public String parseGuess(String guess) {
    char[] result = new char[word.length()];
    for (int i = 0; i < word.length(); i ++) {
      if (word.substring(i,i+1).equalsIgnoreCase(guess.substring(i,i+1))) {
        result[i] = 'G';
      } else if (word.contains(guess.substring(i, i + 1))) {
        result[i] = 'Y';
      } else {
        result[i] = 'O';
      }
    }
    String result2 = "";
    correct = true;
    for (char i : result) {
      result2 += i;
      if (i != 'G') {
        correct = false;
      }
    }
    return result2;
  }

  public boolean validGuess(String guess) {
    boolean valid = true;
    if (guess.equals(CHEAT)) {
      System.out.println("Cheat code activated! Word: " + word);
    }
    if (!guessList.contains(guess)) {
      System.out.println("not in bird list");
      valid = false;
    }
    if (guess.length() != word.length()) {
      System.out.println("length does not match");
      valid = false;
    }
    return valid;
  }


  // TESTER METHODS

  private void testBirds() {
    print();
    int[] lengths = new int[19];
    for (String b : birds) {
      lengths[b.length()]  ++;
    }
    printArr(lengths);
  }

  private void print() {
    for (int i = 0; i < birds.size(); i ++) {
      System.out.println(i + 1 + "  " + birds.get(i));
    }
  }

  public void printArr(int[] lengths) {
    System.out.print("[ ");
    for (int i : lengths) {
      System.out.print(i + " ");
    }
    System.out.print("]");
  }

  public static int getGuesses() {
    return GUESSES;
  }
  public static int getLetters() {
    return LETTERS;
  }

  public static boolean isCorrect(String parsedGuess) {
    for (int i = 0; i < parsedGuess.length(); i ++) {
      if (parsedGuess.charAt(i) != 'G') {
        return false;
      }
    }
    return true;
  }
}