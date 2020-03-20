package com.griddynamics.restapp.controllers;

import com.griddynamics.hotelmodel.rooms.Room;
import com.griddynamics.restapp.HotelProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

  private HotelProvider hotelProvider;

  @Autowired
  AdminController(HotelProvider hotelProvider) {
    this.hotelProvider = hotelProvider;
  }

  @PostMapping("/add")
  public Room addOneBedroom(@RequestBody Room room) {
    return hotelProvider.getAdminFunctions().addRoom(room).orElse(null);
  }

  @PutMapping("/update")
  public Room updateOneBedroom(@RequestBody Room room) {
    return hotelProvider.getAdminFunctions().updateRoom(room).orElse(null);
  }

  @DeleteMapping("/delete")
  public Room deleteRoom(@RequestParam(name = "number") int number) {
    return hotelProvider.getAdminFunctions().deleteRoom(number).orElse(null);
  }
}
