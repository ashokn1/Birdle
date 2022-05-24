import javax.swing.*;
import java.awt.*;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Dimension;


public class BirdleDisplay{

  // Attributes
  private JTextField[][] disp;
  
  private Color green;
  private Color yellow;
  private Color grey;
  private Color lightGrey;
  private Color white;
  private Color black;

  private Dimension letterD;
  private Dimension borderD;
  private Font font;

  // Constructor
	public BirdleDisplay(){
    disp = new JTextField[BirdleGame.getGuesses()][BirdleGame.getLetters()];
    
    green = new Color(108,172,100);
    yellow = new Color(204,180,92);
    grey = new Color(124,124,124);
    lightGrey = new Color(213,213,220);
    white = new Color(255,255,255);
    black = new Color(0,0,0);

    letterD = new Dimension(40,40);
    borderD = new Dimension(48,48);
    font = new Font("Courier", Font.BOLD, 15);
	}	

  // returns a JPanel grid using the panels stored in JTextField[][] disp
  public JPanel display() {
    JPanel result = new JPanel();
    GridLayout grid = new GridLayout(disp.length, disp[0].length, 4, 4);
    result.setLayout(grid);
    for (int r = 0; r < disp.length; r ++) {
      for (int c = 0; c < disp[0].length; c ++ ) {
        result.add(r + " " + c, disp[r][c]);
      }
    }
    return result;
  }

  // Edits the JTextField[][] disp to reflect information from the char[][] guesses and char[][] parses, sent from the BirdleAlphabet class
  public void setDisp(char[][] guesses, char[][] parses) {
    for (int c = 0; c < disp[0].length; c ++) {
      for (int r = 0; r < disp.length; r ++ ) {
        JTextField a2inset = new JTextField("" + guesses[r][c]);
        if (guesses[r][c] == 0) {
          a2inset = new JTextField("");
        }
        a2inset.setEditable(false);
        a2inset.setPreferredSize(letterD);
        a2inset.setHorizontalAlignment(JTextField.CENTER);
        a2inset.setFont(font);
        a2inset.setBorder(null);
        if (parses[r][c] == 'G') {
          a2inset.setBackground(green);
          a2inset.setForeground(white);
        } else if (parses[r][c] == 'Y') {
          a2inset.setBackground(yellow);
          a2inset.setForeground(white);
        } else if (parses[r][c] == 'O') {
          a2inset.setBackground(grey);
          a2inset.setForeground(white);
        } else {
          a2inset.setBackground(white);
          a2inset.setForeground(black);
        }        
        disp[r][c] = a2inset;
      }
    }
    printArr(guesses);
  }

  // Tester method to print to command line
  public static void printArr(char[][] arr) {
    for (int r = 0; r < arr.length; r ++) {
      for (int c = 0; c < arr[0].length; c ++ ) {
        System.out.print(arr[r][c] + "  ");
      }
      System.out.println();
    }
  }

  

}