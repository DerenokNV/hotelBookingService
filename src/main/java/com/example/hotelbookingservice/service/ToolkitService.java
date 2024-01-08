package com.example.hotelbookingservice.service;

import com.example.hotelbookingservice.entity.Hotel;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ToolkitService {

  public static Double calcHotelRating( Hotel hotel, Integer newMark ) {
    if ( hotel.getRating() == null ) {
      return Double.valueOf( newMark );
    }

    Double totalRating =  hotel.getRating() * hotel.getCountRating();
    Double rating = round( ( totalRating + newMark ) / ( hotel.getCountRating() + 1 ), 2 );
    return rating;
  }

  private static double round( double value, int places ) {
    if (places < 0) throw new IllegalArgumentException();

    BigDecimal bd = new BigDecimal( Double.toString( value ) );
    bd = bd.setScale(places, RoundingMode.HALF_UP);

    return bd.doubleValue();
  }
}
