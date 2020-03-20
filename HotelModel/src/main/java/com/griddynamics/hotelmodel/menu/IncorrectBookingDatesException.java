package com.griddynamics.hotelmodel.menu;

public final class IncorrectBookingDatesException extends Exception {

  public IncorrectBookingDatesException(String errorMessage) {
    super(errorMessage);
  }
}
