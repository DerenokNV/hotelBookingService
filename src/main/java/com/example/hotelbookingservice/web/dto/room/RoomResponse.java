package com.example.hotelbookingservice.web.dto.room;

import com.example.hotelbookingservice.web.dto.booking.BookingDtResponse;
import com.example.hotelbookingservice.web.dto.hotel.HotelResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
@Schema(description = "Ответ о Создании/Изменении комнат")
public class RoomResponse {

  @Schema(description = "ID комнаты")
  private Long id;

  @Schema(description = "Имя комнаты")
  private String name;

  @Schema(description = "Описание комнаты")
  private String description;

  @Schema(description = "Номер комнаты")
  private String number;

  @Schema(description = "Стоимость комнаты")
  private Long price;

  @Schema(description = "Максимальное количество людей для данной комнаты")
  private Long maxPeople;

  @Schema(description = "Отель к которому принадлежит комната")
  private HotelResponse hotel;

  @Schema(description = "Даты в которые комната занята")
  private Set<BookingDtResponse> roomBookings;

}
