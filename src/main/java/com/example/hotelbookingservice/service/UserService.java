package com.example.hotelbookingservice.service;


import com.example.hotelbookingservice.entity.User;
import com.example.hotelbookingservice.web.dto.PagesRequest;

import java.util.List;

public interface UserService {

  List<User> findAll( PagesRequest pagesRequest );

  User findById( Long id );

  User save( User user );

  User update( User user );

  void deleteById( Long id );

  User findByFirstName( String firstName );
}
