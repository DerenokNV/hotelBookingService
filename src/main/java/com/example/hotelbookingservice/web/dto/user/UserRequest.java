package com.example.hotelbookingservice.web.dto.user;

import com.example.hotelbookingservice.entity.RoleType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
@Schema(description = "Сущность на запрос, для Внесения/Изменения отелей")
public class UserRequest {

  @NotBlank(message = "Имя пользователя должно быть заполнено!")
  @Schema(description = "Имя пользователя")
  private String firstName;

  @NotBlank(message = "Электронная почта должна быть заполнена!")
  @Schema(description = "Электронная почта")
  private String email;

  @NotBlank(message = "Пароль просто обязателен!")
  @Schema(description = "Пароль")
  private String password;

  @Schema(description = "Роли которые могут быть у пользователя")
  private Set<RoleType> roles;

}
