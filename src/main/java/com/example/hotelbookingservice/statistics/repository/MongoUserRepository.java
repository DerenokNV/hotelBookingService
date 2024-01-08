package com.example.hotelbookingservice.statistics.repository;

import com.example.hotelbookingservice.statistics.entity.MongoUserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoUserRepository extends MongoRepository<MongoUserEntity, String> {

}
