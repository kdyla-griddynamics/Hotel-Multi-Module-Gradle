package com.griddynamics.restapp.repositories;

import com.griddynamics.hotelmodel.rooms.Room;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface HotelRepository {

  List<Room> findAllByType(String type);

  List<Room> getAllByBookedFalse();

//  List<Room> findAllByBookedFalse();

//  List<Room> findAllByRoomProperties(List<Properties> properties);

//  Room book(int number, User user, String dateFrom, String dateUntil);
//
//  Room updateBook(int number, User user, String dateFrom, String dateUntil);

  Room save(Room room);

  void deleteByNumber(int number);
}
