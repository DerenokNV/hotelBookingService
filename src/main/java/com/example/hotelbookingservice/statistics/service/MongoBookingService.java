package com.example.hotelbookingservice.statistics.service;

import com.example.hotelbookingservice.statistics.entity.MongoBookingEntity;
import com.example.hotelbookingservice.statistics.entity.MongoUserEntity;
import com.example.hotelbookingservice.statistics.repository.MongoBookingRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class MongoBookingService {

  private final MongoBookingRepository bookingRepository;

  public List<MongoBookingEntity> findAll() {
    return bookingRepository.findAll();
  }

  public void save( MongoBookingEntity mongoBooking ) {
    bookingRepository.save( mongoBooking );
  }

}
