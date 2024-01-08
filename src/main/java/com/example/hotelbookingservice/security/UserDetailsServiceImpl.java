package com.example.hotelbookingservice.security;

import com.example.hotelbookingservice.entity.User;
import com.example.hotelbookingservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException {
    Optional<User> user = userRepository.findByFirstName( username );
    if ( user.isPresent() ) {
      return new AppUserPrincipal( user.get() );
    } else {
      throw new UsernameNotFoundException( "User not found. Username is: " + username );
    }
  }
}
