package com.example.hotelbookingservice.service.impl;

import com.example.hotelbookingservice.entity.Hotel;
import com.example.hotelbookingservice.repository.HotelRepository;
import com.example.hotelbookingservice.repository.HotelSpecification;
import com.example.hotelbookingservice.service.HotelService;
import com.example.hotelbookingservice.service.ToolkitService;
import com.example.hotelbookingservice.web.dto.PagesRequest;
import com.example.hotelbookingservice.web.dto.hotel.HotelFilterRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;

import java.text.MessageFormat;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class PgHotelService implements HotelService {

  private final HotelRepository hotelRepository;

  @Override
  public List<Hotel> filterBy( HotelFilterRequest filter ) {
    return hotelRepository.findAll(
             HotelSpecification.withFilter( filter ),
             PageRequest.of( filter.getPageNumber(), filter.getPageSize() )
           ).getContent();
  }

  @Override
  public List<Hotel> findAll( PagesRequest pagesRequest ) {
    return hotelRepository.findAll( PageRequest.of( pagesRequest.getPageNumber(), pagesRequest.getPageSize() ) ).getContent();
  }

  @Override
  public Hotel findById( Long id ) {
    return hotelRepository.findById( id ).orElseThrow(
            () -> new EntityNotFoundException( MessageFormat.format( "Отель с ID {0} не найдена!", id ) )
    );
  }

  @Override
  public Hotel save( Hotel hotel ) {
    return hotelRepository.save( hotel );
  }

  @Override
  public Hotel update( Hotel hotel ) {
    return save( hotel );
  }

  @Override
  public void deleteById( Long id ) {
    hotelRepository.deleteById( id );
  }

  @Override
  public void updateHotelRating( Long id, Integer newMark ) {
    Hotel hotel = findById( id );
    Double rating = ToolkitService.calcHotelRating( hotel, newMark );
    hotel.setRating( rating );
    hotel.setCountRating( hotel.getCountRating() == null ? 1 : hotel.getCountRating() + 1 );
    save( hotel );
  }
}
