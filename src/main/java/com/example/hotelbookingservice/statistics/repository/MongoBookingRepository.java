package com.example.hotelbookingservice.statistics.repository;

import com.example.hotelbookingservice.statistics.entity.MongoBookingEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoBookingRepository extends MongoRepository<MongoBookingEntity, String> {

}
