package com.example.hotelbookingservice.statistics.dto;


import com.example.hotelbookingservice.statistics.entity.MongoUserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEvent {

  private String id;

  private Long userId;

  public static UserEvent from( MongoUserEntity entity ) {
    var model = new UserEvent();
    model.setId( entity.getId() );
    model.setUserId( entity.getUserId() );
    return model;
  }
}
