package bank;

public class Account {
  private int id;
  private String type;
  private double balance;

  public Account(int id, String type, double balance) {
    this.id = id;
    this.type = type;
    this.balance = balance;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public double getBalance() {
    return this.balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public void deposit(double amount) throws IllegalArgumentException {
    if(amount > 0) {
      balance = balance + amount;
      DataSource.updateAccountBalance(id, balance);
      System.out.println("Your new balance is : $" + balance);
    } else {
      throw new IllegalArgumentException("Input deposit amount is not valid");
    }
  }

  public void withdraw(double amount) {
    if (amount > 0 && amount <= balance) {
      balance = balance - amount;
      DataSource.updateAccountBalance(id, balance);
      System.out.println("Your new balance is : $" + balance);
    } else {
      throw new IllegalArgumentException("Input withdrawal amount is not valid");
    }
  }

  public void showBalance() {
    System.out.println("Your current balance is : $" + balance);
  }
}
