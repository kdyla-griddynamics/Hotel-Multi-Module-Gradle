package com.griddynamics.hotelmodel.users;

import java.util.Collection;
import java.util.HashSet;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Users {

  static Collection<User> allUsers = new HashSet<>();

  /**
   * Factory method that returns an {@link com.griddynamics.hotelmodel.users.User}
   * object from the set, if one with provided first name and last name already exists
   * or create a new one.
   *
   * @param firstName specifies the first name of the {@link com.griddynamics.hotelmodel.users.User}
   * @param lastName  specifies the last name of the {@link com.griddynamics.hotelmodel.users.User}
   * @return an {@link com.griddynamics.hotelmodel.users.User} object
   */
  public static User getUser(String firstName, String lastName) {
    for (User user : allUsers) {
      if (user.getFirstName().equals(firstName) && user.getLastName().equals(lastName)) {
        return user;
      }
    }
    User newUser = new User();
    newUser.setFirstName(firstName);
    newUser.setLastName(lastName);
    allUsers.add(newUser);
    return newUser;
  }
}

