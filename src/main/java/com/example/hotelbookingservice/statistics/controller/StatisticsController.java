package com.example.hotelbookingservice.statistics.controller;

import com.example.hotelbookingservice.statistics.dto.BookingEvent;
import com.example.hotelbookingservice.statistics.dto.UserEvent;
import com.example.hotelbookingservice.statistics.service.MongoBookingService;
import com.example.hotelbookingservice.statistics.service.MongoUserService;
import com.example.hotelbookingservice.statistics.service.Toolkit;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/stat")
@RequiredArgsConstructor
public class StatisticsController {

  private final MongoUserService mongoUserService;

  private final MongoBookingService mongoBookingService;

  @GetMapping("/allUsers")
  public List<UserEvent> getAllUsers() {
    return mongoUserService.findAll().stream().map( UserEvent::from ).toList();
  }


  @GetMapping("/allBookings")
  public List<BookingEvent> getAllBookings() {
    return mongoBookingService.findAll().stream().map( BookingEvent::from ).toList();
  }

  @GetMapping("/info")
  public ResponseEntity<?> downloadFile() {
    String nameFile = "/stat" + System.currentTimeMillis() + ".csv";
    if ( ! Toolkit.createFileStatInfo( nameFile, getAllUsers(), getAllBookings(), getClass().getClassLoader() ) ) {
      return ResponseEntity.notFound().build();
    }

    Resource fileResource = new ClassPathResource( Toolkit.NAME_PATH + nameFile );

    HttpHeaders headers = new HttpHeaders();
    headers.add( HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + nameFile );
    headers.setContentType( MediaType.TEXT_PLAIN );

    return ResponseEntity.ok()
            .headers( headers )
            .body( fileResource );
  }

}
