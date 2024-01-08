package com.example.hotelbookingservice.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Room {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String description;

  private String number;

  private Long price;

  private Long maxPeople;

  // Ссылка на отель
  @ManyToOne
  @JoinColumn(name = "hotel_id")
  @ToString.Exclude
  private Hotel hotel;

  // Список дат, недоступна
  @OneToMany(mappedBy = "roomBooking", cascade = CascadeType.ALL)
  @ToString.Exclude
  @Builder.Default
  @JsonIgnoreProperties("roomBooking")
  private List<Booking> roomBookings = new ArrayList<>();
}
