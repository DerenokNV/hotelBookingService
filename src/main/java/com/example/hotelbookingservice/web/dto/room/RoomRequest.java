package com.example.hotelbookingservice.web.dto.room;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Сущность на запрос, для Внесения/Изменения комнат")
public class RoomRequest {

  @NotBlank(message = "Название комнаты не может быть пустым")
  @Schema(description = "Название комнаты")
  private String name;

  @NotBlank(message = "Описание комнаты не может быть пустым")
  @Schema(description = "Описание комнаты")
  private String description;

  @NotBlank(message = "Номер комнаты не может быть пустым, но может быть с литером")
  @Schema(description = "Номер комнаты")
  private String number;

  @NotNull(message = "Клиент в праве знать стоимость снимаемого жилья")
  @Schema(description = "Стоимость комнаты")
  private Long price;

  @NotNull(message = "Укажите максимальное количество людей, которое может разместиться в комнате")
  @Schema(description = "Максимальное количество людей")
  private Long maxPeople;

  @NotNull(message = "При создании/редактировании комнаты, привяжите ее к отелю пожалуйста")
  @Schema(description = "Отель к которому относиться комната")
  private Long hotelId;

}
