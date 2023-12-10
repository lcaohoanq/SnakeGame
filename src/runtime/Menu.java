package runtime;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Menu extends JPanel implements ActionListener {

  Map<String, String> accountList = new HashMap<>();

  //cần phải tạo ra 3 panel chứa 3 thành phần: username, pwd, button
  private JPanel logoPanel = new JPanel(new FlowLayout());
  private JPanel usernamePanel = new JPanel(new FlowLayout());
  private JPanel passwordPanel = new JPanel(new FlowLayout());
  private JPanel buttonPanel = new JPanel(new BorderLayout());
  private JPanel buttonPanel_primary = new JPanel(new FlowLayout()); //Play, Sign in, Sign up
  private JPanel buttonPanel_secondary = new JPanel(new FlowLayout()); //Play without sign in
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

  public Menu(){
    initMenu();
  }
  private void initMenu(){
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    logoPanel.add(logo);
    usernamePanel.add(username_lb);
    usernamePanel.add(username_tf);
    passwordPanel.add(password_lb);
    passwordPanel.add(password_tf);

    username_tf.addKeyListener(new GetInfo());
    password_tf.addKeyListener(new GetInfo());
    play.setEnabled(false);
    play.addActionListener(new PlayGame());
    sign_up.addActionListener(new SignUp());
    play_without_sign_in.addActionListener(new PlayGame());

    buttonPanel_primary.add(play);
    buttonPanel_primary.add(sign_in);
    buttonPanel_primary.add(sign_up);
    buttonPanel_secondary.add(play_without_sign_in);

    buttonPanel.add(buttonPanel_primary, BorderLayout.NORTH);
    buttonPanel.add(buttonPanel_secondary, BorderLayout.SOUTH);

    //panel tổng sẽ dùng BorderLayout.Center
    add(logoPanel);
    add(usernamePanel);
    add(passwordPanel);
    add(buttonPanel);

    Board.playAudio("src/resources/intro.wav");
  }

  @Override
  public void actionPerformed(ActionEvent e) {

  }

  private class GetInfo implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {
      //cả 2 ô này đều được fill thì release cái button Play ra
      if(!username_tf.getText().equals("") && !password_tf.getText().equals("")){
        play.setEnabled(true);
      }
      else{
        play.setEnabled(false);
      }
    }

    @Override
    public void keyPressed(KeyEvent e) {
      if(e.getKeyChar() == KeyEvent.VK_ENTER){
        e.consume();
      }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
  }

  private class PlayGame implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      new Snake();
    }
  }

  private class SignUp implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      userData = username_tf.getText();
      pwdData = password_tf.getText();

      System.out.println(accountList);

      if(isDuplicate(userData)){
        JOptionPane.showMessageDialog(null, Message.IS_DUPLICATE_USERNAME.getMessage());
        System.out.println("Username is already taken");
      }else if(isEmpty(userData, pwdData)) {
        JOptionPane.showMessageDialog(null, Message.IS_EMPTY_USERNAME_OR_PASSWORD.getMessage());

      }else{
        System.out.println("Username is available");
        accountList.put(userData, pwdData);
        JOptionPane.showMessageDialog(null, Message.IS_SIGNUP_SUCCESSFULLY.getMessage());
        play.setEnabled(true);
      }
      System.out.println(accountList);
      //chưa check trùng username
    }
  }

  private boolean isDuplicate(String user){
    return accountList.containsKey(user);
  }

  private boolean isEmpty(String user, String pwd){
    return user.equals("") || pwd.equals("");
  }

  private boolean isCorrect(String user, String pwd){
    return accountList.get(user).equals(pwd);
  }

  private class SignIn implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      userData = username_tf.getText();
      pwdData = password_tf.getText();
      if(userData.equals("") || pwdData.equals("")){
        JOptionPane.showMessageDialog(null, "Username or password is empty");
      }
      if(isCorrect(pwdData, pwdData)){
        new Snake();
      }
      else{
        JOptionPane.showMessageDialog(null, "Wrong password");
      }
      //chưa check trùng username
    }
  }
}
