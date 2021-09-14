package com.bugtracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.bugtracker.entity.Application;
import com.bugtracker.entity.Release;
import com.bugtracker.entity.Ticket;
import com.bugtracker.repository.ApplicationRepository;
import com.bugtracker.repository.TicketRepository;
import com.bugtracker.service.ApplicationService;
import com.bugtracker.service.ReleaseService;
import com.bugtracker.service.TicketService;

@SpringBootApplication
public class BugtrackerApplication {

	private static final Logger log = LoggerFactory.getLogger(BugtrackerApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(BugtrackerApplication.class, args);
	}

	
//	@Bean
//	public CommandLineRunner demoApplication(ApplicationService service) {
//		
//		return (args)->{
//			
//			for (Application application : service.findByName("Expenses")) {
//				log.info("The application is: " + application.toString());
//			}
//			
//			for (Application application : service.findAll()) {
//				log.info("The application is: " + application.toString());
//			}
//			
//		};
//		
//	}
//
//	@Bean
//	public CommandLineRunner demoRelease(ReleaseService service) {
//		
//		return (args)->{
//			
//			for (Release release : service.findAll()) {
//				log.info("The release is: " + release.toString());
//			}
//			
//		};
//		
//	}
//	
//	@Bean
//	public CommandLineRunner demoTicket(TicketService service) {
//		
//		return (args)->{
//			
//			for (Ticket ticket : service.findAll()) {
//				log.info("The Ticket is: " + ticket.toString());
//			}
//			
//		};
//		
//	}
	
}
