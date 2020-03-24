package com.griddynamics.restapp.controllers;

import com.griddynamics.hotelmodel.rooms.Room;
import com.griddynamics.hotelmodel.users.User;
import com.griddynamics.hotelmodel.users.Users;
import com.griddynamics.restapp.repositories.HotelRepository;
import java.util.Collection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hotel")
public class UserController {

  private static Logger logger = LogManager.getLogger(UserController.class);
  private HotelRepository hotelRepository;

  @Autowired
  UserController(@Qualifier(value = "hotelDatabaseImplRepo") HotelRepository hotelRepository) {
    this.hotelRepository = hotelRepository;
  }

  @GetMapping("/rooms")
  public Collection<? extends Room> showRoomsByType(@RequestParam(name = "type") String type) {
    return hotelRepository.findAllByType(type);
  }

  @GetMapping("/available")
  public Collection<Room> showAvailableRooms() {
    return hotelRepository.getAllByBookedFalse();
  }

//  @GetMapping("/properties")
//  public Collection<? extends Room> showRoomsByProperties(@RequestParam(name = "property")
//                                                              List<Properties> property) {
//    return hotelRepository.findAllByRoomProperties(property);
//  }

//  @PostMapping("/book")
//  public Room bookRoom(@RequestParam(name = "number") int number,
//                       @RequestParam(name = "from") String from,
//                       @RequestParam(name = "until") String until) {
//    User user = getUserFromAuthentication();
//    return hotelRepository.book(number, user, from, until);
//  }
//
//  @PutMapping("/update")
//  public Room updateBooking(@RequestParam(name = "number") int number,
//                            @RequestParam(name = "from") String from,
//                            @RequestParam(name = "until") String until) {
//    User user = getUserFromAuthentication();
//    return hotelRepository.updateBook(number, user, from, until);
//  }

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
