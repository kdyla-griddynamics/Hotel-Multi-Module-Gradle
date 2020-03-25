package com.griddynamics.hotelmodel.rooms;

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
public class OneBedroom extends Room {

  private static Logger logger = LogManager.getLogger(OneBedroom.class);

  /**
   * Constructor that accepts room number and floor number and sets
   * the room status as not booked. It sets the price and the total size
   * of this kind of room. It also adds room property
   * 'TV' to the list of properties.
   *
   * @param number sets a number of the room.
   *               {@link com.griddynamics.hotelmodel.hotel.HotelBuilder}
   *               methods automatically assign a consecutive value on given floor.
   * @param floor  sets a number of the floor on which the room is being set.
   */
  public OneBedroom(int number, int floor) {
    super(number, floor);
    this.type = "onebedroom";
    this.price = 180;
    this.totalSize = 12;
    roomProperties.add(TV);
  }

  public OneBedroom(Room room) {
    this.number = room.getNumber();
    this.floor = room.getFloor();
    this.type = "onebedroom";
    this.price = room.getPrice();
    this.totalSize = room.getTotalSize();
    this.booked = room.isBooked();
    this.bookedFrom = room.getBookedFrom();
    this.bookedUntil = room.getBookedUntil();
    this.roomProperties = room.getRoomProperties();
  }

  @SuppressWarnings("checkstyle:OperatorWrap")
  @Override
  public String toString() {
    return "OneBedroom {"
        + "number: " + number
        + ", floor: " + floor
        + '}';
  }
}

