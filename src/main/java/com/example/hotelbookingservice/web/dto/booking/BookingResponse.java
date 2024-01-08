package com.example.hotelbookingservice.web.dto.booking;

import com.example.hotelbookingservice.web.dto.room.RoomResponse;
import com.example.hotelbookingservice.web.dto.user.UserResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Ответ о Создании/Изменении бронирования комнат")
public class BookingResponse {

  @Schema(description = "Id сущности бронирования")
  private Long id;

  @Schema(description = "Пользователь который забронировал")
  private UserResponse userBooking;

  @Schema(description = "Комната, которую забронировали")
  private RoomResponse roomBooking;

  @Schema(description = "Дата начала бронирования")
  private LocalDate dtBegin;

  @Schema(description = "Дата окончания бронирования")
  private LocalDate dtEnd;

}
