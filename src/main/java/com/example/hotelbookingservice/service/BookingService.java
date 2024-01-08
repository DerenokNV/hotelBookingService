package com.example.hotelbookingservice.service;

import com.example.hotelbookingservice.entity.Booking;
import com.example.hotelbookingservice.web.dto.PagesRequest;

import java.util.List;

public interface BookingService {

  List<Booking> findAll( PagesRequest pagesRequest );

  Booking findById( Long id );

  Booking save( Booking booking );

  Booking update( Booking booking );

  void deleteById( Long id );

}
