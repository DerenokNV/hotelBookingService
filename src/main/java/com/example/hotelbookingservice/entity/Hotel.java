package com.example.hotelbookingservice.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Hotel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String headline;

  private String city;

  private String address;

  private Long distance;

  private Double rating;

  private Long countRating;

  @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
  @ToString.Exclude
  @Builder.Default
  @Transient
  private List<Room> rooms = new ArrayList<>();

}
