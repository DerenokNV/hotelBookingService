package com.example.hotelbookingservice.statistics.entity;

import com.example.hotelbookingservice.statistics.dto.UserEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "usr")
public class MongoUserEntity {

  @Id
  private String id;

  private Long userId;

  public static MongoUserEntity from( UserEvent user ) {
    return new MongoUserEntity( user.getId(), user.getUserId() );
  }
}
