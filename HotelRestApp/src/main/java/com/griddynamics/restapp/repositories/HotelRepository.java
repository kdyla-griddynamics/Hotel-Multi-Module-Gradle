package com.griddynamics.restapp.repositories;

import com.griddynamics.hotelmodel.rooms.Room;
import com.griddynamics.hotelmodel.users.User;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public interface HotelRepository {

  Logger logger = LogManager.getLogger(HotelRepository.class);

  List<Room> findAll();

  Room findByNumber(int number);

  List<Room> findAllByType(String type);

  List<Room> getAllByBookedFalse();

  Room save(Room room);

  void deleteByNumber(int number);

  default Optional<Room> book(Room room, User user, String dateFrom, String dateUntil) {
    LocalDate from = LocalDate.parse(dateFrom);
    LocalDate until = LocalDate.parse(dateUntil);
    if (room.isBooked()) {
      logger.error("User tried to book already booked room");
      return Optional.empty();
    } else {
      if (until.isAfter(from) && user.getBookedRooms().size() < 2) {
        room.setBooked(true);
        user.getBookedRooms().add(room);
        room.setBookedFrom(from);
        room.setBookedUntil(until);
        return Optional.of(room);
      } else {
        logger.error("Room has not been booked: " +
            "either the dates were incorrect or user already booked more than two rooms");
        return Optional.empty();
      }
    }
  }

  default Optional<Room> updateBook(Room room, String dateFrom, String dateUntil) {
    LocalDate from = LocalDate.parse(dateFrom);
    LocalDate until = LocalDate.parse(dateUntil);
    if (room.isBooked()) {
      if (until.isAfter(from)) {
        room.setBookedFrom(from);
        room.setBookedUntil(until);
        return Optional.of(room);
      } else {
        logger.error("Booking was not updated, because dates were incorrect");
        return Optional.empty();
      }
    } else {
      logger.error("Booking was not updated, because the room was not booked");
      return Optional.empty();
    }
  }
}
