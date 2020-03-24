package com.griddynamics.restapp.repositories;

import com.griddynamics.hotelmodel.rooms.Room;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface HotelRepository {

  List<Room> findAll();

  Room findByNumber(int number);

  List<Room> findAllByType(String type);

  List<Room> getAllByBookedFalse();

  Room save(Room room);

  void deleteByNumber(int number);
}
