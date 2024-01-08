package com.example.hotelbookingservice.service.impl;

import com.example.hotelbookingservice.entity.Booking;
import com.example.hotelbookingservice.repository.BookingRepository;
import com.example.hotelbookingservice.security.AppUserPrincipal;
import com.example.hotelbookingservice.security.facadeuser.IAuthenticationFacade;
import com.example.hotelbookingservice.service.BookingService;
import com.example.hotelbookingservice.web.dto.PagesRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PgBookingService implements BookingService {

  private final BookingRepository bookingRepository;

  private final IAuthenticationFacade authenticationFacade;

  @Override
  public List<Booking> findAll( PagesRequest pagesRequest ) {
    return bookingRepository.findAll( PageRequest.of( pagesRequest.getPageNumber(), pagesRequest.getPageSize() ) ).getContent();
  }

  @Override
  public Booking findById( Long id ) {
    return bookingRepository.findById( id ).orElseThrow(
            () -> new EntityNotFoundException( MessageFormat.format( "Отель с ID {0} не найдена!", id ) )
    );
  }

  @Override
  public Booking save( Booking booking ) {
    if ( booking.getDtEnd().isBefore( booking.getDtBegin() ) ) {
      throw new EntityNotFoundException( MessageFormat.format("Укажите корректно даты, сейчас дата начала {0}, больше даты окончания {1} периода бронирования ",  booking.getDtBegin(), booking.getDtEnd() ) );
    }

    Optional<List<Booking>> bookingOptional = bookingRepository.customGetBookingDtRequest( booking.getDtBegin(), booking.getDtEnd(), booking.getRoomBooking().getId() );
    if ( bookingOptional.isPresent() && ! bookingOptional.get().isEmpty() ) {
      throw new EntityNotFoundException( MessageFormat.format("На даты: Дата начала {0} и Дата окончания {1}, квартира c ID {2} занята ",  booking.getDtBegin(), booking.getDtEnd(), booking.getRoomBooking().getId() ) );
    }

    AppUserPrincipal principal = (AppUserPrincipal)authenticationFacade.getAuthentication().getPrincipal();
    booking.setUserBooking( principal.getUser() );

    return bookingRepository.save( booking );
  }

  @Override
  public Booking update( Booking booking ) {
    return save( booking );
  }

  @Override
  public void deleteById( Long id ) {
    bookingRepository.deleteById( id );
  }
}
