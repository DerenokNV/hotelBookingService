package com.example.hotelbookingservice.statistics.listener;

import com.example.hotelbookingservice.statistics.dto.BookingEvent;
import com.example.hotelbookingservice.statistics.dto.UserEvent;
import com.example.hotelbookingservice.statistics.entity.MongoBookingEntity;
import com.example.hotelbookingservice.statistics.entity.MongoUserEntity;
import com.example.hotelbookingservice.statistics.service.MongoBookingService;
import com.example.hotelbookingservice.statistics.service.MongoUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaServiceListener {

  private final MongoUserService mongoUserService;

  private final MongoBookingService mongoBookingService;

  @KafkaListener( topics = "${app.kafka.kafkaUserTopic}",
          groupId = "${app.kafka.kafkaBookingGroupId}",
          containerFactory = "kafkaConcurrentKafkaListenerContainerFactory"
  )
  public void listen( @Payload UserEvent message,
                      @Header(value = KafkaHeaders.RECEIVED_KEY, required = false) UUID key,
                      @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                      @Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition,
                      @Header(KafkaHeaders.RECEIVED_TIMESTAMP) Long timestamp
  ) {

    mongoUserService.save( new MongoUserEntity( message.getId(), message.getUserId() ) );
  }

  @KafkaListener( topics = "${app.kafka.kafkaBookingTopic}",
                  groupId = "${app.kafka.kafkaBookingGroupId}",
                  containerFactory = "kafkaConcurrentKafkaListenerContainerFactory"
  )
  public void listenBooking( @Payload BookingEvent message,
                             @Header(value = KafkaHeaders.RECEIVED_KEY, required = false) UUID key,
                             @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                             @Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition,
                             @Header(KafkaHeaders.RECEIVED_TIMESTAMP) Long timestamp
  ) {

    mongoBookingService.save( new MongoBookingEntity( message.getId(), message.getUserId(), message.getRoomId(), message.getDtBegin(), message.getDtEnd() ) );
  }

}
