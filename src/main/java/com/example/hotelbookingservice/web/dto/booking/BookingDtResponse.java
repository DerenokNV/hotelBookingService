package com.example.hotelbookingservice.web.dto.booking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDtResponse {

  private LocalDate dtBegin;

  private LocalDate dtEnd;
}
