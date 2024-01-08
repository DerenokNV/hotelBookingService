package com.example.hotelbookingservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HotelBookingServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(HotelBookingServiceApplication.class, args);

		// почистим каталог от файлов созданных
		Thread thread = new DaemonThread();
		thread.setDaemon(true);
		thread.start();
	}

}
