package com.griddynamics.restapp.controllers;

import com.griddynamics.hotelmodel.rooms.Room;
import com.griddynamics.restapp.repositories.HotelListImplRepo;
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

  private HotelListImplRepo hotelListImplRepo;

  @Autowired
  AdminController(HotelListImplRepo hotelListImplRepo) {
    this.hotelListImplRepo = hotelListImplRepo;
  }

  @PostMapping("/add")
  public Room addRoom(@RequestBody Room room) {
    return hotelListImplRepo.getAdminFunctions().addRoom(room).orElse(null);
  }

  @PutMapping("/update")
  public Room updateRoom(@RequestBody Room room) {
    return hotelListImplRepo.getAdminFunctions().updateRoom(room).orElse(null);
  }

  @DeleteMapping("/delete")
  public Room deleteRoom(@RequestParam(name = "number") int number) {
    return hotelListImplRepo.getAdminFunctions().deleteRoom(number).orElse(null);
  }
}
