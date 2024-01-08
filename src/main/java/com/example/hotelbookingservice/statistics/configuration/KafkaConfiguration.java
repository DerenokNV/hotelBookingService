package com.example.hotelbookingservice.statistics.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfiguration {

  @Value("${spring.kafka.bootstrap-servers}")
  private String bootstrapServers;

  @Value("${app.kafka.kafkaBookingGroupId}")
  private String kafkaBookingGroupId;

  @Bean
  public ProducerFactory<String, Object> stringKafkaProducerFactory( ObjectMapper objectMapper ) {
    Map<String, Object> config = new HashMap<>();

    config.put( ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers );
    config.put( ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class );
    config.put( ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class );

    return new DefaultKafkaProducerFactory<>( config, new StringSerializer(), new org.springframework.kafka.support.serializer.JsonSerializer<>( objectMapper ) );
  }

  @Bean
  public KafkaTemplate<String, Object> kafkaTemplate( ProducerFactory<String, Object> kafkaProducerFactory ) {
    return new KafkaTemplate<>( kafkaProducerFactory );
  }

  @Bean
  public ConsumerFactory<String, Object> kafkaConsumerFactory( ObjectMapper objectMapper ) {
    Map<String, Object> config = new HashMap<>();

    config.put( ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers );
    config.put( ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class );
    config.put( ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class );
    config.put( ConsumerConfig.GROUP_ID_CONFIG, kafkaBookingGroupId );
    config.put( JsonDeserializer.TRUSTED_PACKAGES, "*" );

    return new DefaultKafkaConsumerFactory<>( config, new StringDeserializer(), new JsonDeserializer<>( objectMapper ) );
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaConcurrentKafkaListenerContainerFactory(
          ConsumerFactory<String, Object> kafkaMessageConsumerFactory
  ) {
    ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory( kafkaMessageConsumerFactory );

    return factory;
  }
}
