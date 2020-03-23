package com.griddynamics.hotelmodel.rooms;

import static com.griddynamics.hotelmodel.rooms.Properties.BALCONY;

import com.griddynamics.hotelmodel.menu.UserFunctions;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Abstract class that contains the most relevant information
 * that all child classes use.
 * It is used as a type in {@link com.griddynamics.hotelmodel.hotel.Hotel} class lists of rooms
 * and in {@link UserFunctions} methods.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@MappedSuperclass
@Entity
@Table
public class Room {

  private static Logger logger = LogManager.getLogger(Room.class);
  @Id
  int number;
  @Column
  int floor;
  @Column
  int price;
  @Column
  double totalSize;
  @Column
  boolean isBooked;
  @Column
  LocalDate bookedFrom;
  @Column
  LocalDate bookedUntil;
  @ElementCollection
      @CollectionTable(name = "room_properties", joinColumns = @JoinColumn(name = "room_id"))
      @Column(name = "property")
  List<Properties> roomProperties = new ArrayList<>();

  /**
   * Constructor that accepts room number and floor number and sets
   * the room status as not booked. It also adds a room property
   * 'Balcony' to the list of properties.
   *
   * @param number sets a number of the room.
   *               {@link com.griddynamics.hotelmodel.hotel.HotelBuilder}
   *               methods automatically assign a consecutive value on given floor.
   * @param floor  sets a number of the floor on which the room is being set.
   */
  public Room(int number, int floor) {
    this.number = number;
    this.floor = floor;
    this.isBooked = false;
    this.bookedFrom = null;
    this.bookedUntil = null;
    roomProperties.add(BALCONY);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null) {
      return false;
    }
    Room room = (Room) o;
    return getNumber() == room.getNumber()
        && getFloor() == room.getFloor()
        && getPrice() == room.getPrice()
        && Double.compare(room.getTotalSize(), getTotalSize()) == 0
        && isBooked() == room.isBooked()
        && getRoomProperties().equals(room.getRoomProperties());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getNumber(), getFloor(), getPrice(),
        getTotalSize(), isBooked(), getRoomProperties());
  }
}

