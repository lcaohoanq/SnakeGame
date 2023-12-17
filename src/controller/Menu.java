package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import constants.Message;
import view.Snake;

public class Menu extends JPanel {

  Map<String, String> accountList = new HashMap<>();
  private JMenuBar menuBar = new JMenuBar();
  private JMenu menu1 = new JMenu("HELP");
  private JMenuItem aboutMe = new JMenuItem("About me");
  private JPanel logoPanel = new JPanel(new FlowLayout());
  private JPanel usernamePanel = new JPanel(new FlowLayout());
  private JPanel passwordPanel = new JPanel(new FlowLayout());
  private JPanel buttonPanel = new JPanel(new BorderLayout());
  private JPanel buttonPanel_primary = new JPanel(new FlowLayout()); // Play, Sign in, Sign up
  private JPanel buttonPanel_secondary = new JPanel(new FlowLayout()); // Play without sign in
  private JLabel logo = new JLabel("Snake Game");
  private JLabel username_lb = new JLabel("username");
  private JLabel password_lb = new JLabel("password");
  private JTextField username_tf = new JTextField(10);
  private JTextField password_tf = new JPasswordField(10);
  private JButton play = new JButton("Play");
  private JButton sign_in = new JButton("Sign in");
  private JButton sign_up = new JButton("Sign up");
  private JButton play_without_sign_in = new JButton("Play without sign in!");

  private String userData = "";
  private String pwdData = "";

  private String FILE_PATH = "src/resources/account.txt";

  private boolean isVerified = false;

  // private JFrame GUI = new JFrame();

  public Menu() {
    initMenu();
    readFile();
  }

  private void initMenu() {
    configAccount();
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    logo.setFont(new Font("Roboto", Font.BOLD, 30));
    logoPanel.add(logo);
    menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
    menuBar.add(menu1);
    menu1.add(aboutMe);
    usernamePanel.add(username_lb);
    usernamePanel.add(username_tf);
    passwordPanel.add(password_lb);
    passwordPanel.add(password_tf);

    play.setEnabled(false);

    aboutMe.addActionListener(new Info());
    buttonPanel_primary.add(play);
    play.addActionListener(new PlayGame());
    buttonPanel_primary.add(sign_in);
    sign_in.addActionListener(new SignIn());
    buttonPanel_primary.add(sign_up);
    sign_up.addActionListener(new SignUp());
    buttonPanel_secondary.add(play_without_sign_in);
    play_without_sign_in.addActionListener(new PlayGame());

    buttonPanel.add(buttonPanel_primary, BorderLayout.NORTH);
    buttonPanel.add(buttonPanel_secondary, BorderLayout.SOUTH);

    add(menuBar);
    add(logoPanel);
    add(usernamePanel);
    add(passwordPanel);
    add(buttonPanel);
  }

  private class SignUp implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      Message.signUpMsg();
      controlPlayButton("disable");
      // người dùng nhập lại, nhấn enter phát là mình sẽ check tiếp khúc dưới này thì
      // phải làm sao?
      username_tf.addActionListener(new DataCollector());
      password_tf.addActionListener(new DataCollector());
    }
  }

  private class DataCollector implements ActionListener, KeyListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      userData = username_tf.getText();
      pwdData = password_tf.getText();
      if (userData.isEmpty() || pwdData.isEmpty()) {
        Message.IS_EMPTY_USERNAME_OR_PASSWORD();
      } else {
        if (isDuplicateUserName(userData)) {
          Message.IS_DUPLICATE_USERNAME();
        } else {
          accountList.put(userData, pwdData);
          writeFile();
          Message.IS_SIGNUP_SUCCESSFULLY();
        }
      }
    }

    @Override
    public void keyTyped(KeyEvent e) {
      if (userData.isEmpty() || pwdData.isEmpty()) {
        controlPlayButton("disable");
      } else {
        controlPlayButton("enble");
      }
    }

    @Override
    public void keyPressed(KeyEvent e) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'keyPressed'");
    }

    @Override
    public void keyReleased(KeyEvent e) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
    }

  }

  private class SignIn implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      userData = username_tf.getText();
      pwdData = password_tf.getText();
      if (userData.isEmpty() || pwdData.isEmpty()) {
        controlPlayButton("disable");
        Message.IS_EMPTY_USERNAME_OR_PASSWORD();
      } else {
        if (accountList.containsKey(userData)) {
          if (accountList.get(userData).equals(pwdData)) {
            Message.IS_SIGNIN_SUCCESSFULLY();
            controlPlayButton("enble");
            isVerified = true;
          } else {
            Message.IS_WRONG_PASSWORD();
          }
        } else {
          controlPlayButton("disable");
          Message.IS_WRONG_USERNAME();
        }
      }
    }

  }

  private class PlayGame implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      // !fix this later
      // GUI.setVisible(false);
      new Snake();
    }
  }

  private class Info implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      Message.aboutMe();
      Message.copyRight();
    }
  }

  // default account for testing
  private void configAccount() {
    accountList.put("admin", "admin");
  }

  private void controlPlayButton(String value) {
    if (value.equals("enble")) {
      play.setEnabled(true);
      play.setBackground(Color.GREEN);
    } else {
      play.setEnabled(false);
    }
  }

  private boolean isDuplicateUserName(String username) {
    return accountList.containsKey(username);
  }

  private boolean readFile() {
    try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] parts = line.split(":", 2);
        if (parts.length == 2) {
          String username = parts[0].trim();
          String password = parts[1].trim();
          accountList.put(username, password);
        }
      }
      return true;
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
  }

  private boolean writeFile() {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
      for (Map.Entry<String, String> entry : accountList.entrySet()) {
        bw.write(entry.getKey() + ":" + entry.getValue());
        bw.newLine();
      }
      return true;
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
  }
}
