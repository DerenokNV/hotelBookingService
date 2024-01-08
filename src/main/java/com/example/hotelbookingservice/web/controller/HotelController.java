package com.example.hotelbookingservice.web.controller;

import com.example.hotelbookingservice.mapper.HotelMapper;
import com.example.hotelbookingservice.service.HotelService;
import com.example.hotelbookingservice.web.dto.PagesRequest;
import com.example.hotelbookingservice.web.dto.hotel.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hotel")
@AllArgsConstructor
@Tag(name = "Отели", description = "Client API version V1, для управления сущностью Отели")
public class HotelController {

  private final HotelService hotelService;

  private final HotelMapper hotelMapper;

  @GetMapping("/filter")
  @Operation(
          summary = "Список отелей по фильтру",
          description = "Получить полный список отелей по фильтру",
          tags = { "hotel", "find", "filter" }
  )
  public HotelListResponse filterBy( @Valid HotelFilterRequest filter ) {
    return  hotelMapper.modelAllToResponseAll( hotelService.filterBy( filter ) );
  }

  @GetMapping
  @Operation(
          summary = "Список отелей",
          description = "Получить полный список отелей",
          tags = { "hotel", "find" }
  )
  public HotelListResponse findAll( @Valid PagesRequest pagesRequest ) {
    return hotelMapper.modelAllToResponseAll( hotelService.findAll( pagesRequest) );
  }

  @GetMapping("/{id}")
  @Operation(
          summary = "Найти отель по его ID",
          description = "Найти отель по его ID",
          tags = { "hotel", "find" }
  )
  public HotelResponse findById( @PathVariable @Parameter(description = "ID отеля") Long id ) {
    return hotelMapper.modelToResponse( hotelService.findById( id ) );
  }

  @PostMapping
  @Operation(
          summary = "Создать отель",
          description = "Создать отель",
          tags = { "hotel", "create" }
  )

  @PreAuthorize( "hasAnyAuthority('ROLE_ADMIN')" )
  public HotelResponse create( @RequestBody @Valid HotelRequest request ) {
    return hotelMapper.modelToResponse( hotelService.save( hotelMapper.requestToModel( request ) ) );
  }

  @PutMapping("/{id}")
  @Operation(
          summary = "Изменить параметры отеля ",
          description = "Внести изменения в параметры отеля",
          tags = { "hotel", "update" }
  )
  @PreAuthorize( "hasAnyAuthority('ROLE_ADMIN')" )
  public HotelResponse update( @PathVariable @Parameter(description = "ID отеля") Long id,
                               @RequestBody @Valid HotelRequest request ) {
    return hotelMapper.modelToResponse( hotelService.update( hotelMapper.requestToModel( id, request ) ) );
  }

  @DeleteMapping("/{id}")
  @Operation(
          summary = "Удалить отель",
          description = "Удалить отель",
          tags = { "hotel", "delete" }
  )
  @PreAuthorize( "hasAnyAuthority('ROLE_ADMIN')" )
  public void deleteById( @PathVariable @Parameter(description = "ID отеля для удаления") Long id ) {
    hotelService.deleteById( id );
  }

  @PostMapping("/rating")
  @Operation(
          summary = "Оценить отель по его ID",
          description = "Найти отель по его ID",
          tags = { "hotel", "rating" }
  )
  public void addRating( @RequestBody @Valid HotelRatingRequest request) {
    hotelService.updateHotelRating( request.getId(), request.getNewMark().intValue() );
  }

}
