package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

import model.Account;

public class DataHandler {

  public static ArrayList<Account> accountList = new ArrayList<>();

  public boolean readFile(String url) {
    try {
      FileReader fr = new FileReader(url);
      BufferedReader br = new BufferedReader(fr);
      String line = "";
      while ((line = br.readLine()) != null) {
        StringTokenizer stk = new StringTokenizer(line, " ");
        String username = stk.nextToken();
        String password = stk.nextToken();
        accountList.add(new Account(username, password));

        // String[] arr = line.split(" ");
        // String username = arr[0];
        // String password = arr[1];
        // registerFormView.accountList.add(new Account(username, password));
      }
      br.close();
      fr.close();
      return true;
    } catch (Exception e) {
      System.out.println("Loi doc file: " + e);
    }
    return false;
  }

  public void writeFile(String url) {
    try {
      File file = new File(url);
      if (!file.exists()) {
        file.createNewFile();
      }
      FileWriter fileWriter = new FileWriter(file);
      BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
      for (Account item : accountList) {
        bufferedWriter.write(item.getUsername() + " " + item.getPassword());
        bufferedWriter.newLine();
      }
      bufferedWriter.close();

      // BufferedWriter BufferedWriter = new BufferedWriter(new FileWriter(url));
      // for (Account item : accountList) {
      // BufferedWriter.write(item.getUsername() + " " + item.getPassword());
      // BufferedWriter.newLine();
      // }
      // BufferedWriter.close();
    } catch (Exception e) {
      System.out.println("Loi write file" + e);
    }
  }
}
