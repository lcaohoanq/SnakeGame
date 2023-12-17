package constants;

import javax.swing.JOptionPane;

public class Message {
  public static void aboutMe() {
    JOptionPane.showMessageDialog(null,
        "This is a project made by lcaohoanq" + "\n" + "Github: https://github.com/lcaohoanq", null,
        JOptionPane.INFORMATION_MESSAGE);
  }

  public static void copyRight() {
    JOptionPane.showMessageDialog(null,
        "Core logic of Snake game by janbodnar" + "\n"
            + "Github: https://github.com/janbodnar/Java-Snake-Game?tab=readme-ov-file",
        null,
        JOptionPane.INFORMATION_MESSAGE);
  }

  public static void signUpMsg() {
    JOptionPane.showMessageDialog(null, "Enter your new username and password", "Register",
        JOptionPane.INFORMATION_MESSAGE);
  }

  public static void IS_DUPLICATE_USERNAME() {
    JOptionPane.showMessageDialog(null, "This username is already taken!", "Register",
        JOptionPane.WARNING_MESSAGE);
  }

  public static void IS_SIGNUP_SUCCESSFULLY() {
    JOptionPane.showMessageDialog(null, "Sign up successfully!", "Register",
        JOptionPane.INFORMATION_MESSAGE);
  }

  public static void IS_EMPTY_USERNAME_OR_PASSWORD() {
    JOptionPane.showMessageDialog(null, "Username or password is empty!", "Register",
        JOptionPane.WARNING_MESSAGE);
  }

  public static void IS_WRONG_PASSWORD() {
    JOptionPane.showMessageDialog(null, "Wrong password!", "Sign in",
        JOptionPane.WARNING_MESSAGE);
  }

  public static void IS_SIGNIN_SUCCESSFULLY() {
    JOptionPane.showMessageDialog(null, "Sign in successfully!", "Sign in",
        JOptionPane.INFORMATION_MESSAGE);
  }

  public static void IS_WRONG_USERNAME() {
    JOptionPane.showMessageDialog(null, "Wrong username!", "Sign in",
        JOptionPane.WARNING_MESSAGE);
  }
}
