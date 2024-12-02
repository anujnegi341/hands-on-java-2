package bank;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {

    System.out.println("Welcome to Globe Internationl Bank");

    Menu menu = new Menu();
    menu.scanner = new Scanner(System.in);

    Customer customer = menu.authenticateUser();

    if(customer != null) {
      Account account = DataSource.getAccount(customer.getAccountId());
      menu.showMenu(customer, account);
    }

    menu.scanner.close();
  }
}
