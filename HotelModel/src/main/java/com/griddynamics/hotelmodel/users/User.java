package com.griddynamics.hotelmodel.users;

import com.griddynamics.hotelmodel.rooms.Room;
import java.util.ArrayList;
import java.util.Collection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {

  private String firstName;
  private String lastName;
  Collection<Room> bookedRooms = new ArrayList<>();
}
