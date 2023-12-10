package runtime;

public enum Message {
  IS_DUPLICATE_USERNAME("This username is already taken!"),
  IS_EMPTY_USERNAME("Username is empty!"),
  IS_EMPTY_PASSWORD("Password is empty!"),
  IS_EMPTY_USERNAME_OR_PASSWORD("Username or password is empty!"),
  IS_SIGNUP_SUCCESSFULLY("Sign up successfully!");

  public final String message;

  Message(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }


}
