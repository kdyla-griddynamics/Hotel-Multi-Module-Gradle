package com.griddynamics.restapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.griddynamics.restapp.repositories")
@EntityScan("com.griddynamics.hotelmodel.rooms")
public class HotelRestAppApplication extends SpringBootServletInitializer {

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(HotelRestAppApplication.class);
  }

  public static void main(String[] args) {
    SpringApplication.run(HotelRestAppApplication.class, args);
  }

}
