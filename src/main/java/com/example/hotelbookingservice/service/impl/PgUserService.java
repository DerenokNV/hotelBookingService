package com.example.hotelbookingservice.service.impl;

import com.example.hotelbookingservice.entity.User;
import com.example.hotelbookingservice.repository.UserRepository;
import com.example.hotelbookingservice.service.UserService;
import com.example.hotelbookingservice.web.dto.PagesRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PgUserService implements UserService {

  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;

  @Override
  public List<User> findAll( PagesRequest pagesRequest ) {
    return userRepository.findAll( PageRequest.of( pagesRequest.getPageNumber(), pagesRequest.getPageSize() ) ).getContent();
  }

  @Override
  public User findById( Long id ) {
    return userRepository.findById( id )
            .orElseThrow(
                    () -> new EntityNotFoundException( MessageFormat.format("Пользователь с ID {0} не найден!", id ) )
            );
  }

  @Override
  public User save( User user ) {
    Optional<User> userCheckDouble = userRepository.findByFirstNameAndEmail( user.getFirstName(), user.getEmail() );
    if ( userCheckDouble.isPresent() ) {
      throw new EntityNotFoundException( MessageFormat.format("Пользователь с таким Именем {0} и email {1} существует!", user.getFirstName(), user.getEmail() ) );
    }

    user.setPassword( passwordEncoder.encode( user.getPassword() ) );

    return userRepository.saveAndFlush( user );
  }

  @Override
  public User update( User user ) {
    return save( user );
  }

  @Override
  public void deleteById( Long id ) {
    userRepository.deleteById( id );
  }

  @Override
  public User findByFirstName( String firstName ) {
    return userRepository.findByFirstName( firstName )
            .orElseThrow( () -> new RuntimeException("Username not found!") );
  }
}
