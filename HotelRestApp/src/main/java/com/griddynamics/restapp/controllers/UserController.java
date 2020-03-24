package com.griddynamics.restapp.controllers;

import com.griddynamics.hotelmodel.rooms.Properties;
import com.griddynamics.hotelmodel.rooms.Room;
import com.griddynamics.hotelmodel.users.User;
import com.griddynamics.hotelmodel.users.Users;
import com.griddynamics.restapp.repositories.HotelRepository;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
  private HotelRepository hotelRepository;

  @Autowired
  UserController(@Qualifier(value = "hotelDatabaseImplRepo") HotelRepository hotelRepository) {
    this.hotelRepository = hotelRepository;
  }

  @GetMapping("/rooms")
  public Collection<? extends Room> showRoomsByType(@RequestParam(name = "type") String type) {
    if (type.equals("all")) {
      return hotelRepository.findAll();
    } else {
      return hotelRepository.findAllByType(type);
    }
  }

  @GetMapping("/available")
  public Collection<Room> showAvailableRooms() {
    return hotelRepository.getAllByBookedFalse();
  }

  @GetMapping("/properties")
  public Collection<? extends Room> showRoomsByProperties(@RequestParam
                                                              List<Properties> property) {
    List<Room> roomsToFilter = hotelRepository.findAll();
    return roomsToFilter.stream()
        .filter(room -> room.getRoomProperties().containsAll(property))
        .collect(Collectors.toList());
  }

  @PostMapping("/book")
  public Room bookRoom(@RequestParam(name = "number") int number,
                       @RequestParam(name = "from") String from,
                       @RequestParam(name = "until") String until) {
    User user = getUserFromAuthentication();
    Room roomToBook = hotelRepository.findByNumber(number);
    LocalDate dateFrom = LocalDate.parse(from);
    LocalDate dateUntil = LocalDate.parse(until);
    if (roomToBook.isBooked()) {
      logger.error("User tried to book already booked room");
      return null;
    } else {
      if (dateUntil.isAfter(dateFrom) && user.getBookedRooms().size() < 2) {
        roomToBook.setBooked(true);
        user.getBookedRooms().add(roomToBook);
        roomToBook.setBookedFrom(dateFrom);
        roomToBook.setBookedUntil(dateUntil);
        return hotelRepository.save(roomToBook);
      } else {
        logger.error("Room has not been booked: " +
            "either the dates were incorrect or user already booked more than two rooms");
        return null;
      }
    }
  }

  @PutMapping("/update")
  public Room updateBooking(@RequestParam(name = "number") int number,
                            @RequestParam(name = "from") String from,
                            @RequestParam(name = "until") String until) {
    Room roomToBook = hotelRepository.findByNumber(number);
    System.out.println(roomToBook);
    LocalDate dateFrom = LocalDate.parse(from);
    LocalDate dateUntil = LocalDate.parse(until);
    if (roomToBook.isBooked()) {
      if (dateUntil.isAfter(dateFrom)) {
        roomToBook.setBookedFrom(dateFrom);
        roomToBook.setBookedUntil(dateUntil);
        return hotelRepository.save(roomToBook);
      } else {
        logger.error("Booking was not updated, because dates were incorrect");
        return null;
      }
    } else {
      logger.error("Booking was not updated, because the room was not booked");
      return null;
    }
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
