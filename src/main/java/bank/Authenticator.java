package bank;

import javax.security.auth.login.LoginException;

public class Authenticator {

  public static Customer login(String username, String password) throws LoginException {
    Customer customer = DataSource.getCustomer(username);
    if(customer == null) {
      throw new LoginException("Username not found!");
    }

    if(password.equals(customer.getPassword())) {
      System.out.println("You are successfully logged in");
      customer.setAuthenticated(true);
    } else {
      throw new LoginException("Password does not match!");
    }

    return customer;
  }

  public static void logout(Customer customer) {
    // check if customer logged in?
    // if not throw exception of unkown error
    // if yes, then logut
    customer.setAuthenticated(false);
  }
}
