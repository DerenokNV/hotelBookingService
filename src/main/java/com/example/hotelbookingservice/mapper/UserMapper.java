package com.example.hotelbookingservice.mapper;

import com.example.hotelbookingservice.entity.User;
import com.example.hotelbookingservice.web.dto.user.UserListResponse;
import com.example.hotelbookingservice.web.dto.user.UserRequest;
import com.example.hotelbookingservice.web.dto.user.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

  User requestToModel( UserRequest request );

  @Mapping(source = "updateId", target = "id")
  User requestToModel( Long updateId, UserRequest request  );

  UserResponse modelToResponse( User user );

  default UserListResponse modelAllToResponseAll( List<User> users ) {
    UserListResponse response = new UserListResponse();

    response.setUsers( users.stream().map( this::modelToResponse ).toList() );

    return response;
  }
}
