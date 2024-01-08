package com.example.hotelbookingservice.statistics.entity;

import com.example.hotelbookingservice.statistics.dto.BookingEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "booking")
public class MongoBookingEntity {

  @Id
  private String id;

  private Long userId;

  private Long roomId;

  private LocalDate dtBegin;

  private LocalDate dtEnd;

  public static MongoBookingEntity from( BookingEvent booking ) {
    return new MongoBookingEntity( booking.getId(), booking.getUserId(), booking.getRoomId(), booking.getDtBegin(), booking.getDtEnd() );
  }
}
