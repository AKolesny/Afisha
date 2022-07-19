package by.it_academy.events_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("by.it_academy.events_service.dao.api")
public class EventsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventsServiceApplication.class, args);
    }
}
