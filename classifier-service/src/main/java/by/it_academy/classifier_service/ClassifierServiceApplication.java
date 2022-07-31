package by.it_academy.classifier_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories("by.it_academy.classifier_service.dao.api")
public class ClassifierServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClassifierServiceApplication.class, args);
    }
}
