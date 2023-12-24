package view;

import constants.Message;
import controller.PlayController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import constants.ColorsHandling;
import constants.Path;
import controller.LoginFormController;
import utils.DataHandler;

public class LoginFormView extends JFrame {
  // MenuBar
  private JMenuBar jMenuBar = new JMenuBar();
  private JMenu jMenu = new JMenu("HELP");
  private JMenuItem jMenuItem_AboutMe = new JMenuItem("About me");
  // Components for Login Form
  private JLabel jLabel_Logo = new JLabel("Login", JLabel.CENTER);
  private JLabel jLabel_Username = new JLabel("Username: ");
  private JLabel jLabel_Password = new JLabel("Password: ");

  // Text fields and button
  public static JTextField jTextField_Username = new JTextField(10);
  public static JTextField jTextField_Password = new JTextField(10);
  private JButton jButton_LoginButton = new JButton("Login");
  public static JButton jButton_PlayButton = new JButton("Play");
  private JPanel jPanel_emptyJPanel = new JPanel();

  // Additional label for other options
  private JButton jButton_OtherOption = new JButton("Do not have an account? Sign up here");
  public JButton jButton_PlayWithoutSignIn = new JButton("Play without sign in!");

  // Fonts and dimensions
  Font font_logo = new Font("Dialog", Font.BOLD, 50);
  Font font_text_jLabel = new Font("Dialog", Font.PLAIN, 20);
  Font font_text_JTextField = new Font("Dialog", Font.PLAIN, 35);
  Font font_button = new Font("Dialog", Font.BOLD, 20);
  // Dimension sizeText = new Dimension(100, 30);
  Dimension sizeInputField = new Dimension(50, 10);
  Dimension sizeButton = new Dimension(100, 50);

  // Borders and styling
  EmptyBorder containerBorder = new EmptyBorder(0, 30, 0, 30);
  EmptyBorder logoBorder = new EmptyBorder(50, 0, 0, 0);
  EmptyBorder userNamePanelBorder = new EmptyBorder(50, 0, 50, 0);
  EmptyBorder jLabelBorder = new EmptyBorder(0, 0, 10, 0);
  EmptyBorder userNameFieldBorder = new EmptyBorder(0, 10, 0, 10);
  EmptyBorder passwordPanelBorder = new EmptyBorder(50, 0, 50, 0);
  EmptyBorder passwordFieldBorder = new EmptyBorder(0, 10, 0, 10);
  // EmptyBorder dataZoneBorder = new EmptyBorder(150, 20, 150, 20);
  EmptyBorder buttonBorder = new EmptyBorder(30, 0, 20, 0);
  EmptyBorder otherOptionsBorder = new EmptyBorder(0, 0, 15, 0);
  Border border = BorderFactory.createLineBorder(Color.WHITE, 1);
  URL iconURL = LoginFormView.class.getResource("/resources/key.png");
  Image img = Toolkit.getDefaultToolkit().createImage(iconURL);

  // Panels for organizing components
  private JPanel jPanel_container = new JPanel();
  private JPanel jPanel_TopZone = new JPanel();
  private JPanel jPanel_Username = new JPanel(); // Panel for username components
  private JPanel jPanel_Password = new JPanel(); // Panel for password components
  private JPanel jPanel_MiddleZone = new JPanel(); // Middle zone combining username and password
  private JPanel jPanel_Button = new JPanel(new GridLayout(1, 3)); // Panel for login button
  private JPanel jPanel_OtherOptions = new JPanel(new FlowLayout()); // Panel for other options
  private JPanel jPanel_BottomZone = new JPanel(new BorderLayout()); // Panel for login button and other options

  // Others
  private DataHandler dataHandler = new DataHandler();
  ActionListener ac;

  public LoginFormView() {
    setTitle("Login");
    setSize(520, 680);
    setIconImage(img);
    // pack();
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setResizable(false);

    System.out.println("Truoc data Handler");
    dataHandler.readFile(Path.url); // load data account
    initForm();
  }

