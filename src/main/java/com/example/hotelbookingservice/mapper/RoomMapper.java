package com.example.hotelbookingservice.mapper;

import com.example.hotelbookingservice.entity.Room;
import com.example.hotelbookingservice.web.dto.room.RoomListResponse;
import com.example.hotelbookingservice.web.dto.room.RoomRequest;
import com.example.hotelbookingservice.web.dto.room.RoomResponse;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@DecoratedWith(RoomMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {HotelMapper.class})
public interface RoomMapper {

  Room requestToModel( RoomRequest request );

  RoomResponse modelToResponse( Room room );

  @Mapping(source = "updateId", target = "id")
  Room requestToModel( Long updateId, RoomRequest request  );

  default RoomListResponse modelAllToResponseAll( List<Room> rooms ) {
    RoomListResponse response = new RoomListResponse();

    response.setRoomAll( rooms.stream().map( this::modelToResponse ).toList() );

    return response;
  }
}
