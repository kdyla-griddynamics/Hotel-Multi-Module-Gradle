package com.griddynamics.hotelmodel.menu;

import static com.griddynamics.hotelmodel.rooms.Properties.BALCONY;
import static com.griddynamics.hotelmodel.rooms.Properties.JACUZZI;
import static com.griddynamics.hotelmodel.rooms.Properties.MINIBAR;
import static com.griddynamics.hotelmodel.rooms.Properties.REFRIGERATOR;
import static com.griddynamics.hotelmodel.rooms.Properties.TV;

import com.griddynamics.hotelmodel.hotel.Hotel;
import com.griddynamics.hotelmodel.rooms.Properties;
import com.griddynamics.hotelmodel.rooms.Room;
import com.griddynamics.hotelmodel.rooms.Type;
import com.griddynamics.hotelmodel.users.User;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Utility class that provides methods to perform operations
 * on rooms in the hotel during user session.
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserFunctions {

  private static Logger logger = LogManager.getLogger(UserFunctions.class);
  private Hotel hotel;
  static final Properties[] propsList = {BALCONY, TV, REFRIGERATOR, MINIBAR, JACUZZI};

  public UserFunctions(Hotel hotel) {
    this.hotel = hotel;
  }

  static List<Properties> getAllProperties() {
    List<Properties> allProperties = new ArrayList<>();
    Collections.addAll(allProperties, propsList);
    return allProperties;
  }

  public List<Room> filterByProperty(List<Properties> properties) {

    return hotel.getAllRooms().stream()
        .filter(room1 -> room1.getRoomProperties().containsAll(properties))
        .collect(Collectors.toList());
  }

  public Optional<Room> book(int number, User user, String dateFrom, String dateUntil)
      throws IncorrectBookingDatesException {
    Optional<Room> bookedRoom = hotel.getAllRooms().stream()
        .filter(room -> room.getNumber() == number)
        .filter(room -> !room.isBooked())
        .findAny();

    LocalDate from = LocalDate.parse(dateFrom);
    LocalDate until = LocalDate.parse(dateUntil);

    if (until.isBefore(from)) {
      throw new IncorrectBookingDatesException("Date 'until' is before date 'from'");
    }
    if (user.getBookedRooms().size() < 2) {
      bookedRoom.ifPresent(room -> {
        room.setBooked(true);
        user.getBookedRooms().add(room);
        room.setBookedFrom(from);
        room.setBookedUntil(until);
      });
      return bookedRoom;
    } else {
      return Optional.empty();
    }
  }

  public Optional<Room> updateBooking(int number, User user, String dateFrom, String dateUntil)
      throws IncorrectBookingDatesException {
    Optional<Room> bookingToUpdate = user.getBookedRooms().stream()
        .filter(room -> room.getNumber() == number)
        .findAny();

    LocalDate from = LocalDate.parse(dateFrom);
    LocalDate until = LocalDate.parse(dateUntil);

    if (until.isBefore(from)) {
      throw new IncorrectBookingDatesException("Date 'until' is before date 'from'");
    }
    bookingToUpdate.ifPresent(room -> {
      room.setBookedFrom(from);
      room.setBookedUntil(until);
    });
    return bookingToUpdate;
  }

  public Collection<Room> checkIfAvailable() {
    return hotel.getAllRooms().stream()
        .filter(room -> !room.isBooked())
        .collect(Collectors.toList());
  }

  public Collection<? extends Room> filterByType(Type type) {
    switch (type) {
      case ALL:
        return hotel.getAllRooms();
      case ONEBEDROOM:
        return hotel.getOneBedroomList();
      case STANDARD:
        return hotel.getStandardList();
      case PENTHOUSE:
        return hotel.getPenthouseList();
      default:
        logger.info("User chose incorrect type filter menu option");
        return null;
    }
  }
}
