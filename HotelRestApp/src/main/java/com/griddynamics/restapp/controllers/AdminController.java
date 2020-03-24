package com.griddynamics.restapp.controllers;

import com.griddynamics.hotelmodel.rooms.Room;
import com.griddynamics.restapp.repositories.HotelDatabaseImplRepo;
import com.griddynamics.restapp.repositories.HotelListImplRepo;
import com.griddynamics.restapp.repositories.HotelRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

  private static Logger logger = LogManager.getLogger(AdminController.class);
  private HotelRepository hotelRepository;

  @Autowired
  AdminController(@Qualifier(value = "hotelDatabaseImplRepo") HotelRepository hotelRepository) {
    this.hotelRepository = hotelRepository;
  }

  @PostMapping("/add")
  public Room addRoom(@RequestBody Room room) {
    return hotelRepository.save(room);
  }

  @PutMapping("/update")
  public Room updateRoom(@RequestBody Room room) {
    return hotelRepository.save(room);
  }

  @DeleteMapping("/delete")
  public void deleteRoom(@RequestParam(name = "number") int number) {
    hotelRepository.deleteByNumber(number);
  }
}
