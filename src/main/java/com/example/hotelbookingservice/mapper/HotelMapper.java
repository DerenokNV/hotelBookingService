package com.example.hotelbookingservice.mapper;

import com.example.hotelbookingservice.entity.Hotel;
import com.example.hotelbookingservice.web.dto.hotel.HotelListResponse;
import com.example.hotelbookingservice.web.dto.hotel.HotelRequest;
import com.example.hotelbookingservice.web.dto.hotel.HotelResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HotelMapper {

  Hotel requestToModel( HotelRequest request );

  @Mapping(source = "updateId", target = "id")
  Hotel requestToModel( Long updateId, HotelRequest request  );

  HotelResponse modelToResponse( Hotel hotel );

  default HotelListResponse modelAllToResponseAll( List<Hotel> hotels ) {
    HotelListResponse response = new HotelListResponse();

    response.setHotelAll( hotels.stream().map( this::modelToResponse ).toList() );

    return response;
  }

}
