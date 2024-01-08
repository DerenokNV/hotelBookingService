package com.example.hotelbookingservice.web.controller;

import com.example.hotelbookingservice.mapper.RoomMapper;
import com.example.hotelbookingservice.service.RoomService;
import com.example.hotelbookingservice.web.dto.hotel.HotelFilterRequest;
import com.example.hotelbookingservice.web.dto.hotel.HotelListResponse;
import com.example.hotelbookingservice.web.dto.room.RoomFilterRequest;
import com.example.hotelbookingservice.web.dto.room.RoomListResponse;
import com.example.hotelbookingservice.web.dto.room.RoomRequest;
import com.example.hotelbookingservice.web.dto.room.RoomResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Validated
@RestController
@RequestMapping("/api/room")
@AllArgsConstructor
@Tag(name = "Комната", description = "Client API version V1, для управления сущностью Комната")
public class RoomController {

  private final RoomService service;

  private final RoomMapper mapper;

  @GetMapping("/filter")
  @Operation(
          summary = "Список отелей по фильтру",
          description = "Получить полный список отелей по фильтру",
          tags = { "hotel", "find", "filter" }
  )
  public RoomListResponse filterBy( @Valid RoomFilterRequest filter ) {
    return  mapper.modelAllToResponseAll( service.filterBy( filter ) );
  }

  @PostMapping
  @Operation(
          summary = "Создать комнату",
          description = "Создать комнату",
          tags = { "room", "create" }
  )
  @PreAuthorize( "hasAnyAuthority('ROLE_ADMIN')" )
  public RoomResponse create( @RequestBody RoomRequest request ){
    return mapper.modelToResponse( service.save( mapper.requestToModel( request ) ) );
  }

  // Поставила костыль, связка @Validated + @PathVariable + @NotNull/@Positive ни в какую не хочет срабатывать в ExceptionHandlerController
  @GetMapping("/")
  public RoomResponse findById() {
    throw new ResponseStatusException( HttpStatus.BAD_REQUEST, "Передайте Id комнаты" );
  }

  @GetMapping("/{id}")
  @Operation(
          summary = "Найти комнату по ее ID",
          description = "Найти комнату по ее ID",
          tags = { "room", "find" }
  )
  public RoomResponse findById( @Parameter(description = "ID комнаты")
                                @PathVariable("id") @Positive Long id ) {
    return mapper.modelToResponse( service.findById( id ) );
  }

  @PutMapping("/{id}")
  @Operation(
          summary = "Изменить параметры комнаты ",
          description = "Внести изменения в параметры комнаты",
          tags = { "room", "update" }
  )
  @PreAuthorize( "hasAnyAuthority('ROLE_ADMIN')" )
  public RoomResponse update( @PathVariable @NotNull(message = "Передайте Id комнаты") @Parameter(description = "ID комнаты") Long id,
                              @RequestBody @Valid RoomRequest request ) {
    return mapper.modelToResponse( service.update( mapper.requestToModel( id, request ) ) );
  }

  @DeleteMapping("/{id}")
  @Operation(
          summary = "Удалить комнату",
          description = "Удалить комнату",
          tags = { "room", "delete" }
  )
  @PreAuthorize( "hasAnyAuthority('ROLE_ADMIN')" )
  public void deleteById( @PathVariable @Parameter(description = "ID комнаты для удаления") @Positive(message = "Student ID must be greater than zero") Long id ) {
    service.deleteById( id );
  }

}
