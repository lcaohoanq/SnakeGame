package runtime;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI extends JFrame{

  public GUI() {
    initGUI();
  }

  private void initGUI(){
    add(new Menu(), BorderLayout.PAGE_START); //thêm panel vào frame
    setResizable(false);
    pack();
    setTitle("Snake Game");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(500, 500);
    setLocationRelativeTo(null);
    setVisible(true);
  }
  public static void main(String[] args) {
    new GUI();
  }
}
