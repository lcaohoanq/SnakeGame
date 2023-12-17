package view;

import javax.swing.JFrame;
import controller.Menu;

public class GUI extends JFrame {

  public GUI() {
    initGUI();
  }

  private void initGUI() {
    add(new Menu());
    setResizable(false);
    pack();
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setVisible(true);
  }

  public static void main(String[] args) {
    new GUI();
  }
}
