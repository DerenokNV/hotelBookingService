package com.example.hotelbookingservice.web.dto.hotel;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Ответ о Создании/Изменении отеля")
public class HotelResponse {

  @Schema(description = "ID отеля")
  private Long id;

  @Schema(description = "Имя отеля")
  private String name;

  @Schema(description = "Заголовок для объявления отеля")
  private String headline;

  @Schema(description = "Город в котором располагается отель")
  private String city;

  @Schema(description = "Адрес отеля")
  private String address;

  @Schema(description = "Расстояние до центра города")
  private Long distance;

  @Schema(description = "Рейтинг отеля")
  private Double rating;

  @Schema(description = "Количество оценок, на основании которых, был рассчитан рейтинг отеля")
  private Long countRating;

}
