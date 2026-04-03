package com.firstspring.reservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class ReservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservationApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void onApplicationReady() {
		System.out.println("\n====================================================");
		System.out.println("🚀 Reservation Application is running!");
		System.out.println("👉 Swagger UI: http://localhost:8080/swagger-ui/index.html");
		System.out.println("====================================================\n");
	}

}
