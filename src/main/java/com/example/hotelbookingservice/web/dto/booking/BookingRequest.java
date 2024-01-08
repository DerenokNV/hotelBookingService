package com.example.hotelbookingservice.web.dto.booking;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Сущность на запрос, для Внесения/Изменения бронирования комнат>")
public class BookingRequest {

  @NotNull(message = "Дата начала бронирования не может быть пустой")
  @Schema(description = "Дата начала бронирования")
  @DateTimeFormat(pattern = "dd.MM.yyyy", iso = DateTimeFormat.ISO.DATE_TIME)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
  private LocalDate dtBegin;

  @NotNull(message = "Дата окончания бронирования не может быть пустой")
  @Schema(description = "Дата окончания бронирования")
  @DateTimeFormat(pattern = "dd.MM.yyyy", iso = DateTimeFormat.ISO.DATE_TIME)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
  private LocalDate dtEnd;

  @NotNull(message = "Обязательно укажите ID комнаты, которую бронируете")
  @Schema(description = "ID комнаты для бронирования")
  private Long roomId;

}
