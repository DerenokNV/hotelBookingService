package com.example.hotelbookingservice.mapper;

import com.example.hotelbookingservice.entity.Booking;
import com.example.hotelbookingservice.web.dto.booking.BookingDtResponse;
import com.example.hotelbookingservice.web.dto.booking.BookingListResponse;
import com.example.hotelbookingservice.web.dto.booking.BookingRequest;
import com.example.hotelbookingservice.web.dto.booking.BookingResponse;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@DecoratedWith(BookingMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {RoomMapper.class})
public interface BookingMapper {

  Booking requestToModel( BookingRequest request );

  BookingResponse modelToResponse( Booking booking );

  BookingDtResponse modelToResponseDt( Booking booking );

  @Mapping(source = "updateId", target = "id")
  Booking requestToModel( Long updateId, BookingRequest request );

  default BookingListResponse modelAllToResponseAll( List<Booking> booking ) {
    BookingListResponse response = new BookingListResponse();

    response.setBookings( booking.stream().map( this::modelToResponse ).toList() );

    return response;
  }
}
