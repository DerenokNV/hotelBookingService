package com.example.hotelbookingservice.web.dto.hotel;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Сущность на запрос, для Внесения/Изменения отелей")
public class HotelRequest {

  @NotBlank(message = "Название отеля не может быть пустым")
  @Schema(description = "Название отеля")
  private String name;

  @NotBlank(message = "Заголовок объявления не может быть пустым")
  @Schema(description = "Заголовок объявления")
  private String headline;

  @NotBlank(message = "Город, где располагается отель, не может быть пустым")
  @Schema(description = "Город где располагается отель")
  private String city;

  private String address;

  @NotNull(message = "Расстояние до центра города, не может быть пустым")
  @Schema(description = "Расстояние до центра города")
  private Long distance;
}
