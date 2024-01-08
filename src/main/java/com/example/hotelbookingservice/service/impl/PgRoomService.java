package com.example.hotelbookingservice.service.impl;

import com.example.hotelbookingservice.entity.Booking;
import com.example.hotelbookingservice.entity.Hotel;
import com.example.hotelbookingservice.entity.Room;
import com.example.hotelbookingservice.repository.BookingRepository;
import com.example.hotelbookingservice.repository.RoomRepository;
import com.example.hotelbookingservice.repository.RoomSpecification;
import com.example.hotelbookingservice.service.HotelService;
import com.example.hotelbookingservice.service.RoomService;
import com.example.hotelbookingservice.web.dto.room.RoomFilterRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class PgRoomService implements RoomService {

  private final RoomRepository repository;

  private final HotelService hotelService;

  private final BookingRepository bookingRepository;

  @Override
  public List<Room> filterBy( RoomFilterRequest filter ) {
    List<Room> rooms = repository.findAll( RoomSpecification.withFilter( filter ),
                                           PageRequest.of( filter.getPageNumber(), filter.getPageSize() )
                                         ).getContent();

    List<Room> removeObj = new ArrayList( rooms );
    for ( Room room : rooms ) {
      if ( room.getRoomBookings() == null || room.getRoomBookings().isEmpty() ) {
        continue;
      }
      for ( Booking boking : room.getRoomBookings() ) {
        if ( boking.getDtBegin().isBefore( filter.getDtEnd() ) && boking.getDtBegin().isAfter( filter.getDtBegin() ) &&
             boking.getDtEnd().isBefore( filter.getDtEnd() ) && boking.getDtEnd().isAfter( filter.getDtBegin() ) ) {
          removeObj.remove( room );
        }
      }

      return removeObj;
    }

    return repository.findAll(
             RoomSpecification.withFilter( filter ),
             PageRequest.of( filter.getPageNumber(), filter.getPageSize() )
           ).getContent();
  }

  @Override
  public Room findById( Long id ) {
    Room room = repository.findById( id ).orElseThrow(
            () -> new EntityNotFoundException( MessageFormat.format( "Комната с ID {0} не найдена!", id ) )
    );

    room.setRoomBookings( room.getRoomBookings().stream().filter( x -> x.getDtBegin().isAfter( LocalDate.now() ) ).collect( Collectors.toList() ) );

    return room;
  }

  @Override
  public Room save( Room room ) {
    Optional<Room> findDoubleRoom = repository.findByHotelIdAndNumber( room.getHotel().getId(), room.getNumber() );
    if ( findDoubleRoom.isPresent() ) {
      throw new EntityNotFoundException( MessageFormat.format("Комната с Номером {0} в отеле {1} существует!", room.getNumber(), room.getHotel().getId() ) );
    }

    Hotel hotel = hotelService.findById( room.getHotel().getId() );
    room.setHotel( hotel );

    return repository.save( room );
  }

  @Override
  public Room update( Room room ) {
    return save( room );
  }

  @Override
  public void deleteById( Long id ) {
    repository.deleteById( id );
  }
}
