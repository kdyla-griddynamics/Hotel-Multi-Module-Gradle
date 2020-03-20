package com.griddynamics.hotelmodel.hotel;

import com.griddynamics.hotelmodel.rooms.OneBedroom;
import com.griddynamics.hotelmodel.rooms.Penthouse;
import com.griddynamics.hotelmodel.rooms.Room;
import com.griddynamics.hotelmodel.rooms.Standard;
import java.util.ArrayList;
import java.util.Collection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class that is the main entity used to store lists of different type of rooms.
 * It is created by {@link com.griddynamics.hotelmodel.hotel.HotelBuilder} class.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Hotel {

  private static Logger logger = LogManager.getLogger(Hotel.class);
  private final Collection<OneBedroom> oneBedroomList = new ArrayList<>();
  private final Collection<Standard> standardList = new ArrayList<>();
  private final Collection<Penthouse> penthouseList = new ArrayList<>();
  private final Collection<Room> allRooms = new ArrayList<>();

  Hotel(Collection<OneBedroom> oneBedroomList, Collection<Standard> standardList,
        Collection<Penthouse> penthouseList) {
    this.oneBedroomList.addAll(oneBedroomList);
    this.standardList.addAll(standardList);
    this.penthouseList.addAll(penthouseList);
    allRooms.addAll(oneBedroomList);
    allRooms.addAll(standardList);
    allRooms.addAll(penthouseList);
  }
}

