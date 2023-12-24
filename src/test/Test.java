package test;

import java.awt.EventQueue;
import view.LoginFormView;

public class Test {

  public static void main(String[] args) {
    EventQueue.invokeLater(() -> {
      LoginFormView ex = new LoginFormView();
      ex.setVisible(true);
    });
  }
}
