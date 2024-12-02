package bank;

import java.util.Scanner;

import javax.security.auth.login.LoginException;

public class Menu {
  Scanner scanner;

  Customer authenticateUser() {
    System.out.println("Enter your username");
    String username = scanner.next();
    System.out.println("Enter your password");
    String password = scanner.next();

    Customer customer = null;
    try {
      customer = Authenticator.login(username, password);
    } catch (LoginException e) {
      System.out.println("There is an error : " + e.getMessage());
    }

    return customer;
  }

  void showMenu(Customer customer, Account account) {
    int option = 0;

    while(option != 4 && customer.isAuthenticated()) {
      System.out.println("============================================");
      System.out.println("Select one of the following options");
      System.out.println("1: Deposit");
      System.out.println("2: Withdraw");
      System.out.println("3: Check Balance");
      System.out.println("4: Logout");
      System.out.println("============================================");

      option = scanner.nextInt();
      double amount = 0;

      switch (option) {
        case 1:
          System.out.println("Enter the amount you want to deposit");
          amount = scanner.nextDouble();
          account.deposit(amount);
          break;

        case 2:
          System.out.println("Enter the amount you want to withdraw");
          amount = scanner.nextDouble();
          account.withdraw(amount);
          break;
          
        case 3:
          account.showBalance();
          break;
          
        case 4:
          System.out.println("Logging out...");
          Authenticator.logout(customer);
          System.out.println("Thanks for banking with us!");
          break;
      
        default:
          System.out.println("This option is not available yet");
          break;
      }
    }
  }
}
