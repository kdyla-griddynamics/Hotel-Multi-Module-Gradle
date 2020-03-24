package com.griddynamics.hotelmodel.menu;

import com.griddynamics.hotelmodel.hotel.Hotel;
import com.griddynamics.hotelmodel.rooms.OneBedroom;
import com.griddynamics.hotelmodel.rooms.Penthouse;
import com.griddynamics.hotelmodel.rooms.Room;
import com.griddynamics.hotelmodel.rooms.Standard;
import java.util.Optional;
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
public class AdminFunctions {

  private static Logger logger = LogManager.getLogger(AdminFunctions.class);
  private Hotel hotel;

  public AdminFunctions(Hotel hotel) {
    this.hotel = hotel;
  }

  public Optional<Room> save(Room room) {
    Optional<Room> roomToAdd = hotel.getAllRooms().stream()
        .filter(r -> r.getNumber() == room.getNumber())
        .findFirst();

    if (roomToAdd.isEmpty()) {
      int roomNumber = room.getNumber();
      int roomFloor = room.getFloor();
      if (room.equals(new OneBedroom(roomNumber, roomFloor))) {
        hotel.getOneBedroomList().add(new OneBedroom(room));
      } else if (room.equals(new Standard(roomNumber, roomFloor))) {
        hotel.getStandardList().add(new Standard(room));
      } else if (room.equals(new Penthouse(roomNumber, roomFloor))) {
        hotel.getPenthouseList().add(new Penthouse(room));
      }
      hotel.getAllRooms().add(room);
      return Optional.of(room);
    } else {
      int roomNumber = roomToAdd.get().getNumber();
      hotel.getOneBedroomList().stream()
          .filter(oneBedroom -> oneBedroom.getNumber() == roomNumber)
          .findAny()
          .ifPresent(oneBedroom -> {
            hotel.getOneBedroomList().remove(oneBedroom);
            hotel.getOneBedroomList().add(new OneBedroom(room));
          });
      hotel.getStandardList().stream()
          .filter(standard -> standard.getNumber() == roomNumber)
          .findAny()
          .ifPresent(standard -> {
            hotel.getStandardList().remove(standard);
            hotel.getStandardList().add(new Standard(room));
          });
      hotel.getPenthouseList().stream()
          .filter(penthouse -> penthouse.getNumber() == roomNumber)
          .findAny()
          .ifPresent(penthouse -> {
            hotel.getPenthouseList().remove(penthouse);
            hotel.getPenthouseList().add(new Penthouse(room));
          });
      hotel.getAllRooms().remove(roomToAdd.get());
      hotel.getAllRooms().add(room);
      return Optional.of(room);
    }
  }

  public void deleteRoom(int number) {
    Optional<Room> roomToDelete = hotel.getAllRooms().stream()
        .filter(room -> room.getNumber() == number)
        .findFirst();

    if (roomToDelete.isPresent()) {
      hotel.getOneBedroomList().stream()
          .filter(oneBedroom -> oneBedroom.getNumber() == number)
          .findAny()
          .ifPresent(oneBedroom -> hotel.getOneBedroomList().remove(oneBedroom));
      hotel.getStandardList().stream()
          .filter(standard -> standard.getNumber() == number)
          .findAny()
          .ifPresent(standard -> hotel.getStandardList().remove(standard));
      hotel.getPenthouseList().stream()
          .filter(penthouse -> penthouse.getNumber() == number)
          .findAny()
          .ifPresent(penthouse -> hotel.getPenthouseList().remove(penthouse));
      hotel.getAllRooms().remove(roomToDelete.get());
    }
  }
}
