package com.griddynamics.restapp.repositories;

import com.griddynamics.hotelmodel.rooms.Properties;
import com.griddynamics.hotelmodel.rooms.Room;
import com.griddynamics.hotelmodel.rooms.Type;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface HotelDbImplRepo extends JpaRepository<Room, Integer>, HotelRepository {

  Collection<? extends Room> findAllByType(Type type);
  Collection<Room> findAllByBookedFalse();
  List<Room> findAllByRoomPropertiesContaining(List<Properties> properties);
}
