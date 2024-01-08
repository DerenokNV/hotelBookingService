package com.example.hotelbookingservice.statistics.dto;

import com.example.hotelbookingservice.statistics.entity.MongoBookingEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingEvent {

  private String id;

  private Long userId;

  private Long roomId;

  private LocalDate dtBegin;

  private LocalDate dtEnd;

  public static BookingEvent from( MongoBookingEntity entity ) {
    var model = new BookingEvent();
    model.setId( entity.getId() );
    model.setUserId( entity.getUserId() );
    model.setRoomId( entity.getRoomId() );
    model.setDtBegin( entity.getDtBegin() );
    model.setDtEnd( entity.getDtEnd() );
    return model;
  }

}
