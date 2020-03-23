package com.griddynamics.restapp.repositories;

import com.griddynamics.hotelmodel.rooms.Properties;
import com.griddynamics.hotelmodel.rooms.Room;
import com.griddynamics.hotelmodel.rooms.Type;
import com.griddynamics.hotelmodel.users.User;
import java.util.Collection;
import java.util.List;

public interface HotelRepository {

  Collection<? extends Room> findAllByType(Type type);

  Collection<Room> findAllByBookedFalse();

  List<Room> findAllByRoomPropertiesContaining(List<Properties> properties);

  Room book(int number, User user, String dateFrom, String dateUntil);

  Room update(int number, User user, String dateFrom, String dateUntil);
}
