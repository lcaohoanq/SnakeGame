package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import constants.Message;
import model.Account;
import utils.DataHandler;
import view.LoginFormView;

public class LoginFormController implements ActionListener {

  private DataHandler dataHandler;

  public LoginFormController(DataHandler dataHandler) {
    this.dataHandler = dataHandler;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String username = LoginFormView.jTextField_Username.getText();
    String password = LoginFormView.jTextField_Password.getText();

    if (isEmpty(username, password)) {
      Message.IS_EMPTY_USERNAME_OR_PASSWORD();
    } else {
      if (!isMatching(username, password)) {
        Message.IS_WRONG_USERNAME_OR_PASSWORD();
      } else {
        Message.IS_LOGIN_SUCCESS();
        Message.IS_WELLCOME(username);
        LoginFormView.jButton_PlayButton.setEnabled(true);
        System.out.println("Login success: " + DataHandler.accountList.toString());
      }
    }
  }

  private boolean isEmpty(String username, String password) {
    return username.isEmpty() || password.isEmpty();
  }

  private boolean isMatching(String username, String password) {
    for (Account item : DataHandler.accountList) {
      if (item.getUsername().equals(username) && item.getPassword().equals(password)) {
        return true;
      }
    }
    return false;
  }
}
