package com.example.hotelbookingservice.statistics.service;

import com.example.hotelbookingservice.statistics.entity.MongoUserEntity;
import com.example.hotelbookingservice.statistics.repository.MongoUserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class MongoUserService {

  private final MongoUserRepository kafkaUserRepository;

  public List<MongoUserEntity> findAll() {
    return kafkaUserRepository.findAll();
  }

  public void save( MongoUserEntity mongoUser ) {
    kafkaUserRepository.save( mongoUser );
  }

}
