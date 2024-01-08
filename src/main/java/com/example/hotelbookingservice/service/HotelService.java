package com.example.hotelbookingservice.service;

import com.example.hotelbookingservice.entity.Hotel;
import com.example.hotelbookingservice.web.dto.PagesRequest;
import com.example.hotelbookingservice.web.dto.hotel.HotelFilterRequest;

import java.util.List;

public interface HotelService {

  List<Hotel> filterBy( HotelFilterRequest filter );

  List<Hotel> findAll( PagesRequest pagesRequest );

  Hotel findById( Long id );

  Hotel save( Hotel hotel );

  Hotel update( Hotel hotel );

  void deleteById( Long id );

  void updateHotelRating( Long id, Integer newMark );

}
