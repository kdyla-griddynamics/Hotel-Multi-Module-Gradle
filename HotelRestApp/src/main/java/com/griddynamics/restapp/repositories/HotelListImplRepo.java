package com.griddynamics.restapp.repositories;

import com.griddynamics.hotelmodel.hotel.Hotel;
import com.griddynamics.hotelmodel.hotel.HotelBuilder;
import com.griddynamics.hotelmodel.hotel.InvalidBuilderInputException;
import com.griddynamics.hotelmodel.menu.AdminFunctions;
import com.griddynamics.hotelmodel.menu.IncorrectBookingDatesException;
import com.griddynamics.hotelmodel.menu.UserFunctions;
import com.griddynamics.hotelmodel.rooms.Properties;
import com.griddynamics.hotelmodel.rooms.Room;
import com.griddynamics.hotelmodel.rooms.Type;
import com.griddynamics.hotelmodel.users.User;
import com.griddynamics.restapp.controllers.UserController;
import java.util.Collection;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class HotelListImplRepo implements HotelRepository {

  private static Logger logger = LogManager.getLogger(HotelListImplRepo.class);
  private final Hotel hotel;
  private UserFunctions userFunctions;
  private AdminFunctions adminFunctions;

  HotelListImplRepo() throws InvalidBuilderInputException {
    this.hotel = new HotelBuilder().withOneBedrooms(5,1)
        .withStandardRooms(5,2)
        .withPenthouses(5,3)
        .build();
    this.userFunctions = new UserFunctions(this.hotel);
    this.adminFunctions = new AdminFunctions(this.hotel);
  }

  @Override
  public Collection<? extends Room> findAllByType(Type type) {
    return getUserFunctions().filterByType(type);
  }

  @Override
  public Collection<Room> findAllByBookedFalse() {
    return getUserFunctions().checkIfAvailable();
  }

  @Override
  public List<Room> findAllByRoomPropertiesContaining(List<Properties> properties) {
    return getUserFunctions().filterByProperty(properties);
  }

  @Override
  public Room book(int number, User user, String from, String until) {
    try {
      return getUserFunctions().book(number, user,
          from, until).orElse(null);
    } catch (IncorrectBookingDatesException e) {
      logger.error(e.getMessage());
    }
    return null;
  }

  @Override
  public Room update(int number, User user, String from, String until) {
    try {
      return getUserFunctions().updateBooking(number, user,
          from, until).orElse(null);
    } catch (IncorrectBookingDatesException e) {
      logger.error(e.getMessage());
    }
    return null;
  }
}
