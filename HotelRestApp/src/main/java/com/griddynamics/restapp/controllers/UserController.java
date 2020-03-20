package com.griddynamics.restapp.controllers;

import com.griddynamics.hotelmodel.menu.IncorrectBookingDatesException;
import com.griddynamics.hotelmodel.rooms.Room;
import com.griddynamics.hotelmodel.users.User;
import com.griddynamics.hotelmodel.users.Users;
import com.griddynamics.restapp.HotelProvider;
import java.util.Collection;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hotel")
public class UserController {

  private static Logger logger = LogManager.getLogger(UserController.class);
  private HotelProvider hotelProvider;

  @Autowired
  UserController(HotelProvider hotelProvider) {
    this.hotelProvider = hotelProvider;
  }

  @GetMapping("/rooms")
  public Collection<? extends Room> showRoomsByType(@RequestParam(name = "type") String type) {
    return hotelProvider.getUserFunctions().filterByType(type);
  }

  @GetMapping("/available")
  public Collection<Room> showAvailableRooms() {
    return hotelProvider.getUserFunctions().checkIfAvailable();
  }

  @GetMapping("/properties")
  public Collection<? extends Room> showRoomsByProperties(@RequestParam(name = "property")
                                                              List<String> property) {
    return hotelProvider.getUserFunctions().filterByProperty(property);
  }

  @PostMapping("/book")
  public Room bookRoom(@RequestParam(name = "number") int number,
                       @RequestParam(name = "from") String from,
                       @RequestParam(name = "until") String until) {
    User user = getUserFromAuthentication();
    try {
      return hotelProvider.getUserFunctions().book(number, user,
          from, until).orElse(null);
    } catch (IncorrectBookingDatesException e) {
      logger.error(e.getMessage());
    }
    return null;
  }

  @PutMapping("/update")
  public Room updateBooking(@RequestParam(name = "number") int number,
                            @RequestParam(name = "from") String from,
                            @RequestParam(name = "until") String until) {
    User user = getUserFromAuthentication();
    try {
      return hotelProvider.getUserFunctions().updateBooking(number, user,
          from, until).orElse(null);
    } catch (IncorrectBookingDatesException e) {
      logger.error(e.getMessage());
    }
    return null;
  }

  @GetMapping("/booked")
  public User showRoomsBookedByUser() {
    return getUserFromAuthentication();
  }

  private User getUserFromAuthentication() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String currentName = authentication.getName();
    int splitIndex = currentName.indexOf("_");
    String firstName = currentName.substring(0, splitIndex);
    String lastName = currentName.substring(splitIndex + 1);
    return Users.getUser(firstName, lastName);
  }
}
