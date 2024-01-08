package com.example.hotelbookingservice.web.dto.booking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingListResponse {

  private List<BookingResponse> bookings;
}
