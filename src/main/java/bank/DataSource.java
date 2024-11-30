package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataSource {

  public static Connection connect() {
    Connection connection = null;
    String db_url = "jdbc:sqlite:resources/bank.db";
    try{
      connection = DriverManager.getConnection(db_url);
      System.out.println("Successfully connect to bank database");
    } catch(SQLException e) {
      e.printStackTrace();
    }

    return connection;
  }

  public static Account getAccount(int id) {
    String query = "Select * from accounts where id = ?";
    Account account = null;

    try (
      Connection connection = connect();
      PreparedStatement ps = connection.prepareStatement(query);
    ) {
      
      ps.setInt(1, id);
      try (ResultSet rs = ps.executeQuery()) {
        account = new Account(
          rs.getInt("id"), 
          rs.getString("type"),
          rs.getDouble("balance")
          );
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return account;
  }

  public static Customer getCustomer(String username) {

    String query = "Select * from customers where username = ?";
    Customer customer = null;

    try (
      Connection connection = connect();
      PreparedStatement ps = connection.prepareStatement(query);
      ) {
      
        ps.setString(1, username);
        try (ResultSet rs = ps.executeQuery()) {
          customer = new Customer(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getString("username"),
            rs.getString("password"),
            rs.getInt("account_id")
          );
        }

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return customer;

  }

  public static void main(String[] args) {
    Customer hatim = getCustomer("rcharville3k@ovh.net");
    System.out.println(hatim.getName());

    Account hatimAcc = getAccount(hatim.getAccountId());
    System.out.println(hatimAcc.getBalance());
  }
}
