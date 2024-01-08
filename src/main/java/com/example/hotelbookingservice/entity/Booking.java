package com.example.hotelbookingservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "booking")
public class Booking {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private LocalDate dtBegin;

  private LocalDate dtEnd;

  @ManyToOne
  @JoinColumn(name = "room_id")
  @ToString.Exclude
  @JsonIgnoreProperties("roomBookings")
  private Room roomBooking;

  @ManyToOne
  @JoinColumn(name = "user_id")
  @ToString.Exclude
  @JsonIgnoreProperties("userBookings")
  private User userBooking;
}
