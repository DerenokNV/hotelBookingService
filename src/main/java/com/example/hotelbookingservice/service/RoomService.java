package com.example.hotelbookingservice.service;

import com.example.hotelbookingservice.entity.Room;
import com.example.hotelbookingservice.web.dto.room.RoomFilterRequest;


import java.util.List;

public interface RoomService {

  List<Room> filterBy( RoomFilterRequest filter );
  Room findById( Long id );

  Room save( Room room );

  Room update( Room room );

  void deleteById( Long id );

}
