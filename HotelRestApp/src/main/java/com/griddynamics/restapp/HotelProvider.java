package com.griddynamics.restapp;

import com.griddynamics.hotelmodel.hotel.Hotel;
import com.griddynamics.hotelmodel.hotel.HotelBuilder;
import com.griddynamics.hotelmodel.hotel.InvalidBuilderInputException;
import com.griddynamics.hotelmodel.menu.AdminFunctions;
import com.griddynamics.hotelmodel.menu.UserFunctions;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class HotelProvider {

  private final Hotel hotel;
  private UserFunctions userFunctions;
  private AdminFunctions adminFunctions;

  HotelProvider() throws InvalidBuilderInputException {
    this.hotel = new HotelBuilder().withOneBedrooms(5,1)
        .withStandardRooms(5,2)
        .withPenthouses(5,3)
        .build();
    this.userFunctions = new UserFunctions(this.hotel);
    this.adminFunctions = new AdminFunctions(this.hotel);
  }
}
