package com.griddynamics.restapp.repositories;

import com.griddynamics.hotelmodel.hotel.Hotel;
import com.griddynamics.hotelmodel.hotel.HotelBuilder;
import com.griddynamics.hotelmodel.hotel.InvalidBuilderInputException;
import com.griddynamics.hotelmodel.menu.AdminFunctions;
import com.griddynamics.hotelmodel.menu.UserFunctions;
import com.griddynamics.hotelmodel.rooms.Room;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component(value = "list")
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
  public List<Room> findAllByType(String type) {
    return getUserFunctions().filterByType(type);
  }

  @Override
  public List<Room> getAllByBookedFalse() {
    return getUserFunctions().checkIfAvailable();
  }

//  @Override
//  public List<Room> findAllByBookedFalse() {
//    return getUserFunctions().checkIfAvailable();
//  }

//  @Override
//  public List<Room> findAllByRoomProperties(List<Properties> properties) {
//    return getUserFunctions().filterByProperty(properties);
//  }

//  @Override
//  public Room book(int number, User user, String from, String until) {
//    try {
//      return getUserFunctions().book(number, user,
//          from, until).orElse(null);
//    } catch (IncorrectBookingDatesException e) {
//      logger.error(e.getMessage());
//    }
//    return null;
//  }
//
//  @Override
//  public Room updateBook(int number, User user, String from, String until) {
//    try {
//      return getUserFunctions().updateBooking(number, user,
//          from, until).orElse(null);
//    } catch (IncorrectBookingDatesException e) {
//      logger.error(e.getMessage());
//    }
//    return null;
//  }

  @Override
  public Room save(Room room) {
    return getAdminFunctions().save(room).orElse(null);
  }

  @Override
  public void deleteByNumber(int number) {
    getAdminFunctions().deleteRoom(number);
  }
}
