import javax.swing.*;
import java.awt.*;
import java.awt.event.*; 

class Main {
  public static void main(String[] args) {
    //new BirdleAlphabet("easy");
    buttons();
  }

  public static void buttons() {
    String result = "";
    JRadioButton fiveButton = new JRadioButton("5 letter mode (EASY)");
    fiveButton.setMnemonic(KeyEvent.VK_B);
    //fiveButton.setSelected(true);

    JRadioButton allButton = new JRadioButton("any number of letters (HARD)");
    allButton.setMnemonic(KeyEvent.VK_C);

    //Group the radio buttons.
    ButtonGroup group = new ButtonGroup();
    group.add(fiveButton);
    group.add(allButton);

    //Register a listener for the radio buttons.
    fiveButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        new BirdleAlphabet("easy");
      }
    });
    allButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        new BirdleAlphabet("hard");
      }
    });

    JFrame frame = new JFrame("Make a choice!");
    JPanel panel = new JPanel();
		frame.setSize(600, 600);
    frame.setBackground(Color.white);
    frame.setVisible(true);
    panel.add(fiveButton);
    panel.add(allButton);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(panel);

    System.out.println(result);
  }
}

// TO DO

// make game end once all green
// add message if not valid entry (red letters?)
// keyboard - change color to match letter status
// keyboard - make it enterable from computer keyboard
// ID green/yellow right!!!!


// FEATURES

// changeable number of letters in chosen bird
   // how to decide # guesses?
   // add one guess per extra two letters?

// "easy" "medium" "hard" modes
   // changes # letters in bird chosen
   // easy = fewer letters ??