import javax.swing.*;
import java.awt.*;
import java.awt.event.*; 

// Main class that does the work

public class BirdleAlphabet{

  // Attributes
  private final String[] LETTY = {"qwertyuiop", "asdfghjkl", "zxcvbnm"};
  private final JPanel[] KEYBOARD = {new JPanel(), new JPanel(), new JPanel()};
  private String guess;
  private String parsedGuess;
  private BirdleGame game;
  private BirdleDisplay display;
  private char[][] guesses;
  private char[][] parses;
  private int onRow;

  private JPanel disp;
  private JPanel alph;
  private JPanel all;
  private JFrame frame;

  private boolean d;
  private boolean correct;

  // Constructor
	public BirdleAlphabet(){
		this.game = new BirdleGame();
    this.display = new BirdleDisplay();
    onRow = 0;
    guess = "";
    guesses = new char[BirdleGame.getGuesses()][BirdleGame.getLetters()];
    parses = new char[BirdleGame.getGuesses()][BirdleGame.getLetters()];
    for (int r = 0; r < guesses.length; r ++) {
      for (int c = 0; c < guesses[0].length; c ++) {
        guesses[r][c] = 0;
        parses[r][c] = 'W';
      }
    }
    display.setDisp(guesses, parses);

    frame = new JFrame("Birdle");
		frame.setSize(600, 600);
    frame.setBackground(Color.white);
    
    all = new JPanel();   
    alph = this.get2();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);	
    editDisplay();


    while (!correct) {
      if (d != true) {
        editDisplay();
        d = true;
      }
    }
	}	

  // Returns the keyboard
  public JPanel get2() {
    JPanel keyboard = new JPanel();
    keyboard.setLayout(new BoxLayout(keyboard, BoxLayout.Y_AXIS));
    Dimension letSpacing = new Dimension(10, 10);

    JButton enter = new JButton("->");
    enter.setPreferredSize(new Dimension(50, 50));
    enter.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        System.out.println("\nguess is " + guess);
        if (game.validGuess(guess)) {
          parsedGuess = game.parseGuess(guess);
          System.out.println(parsedGuess);
          if (BirdleGame.isCorrect(parsedGuess)) {
            correct = true;
          }
          for (int i = 0; i < parses[0].length; i ++) {
            parses[onRow][i] = parsedGuess.charAt(i);
    
          }
          onRow ++;
          guess = "";
          editDisplay();
        } else {
          System.out.println("Please enter valid guess");
          guess = "";
        }
      }
    });

    JButton delete = new JButton("<<");
    delete.setPreferredSize(new Dimension(50, 50));
    delete.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        if (guess.length() > 0) {
          guess = guess.substring(0,guess.length()-1);
          //System.out.print("\n" + guess);
        }
        editDisplay();
      }
    });	
    
    for (int i = 0; i < KEYBOARD.length; i ++) {
      KEYBOARD[i].setLayout(new BoxLayout(KEYBOARD[i], BoxLayout.X_AXIS));
      if (i == 2) {
        KEYBOARD[i].add(delete);
        KEYBOARD[i].add(Box.createRigidArea(letSpacing));
      }
      keyboard.add(Box.createRigidArea(new Dimension(10, 10)));
      for (int v = 0; v < LETTY[i].length(); v ++) {
        String letter = LETTY[i].substring(v, v+1);
        JButton btn = new JButton(letter);
        btn.setPreferredSize(new Dimension(50, 50));
        btn.addActionListener(new ActionListener(){
    			public void actionPerformed(ActionEvent e){
            if (guess.length() <= BirdleGame.getLetters()) {
              //System.out.print(let);
              guess += letter;
              editDisplay();
              d = false;
            }
    			}
    		});	
        KEYBOARD[i].add(btn);
        KEYBOARD[i].add(Box.createRigidArea(letSpacing));
        if (i == 2) {
          KEYBOARD[i].add(enter);
        }
      }
      keyboard.add(KEYBOARD[i]);
    }
    return keyboard;
  }

  public void editDisplay() {
    for (int c = 0; c < guesses[0].length; c ++) {
      if (c < guess.length()) {
        guesses[onRow][c] = guess.charAt(c);
      } else {
        guesses[onRow][c] = 0;
      }
    }
    display.setDisp(guesses, parses);
    disp = display.display();
    frame.getContentPane().removeAll();
    frame.invalidate();
    frame.add(disp);
    frame.add(alph);
    frame.setContentPane(all);
  }
}