  private void initForm() {
  // Setting up menu bar
    setUpMenuBar();
    // Setting up title label
    setUpTopZone();

    // Setting up username components
    setUpUsername();

    // Setting up password components
    setUpPassword();

    // Middle zone setup (combining username and password)
    setUpMiddleZone();

    // Bottom zone setup
    setUpBottomZone();

    // Container setup
    setUpContainer();

    // Action
    doAction();
  }
  private class Info implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      Message.IS_ABOUT_ME();
      Message.IS_COPYRIGHT();
    }
  }

  private class ClickOtherOption implements ActionListener {
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
      dispose();
      new RegisterFormView();
    }
  }

  private class PressEnter implements ActionListener {

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
      jButton_LoginButton.doClick();
    }

  }
  private void setUpMenuBar(){
    jMenuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
//    jMenuBar.setBackground(ColorsHandling.PRIMARY_COLOR);
    jMenuBar.setBorder(border);
    jMenuBar.add(jMenu);
    jMenu.add(jMenuItem_AboutMe);
    this.setJMenuBar(jMenuBar);
  }
  private void setUpTopZone() {
    jLabel_Logo.setFont(font_logo);
    // jLabel_Logo.setPreferredSize(sizeText);
    jLabel_Logo.setForeground(ColorsHandling.TEXT_COLOR);
    jLabel_Logo.setBackground(ColorsHandling.PRIMARY_COLOR);
    jPanel_TopZone.setLayout(new BorderLayout());
    jPanel_TopZone.setBackground(ColorsHandling.PRIMARY_COLOR);
    jPanel_TopZone.setBorder(logoBorder);
    jPanel_TopZone.add(jLabel_Logo, BorderLayout.CENTER);
  }

  private void setUpUsername() {
    jLabel_Username.setFont(font_text_jLabel);
    // jLabel_Username.setPreferredSize(sizeText);
    jLabel_Username.setBorder(jLabelBorder);
    jLabel_Username.setForeground(ColorsHandling.TEXT_COLOR);
    jLabel_Username.setBackground(ColorsHandling.PRIMARY_COLOR);
    // jTextField_Username.setBorder(border);
    jTextField_Username.setFont(font_text_JTextField);
    jTextField_Username.setBackground(ColorsHandling.SECONDARY_COLOR);
    jTextField_Username.setForeground(ColorsHandling.TEXT_COLOR);
    jPanel_Username.setBorder(userNamePanelBorder);
    jPanel_Username.setBackground(ColorsHandling.PRIMARY_COLOR);
    jPanel_Username.setLayout(new BoxLayout(jPanel_Username, BoxLayout.Y_AXIS));
    jPanel_Username.add(jLabel_Username);
    jPanel_Username.add(jTextField_Username);
  }

  private void setUpPassword() {
    jLabel_Password.setFont(font_text_jLabel);
    jLabel_Password.setBorder(jLabelBorder);
    // jLabel_Password.setPreferredSize(sizeText);
    jLabel_Password.setForeground(ColorsHandling.TEXT_COLOR);
    jLabel_Password.setBackground(ColorsHandling.PRIMARY_COLOR);
    // jTextField_Password.setBorder(border);
    jTextField_Password.setFont(font_text_JTextField);
    jTextField_Password.setBorder(border);
    jTextField_Password.setBackground(ColorsHandling.SECONDARY_COLOR);
    jTextField_Password.setForeground(ColorsHandling.TEXT_COLOR);

    jPanel_Password.setBorder(passwordPanelBorder);
    jPanel_Password.setBackground(ColorsHandling.PRIMARY_COLOR);
    jPanel_Password.setLayout(new BoxLayout(jPanel_Password, BoxLayout.Y_AXIS));
    jPanel_Password.add(jLabel_Password);
    jPanel_Password.add(jTextField_Password);
  }

  private void setUpMiddleZone() {
    jPanel_MiddleZone.setLayout(new BorderLayout());
    // jPanel_MiddleZone.setBorder(dataZoneBorder);
    jPanel_MiddleZone.setBackground(ColorsHandling.PRIMARY_COLOR);
    jPanel_MiddleZone.setLayout(new GridLayout(2, 1));
    jPanel_MiddleZone.add(jPanel_Username);
    jPanel_MiddleZone.add(jPanel_Password);
  }

  private void setUpBottomZone() {
    jButton_LoginButton.setPreferredSize(sizeButton);
    jButton_LoginButton.setFont(font_button);
    jButton_LoginButton.setForeground(ColorsHandling.PRIMARY_COLOR);
    jButton_LoginButton.setBackground(ColorsHandling.TEXT_COLOR);

    jPanel_emptyJPanel.setBackground(ColorsHandling.PRIMARY_COLOR);

    jButton_PlayButton.setEnabled(false);
    jButton_PlayButton.setPreferredSize(sizeButton);
    jButton_PlayButton.setFont(font_button);
    jButton_PlayButton.setForeground(ColorsHandling.PRIMARY_COLOR);
    jButton_PlayButton.setBackground(ColorsHandling.TEXT_COLOR);

    // jButton_LoginButton.setBorder(border);
    jButton_OtherOption.setBorder(null);
    // jButton_OtherOption.setFocusPainted(false); //tat di trang thai hover
    jButton_OtherOption.setRolloverEnabled(false);
    jButton_OtherOption.setForeground(ColorsHandling.TEXT_COLOR);
    jButton_OtherOption.setBackground(ColorsHandling.PRIMARY_COLOR);

    jButton_PlayWithoutSignIn.setBorder(null);
    // jButton_OtherOption.setFocusPainted(false); //tat di trang thai hover
    jButton_PlayWithoutSignIn.setRolloverEnabled(false);
    jButton_PlayWithoutSignIn.setForeground(ColorsHandling.TEXT_COLOR);
    jButton_PlayWithoutSignIn.setBackground(ColorsHandling.PRIMARY_COLOR);

    jPanel_BottomZone.setBackground(ColorsHandling.PRIMARY_COLOR);
    // jPanel_BottomZone.setLayout(new BoxLayout(jPanel_BottomZone,
    // BoxLayout.Y_AXIS));

    jPanel_Button.add(jButton_LoginButton);
    jPanel_Button.add(jPanel_emptyJPanel);
    jPanel_Button.add(jButton_PlayButton);
    jPanel_Button.setBackground(ColorsHandling.PRIMARY_COLOR);
    jPanel_Button.setBorder(buttonBorder);
    jPanel_OtherOptions.add(jButton_OtherOption);
    jPanel_OtherOptions.add(jButton_PlayWithoutSignIn);
    jPanel_OtherOptions.setBackground(ColorsHandling.PRIMARY_COLOR);
    jPanel_OtherOptions.setBorder(otherOptionsBorder);
    jPanel_BottomZone.add(jPanel_Button, BorderLayout.NORTH);
    jPanel_BottomZone.add(jPanel_OtherOptions, BorderLayout.SOUTH);
  }

  private void setUpContainer() {
    jPanel_container.setLayout(new BorderLayout());
    jPanel_container.setBackground(ColorsHandling.PRIMARY_COLOR);
    jPanel_container.setBorder(containerBorder);
    jPanel_container.add(jPanel_TopZone, BorderLayout.NORTH);
    jPanel_container.add(jPanel_MiddleZone, BorderLayout.CENTER);
    jPanel_container.add(jPanel_BottomZone, BorderLayout.SOUTH);

    this.add(jPanel_container);
  }

  private void doAction() {
    ac = new LoginFormController(dataHandler);
    jMenuItem_AboutMe.addActionListener(new Info());
    jButton_PlayButton.addActionListener(new PlayController(this));
    jButton_LoginButton.addActionListener(ac);
    jButton_OtherOption.addActionListener(new ClickOtherOption());
    jButton_PlayWithoutSignIn.addActionListener(new PlayController(this));
    jTextField_Password.addActionListener(new PressEnter());
  }

  @Override
  public void dispose() {
    super.dispose();
  }
}
