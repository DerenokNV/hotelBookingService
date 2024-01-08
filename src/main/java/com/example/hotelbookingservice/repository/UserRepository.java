package com.example.hotelbookingservice.repository;

import com.example.hotelbookingservice.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByFirstNameAndEmail( String firstName, String email );

  @EntityGraph(attributePaths = {"roles"})
  Optional<User> findByFirstName( String firstName );
}
