package com.example.hotelbookingservice.web.dto.user;

import com.example.hotelbookingservice.entity.RoleType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Ответ о Создании/Изменении пользователя")
public class UserResponse {

  @Schema(description = "Id пользователя")
  private Long id;

  @Schema(description = "Уникальное имя пользователя")
  private String firstName;

  @Schema(description = "Электронная почта пользователя")
  private String email;

  @Schema(description = "Роли пользователя")
  private Set<RoleType> roles;
}
