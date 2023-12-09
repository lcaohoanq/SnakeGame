package runtime;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Menu extends JPanel implements ActionListener {

  //cần phải tạo ra 3 panel chứa 3 thành phần: username, pwd, button
  JPanel usernamePanel = new JPanel(new FlowLayout());
  JPanel passwordPanel = new JPanel(new FlowLayout());
  JPanel buttonPanel = new JPanel(new FlowLayout());

  JLabel user = new JLabel("username");
  JLabel pwd = new JLabel("password");

  JTextField username = new JTextField(10);
  JTextField password = new JPasswordField(10);

  JButton play = new JButton("Play");
  public Menu(){
    initMenu();
  }
  private void initMenu(){
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    usernamePanel.add(user);
    usernamePanel.add(username);

    passwordPanel.add(pwd);
    passwordPanel.add(password);

    play.addActionListener(this);

    buttonPanel.add(play);

    //panel tổng sẽ dùng BorderLayout.Center
    add(usernamePanel);
    add(passwordPanel);
    add(buttonPanel);

    Board.playAudio("src/resources/intro.wav");
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    new Snake();
  }
}
