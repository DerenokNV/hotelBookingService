package com.example.hotelbookingservice.web.controller;

import com.example.hotelbookingservice.mapper.UserMapper;
import com.example.hotelbookingservice.service.UserService;
import com.example.hotelbookingservice.web.dto.PagesRequest;
import com.example.hotelbookingservice.web.dto.user.UserListResponse;
import com.example.hotelbookingservice.web.dto.user.UserRequest;
import com.example.hotelbookingservice.web.dto.user.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Tag(name = "Пользователи", description = "Client API version V1, для управления сущностью Пользователь")
public class UserController {

  private final UserService userService;

  private final UserMapper userMapper;

  @GetMapping
  @Operation(
          summary = "Список пользователей",
          description = "Получить полный список пользователей",
          tags = { "user", "find" }
  )
  public UserListResponse findAll( @Valid PagesRequest pagesRequest ) {
    return userMapper.modelAllToResponseAll( userService.findAll( pagesRequest) );
  }

  @GetMapping("/{id}")
  @Operation(
          summary = "Найти пользователя по его ID",
          description = "Найти пользователя по его ID",
          tags = { "user", "find" }
  )
  public UserResponse findById( @PathVariable Long id ) {
    return userMapper.modelToResponse( userService.findById( id ) );
  }

  @PutMapping("/{id}")
  @Operation(
          summary = "Изменить параметры пользователя ",
          description = "Внести изменения в параметры пользователя",
          tags = { "user", "update" }
  )
  public UserResponse update( @PathVariable Long id,
                              @RequestBody @Valid UserRequest request ) {
    return userMapper.modelToResponse( userService.update( userMapper.requestToModel( id, request ) ) );
  }

  @DeleteMapping("/{id}")
  @Operation(
          summary = "Удалить пользователя",
          description = "Удалить пользователя",
          tags = { "user", "delete" }
  )
  public void deleteById( @PathVariable Long id ) {
    userService.deleteById( id );
  }

}
