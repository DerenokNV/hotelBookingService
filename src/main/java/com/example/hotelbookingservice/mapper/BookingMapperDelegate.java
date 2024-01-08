package com.example.hotelbookingservice.mapper;

import com.example.hotelbookingservice.entity.Booking;
import com.example.hotelbookingservice.entity.Room;
import com.example.hotelbookingservice.service.RoomService;
import com.example.hotelbookingservice.web.dto.booking.BookingRequest;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BookingMapperDelegate implements BookingMapper{

  @Autowired
  private RoomService roomService;

  @Override
  public Booking requestToModel( BookingRequest request ) {
    Booking booking = new Booking();
    booking.setDtBegin( request.getDtBegin() );
    booking.setDtEnd( request.getDtEnd() );
    Room room = roomService.findById( request.getRoomId() );
    booking.setRoomBooking( room );

    return booking;
  }

  @Override
  public Booking requestToModel( Long updateId, BookingRequest request ) {
    Booking booking = requestToModel( request );
    booking.setId( updateId );

    return booking;
  }
}
