package com.example.hotelbookingservice.web.controller;

import com.example.hotelbookingservice.mapper.UserMapper;
import com.example.hotelbookingservice.service.UserService;
import com.example.hotelbookingservice.statistics.dto.UserEvent;
import com.example.hotelbookingservice.web.dto.user.UserRequest;
import com.example.hotelbookingservice.web.dto.user.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/public")
@RequiredArgsConstructor
@Tag(name = "Доступные контроллеры, без авторизации", description = "Client API version V1, контроллеры, без авторизации")
public class PublicController {

  private final UserService userService;

  private final UserMapper userMapper;

  @Value( "${app.kafka.kafkaUserTopic}" )
  private String userTopicName;

  private final KafkaTemplate<String, Object> kafkaTemplateUser;


  @PostMapping("/user")
  @Operation(
          summary = "Создать пользователя",
          description = "Создать пользователя",
          tags = { "user", "create" }
  )
  public UserResponse create( @RequestBody @Valid UserRequest request ) {
    UserResponse result = userMapper.modelToResponse( userService.save( userMapper.requestToModel( request ) ) );

    // пишем в топик User
    kafkaTemplateUser.send( userTopicName, new UserEvent( UUID.randomUUID().toString(), result.getId() ) );

    return result;
  }
}
