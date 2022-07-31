package by.it_academy.events_service.config;

import by.it_academy.events_service.controller.utils.json.LocalDateTimeDeserializer;
import by.it_academy.events_service.controller.utils.json.LocalDateTimeSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Configuration
public class ControllerConfig {

    @Bean
    public Jackson2ObjectMapperBuilder mapperBuilder() {
        ObjectMapper mapper = new ObjectMapper();
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        builder.serializerByType(LocalDateTime.class, new LocalDateTimeSerializer());
        builder.deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer());
        builder.configure(mapper);

        return builder;
    }
}
