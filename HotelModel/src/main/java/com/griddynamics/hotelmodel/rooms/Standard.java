package com.griddynamics.hotelmodel.rooms;

import static com.griddynamics.hotelmodel.rooms.Properties.REFRIGERATOR;
import static com.griddynamics.hotelmodel.rooms.Properties.TV;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class that extends {@link com.griddynamics.hotelmodel.rooms.Room}
 * abstract class and adds fields
 * that are specific to this type of room.
 */
@Getter
@Setter
@NoArgsConstructor
public class Standard extends Room {

  private static Logger logger = LogManager.getLogger(Standard.class);

  /**
   * Constructor that accepts room number and floor number and sets
   * the room status as not booked. It sets the price and the total size
   * of this kind of room. It also adds room properties
   * 'TV' and 'Refrigerator' to the list of properties.
   *
   * @param number sets a number of the room.
   *               {@link com.griddynamics.hotelmodel.hotel.HotelBuilder}
   *               methods automatically assign a consecutive value on given floor.
   * @param floor  sets a number of the floor on which the room is being set.
   */
  public Standard(int number, int floor) {
    super(number, floor);
    this.price = 280;
    this.totalSize = 20;
    roomProperties.add(TV);
    roomProperties.add(REFRIGERATOR);
  }

  public Standard(Room room) {
    this.number = room.getNumber();
    this.floor = room.getFloor();
    this.price = room.getPrice();
    this.totalSize = room.getTotalSize();
    this.isBooked = room.isBooked();
    this.bookedFrom = room.getBookedFrom();
    this.bookedUntil = room.getBookedUntil();
    this.roomProperties = room.getRoomProperties();
  }

  @Override
  public String toString() {
    return "Standard {"
        + "number: " + number
        + ", floor: " + floor
        + '}';
  }
}

