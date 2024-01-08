package com.example.hotelbookingservice.repository;

import com.example.hotelbookingservice.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {


  @Query( value = " select * " +
                  " from app_schema.booking b " +
                  " where b.room_id = :roomId " +
                  "   and  ( b.dt_begin between :dtBegin and :dtEnd " +
                  "          or " +
                  "          b.dt_end between :dtBegin and :dtEnd ) ",
          nativeQuery = true )
  Optional<List<Booking>> customGetBookingDtRequest( LocalDate dtBegin, LocalDate dtEnd, Long roomId );

}
