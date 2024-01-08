package com.example.hotelbookingservice.web.dto.hotel;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Сущность на запрос, для Внесения/Изменения рейтинга отелей")
public class HotelRatingRequest {

  @NotNull(message = "Id отеля не может быть пустым")
  @Schema(description = "Id отеля")
  private Long id;

  @NotNull(message = "Оценка отеля не может быть пустой")
  @Max(value = 5, message = "Если отель очень понравился, то поставьте 5 баллов, больше не надо")
  @Schema(description = "Оценка отеля")
  private Long newMark;
}
