package com.example.hotelbookingservice.mapper;

import com.example.hotelbookingservice.entity.Room;
import com.example.hotelbookingservice.service.HotelService;
import com.example.hotelbookingservice.web.dto.room.RoomRequest;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class RoomMapperDelegate implements RoomMapper{

  @Autowired
  private HotelService hotelService;

  @Override
  public Room requestToModel( RoomRequest request ) {
    Room room = new Room();
    room.setName( request.getName() );
    room.setDescription( request.getDescription() );
    room.setNumber( request.getNumber() );
    room.setPrice( request.getPrice() );
    room.setMaxPeople( request.getMaxPeople() );
    room.setHotel( hotelService.findById( request.getHotelId() ) );

    return room;
  }

  @Override
  public Room requestToModel( Long updateId, RoomRequest request ) {
    Room room = requestToModel( request );
    room.setId( updateId );

    return room;
  }


}
