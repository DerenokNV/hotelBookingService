package com.example.hotelbookingservice.web.dto.room;

import com.example.hotelbookingservice.web.dto.PagesRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Сущность на запрос, для фильтрации информации по комнатам")
public class RoomFilterRequest extends PagesRequest {

  @Schema(description = "ID комнаты")
  private Long id;

  @Schema(description = "Имя комнаты")
  private String name;

  @Schema(description = "Максимальное количество людей для данной комнаты")
  private Long maxPeople;

  @Schema(description = "Минимальная цена")
  private Long minCost;

  @Schema(description = "Максимальная цена")
  private Long maxCost;

  @Schema(description = "ID Отель к которому принадлежит комната")
  private Long hotelId;

  @Schema(description = "Дата заезда")
  private LocalDate dtBegin;

  @Schema(description = "Дата выезда")
  private LocalDate dtEnd;
}
