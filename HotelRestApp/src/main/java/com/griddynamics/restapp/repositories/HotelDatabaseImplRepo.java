package com.griddynamics.restapp.repositories;

import com.griddynamics.hotelmodel.rooms.Room;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface HotelDatabaseImplRepo extends JpaRepository<Room, Integer>, HotelRepository {
  @Override
  List<Room> findAllByType(String type);

  @Override
  List<Room> getAllByBookedFalse();

  //  @Override
//  List<Room> findAllByBookedFalse();

//  @Override
//  List<Room> findAllByRoomProperties(List<Properties> properties);

//  @Override
//  Room book(int number, User user, String dateFrom, String dateUntil);
//
//  @Override
//  Room updateBook(int number, User user, String dateFrom, String dateUntil);

  @Override
  Room save(Room room);

  @Override
  void deleteByNumber(int number);
}
