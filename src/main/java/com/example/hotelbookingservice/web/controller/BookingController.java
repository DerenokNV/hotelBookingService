package com.example.hotelbookingservice.web.controller;

import com.example.hotelbookingservice.mapper.BookingMapper;
import com.example.hotelbookingservice.service.BookingService;
import com.example.hotelbookingservice.statistics.dto.BookingEvent;
import com.example.hotelbookingservice.web.dto.PagesRequest;
import com.example.hotelbookingservice.web.dto.booking.BookingListResponse;
import com.example.hotelbookingservice.web.dto.booking.BookingRequest;
import com.example.hotelbookingservice.web.dto.booking.BookingResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/booking")
@RequiredArgsConstructor
@Tag(name = "Бронирование", description = "Client API version V1, для управления сущностью Бронирование")
public class BookingController {

  private final BookingService bookingService;

  private final BookingMapper bookingMapper;

  @Value( "${app.kafka.kafkaBookingTopic}" )
  private String bookingTopicName;

  private final KafkaTemplate<String, Object> kafkaTemplateBooking;

  @PostMapping
  @Operation(
          summary = "Забронировать квартиру",
          description = "Забронировать квартиру",
          tags = { "booking", "create" }
  )
  public BookingResponse create( @RequestBody @Valid BookingRequest request ) {
    BookingResponse result = bookingMapper.modelToResponse( bookingService.save( bookingMapper.requestToModel( request ) ) );

    // пишем в топик Booking
    kafkaTemplateBooking.send( bookingTopicName, new BookingEvent( UUID.randomUUID().toString(),
                                                                   result.getId(),
                                                                   result.getRoomBooking().getId(),
                                                                   result.getDtBegin(),
                                                                   result.getDtEnd() ) );

    return result;
  }

  @GetMapping
  @Operation(
          summary = "Список всех броней",
          description = "Получить полный список бронирования",
          tags = { "user", "find" }
  )
  @PreAuthorize( "hasAnyAuthority('ROLE_ADMIN')" )
  public BookingListResponse findAll( @Valid PagesRequest pagesRequest ) {
    return bookingMapper.modelAllToResponseAll( bookingService.findAll( pagesRequest ) );
  }

}
