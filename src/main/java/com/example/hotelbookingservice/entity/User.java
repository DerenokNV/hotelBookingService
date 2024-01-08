package com.example.hotelbookingservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String firstName;

  private String password;

  private String email;

  @ElementCollection(targetClass = RoleType.class, fetch = FetchType.EAGER)
  @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
  @Column(name = "roles", nullable = false)
  @Enumerated(EnumType.STRING)
  @Builder.Default
  private Set<RoleType> roles = new HashSet<>();

  @OneToMany(mappedBy = "userBooking", cascade = CascadeType.ALL)
  @ToString.Exclude
  @Builder.Default
  private List<Booking> userBookings = new ArrayList<>();


}
