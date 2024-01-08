package com.example.hotelbookingservice.repository;

import com.example.hotelbookingservice.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>, JpaSpecificationExecutor<Room> {

  Optional<Room> findByHotelIdAndNumber( Long hotelId, String number );

  @Override
  List<Room> findAll();


}
